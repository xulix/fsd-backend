package com.fsd.mod.user.dto;

import lombok.Data;

import java.util.Set;

@Data
public class AuthUserDto {
    private Long id;
    private String username;
    private String password;
    private Set<RoleDto> roles;
}
