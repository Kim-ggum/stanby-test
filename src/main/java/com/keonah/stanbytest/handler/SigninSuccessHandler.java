package com.keonah.stanbytest.handler;

import com.keonah.stanbytest.entity.AdminEntity;
import com.keonah.stanbytest.repository.AdminRepository;
import com.keonah.stanbytest.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SigninSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtService jwtService;
    private final AdminRepository adminRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain; charset=UTF-8");

        String id = authentication.getName();
        String accessToken = jwtService.createAccessToken(id);
        String refreshToken = jwtService.createRefreshToken(id);

        jwtService.setAccessTokenHeader(response, accessToken);
        jwtService.setRefreshTokenHeader(response, refreshToken);

        AdminEntity adminEntity = adminRepository.findById(id);
        if(adminEntity != null) {
            adminEntity.setRefreshToken(refreshToken);
            adminRepository.save(adminEntity);
        }


        response.getWriter().write("로그인 성공.");
    }
}