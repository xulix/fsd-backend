package com.fsd.mod.user.service;

import com.fsd.mod.user.dto.PageDto;
import com.fsd.mod.user.dto.RoleDto;
import com.fsd.mod.user.entity.Role;
import com.fsd.mod.user.vo.RoleVo;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IRoleService {
    void addRole(RoleVo roleVo);
    void deleteRoleById(Long id);
    RoleDto updateRole(Long id, RoleVo roleVo);
    Optional<RoleDto> findRoleById(Long id);
    PageDto<RoleDto> findAll(String keyword, Pageable pageable);

    RoleDto convertToDto(Role role);
    Optional<Role> findById(Long id);
}
