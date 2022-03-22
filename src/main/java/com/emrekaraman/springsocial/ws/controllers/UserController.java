package com.emrekaraman.springsocial.ws.controllers;

import com.emrekaraman.springsocial.business.abstracts.UserService;
import com.emrekaraman.springsocial.business.dtos.UserDto;
import com.emrekaraman.springsocial.core.utilities.DataResult;
import com.emrekaraman.springsocial.core.utilities.Result;
import com.emrekaraman.springsocial.entities.concretes.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/usercontroller")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@Valid @RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.add(userDto));
    }

    @GetMapping("findByUsername")
    public ResponseEntity<DataResult<User>> finByUsername(@RequestParam String username){
        return ResponseEntity.ok(userService.findByUserName(username));
    }

    @GetMapping("getAllUsers")
    public ResponseEntity<DataResult<List<User>>> getAllUsers(){
        return ResponseEntity.ok(userService.getALlUsers());
    }

    @GetMapping("getAllUsersWithPage")
    public ResponseEntity<DataResult<List<User>>> getALlUsers(@RequestParam(required = false,defaultValue = "1")  int pageNo,@RequestParam(required = false,defaultValue = "10") int pageSize){
        return ResponseEntity.ok(userService.getALlUsers(pageNo,pageSize));
    }

}
