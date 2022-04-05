package com.example.restapis3v1.config;

import com.example.restapis3v1.security.jwt.JwtConfigurer;
import com.example.restapis3v1.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    private static final String ADMIN_ENDPOINT = "/api/v1/admin/**";
    private static final String LOGIN_ENDPOINT = "/api/v1/auth/login";
    private static final String ADMIN_FILE_ENDPOINT = "/api/v1/files/**";
    private static final String MODERATOR_FILE_UPLOAD = "/api/v1/files/upload/**";
    private static final String MODERATOR_FILE_DOWNLOAD = "/api/v1/files/download/**";
    private static final String USER_FILE_BY_NAME = "/api/v1/files/findbyname/**";
    private static final String USER_ALL_FILES = "/api/v1/files/getall/**";

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(LOGIN_ENDPOINT).permitAll()
                .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
                .antMatchers(ADMIN_FILE_ENDPOINT).hasRole("ADMIN")
                .antMatchers(MODERATOR_FILE_UPLOAD).hasRole("MODERATOR")
                .antMatchers(MODERATOR_FILE_DOWNLOAD).hasRole("MODERATOR")
                .antMatchers(USER_FILE_BY_NAME).hasRole("USER")
                .antMatchers(USER_ALL_FILES).hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}

