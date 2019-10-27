package com.fsd.mod.user.controller;

import com.fsd.mod.user.dto.PageDto;
import com.fsd.mod.user.dto.ResourceDto;
import com.fsd.mod.user.service.IResourceService;
import com.fsd.mod.user.util.ResponseResult;
import com.fsd.mod.user.vo.ResourceVo;
import org.hibernate.validator.internal.metadata.aggregated.rule.OverridingMethodMustNotAlterParameterConstraints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.font.OpenType;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/resources")
public class ResourceController {

    @Autowired
    private IResourceService resourceService;

    @GetMapping
    public ResponseResult<PageDto<ResourceDto>> getAll(
            @RequestParam(value = "q", required = false, defaultValue = "") String keyword,
            @PageableDefault(sort = {"updatedTime"}) Pageable pageable
            ) {

        PageDto<ResourceDto> resourcePage = resourceService.findAll(keyword, pageable);

        return ResponseResult.success("get resources successfully", resourcePage, null);
    }

    @PostMapping
    public ResponseResult create(@Valid @RequestBody ResourceVo resourceVo) {

        resourceService.addResource(resourceVo);

        return ResponseResult.success("create resource successfully", null, null);
    }

    @GetMapping(value = "/{id}")
    public ResponseResult<Object> getById(@PathVariable(value = "id") Long id) {

        Optional<ResourceDto> optional = resourceService.findResourceById(id);
        if (optional.isPresent()) {
            return ResponseResult.success("get resource successfully", optional.get(), null);
        } else {
            return ResponseResult.fail("failed to get resource " + id, null);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseResult deleteById(@PathVariable(value = "id") Long id) {

        resourceService.deleteResourceById(id);

        return ResponseResult.success("delete resource " + id + " successfully", null, null);
    }

    @PutMapping(value = "/{id}")
    public ResponseResult<ResourceDto> updateById(
            @PathVariable(value = "id") Long id,
            @Valid @RequestBody ResourceVo resourceVo) {

        ResourceDto resourceDto = resourceService.updateResource(id, resourceVo);

        return ResponseResult.success("update resource successfully", resourceDto, null);
    }
}
