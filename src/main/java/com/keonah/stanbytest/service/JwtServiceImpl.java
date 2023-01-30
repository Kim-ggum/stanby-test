package com.keonah.stanbytest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keonah.stanbytest.repository.AdminRepository;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService{

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.access.expiration}")
    private long accessTokenValidity;
    @Value("${jwt.refresh.expiration}")
    private long refreshTokenValidity;
    @Value("${jwt.access.header}")
    private String accessHeader;
    @Value("${jwt.refresh.header}")
    private String refreshHeader;

    @Override
    public String createAccessToken(String id) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject("accessToken")
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .claim("id",id)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + accessTokenValidity * 1000))
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }

    @Override
    public String createRefreshToken(String id) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject("refreshToken")
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + refreshTokenValidity * 1000))
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            if (Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody().getExpiration().before(new Date())) {
                return false;
            }

            return true;
        } catch (Exception e) {

            return false;

        }
    }

    @Override
    public String extractAccessToken(HttpServletRequest httpServletRequest) throws IOException, ServletException {
        return Optional.ofNullable(httpServletRequest.getHeader(accessHeader)).map(accessToken -> accessToken.replace("Bearer", "")).orElse(null);
    }

    @Override
    public String extractRefreshToken(HttpServletRequest httpServletRequest) throws IOException, ServletException {
        return Optional.ofNullable(httpServletRequest.getHeader(refreshHeader)).map(refreshToken -> refreshToken.replace("Bearer", "")).orElse(null);
    }

    @Override
    public String extractId(String accessToken) {
        return (String) Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(accessToken).getBody().get("id");
    }

    @Override
    public void setAccessTokenHeader(HttpServletResponse httpServletResponse, String accessToken) {
        httpServletResponse.setHeader(accessHeader, accessToken);
    }

    @Override
    public void setRefreshTokenHeader(HttpServletResponse httpServletResponse, String refreshToken) {
        httpServletResponse.setHeader(refreshHeader, refreshToken);
    }

}
