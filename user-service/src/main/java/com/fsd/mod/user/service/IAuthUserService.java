package com.fsd.mod.user.service;

import com.fsd.mod.user.dto.AuthUserDto;

import java.util.Optional;

public interface IAuthUserService {

    Optional<AuthUserDto> loadUserByUsername(String username);

}
