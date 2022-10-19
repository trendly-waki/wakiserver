package com.example.wakiserver.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResponseException extends Exception{
    private ResponseTemplateStatus status;
}
