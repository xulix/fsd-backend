package com.fsd.mod.training.feign;

import com.fsd.mod.training.dto.TechnologyDto;
import com.fsd.mod.training.dto.UserDto;
import com.fsd.mod.training.util.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserServiceFeignClient {

    @GetMapping(value = "/api/v1/users/{id}")
    ResponseResult<UserDto> getUserById(@PathVariable(value = "id") Long id);
}
