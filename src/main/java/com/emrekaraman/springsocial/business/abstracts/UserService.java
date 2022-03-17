package com.emrekaraman.springsocial.business.abstracts;

import com.emrekaraman.springsocial.business.dtos.UserDto;
import com.emrekaraman.springsocial.core.utilities.DataResult;
import com.emrekaraman.springsocial.core.utilities.Result;
import com.emrekaraman.springsocial.entities.concretes.User;

public interface UserService {

    Result add(UserDto userDto);
    Result existsByUserName(String username);
    DataResult<User> findByUserName(String username);
}
