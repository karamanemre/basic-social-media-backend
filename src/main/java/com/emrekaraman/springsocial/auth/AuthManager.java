package com.emrekaraman.springsocial.auth;

import com.emrekaraman.springsocial.auth.userAuthService.UserDetailsManager;
import com.emrekaraman.springsocial.business.abstracts.UserService;
import com.emrekaraman.springsocial.business.constants.Messages;
import com.emrekaraman.springsocial.core.constraint.abstracts.CurrentUser;
import com.emrekaraman.springsocial.core.utilities.*;
import com.emrekaraman.springsocial.entities.concretes.User;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthManager implements AuthService {

    private final ModelMapper modelMapper;

    public AuthManager(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public DataResult<LoginResponse> authenticationHandle(UserDetailsManager userDetailsManager) {
        LoginResponse loginResponse = modelMapper.map(userDetailsManager.getUser(),LoginResponse.class);
        return new SuccessDataResult(loginResponse);
    }

}
