package com.fsd.mod.security.service.impl;

import com.fsd.mod.security.dto.UserDto;
import com.fsd.mod.security.feign.UserServiceFeignClient;
import com.fsd.mod.security.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserServiceFeignClient userServiceFeignClient;

    @Override
    public UserDto loadUserByUsername(String username) throws UsernameNotFoundException {
        ResponseResult<UserDto> result = userServiceFeignClient.getAuthByUsername(username);
        return result.getData();
    }
}
