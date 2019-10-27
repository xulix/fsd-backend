package com.fsd.mod.technology.service;

import com.fsd.mod.technology.dto.PageDto;
import com.fsd.mod.technology.dto.TechnologyDto;
import com.fsd.mod.technology.vo.TechnologyVo;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ITechnologyService {

    void addTechnology(TechnologyVo technologyVo);
    void deleteTechnologyById(Long id);
    TechnologyDto updateTechnologyById(Long id, TechnologyVo technologyVo);
    Optional<TechnologyDto> findTechnologyById(Long id);
    PageDto<TechnologyDto> findAll(String keyword, Set<Long> ids, Pageable pageable);
}
