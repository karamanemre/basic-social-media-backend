package com.emrekaraman.springsocial.business.concretes;

import com.emrekaraman.springsocial.business.abstracts.UserService;
import com.emrekaraman.springsocial.business.dtos.UserDto;
import com.emrekaraman.springsocial.core.utilities.Result;
import com.emrekaraman.springsocial.core.utilities.SuccessResult;
import com.emrekaraman.springsocial.dao.UserDao;
import com.emrekaraman.springsocial.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements UserService {

    private final UserDao userDao;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserManager(UserDao userDao, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Result add(UserDto userDto) {
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        User user = modelMapper.map(userDto,User.class);
        this.userDao.save(user);
        return new SuccessResult("Başarılı");
    }
}
