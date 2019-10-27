package com.fsd.mod.user.controller;

import com.fsd.mod.user.dto.PageDto;
import com.fsd.mod.user.dto.RoleDto;
import com.fsd.mod.user.service.IRoleService;
import com.fsd.mod.user.util.ResponseResult;
import com.fsd.mod.user.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/roles")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @GetMapping
    public ResponseResult<PageDto<RoleDto>> getAll(
            @RequestParam(value = "q", required = false, defaultValue = "") String keyword,
            @PageableDefault(sort = {"updatedTime"}) Pageable pageable) {

        PageDto<RoleDto> rolePage = roleService.findAll(keyword, pageable);

        return ResponseResult.success("get roles successfully", rolePage, null);
    }

    @PostMapping
    public ResponseResult create(@Valid @RequestBody RoleVo roleVo) {

        roleService.addRole(roleVo);

        return ResponseResult.success("create role successfully", null, null);
    }

    @GetMapping(value = "/{id}")
    public ResponseResult getById(@PathVariable(value = "id") Long id) {
        Optional<RoleDto> optional = roleService.findRoleById(id);

        if (optional.isPresent()) {
            return ResponseResult.success("get role successfully", optional.get(), null);
        } else {
            return ResponseResult.fail("failed to get the role " + id, null);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseResult deleteById(@PathVariable(value = "id") Long id) {

        roleService.deleteRoleById(id);

        return ResponseResult.success("delete the role successfully", null, null);
    }

    @PutMapping(value = "/{id}")
    public ResponseResult<RoleDto> updateById(@PathVariable(value = "id") Long id,
                                              @Valid @RequestBody RoleVo roleVo) {

        RoleDto roleDto = roleService.updateRole(id, roleVo);

        return ResponseResult.success("update role successfully", roleDto, null);
    }

}
