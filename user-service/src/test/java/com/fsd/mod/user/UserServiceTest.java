package com.fsd.mod.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@ContextConfiguration(classes = { TestConfig.class })
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Test
    public void test() {
        System.out.println("UserServiceTest");
    }

}
