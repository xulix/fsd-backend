package com.fsd.mod.security.test;

import com.fsd.mod.security.SecurityServiceTest;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderTest extends SecurityServiceTest {

    @Test
    public void encode() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("admin"));
    }

}
