package com.emrekaraman.springsocial.business.constants;

public class Messages {

    //Entity Validation Messages
    public static final String USER_NAME_CAN_NOT_BE_EMPTY = "User name can't be empty";
    public static final String USER_NAME_MUST_BE_UNIQE = "This username is already taken";
    public static final String PASSWORD_CAN_NOT_BE_EMPTY = "Password can't be empty";
    public static final String PASSWORD_MIN_VALUE = "Password must be min 6 charachter";
    public static final String PASSWORD_MAX_VALUE = "Password must be max 32 charachter";
    public static final String PASSWORD_MUST_CONTAIN_THIS = "Password must be,one uppercase, one lowercase, one number and one special charachter contains";
    public static final String FULL_NAME_CAN_NOT_BE_EMPTY = "Full name can't be empty";
    public static final String VALIDATION_EROR_MESSAGE = "Validations errors";

    //Auth Messages
    public static final String VERIFICATION_FAILED = "Verification failed";
    public static final String VERIFICATION_SUCCESS = "Verification success";
    public static final String UNAUTHORIZE = "Unauthorize";

    //Business Messages
    public static final String USERNAME_NOT_FOUND = "Username not found";
    public static final String USERNAME_FOUND = "Username not found";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String USER_FOUND = "User found";

    //CRUD Messages
    public static final String SUCCESSFULLY_ADDED = "Successfully added";
    public static final String FAILED_ADDED = "Failed added";
    public static final String SUCCESSFULLY_UPDATED = "Successfully updated";
    public static final String FAILED_UPDATED = "Failed updated";
    public static final String SUCCESSFULLY_DELETED = "Successfully deleted";
    public static final String FAILED_DELETED = "Failed deleted";
    public static final String SUCCESSFULLY_GETALL = "Successfully getall";
    public static final String FAILED_GETALL = "Failed getall";

    //General Messages
    public static final String SUCCESSFULLY_PROCESS = "Successfully process";
    public static final String FAILED_PROCESS = "Failed process";

}
