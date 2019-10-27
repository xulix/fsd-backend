package com.fsd.mod.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsd.mod.security.util.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response, AuthenticationException e)
            throws IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        response.getWriter().write(
                new ObjectMapper().
                        writeValueAsString(
                                ResponseResult.fail(e.getMessage(), null)
                        )
        );
    }
}
