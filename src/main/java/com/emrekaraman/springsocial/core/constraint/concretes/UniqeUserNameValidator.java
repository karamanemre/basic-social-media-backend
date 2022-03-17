package com.emrekaraman.springsocial.core.constraint.concretes;

import com.emrekaraman.springsocial.core.constraint.abstracts.UniqeUserName;
import com.emrekaraman.springsocial.dataAccess.abstracts.UserDao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//ilk değer interfacemizin ismi,diğeri bunu hangi fieldda kullanacağımız onun tipi
public class UniqeUserNameValidator implements ConstraintValidator<UniqeUserName,String> {

    private final UserDao userDao;

    public UniqeUserNameValidator(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        if (userDao.existsByUsername(username)){
            return false;
        }
        return true;
    }
}
