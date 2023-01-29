package com.keonah.stanbytest.service;

import com.keonah.stanbytest.dto.SignupDto;
import com.keonah.stanbytest.entity.AdminEntity;
import com.keonah.stanbytest.entity.Role;
import com.keonah.stanbytest.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    private AdminEntity dtoToEntity(SignupDto signupDto) {
        AdminEntity adminEntity = AdminEntity.builder()
                .id(signupDto.getId())
                .pw(passwordEncoder.encode(signupDto.getPw()))
                .name(signupDto.getName())
                .role(Role.ROLE_ADMIN)
                .build();

        return adminEntity;
    }

    @Override
    public void signUp(SignupDto signupDto) {
        AdminEntity adminEntity = dtoToEntity(signupDto);
        adminRepository.save(adminEntity);
    }

    @Override
    public boolean checkIdDuplication(String id) {
        return adminRepository.existsById(id);
    }

    @Override
    public boolean checkNameDuplication(String name) {
        return adminRepository.existsByName(name);
    }
}
