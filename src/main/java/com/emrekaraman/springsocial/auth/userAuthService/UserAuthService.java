package com.emrekaraman.springsocial.auth.userAuthService;

import com.emrekaraman.springsocial.business.abstracts.UserService;
import com.emrekaraman.springsocial.business.constants.Messages;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService implements UserDetailsService {

    private final UserService userService;

    public UserAuthService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!userService.existsByUserName(username).isSuccess()){
            throw new UsernameNotFoundException(Messages.UNAUTHORIZE);
        }
        return new UserDetailsManager(userService.findByUserName(username).getData());
    }
}
