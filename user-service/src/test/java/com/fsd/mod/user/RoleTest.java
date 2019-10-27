package com.fsd.mod.user;

import com.fsd.mod.user.dto.PageDto;
import com.fsd.mod.user.dto.RoleDto;
import com.fsd.mod.user.service.IRoleService;
import com.fsd.mod.user.vo.RoleVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

public class RoleTest extends UserServiceTest {

    @Autowired
    private IRoleService roleService;

//    @Test
//    public void createRole() {
//        assertNotNull(roleService);
//        RoleVo roleVo = new RoleVo();
//        roleVo.setName("user");
//
//        Set<Long> resourceIds = new HashSet<>();
//        resourceIds.add((long) 2);
//        roleVo.setResourceIds(resourceIds);
//        roleService.addRole(roleVo);
//    }

    @Test
    public void updateRole() {

    }

    @Test
    public void findAll() {
        assertNotNull(roleService);
        PageDto<RoleDto> page = roleService.findAll("", PageRequest.of(0, 10));
        System.out.println("Total : " + page.getTotal());
        page.getList().forEach(roleDto -> {
            System.out.println("-----role start-----");
            System.out.println("Role : " + roleDto.getName());
            roleDto.getResources().forEach(resourceDto -> {
                System.out.println("-----resource start-----");
                System.out.println("Resource Name : " + resourceDto.getName());
                System.out.println("Resource Url  : " + resourceDto.getUrl());
                System.out.println("-----resource end-------");
            });
            System.out.println("-----role end-------");
        });
    }



}
