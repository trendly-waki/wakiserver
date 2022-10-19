package com.example.wakiserver.exception;

import com.example.wakiserver.response.ResponseTemplateStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException{
    private ResponseTemplateStatus responseTemplateStatus;
}
