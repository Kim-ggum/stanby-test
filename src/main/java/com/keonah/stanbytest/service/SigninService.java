package com.keonah.stanbytest.service;

import com.keonah.stanbytest.entity.AdminEntity;
import com.keonah.stanbytest.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SigninService implements UserDetailsService {
    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        AdminEntity admin = adminRepository.findById(id);

        if(admin == null) {
            throw new UsernameNotFoundException("ID does not exist.");
        }

        return admin;
    }
}
