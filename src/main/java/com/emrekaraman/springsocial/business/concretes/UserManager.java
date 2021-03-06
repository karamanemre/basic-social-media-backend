package com.emrekaraman.springsocial.business.concretes;

import com.emrekaraman.springsocial.business.abstracts.UserService;
import com.emrekaraman.springsocial.business.constants.Messages;
import com.emrekaraman.springsocial.business.dtos.PagesDto;
import com.emrekaraman.springsocial.business.dtos.UserDto;
import com.emrekaraman.springsocial.business.dtos.UserUpdateDto;
import com.emrekaraman.springsocial.core.utilities.*;
import com.emrekaraman.springsocial.dataAccess.abstracts.UserDao;
import com.emrekaraman.springsocial.entities.concretes.User;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        User user = modelMapper.map(userDto,User.class);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        this.userDao.save(user);
        return new SuccessResult(Messages.SUCCESSFULLY_ADDED);
    }

    @Override
    public Result existsByUserName(String username){
        if (!this.userDao.existsByUsername(username)){
            return new ErrorResult(Messages.USERNAME_NOT_FOUND);
        }
        return new SuccessResult(Messages.USERNAME_FOUND);
    }

    @Override
    public DataResult<User> findByUserName(String username) {
        boolean bool = userDao.existsByUsername(username);
        if (!bool){
            return new ErrorDataResult(Messages.USER_NOT_FOUND);
        }
        return new SuccessDataResult(this.userDao.findByUsername(username),Messages.USER_FOUND);
    }

    @Override
    public DataResult<User> findById(Long id) {
        return new SuccessDataResult(this.userDao.findById(id),Messages.SUCCESSFULLY_PROCESS);
    }

    @Override
    public DataResult<List<User>> getALlUsers() {
        return new SuccessDataResult(userDao.findAll(),Messages.VERIFICATION_SUCCESS);
    }

    @Override
    public DataResult<List<User>> getALlUsers(int pageNo,int pageSize) {
        if (pageNo<1 || pageSize<1){
            return new ErrorDataResult(Messages.PAGE_NUMBER_OR_SIZE_CAN_NOT_BE_LESS_THAN_ONE);
        }
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        return new SuccessDataResult(new PagesDto<User>(this.userDao.findAll(pageable)),Messages.VERIFICATION_SUCCESS);
    }

    @Override
    public DataResult<User> update(UserUpdateDto userUpdateDto) {
        String oldUsername = userDao.findById(userUpdateDto.getId()).get().getUsername();
        if (!isCanHaveThisUsername(oldUsername,userUpdateDto.getUsername())){
            return new ErrorDataResult(Messages.USER_NAME_MUST_BE_UNIQE);
        }
        userUpdateDto.setPassword(bCryptPasswordEncoder.encode(userUpdateDto.getPassword()));
        User user = modelMapper.map(userUpdateDto,User.class);
        return new SuccessDataResult(this.userDao.save(user),Messages.SUCCESSFULLY_UPDATED);
    }

    public boolean isCanHaveThisUsername(String oldUsername,String newUsername) {
        List<User> res = userDao.findAllByUserWithoutThisUsername(oldUsername);
        List<Boolean> resQuery = res.stream().map(m -> m.getUsername().equals(newUsername)).collect(Collectors.toList());
        return resQuery.contains(true) ? false : true;
    }

}
