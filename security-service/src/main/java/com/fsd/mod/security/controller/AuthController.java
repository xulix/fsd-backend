package com.fsd.mod.security.controller;

import com.fsd.mod.security.util.ResponseResult;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    @GetMapping(value = "/me")
    public String me() {
        return "auth me";
    }
}
