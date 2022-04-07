package com.emrekaraman.springsocial.auth;
import com.emrekaraman.springsocial.business.abstracts.UserService;
import com.emrekaraman.springsocial.business.constants.Messages;
import com.emrekaraman.springsocial.core.beans.PasswordEncoder;
import com.emrekaraman.springsocial.core.business.BusinessRules;
import com.emrekaraman.springsocial.core.utilities.*;
import com.emrekaraman.springsocial.entities.concretes.User;
import com.emrekaraman.springsocial.security.jwt.TokenManager;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import java.util.UUID;


@Service
public class AuthManager implements AuthService {

    private final ModelMapper modelMapper;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final TokenManager tokenManager;
    private final AuthenticationManager authenticationManager;

    public AuthManager(ModelMapper modelMapper, UserService userService, PasswordEncoder passwordEncoder, TokenManager tokenManager, AuthenticationManager authenticationManager) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.tokenManager = tokenManager;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public DataResult<LoginResponse> authenticationHandle(LoginActivity loginActivity){
        Result result = BusinessRules.run(
                checkExistsByUsername(loginActivity.getUsername()),
                checkPasswordIsCorrect(loginActivity.getUsername(), loginActivity.getPassword())
        );

        if (result != null){
            return new ErrorDataResult(result.getMessage());
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginActivity.getUsername(),loginActivity.getPassword()));

        User user = userService.findByUserName(loginActivity.getUsername()).getData();
        String token = tokenManager.generateToken(user.getUsername());
        LoginResponse loginResponse = modelMapper.map(user,LoginResponse.class);
        loginResponse.setToken(token);
        //loginResponse.setAuthorities(userDetailsManager.getAuthorities().stream().map(m->m.getAuthority()));
        return new SuccessDataResult(loginResponse);
    }


    private Result checkExistsByUsername(String username) {
        if (userService.existsByUserName(username).isSuccess()==false) {
            return new ErrorResult(Messages.UNAUTHORIZE);
        }
        return new SuccessResult();
    }

    private Result checkPasswordIsCorrect(String username, String password) {
        User user = userService.findByUserName(username).getData();
        passwordEncoder.bCryptPasswordEncoder().encode(password);
        if (user!=null && !passwordEncoder.bCryptPasswordEncoder().matches(password,user.getPassword())) {
            return new ErrorResult(Messages.UNAUTHORIZE);
        }
        return new SuccessResult();
    }

    private String generateRandomToken(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

}
