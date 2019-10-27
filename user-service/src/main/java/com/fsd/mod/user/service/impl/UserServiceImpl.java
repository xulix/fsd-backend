package com.fsd.mod.user.service.impl;

import com.fsd.mod.user.dto.PageDto;
import com.fsd.mod.user.dto.RoleDto;
import com.fsd.mod.user.dto.TechnologyDto;
import com.fsd.mod.user.dto.UserDto;
import com.fsd.mod.user.entity.Role;
import com.fsd.mod.user.entity.User;
import com.fsd.mod.user.feign.TechnologyServiceFeignClient;
import com.fsd.mod.user.repository.MentorSkillRepository;
import com.fsd.mod.user.repository.UserRepository;
import com.fsd.mod.user.service.IPageService;
import com.fsd.mod.user.service.IRoleService;
import com.fsd.mod.user.service.IUserService;
import com.fsd.mod.user.util.ResponseResult;
import com.fsd.mod.user.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private TechnologyServiceFeignClient technologyServiceFeignClient;

    @Autowired
    private IPageService pageService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private MentorSkillRepository msDao;

    @Autowired
    private UserRepository dao;

    @Override
    public void addUser(UserVo userVo) {
        User user = convertToEntity(null, userVo);
        dao.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        Optional<User> optionalUser = dao.findById(id);
        optionalUser.ifPresent(user -> dao.delete(user));
    }

    @Override
    public UserDto updateUser(Long id, UserVo userVo) {
        User user = convertToEntity(id, userVo);
        return convertToDto(dao.save(user));
    }

    @Override
    public Optional<UserDto> findUserById(Long id) {
        UserDto userDto = null;

        Optional<User> optionalUser = dao.findById(id);
        if (optionalUser.isPresent()) {
            userDto = convertToDto(optionalUser.get());
        }

        return Optional.ofNullable(userDto);
    }

    @Override
    public PageDto<UserDto> findAll(String keyword, Pageable pageable) {
        Page<User> userPage = dao.findAllUsersByKeywordWithPagination(keyword, pageable);
        return pageService.convertToPageDto(userPage, this::convertToDto);
    }

    private User convertToEntity(Long id, UserVo userVo) {
        User user = new User();
        BeanUtils.copyProperties(userVo, user);

        PasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(userVo.getPassword()));

        Set<Role> roles = new HashSet<>();
        userVo.getRoleIds().forEach(roleId -> {
            Optional<Role> optionalRole = roleService.findById(roleId);
            optionalRole.ifPresent(roles::add);
        });
        user.setRoles(roles);

        if (id != null) {
            Optional<User> optionalUser = dao.findById(id);
            optionalUser.ifPresent(u -> {
                user.setId(u.getId());
                user.setCreatedBy(u.getCreatedBy());
                user.setCreatedTime(u.getCreatedTime());
                user.setUpdatedBy(u.getUpdatedBy());
                user.setUpdatedTime(new Date());
            });
        }

        return user;
    }

    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);

        Set<RoleDto> roles = new HashSet<>();
        user.getRoles().forEach(role -> roles.add(roleService.convertToDto(role)));
        userDto.setRoles(roles);

        Set<Long> skillIds = msDao.findAllByMentorId(user.getId());
        ResponseResult<PageDto<TechnologyDto>> result = technologyServiceFeignClient.getSkillsBySkillIds(
                skillIds, PageRequest.of(0, 999));
        userDto.setSkills(result.getCode() == 0 ? result.getData().getList() : new ArrayList<>());

        return userDto;
    }
}
