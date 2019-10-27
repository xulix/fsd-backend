package com.fsd.mod.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsd.mod.security.dto.UserDto;
import com.fsd.mod.security.service.impl.UserDetailsServiceImpl;
import com.fsd.mod.security.util.JwtUtil;
import com.fsd.mod.security.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RestAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

        UserDto user = userDetailsService.loadUserByUsername(((UserDto) authentication.getPrincipal()).getUsername());

        Map<String, Object> result = new HashMap<>();
        result.put("Authorization", "Bearer " + jwtUtil.generateToken(user));

        response.getWriter().write(
                new ObjectMapper().
                        writeValueAsString(
                                ResponseResult.success("Authentication succeed", result, null)
                        )
        );
    }
}
