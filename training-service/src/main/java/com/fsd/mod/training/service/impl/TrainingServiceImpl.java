package com.fsd.mod.training.service.impl;

import com.fsd.mod.training.dto.PageDto;
import com.fsd.mod.training.dto.TechnologyDto;
import com.fsd.mod.training.dto.TrainingDto;
import com.fsd.mod.training.dto.UserDto;
import com.fsd.mod.training.entity.Training;
import com.fsd.mod.training.feign.TechnologyServiceFeignClient;
import com.fsd.mod.training.feign.UserServiceFeignClient;
import com.fsd.mod.training.repository.TrainingRepository;
import com.fsd.mod.training.service.IPageService;
import com.fsd.mod.training.service.ITrainingService;
import com.fsd.mod.training.util.ResponseResult;
import com.fsd.mod.training.vo.TrainingVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TrainingServiceImpl implements ITrainingService {

    @Autowired
    private TechnologyServiceFeignClient technologyServiceFeignClient;

    @Autowired
    private UserServiceFeignClient userServiceFeignClient;

    @Autowired
    private IPageService pageService;

    @Autowired
    private TrainingRepository dao;

    @Override
    public void proposeTraining(TrainingVo trainingVo) {
        Training training = convertToEntity(trainingVo);
        dao.save(training);
    }

    @Override
    public void finalizeTraining(Long id) {
        Optional<Training> optionalTraining = dao.findById(id);
        optionalTraining.ifPresent(training -> changeStatus(training, "Finalized"));
    }

    @Override
    public void completeTraining(Long id) {
        Optional<Training> optionalTraining = dao.findById(id);
        optionalTraining.ifPresent(training -> changeStatus(training, "Completed"));
    }

    @Override
    public void confirmTraining(Long id) {
        Optional<Training> optionalTraining = dao.findById(id);
        optionalTraining.ifPresent(training -> changeStatus(training, "Confirmed"));
    }

    @Override
    public void rejectTraining(Long id) {
        Optional<Training> optionalTraining = dao.findById(id);
        optionalTraining.ifPresent(training -> changeStatus(training, "Rejected"));
    }

    @Override
    public Optional<TrainingDto> getTrainingById(Long id) {
        TrainingDto trainingDto = null;
        Optional<Training> optionalTraining = dao.findById(id);
        if (optionalTraining.isPresent()) {
            trainingDto = convertToDto(optionalTraining.get());
        }
        return Optional.ofNullable(trainingDto);
    }

    @Override
    public PageDto<TrainingDto> getInprogressTrainings(Pageable pageable) {
        Page<Training> trainingPage = dao.findAllByStatusIn(pageable, "Proposed", "Approved", "Finalized");
        return pageService.convertToPageDto(trainingPage, this::convertToDto);
    }

    @Override
    public PageDto<TrainingDto> getCompletedTrainings(Pageable pageable) {
        Page<Training> trainingPage = dao.findAllByStatusIn(pageable, "Completed");
        return pageService.convertToPageDto(trainingPage, this::convertToDto);
    }

    private Training convertToEntity(TrainingVo trainingVo) {
        Training training = new Training();
        BeanUtils.copyProperties(trainingVo, training);
        training.setStatus("Proposed");
        return training;
    }

    private TrainingDto convertToDto(Training training) {
        TrainingDto trainingDto = new TrainingDto();
        BeanUtils.copyProperties(training, trainingDto);

        ResponseResult<UserDto> userResult = userServiceFeignClient.getUserById(training.getUserId());
        trainingDto.setUserName(userResult.getCode() == 0 ? userResult.getData().getUsername() : "");

        ResponseResult<UserDto> mentorResult = userServiceFeignClient.getUserById(training.getMentorId());
        trainingDto.setMentorName(userResult.getCode() == 0 ? mentorResult.getData().getUsername() : "");

        ResponseResult<TechnologyDto> technologyResult = technologyServiceFeignClient.getTechnologyById(training.getSkillId());
        trainingDto.setSkillName(technologyResult.getCode() == 0 ? technologyResult.getData().getName() : "");

        return trainingDto;
    }

    private void changeStatus(Training training, String status) {
        training.setStatus(status);
        training.setUpdatedTime(new Date());
        dao.save(training);
    }
}
