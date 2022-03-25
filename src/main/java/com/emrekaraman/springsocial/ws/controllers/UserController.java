package com.emrekaraman.springsocial.ws.controllers;

import com.emrekaraman.springsocial.auth.userAuthService.UserDetailsManager;
import com.emrekaraman.springsocial.business.abstracts.UserService;
import com.emrekaraman.springsocial.business.dtos.UserDto;
import com.emrekaraman.springsocial.business.dtos.UserUpdateDto;
import com.emrekaraman.springsocial.core.constraint.abstracts.CurrentUser;
import com.emrekaraman.springsocial.core.utilities.DataResult;
import com.emrekaraman.springsocial.core.utilities.Result;
import com.emrekaraman.springsocial.dataAccess.abstracts.UserDao;
import com.emrekaraman.springsocial.entities.concretes.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/getAllUsers")
    public ResponseEntity<DataResult<List<User>>> getAllUsers(){
        return ResponseEntity.ok(userService.getALlUsers());
    }

    @GetMapping("/getAllUsersWithPage")
    public ResponseEntity<DataResult<List<User>>> getALlUsers(@RequestParam(required = false,defaultValue = "1")  int pageNo,@RequestParam(required = false,defaultValue = "10") int pageSize){
        return ResponseEntity.ok(userService.getALlUsers(pageNo,pageSize));
    }

    @GetMapping("/getByUsername/{username}")
    public ResponseEntity<DataResult<User>> findByUserName(@PathVariable String username){
        if (userService.findByUserName(username).isSuccess()){
            return ResponseEntity.ok(userService.findByUserName(username));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userService.findByUserName(username));
    }

    @PutMapping("/update")
    @PreAuthorize("#userUpdateDto.getId() == #userDetailsManager.user.id") //SpEL(Spring Expression Language) (userDetailsManager yerine "principal.username" denebilir)
    public ResponseEntity<DataResult<User>> update(@Valid @RequestBody UserUpdateDto userUpdateDto,@CurrentUser UserDetailsManager userDetailsManager){
        return ResponseEntity.ok(userService.update(userUpdateDto));
    }


}
