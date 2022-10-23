package com.example.wakiserver.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.wakiserver.response.ResponseTemplateStatus.*;

@Slf4j
@RestControllerAdvice
public class ResponseExceptionHandler {
    @ExceptionHandler(value = {ResponseException.class})
    public ResponseTemplate<Object> handleAdminErrorException(ResponseException exception) {
//        log.error("throw customException : {}", exception.getResponseTemplateStatus());
        ResponseTemplate restApiException = new ResponseTemplate<>(exception.getStatus());
        return new ResponseTemplate<>(restApiException);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseTemplate<String> ValidationException(MethodArgumentNotValidException exception) {
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
        });

        ObjectError objectError = exception.getBindingResult().getAllErrors().stream().findFirst().get();
        if(((FieldError) objectError).getField().equals("email")){
            if(objectError.getDefaultMessage().equals("invalid"))
                return new ResponseTemplate<>(INVALID_EMAIL);
            return new ResponseTemplate<>(EMPTY_EMAIL);
        }
        if(((FieldError) objectError).getField().equals("password")){
            if(objectError.getDefaultMessage().equals("invalid"))
                return new ResponseTemplate<>(INVALID_PWD);
            return new ResponseTemplate<>(EMPTY_PWD);
        }
        if(((FieldError) objectError).getField().equals("phoneNumber")){
            if(objectError.getDefaultMessage().equals("invalid"))
                return new ResponseTemplate<>(INVALID_PHONE);
            return new ResponseTemplate<>(EMPTY_PHONE);
        }
        if(((FieldError) objectError).getField().equals("username")){
            if(objectError.getDefaultMessage().equals("invalid"))
                return new ResponseTemplate<>(INVALID_NAME);
            return new ResponseTemplate<>(EMPTY_NAME);
        }
        String errorMsg = "field : " + ((FieldError) objectError).getField() + ", errorMessage : " + objectError.getDefaultMessage();
        return new ResponseTemplate<>(errorMsg);
    }
}
