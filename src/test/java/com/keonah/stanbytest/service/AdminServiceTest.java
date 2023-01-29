package com.keonah.stanbytest.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class AdminServiceTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("비밀번호_암호화")
    public void 비밀번호_암호화() {
        String pw1 = "hello";
        String pw2 = "hi";
        String pw3 = "hello";

        System.out.println(passwordEncoder.encode(pw1));
        System.out.println(passwordEncoder.encode(pw2));
        System.out.println(passwordEncoder.encode(pw3));

        if(passwordEncoder.matches(pw2, passwordEncoder.encode(pw1))) {
            System.out.println("pw1 = pw2");
        } else if(passwordEncoder.matches(pw3, passwordEncoder.encode(pw2))) {
            System.out.println("pw2 = pw3");
        } else if(passwordEncoder.matches(pw3, passwordEncoder.encode(pw1))) {
            System.out.println("pw1 = pw3");
        }
    }

}
