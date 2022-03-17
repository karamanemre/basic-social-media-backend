package com.emrekaraman.springsocial.auth;

import com.emrekaraman.springsocial.core.utilities.DataResult;
import com.emrekaraman.springsocial.core.utilities.ErrorDataResult;
import com.emrekaraman.springsocial.core.utilities.ErrorResult;
import com.emrekaraman.springsocial.core.utilities.SuccessResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginActivity loginActivity){
        DataResult<LoginResponse> result = authService.login(loginActivity);

        if (!result.isSuccess()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
        return ResponseEntity.ok(result);
    }
}
