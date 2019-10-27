package com.fsd.mod.user.controller;

import com.fsd.mod.user.dto.AuthUserDto;
import com.fsd.mod.user.dto.PageDto;
import com.fsd.mod.user.dto.UserDto;
import com.fsd.mod.user.service.IAuthUserService;
import com.fsd.mod.user.service.IUserService;
import com.fsd.mod.user.util.ResponseResult;
import com.fsd.mod.user.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {

    @Autowired
    private IAuthUserService authUserService;

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseResult<PageDto<UserDto>> getAll(
            @RequestParam(value = "q", defaultValue = "", required = false) String keyword,
            @PageableDefault(sort = {"updatedTime"}) Pageable pageable) {

        PageDto<UserDto> userPage = userService.findAll(keyword, pageable);

        return ResponseResult.success("get users successfully", userPage, null);
    }

    @PostMapping()
    public ResponseResult create(@Valid @RequestBody UserVo userVo) {

        userService.addUser(userVo);

        return ResponseResult.success("create user successfully", null, null);
    }

    @GetMapping(value = "/{id}")
    public ResponseResult<Object> findById(@PathVariable(value = "id") Long id) {
        Optional<UserDto> optional = userService.findUserById(id);

        if (optional.isPresent()) {
            return ResponseResult.success("get user successfully", optional.get(), null);
        } else {
            return ResponseResult.fail("failed to get user", null);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseResult deleteById(@PathVariable(value = "id") Long id) {

        userService.deleteUserById(id);

        return ResponseResult.success("delete user successfully", null, null);
    }

    @PutMapping(value = "/{id}")
    public ResponseResult<UserDto> updateById(@PathVariable(value = "id") Long id,
                                              @Valid @RequestBody UserVo userVo) {
        UserDto userDto = userService.updateUser(id, userVo);

        return ResponseResult.success("update user successfully", userDto, null);
    }

    @GetMapping(value = "/auth")
    public ResponseResult<Object> authUser(@RequestParam(required = true) String username) {

        Optional<AuthUserDto> optional = authUserService.loadUserByUsername(username);

        if (optional.isPresent()) {
            return ResponseResult.success("get auth user successfully", optional.get(), null);
        } else {
            return ResponseResult.fail("failed to get the auth of user " + username, null);
        }

    }

}
