package com.emrekaraman.springsocial.core.validationException;

import com.emrekaraman.springsocial.business.constants.Messages;
import com.emrekaraman.springsocial.core.utilities.ErrorDataResult;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice //It allows us to do global exception handling by handling exceptions.
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationExceptionHandler {
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {
        Map<String, String> validationErrors = new HashMap<>();
        for (FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ErrorDataResult<Object> errors = new ErrorDataResult<>(validationErrors, Messages.VALIDATION_EROR_MESSAGE);
        return errors;
    }
}
