package com.keonah.stanbytest.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface JwtService {

    String createAccessToken(String id);
    String createRefreshToken(String id);

    boolean validateToken(String token);

    String extractAccessToken(HttpServletRequest httpServletRequest) throws IOException, ServletException;
    String extractRefreshToken(HttpServletRequest httpServletRequest) throws IOException, ServletException;

    String extractId(String accessToken);

    void setAccessTokenHeader(HttpServletResponse httpServletResponse, String accessToken);
    void setRefreshTokenHeader(HttpServletResponse httpServletResponse, String refreshToken);

}
