package com.fsd.mod.security.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface RbacService {
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
