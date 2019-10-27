package com.fsd.mod.training.controller;

import com.fsd.mod.training.dto.PageDto;
import com.fsd.mod.training.dto.TrainingDto;
import com.fsd.mod.training.service.ITrainingService;
import com.fsd.mod.training.util.ResponseResult;
import com.fsd.mod.training.vo.TrainingVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/trainings")
public class TrainingController {

    @Autowired
    private ITrainingService trainingService;

    @PostMapping
    public ResponseResult proposeTraining(@Valid @RequestBody TrainingVo trainingVo) {

        trainingService.proposeTraining(trainingVo);

        return ResponseResult.success("propose successfully", null, null);
    }

    @PostMapping("/{id}/finalize")
    public ResponseResult finalizeTraining(@PathVariable(value = "id") Long id) {

        trainingService.finalizeTraining(id);

        return ResponseResult.success("finalize successfully", null, null);
    }

    @PostMapping("/{id}/complete")
    public ResponseResult completeTraining(@PathVariable(value = "id") Long id) {

        trainingService.completeTraining(id);

        return ResponseResult.success("complete successfully", null, null);
    }

    @PostMapping("/{id}/confirm")
    public ResponseResult confirmTraining(@PathVariable(value = "id") Long id) {

        trainingService.confirmTraining(id);

        return ResponseResult.success("confirm successfully", null, null);
    }

    @PostMapping("/{id}/reject")
    public ResponseResult rejectTraining(@PathVariable(value = "id") Long id) {

        trainingService.rejectTraining(id);

        return ResponseResult.success("reject successfully", null, null);
    }

    @GetMapping("/{id}")
    public ResponseResult<Object> getTrainingById(@PathVariable(value = "id") Long id) {

        Optional<TrainingDto> optional = trainingService.getTrainingById(id);
        if (optional.isPresent()) {
            return ResponseResult.success("get successfully", optional.get(), null);
        } else {
            return ResponseResult.fail("get failed", null);
        }
    }

    @GetMapping("/inprogress")
    public ResponseResult<PageDto<TrainingDto>> getInprogressTrainings(
            @PageableDefault(sort = {"updatedTime"}) Pageable pageable) {

        PageDto<TrainingDto> page = trainingService.getInprogressTrainings(pageable);

        return ResponseResult.success("get inprogress trainings successfully", page, null);
    }

    @GetMapping("/completed")
    public ResponseResult<PageDto<TrainingDto>> getCompletedTrainings(
            @PageableDefault(sort = {"updatedTime"}) Pageable pageable) {

        PageDto<TrainingDto> page = trainingService.getCompletedTrainings(pageable);

        return ResponseResult.success("get completed trainings successfully", page, null);
    }

}
