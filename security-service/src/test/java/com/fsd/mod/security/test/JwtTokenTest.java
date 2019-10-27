package com.fsd.mod.security.test;

import com.fsd.mod.security.SecurityServiceTest;
import com.fsd.mod.security.dto.UserDto;
import com.fsd.mod.security.service.impl.UserDetailsServiceImpl;
import com.fsd.mod.security.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.HashSet;

import static org.junit.Assert.assertNotNull;

public class JwtTokenTest extends SecurityServiceTest {

    @Autowired
    private JwtUtil jwtUtil;

    private final static String TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlzcyI6IkZTRCAtIE1lbnRvciBPbiBEZW1hbmQiLCJpYXQiOjE1NzExOTc4OTMsImV4cCI6MTU3MTIxNTg5M30.RijICkBM-Ce2rtcgY_czi4MERQ71mm1amuVEXpPBBnoJ-CYL1Wackf-ksaGKSWV9M1EwxltcbVXW2iaAxbqPJg";

    @Test
    public void testGenerateToken() {
        assertNotNull(jwtUtil);
        UserDto userDto = new UserDto();
        userDto.setUsername("test1");
        userDto.setPassword("test1");
        userDto.setRoles(new HashSet<>());
        System.out.println(jwtUtil.generateToken(userDto));
    }

    @Test
    public void testGetClaimsFromToken() {
        assertNotNull(jwtUtil);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Claims claims = jwtUtil.getClaimsFromToken(TOKEN);

        if (claims != null) {
            System.out.println("Username : " + claims.get("id"));
            System.out.println("Role : " + claims.get("roles").toString());
            System.out.println("Subject : " + claims.getSubject());
            System.out.println("Issuer : " + claims.getIssuer());
            System.out.println("IssuedAt : " + simpleDateFormat.format(claims.getIssuedAt()));
            System.out.println("ExpiredAt : " + simpleDateFormat.format(claims.getExpiration()));
        } else {
            System.out.println("Invalid Claims");
        }
    }

    @Test
    public void testTokenExpired() {
        assertNotNull(jwtUtil);

        System.out.println(jwtUtil.isTokenExpired(TOKEN));
    }
}
