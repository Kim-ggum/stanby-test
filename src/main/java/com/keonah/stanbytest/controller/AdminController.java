package com.keonah.stanbytest.controller;

import com.keonah.stanbytest.dto.SignupDto;
import com.keonah.stanbytest.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admins")
public class AdminController {
    private final AdminService adminService;

    @PostMapping("")
    public ResponseEntity signup(@Valid @RequestBody SignupDto signupDto) {

        if(!adminService.checkIdDuplication(signupDto.getId()) && !adminService.checkNameDuplication(signupDto.getName())) {
            adminService.signUp(signupDto);
            return new ResponseEntity(signupDto,HttpStatus.CREATED);
        } else {
            return new ResponseEntity("이미 존재하는 ID입니다.",HttpStatus.CONFLICT);
        }

    }

}
