package com.keonah.stanbytest.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

// JSON 으로 로그인을 하기 위함
public class JsonIdPwAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper;

    public JsonIdPwAuthenticationFilter(ObjectMapper objectMapper) {
        super(new AntPathRequestMatcher("/admins/signin", "POST"));
        this.objectMapper = objectMapper;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        if(request.getMethod() == null || !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        UsernamePasswordAuthenticationToken authenticationToken;

        if(request.getContentType().equals("application/json")) {

            Map<String, String> idPwMap = objectMapper.readValue(StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8), Map.class);

            String username = idPwMap.get("id");
            String password = idPwMap.get("pw");

            authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        } else {
            String username = request.getParameter("id");
            String password = request.getParameter("pw");

            authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        }



        return this.getAuthenticationManager().authenticate(authenticationToken);
    }

}
