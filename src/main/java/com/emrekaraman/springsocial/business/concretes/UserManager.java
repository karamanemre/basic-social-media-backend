package com.emrekaraman.springsocial.business.concretes;

import com.emrekaraman.springsocial.business.abstracts.UserService;
import com.emrekaraman.springsocial.business.dtos.UserDto;
import com.emrekaraman.springsocial.core.utilities.Result;
import com.emrekaraman.springsocial.core.utilities.SuccessResult;
import com.emrekaraman.springsocial.dao.UserDao;
import com.emrekaraman.springsocial.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements UserService {

    private final UserDao userDao;
    private final ModelMapper modelMapper;

    public UserManager(UserDao userDao, ModelMapper modelMapper) {
        this.userDao = userDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public Result add(UserDto userDto) {
        User user = modelMapper.map(userDto,User.class);
        this.userDao.save(user);
        return new SuccessResult("Başarılı");
    }
}
