package com.fsd.mod.user;

import com.fsd.mod.user.dto.PageDto;
import com.fsd.mod.user.dto.UserDto;
import com.fsd.mod.user.entity.User;
import com.fsd.mod.user.repository.UserRepository;
import com.fsd.mod.user.service.IUserService;
import com.fsd.mod.user.vo.UserVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

public class UserTest extends UserServiceTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserRepository dao;

//    @Test
//    public void testCreateUser() {
//        assertNotNull(userService);
//        UserVo userVo = new UserVo();
//        userVo.setContactNumber("13074148815");
//        userVo.setFirstName("Xu");
//        userVo.setLastName("Li");
//        userVo.setUsername("xulix");
//        userVo.setRoleIds(new HashSet<>());
//        userVo.setPassword("just4test");
//
//        userService.addUser(userVo);
//    }

    @Test
    public void testFindAll() {
        assertNotNull(userService);
        PageDto<UserDto> userPage = userService.findAll("", PageRequest.of(0, 10));
        System.out.println(userPage.getList().get(0).getUsername());
    }
}
