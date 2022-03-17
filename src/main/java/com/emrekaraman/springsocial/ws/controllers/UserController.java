package com.emrekaraman.springsocial.ws.controllers;

import com.emrekaraman.springsocial.business.abstracts.UserService;
import com.emrekaraman.springsocial.business.dtos.UserDto;
import com.emrekaraman.springsocial.core.utilities.ErrorDataResult;
import com.emrekaraman.springsocial.core.utilities.Result;
import com.emrekaraman.springsocial.core.validationException.ValidationExceptionHandler;
import com.emrekaraman.springsocial.dataAccess.abstracts.UserDao;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usercontroller")
public class UserController {

    private final UserService userService;
    private final ValidationExceptionHandler validationExceptionHandler;
    private PasswordEncoder passwordEncoder;


    public UserController(UserService userService, ValidationExceptionHandler validationExceptionHandler) {
        this.userService = userService;
        this.validationExceptionHandler = validationExceptionHandler;
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody @Valid UserDto userDto){
        return ResponseEntity.ok(this.userService.add(userDto));
    }

    @GetMapping("findByUsername")
    public ResponseEntity<?> finByUsername(@RequestParam String username){
        return ResponseEntity.ok(userService.findByUserName(username));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDataResult<Object>> validation(MethodArgumentNotValidException exceptions) {
        return ResponseEntity.badRequest().body(validationExceptionHandler.handleValidationException(exceptions));
    }
}
