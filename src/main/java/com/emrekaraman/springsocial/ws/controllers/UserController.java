package com.emrekaraman.springsocial.ws.controllers;

import com.emrekaraman.springsocial.business.abstracts.UserService;
import com.emrekaraman.springsocial.business.dtos.UserDto;
import com.emrekaraman.springsocial.core.utilities.Result;
import com.emrekaraman.springsocial.core.validationException.ErrorHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usercontroller")
public class UserController {

    private final UserService userService;
    private final ErrorHandler errorHandler;
    private PasswordEncoder passwordEncoder;


    public UserController(UserService userService,ErrorHandler errorHandler) {
        this.userService = userService;
        this.errorHandler = errorHandler;
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody @Valid UserDto userDto){
        return ResponseEntity.ok(this.userService.add(userDto));
    }

    @GetMapping("findByUsername")
    public ResponseEntity<?> finByUsername(@RequestParam String username){
        return ResponseEntity.ok(userService.findByUserName(username));
    }

}
