package com.emrekaraman.springsocial.auth;
import com.emrekaraman.springsocial.auth.userAuthService.UserDetailsManager;
import com.emrekaraman.springsocial.core.utilities.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
public class AuthManager implements AuthService {


    private final ModelMapper modelMapper;

    public AuthManager(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public DataResult<LoginResponse> authenticationHandle(UserDetailsManager userDetailsManager){
        LoginResponse loginResponse = modelMapper.map(userDetailsManager.getUser(),LoginResponse.class);
        loginResponse.authorities=userDetailsManager.getAuthorities().stream().map(m->m.getAuthority());
        return new SuccessDataResult(loginResponse);
    }

//    @Override
//    public DataResult<LoginResponse> authenticationHandle(LoginActivity loginActivity){
//        Result result = BusinessRules.run(
//                checkExistsByUsername(loginActivity.getUsername()),
//                checkPasswordIsCorrect(loginActivity.getUsername(), loginActivity.getPassword())
//        );
//
//        if (result != null){
//            return new ErrorDataResult(result.getMessage());
//        }
//
//        User user = userService.findByUserName(loginActivity.getUsername()).getData();
//        LoginResponse loginResponse = modelMapper.map(user,LoginResponse.class);
//        String token = generateRandomToken();
//        loginResponse.setToken(token);
//        Token tokenEntity = Token.builder().token(token).user(user).build();
//        tokenDao.save(tokenEntity);
//        //loginResponse.setAuthorities(userDetailsManager.getAuthorities().stream().map(m->m.getAuthority()));
//        return new SuccessDataResult(loginResponse);
//    }
//
//
//    private Result checkExistsByUsername(String username) {
//        if (userService.existsByUserName(username).isSuccess()==false) {
//            return new ErrorResult(Messages.UNAUTHORIZE);
//        }
//        return new SuccessResult();
//    }
//
//    private Result checkPasswordIsCorrect(String username, String password) {
//        User user = userService.findByUserName(username).getData();
//        passwordEncoder.bCryptPasswordEncoder().encode(password);
//        if (user!=null && !passwordEncoder.bCryptPasswordEncoder().matches(password,user.getPassword())) {
//            return new ErrorResult(Messages.UNAUTHORIZE);
//        }
//        return new SuccessResult();
//    }
//
//    private String generateRandomToken(){
//        return UUID.randomUUID().toString().replaceAll("-","");
//    }

}
