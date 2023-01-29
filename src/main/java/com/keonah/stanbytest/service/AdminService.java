package com.keonah.stanbytest.service;

import com.keonah.stanbytest.dto.SignupDto;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    void signUp(SignupDto signupDto);

    boolean checkIdDuplication(String Id);
    boolean checkNameDuplication(String name);

}
