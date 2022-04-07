package com.emrekaraman.springsocial.auth;

import com.emrekaraman.springsocial.auth.userAuthService.UserDetailsManager;
import com.emrekaraman.springsocial.core.constraint.abstracts.CurrentUser;
import com.emrekaraman.springsocial.core.utilities.DataResult;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface AuthService {
    DataResult<LoginResponse> authenticationHandle(LoginActivity loginActivity);
}
