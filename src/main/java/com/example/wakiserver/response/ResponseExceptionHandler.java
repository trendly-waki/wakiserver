package com.example.wakiserver.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ResponseExceptionHandler {
    @ExceptionHandler(value = {ResponseException.class})
    public ResponseTemplate<Object> handleAdminErrorException(ResponseException exception) {
//        log.error("throw customException : {}", exception.getResponseTemplateStatus());
        ResponseTemplate restApiException = new ResponseTemplate<>(exception.getStatus());
        return new ResponseTemplate<>(restApiException);
    }
}
