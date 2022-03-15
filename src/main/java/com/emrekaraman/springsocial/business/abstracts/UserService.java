package com.emrekaraman.springsocial.business.abstracts;

import com.emrekaraman.springsocial.business.dtos.UserDto;
import com.emrekaraman.springsocial.core.utilities.Result;

public interface UserService {

    Result add(UserDto userDto);
}
