package com.emrekaraman.springsocial.security;

import com.emrekaraman.springsocial.auth.userAuthService.UserAuthService;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserAuthService userAuthService;  //Spring user'ı burada arayacak

    public SecurityConfiguration(UserAuthService userAuthService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userAuthService = userAuthService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http
                .httpBasic()
                .authenticationEntryPoint(new AuthEntryPoint());

        http
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST,"/api/auth/**").authenticated()
                    .antMatchers(HttpMethod.PUT,"/api/usercontroller/update").authenticated()
                    .antMatchers(HttpMethod.POST,"/api/flowcontroller/add").authenticated()
                    .antMatchers(HttpMethod.DELETE,"/api/flowcontroller/deleteById").authenticated()
                .and()
                .authorizeRequests()
                .anyRequest()
                .permitAll();


        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAuthService).passwordEncoder(bCryptPasswordEncoder);
    }
}
