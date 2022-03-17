package com.emrekaraman.springsocial.auth;

import com.emrekaraman.springsocial.core.utilities.DataResult;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    DataResult<LoginResponse> login(LoginActivity loginActivity);
}
