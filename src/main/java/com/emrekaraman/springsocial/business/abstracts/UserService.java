package com.emrekaraman.springsocial.business.abstracts;

import com.emrekaraman.springsocial.business.dtos.UserDto;
import com.emrekaraman.springsocial.business.dtos.UserUpdateDto;
import com.emrekaraman.springsocial.core.utilities.DataResult;
import com.emrekaraman.springsocial.core.utilities.Result;
import com.emrekaraman.springsocial.entities.concretes.User;

import java.util.List;

public interface UserService {

    Result add(UserDto userDto);
    Result existsByUserName(String username);
    DataResult<User> findByUserName(String username);
    DataResult<User> findById(Long id);
    DataResult<List<User>> getALlUsers();
    DataResult<List<User>> getALlUsers(int pageNo,int pageSize);
    DataResult<User> update(UserUpdateDto userUpdateDto);

}
