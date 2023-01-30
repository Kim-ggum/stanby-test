package com.keonah.stanbytest.handler;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class SigninFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {

        String message;

        if (exception instanceof UsernameNotFoundException) {
            message = "ID does not exist.";
        } else if (exception instanceof InternalAuthenticationServiceException) {
            message = "내부 시스템 문제로 로그인 요청을 처리할 수 없습니다.";
        } else if(exception instanceof BadCredentialsException) {
            message = "Wrong password.";
        } else if (exception instanceof AuthenticationCredentialsNotFoundException) {
            message = "인증 요청이 거부되었습니다.";
        } else {
            message = "알 수 없는 오류로 로그인 요청을 처리할 수 없습니다.";
        }

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write(message);

    }

}
