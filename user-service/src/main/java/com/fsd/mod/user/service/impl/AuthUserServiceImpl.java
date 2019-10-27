package com.fsd.mod.user.service.impl;

import com.fsd.mod.user.dto.AuthUserDto;
import com.fsd.mod.user.dto.RoleDto;
import com.fsd.mod.user.entity.User;
import com.fsd.mod.user.repository.UserRepository;
import com.fsd.mod.user.service.IAuthUserService;
import com.fsd.mod.user.service.IRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthUserServiceImpl implements IAuthUserService {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private UserRepository dao;

    @Override
    public Optional<AuthUserDto> loadUserByUsername(String username) {
        AuthUserDto authUserDto = null;

        Optional<User> optionalUser = dao.findByUsername(username);

        if (optionalUser.isPresent()) {
            authUserDto = convertToDto(optionalUser.get());
        }
        return Optional.ofNullable(authUserDto);
    }

    private AuthUserDto convertToDto(User user) {
        AuthUserDto authUserDto = new AuthUserDto();
        BeanUtils.copyProperties(user, authUserDto);

        Set<RoleDto> roles = new HashSet<>();
        user.getRoles().forEach(role -> roles.add(roleService.convertToDto(role)));

        authUserDto.setRoles(roles);

        return authUserDto;
    }
}
