package com.emrekaraman.springsocial.security;

import com.emrekaraman.springsocial.auth.userAuthService.UserAuthService;
import com.emrekaraman.springsocial.security.jwt.TokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserAuthService userAuthService;  //Spring user'ı burada arayacak
    private final TokenFilter tokenFilter;

    public SecurityConfiguration(UserAuthService userAuthService, BCryptPasswordEncoder bCryptPasswordEncoder, TokenFilter tokenFilter) {
        this.userAuthService = userAuthService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenFilter = tokenFilter;
    }

    @Bean public AuthenticationManager getAuthenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http
                .exceptionHandling()
                .authenticationEntryPoint(new AuthEntryPoint());

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.PUT,"/api/usercontroller/update").authenticated()
                .antMatchers(HttpMethod.GET,"/api/flowcontroller/**").authenticated()
                .and()
                .authorizeRequests()
                .anyRequest()
                .permitAll();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAuthService).passwordEncoder(bCryptPasswordEncoder);
    }
}
