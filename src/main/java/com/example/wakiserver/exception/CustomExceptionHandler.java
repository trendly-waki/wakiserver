package com.example.wakiserver.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {
//    @ExceptionHandler(value = {CustomException.class})
//    public ResponseTemplate<Object> handleAdminErrorException(CustomException exception) {
//        log.error("throw customException : {}", exception.getResponseTemplateStatus());
//        ResponseDto restApiException = new ResponseDto(exception.getResponseTemplateStatus().getHttpStatus().value(), exception.getResponseTemplateStatus().getDetail(), "");
//        return new ResponseTemplate<>(restApiException, exception.getResponseTemplateStatus().getHttpStatus());
//    }
}

