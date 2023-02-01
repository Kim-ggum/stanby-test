package com.keonah.stanbytest.service;

import com.keonah.stanbytest.dto.SignupDTO;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    void signUp(SignupDTO signupDto);

    boolean checkIdDuplication(String Id);
    boolean checkNameDuplication(String name);

}
