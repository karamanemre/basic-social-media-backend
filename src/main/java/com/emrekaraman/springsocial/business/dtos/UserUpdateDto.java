package com.emrekaraman.springsocial.business.dtos;

import com.emrekaraman.springsocial.business.constants.Messages;
import com.emrekaraman.springsocial.core.constraint.abstracts.UniqeUserName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {
    private Long id;

    //@UniqeUserName(message = "{basicsocialmedia.constraints.userName.UniqeUserName.message}")
    @NotEmpty(message = Messages.USER_NAME_CAN_NOT_BE_EMPTY)
    @Size(min = 1,max = 255)
    private String username;

    @NotEmpty(message = Messages.FULL_NAME_CAN_NOT_BE_EMPTY)
    @Size(min = 1,max = 255)
    private String fullname;

    @NotEmpty(message = Messages.PASSWORD_CAN_NOT_BE_EMPTY)
    @Size(min = 6,max = 32)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$",message = Messages.PASSWORD_MUST_CONTAIN_THIS)
    private String password;


    private String imageUrl;
}
