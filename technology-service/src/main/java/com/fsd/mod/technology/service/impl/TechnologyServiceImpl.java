package com.fsd.mod.technology.service.impl;

import com.fsd.mod.technology.dto.PageDto;
import com.fsd.mod.technology.dto.TechnologyDto;
import com.fsd.mod.technology.entity.Technology;
import com.fsd.mod.technology.repository.TechnologyRepository;
import com.fsd.mod.technology.service.IPageService;
import com.fsd.mod.technology.service.ITechnologyService;
import com.fsd.mod.technology.vo.TechnologyVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TechnologyServiceImpl implements ITechnologyService {

    @Autowired
    private IPageService pageService;

    @Autowired
    private TechnologyRepository dao;

    @Override
    public void addTechnology(TechnologyVo technologyVo) {
        Technology technology = convertToEntity(null, technologyVo);
        dao.save(technology);
    }

    @Override
    public void deleteTechnologyById(Long id) {
        Optional<Technology> optionalTechnology = dao.findById(id);
        optionalTechnology.ifPresent(technology -> dao.delete(technology));
    }

    @Override
    public TechnologyDto updateTechnologyById(Long id, TechnologyVo technologyVo) {
        Technology technology = convertToEntity(id, technologyVo);
        return convertToDto(dao.save(technology));
    }

    @Override
    public Optional<TechnologyDto> findTechnologyById(Long id) {
        TechnologyDto technologyDto = null;
        Optional<Technology> optionalTechnology = dao.findById(id);
        if (optionalTechnology.isPresent()) {
            technologyDto = convertToDto(optionalTechnology.get());
        }

        return Optional.ofNullable(technologyDto);
    }

    @Override
    public PageDto<TechnologyDto> findAll(String keyword, Set<Long> ids, Pageable pageable) {
        Page<Technology> technologyPage;
        if (ids == null) {
            technologyPage = dao.findAllByNameContainsIgnoreCase(keyword, pageable);
        } else {
            technologyPage = dao.findAllByNameContainsIgnoreCaseAndIdIn(keyword, ids, pageable);
        }

        return pageService.convertToPageDto(technologyPage, this::convertToDto);
    }

    private Technology convertToEntity(Long id, TechnologyVo technologyVo) {
        Technology technology = new Technology();
        BeanUtils.copyProperties(technologyVo, technology);

        if (id != null) {
            Optional<Technology> optionalTechnology = dao.findById(id);
            optionalTechnology.ifPresent(t -> {
                technology.setId(t.getId());
                technology.setCreatedBy(t.getCreatedBy());
                technology.setCreatedTime(t.getCreatedTime());
                technology.setUpdatedBy(t.getUpdatedBy());
                technology.setUpdatedTime(new Date());
            });
        }

        return technology;
    }

    private TechnologyDto convertToDto(Technology technology) {
        TechnologyDto technologyDto = new TechnologyDto();
        BeanUtils.copyProperties(technology, technologyDto);

        return technologyDto;
    }
}
