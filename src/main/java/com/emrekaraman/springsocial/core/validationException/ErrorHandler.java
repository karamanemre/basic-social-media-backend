package com.emrekaraman.springsocial.core.validationException;

import com.emrekaraman.springsocial.business.constants.Messages;
import com.emrekaraman.springsocial.core.utilities.ErrorDataResult;
import com.emrekaraman.springsocial.core.utilities.ErrorResult;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ErrorHandler implements ErrorController {

    private final ErrorAttributes errorAttributes;

    public ErrorHandler(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("/error")
    public ErrorDataResult<Object> handleError(WebRequest webRequest){

        HashMap<String,String> error = new HashMap<>();
        Map<String, Object> attributes = this.errorAttributes.getErrorAttributes(webRequest,ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE,ErrorAttributeOptions.Include.BINDING_ERRORS));

        error.put("message", (String) attributes.get("message"));
        error.put("path",(String) attributes.get("path"));
        int status = (int) attributes.get("status");
        error.put("status",Integer.toString(status));

        if (attributes.containsKey("errors")){
            List<FieldError> fieldErrors = (List<FieldError>) attributes.get("errors");
            for (FieldError fieldError : fieldErrors) {
                error.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
        }

        return new ErrorDataResult(error, Messages.VALIDATION_EROR_MESSAGE);
    }


}
