package com.fsd.mod.security.dto;

import lombok.Data;

import java.util.Set;

@Data
public class RoleDto {
    private String name;
    private Set<ResourceDto> resources;
}
