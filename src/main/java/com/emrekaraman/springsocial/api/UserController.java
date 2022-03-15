package com.emrekaraman.springsocial.api;

import com.emrekaraman.springsocial.business.abstracts.UserService;
import com.emrekaraman.springsocial.business.dtos.UserDto;
import com.emrekaraman.springsocial.core.utilities.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usercontroller")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody UserDto userDto){
        return ResponseEntity.ok(this.userService.add(userDto));
    }
}
