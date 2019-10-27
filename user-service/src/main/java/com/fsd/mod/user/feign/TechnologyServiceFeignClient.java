package com.fsd.mod.user.feign;

import com.fsd.mod.user.dto.PageDto;
import com.fsd.mod.user.dto.TechnologyDto;
import com.fsd.mod.user.util.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@FeignClient(name = "technology-service")
public interface TechnologyServiceFeignClient {

    @GetMapping("/api/v1/technologies")
    ResponseResult<PageDto<TechnologyDto>> getSkillsBySkillIds(@RequestParam(value = "ids") Set<Long> skillIds,
    Pageable pageable);

}
