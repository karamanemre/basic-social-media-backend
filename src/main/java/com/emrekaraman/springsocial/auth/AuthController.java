package com.emrekaraman.springsocial.auth;

import com.emrekaraman.springsocial.auth.userAuthService.UserDetailsManager;
import com.emrekaraman.springsocial.core.constraint.abstracts.CurrentUser;
import com.emrekaraman.springsocial.core.utilities.DataResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/authenticationHandle")
    public ResponseEntity<?> authenticationHandle(@RequestBody LoginActivity loginActivity){
        return ResponseEntity.ok(authService.authenticationHandle(loginActivity));
    }
}
