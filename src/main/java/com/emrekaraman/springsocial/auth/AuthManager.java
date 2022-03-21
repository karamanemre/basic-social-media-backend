package com.emrekaraman.springsocial.auth;

import com.emrekaraman.springsocial.auth.userAuthService.UserDetailsManager;
import com.emrekaraman.springsocial.core.utilities.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AuthManager implements AuthService {

    private final ModelMapper modelMapper;

    public AuthManager(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public DataResult<LoginResponse> authenticationHandle(UserDetailsManager userDetailsManager){
        LoginResponse loginResponse = modelMapper.map(userDetailsManager.getUser(),LoginResponse.class);
        loginResponse.authorities=userDetailsManager.getAuthorities().stream().map(m->m.getAuthority());
        return new SuccessDataResult(loginResponse);
    }

}
