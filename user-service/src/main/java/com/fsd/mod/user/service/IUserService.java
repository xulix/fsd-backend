package com.fsd.mod.user.service;

import com.fsd.mod.user.dto.PageDto;
import com.fsd.mod.user.dto.UserDto;
import com.fsd.mod.user.vo.UserVo;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IUserService {

    void addUser(UserVo userVo);
    void deleteUserById(Long id);
    UserDto updateUser(Long id, UserVo userVo);
    Optional<UserDto> findUserById(Long id);
    PageDto<UserDto> findAll(String keyword, Pageable pageable);
}
