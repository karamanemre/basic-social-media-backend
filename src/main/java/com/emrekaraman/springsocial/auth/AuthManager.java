package com.emrekaraman.springsocial.auth;

import com.emrekaraman.springsocial.auth.userAuthService.UserDetailsManager;
import com.emrekaraman.springsocial.business.abstracts.UserService;
import com.emrekaraman.springsocial.business.constants.Messages;
import com.emrekaraman.springsocial.core.utilities.*;
import com.emrekaraman.springsocial.entities.concretes.User;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashMap;

@Service
public class AuthManager implements AuthService {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;

    public AuthManager(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder, ModelMapper modelMapper) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.modelMapper = modelMapper;

    }

    @Override
    public DataResult<LoginResponse> login(String authorization) {
        DataResult<HashMap<String,String >> result = decoderAuthorization(authorization);
        DataResult<User> user = userService.findByUserName(result.getData().get("username"));
        LoginResponse loginResponse = modelMapper.map(user.getData(),LoginResponse.class);
        return new SuccessDataResult(loginResponse);
    }

    private DataResult<HashMap<String,String>> decoderAuthorization(String authorization){
        HashMap<String,String> hashMap = new HashMap<>();
        String base64encoded = authorization.split("Basic ")[1];
        String decoded = new String(Base64.getDecoder().decode(base64encoded));
        String[] parts = decoded.split(":");
        String password = parts[1];
        String username = parts[0];
        hashMap.put("username",username);
        hashMap.put("password",password);
        return new SuccessDataResult(hashMap);
    }

    private Result checkIfUserExistsByUsername(String username) {
        return this.userService.findByUserName(username);
    }


    private Result checkIfUserIsThePasswordCorrect(String username,String password) {
        DataResult<User> user = userService.findByUserName(username);
        if (!user.isSuccess() || !bCryptPasswordEncoder.matches(password,user.getData().getPassword())){
            return new ErrorResult(Messages.VERIFICATION_FAILED);
        }
        return new SuccessResult(Messages.VERIFICATION_SUCCESS);
    }

}
