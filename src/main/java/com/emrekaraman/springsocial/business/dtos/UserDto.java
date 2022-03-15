package com.emrekaraman.springsocial.business.dtos;

import com.emrekaraman.springsocial.core.constants.Messages;
import com.emrekaraman.springsocial.core.constraint.UniqeUserName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    @NotEmpty(message = Messages.USER_NAME_CAN_NOT_BE_EMPTY)
    @Size(min = 3,max = 255)
    @UniqeUserName(message = Messages.USERNAME_MUST_BE_UNIQE)
    private String userName;

    @NotEmpty(message = Messages.FULL_NAME_CAN_NOT_BE_EMPTY)
    @Size(min = 1,max = 255)
    private String fullName;

    @NotEmpty(message = Messages.PASSWORD_CAN_NOT_BE_EMPTY)
    @Size(min = 6,message = Messages.PASSWORD_MIN_VALUE)
    @Size(max = 32,message = Messages.PASSWORD_MAX_VALUE)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$",message = Messages.PASSWORD_MUST_CONTAIN_THIS)
    private String password;

}
