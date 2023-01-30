package com.keonah.stanbytest.filter;

import com.keonah.stanbytest.entity.AdminEntity;
import com.keonah.stanbytest.entity.Role;
import com.keonah.stanbytest.repository.AdminRepository;
import com.keonah.stanbytest.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final AdminRepository adminRepository;

    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getRequestURI().equals("/admins") || request.getRequestURI().equals("/admins/signin")) {
            filterChain.doFilter(request, response);
            return;
        }

        String refreshToken = jwtService.extractRefreshToken(request);

        if(refreshToken != null && jwtService.validateToken(refreshToken)){
            checkRefreshTokenAndRecreateAccessToken(response, refreshToken);
        }

        checkAccessTokenAndAuthentication(request, response, filterChain);

    }




    private void checkAccessTokenAndAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String accessToken = jwtService.extractAccessToken(request);

        if(accessToken != null && jwtService.validateToken(accessToken)) {
            String id = jwtService.extractId(accessToken);
            if(id != null) {
                AdminEntity adminEntity = adminRepository.findById(id);
                saveAuthentication(adminEntity);
            }
        }


        filterChain.doFilter(request,response);
    }



    private void saveAuthentication(AdminEntity adminEntity) {
        String role;
        if(adminEntity.getRole().toString().equals("ROLE_ADMIN")) {
            role = "ADMIN";
        } else {
            role = "USER";
        }

        UserDetails user = User.builder()
                .username(adminEntity.getUsername())
                .password(adminEntity.getPassword())
                .roles(role)
                .build();

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null,authoritiesMapper.mapAuthorities(user.getAuthorities()));

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }

    private void checkRefreshTokenAndRecreateAccessToken(HttpServletResponse response, String refreshToken) throws IOException {

        AdminEntity adminEntity = adminRepository.findByRefreshToken(refreshToken);

        if(adminEntity != null) {
            jwtService.setAccessTokenHeader(response, jwtService.createAccessToken(adminEntity.getUsername()));
        }
    }
}
