package com.fsd.mod.user;

import com.fsd.mod.user.dto.PageDto;
import com.fsd.mod.user.dto.ResourceDto;
import com.fsd.mod.user.repository.ResourceRepository;
import com.fsd.mod.user.service.IResourceService;
import com.fsd.mod.user.vo.ResourceVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import static org.junit.Assert.assertNotNull;

public class ResourceTest extends UserServiceTest {

    @Autowired
    private IResourceService service;

//    @Test
//    public void createResource() {
//        assertNotNull(service);
//        ResourceVo resourceVo = new ResourceVo();
//        resourceVo.setName("createUser");
//        resourceVo.setUrl("/api/v1/users:POST");
//        service.addResource(resourceVo);
//    }

//    @Test
//    public void deleteResource() {
//        assertNotNull(service);
//        service.deleteResourceById((long) 1);
//    }

    @Test
    public void findResources() {
        assertNotNull(service);
        PageDto<ResourceDto> pageDto = service.findAll("create", PageRequest.of(0, 10));
        System.out.println(pageDto.getTotal());
    }

}
