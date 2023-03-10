package com.keonah.stanbytest.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keonah.stanbytest.filter.JsonIdPwAuthenticationFilter;
import com.keonah.stanbytest.filter.JwtAuthenticationFilter;
import com.keonah.stanbytest.handler.SigninFailureHandler;
import com.keonah.stanbytest.handler.SigninSuccessHandler;
import com.keonah.stanbytest.repository.AdminRepository;
import com.keonah.stanbytest.service.JwtService;
import com.keonah.stanbytest.service.SigninService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final ObjectMapper objectMapper;
    private final SigninService signinService;
    private final SigninFailureHandler signinFailureHandler;
    private final SigninSuccessHandler signinSuccessHandler;
    private final JwtService jwtService;
    private final AdminRepository adminRepository;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http
                .formLogin().disable()
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/admins","/admins/signin").permitAll() // ???????????? ?????? ??????
                .anyRequest().authenticated();

        http.addFilterAfter(jsonIdPwAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtAuthenticationProcessingFilter(), JsonIdPwAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public JsonIdPwAuthenticationFilter jsonIdPwAuthenticationFilter() {
        JsonIdPwAuthenticationFilter jsonIdPwAuthenticationFilter = new JsonIdPwAuthenticationFilter(objectMapper);
        jsonIdPwAuthenticationFilter.setAuthenticationManager(authenticationManager());
        jsonIdPwAuthenticationFilter.setAuthenticationFailureHandler(signinFailureHandler);
        jsonIdPwAuthenticationFilter.setAuthenticationSuccessHandler(signinSuccessHandler);

        return jsonIdPwAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(signinService);
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        // ???????????? ?????? ID ???????????? ?????? ???????????? ?????? ??????

        return new ProviderManager(daoAuthenticationProvider);
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationProcessingFilter(){
        JwtAuthenticationFilter jsonIdPwSigninFilter = new JwtAuthenticationFilter(jwtService, adminRepository);

        return jsonIdPwSigninFilter;
    }

}
