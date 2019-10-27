package com.fsd.mod.user.vo;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserVo {
    @NotBlank(message = "username must not be blank")
    private String username;

    @NotBlank(message = "password must not be blank")
    @Size(min = 8, message = "password must be at least 8 characters")
    private String password;

    @NotBlank(message = "first name must not be blank")
    private String firstName;

    @NotBlank(message = "last name must not be blank")
    private String lastName;

    @NotBlank(message = "contact number must not be blank")
    private String contactNumber;

    @NotBlank(message = "email must not be blank")
    private String email;

    private String linkedinUrl;

    private Set<Long> roleIds = new HashSet<>();

    private Set<Long> skillIds = new HashSet<>();
}
