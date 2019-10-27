package com.fsd.mod.user.dto;

import lombok.Data;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String contactNumber;
    private String email;
    private String linkedinUrl;
    private Set<RoleDto> roles;
    private List<TechnologyDto> skills;
}
