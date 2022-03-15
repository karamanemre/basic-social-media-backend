package com.emrekaraman.springsocial.core.constraint.abstracts;

import com.emrekaraman.springsocial.core.constraint.concretes.UniqeUserNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD}) //annoationu nerelerde kullanabileceğimizi ayarlarız
@Retention(RetentionPolicy.RUNTIME) //hata yakalama yeri burda run time da
@Constraint(validatedBy = {UniqeUserNameValidator.class}) //bu annonationun kullanışacağı yerde
public @interface UniqeUserName {

    String message() default "Username must be uniqe";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
