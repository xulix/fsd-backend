package com.fsd.mod.security.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class ResourceDto implements GrantedAuthority {
    private String name;
    private String url;

    @Override
    public String getAuthority() {
        return url;
    }
}
