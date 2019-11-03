package com.fsd.mod.technology.controller;

import com.fsd.mod.technology.util.ResponseResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/technologies/config")
@RefreshScope
public class ConfigController {

    @Value("${config.name}")
    private String configName;

    @GetMapping
    public ResponseResult<String> getConfigName() {
        return ResponseResult.success("action successfully", configName, null);
    }

}
