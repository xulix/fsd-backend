package com.fsd.mod.training.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
}
