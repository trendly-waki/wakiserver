package com.example.wakiserver.web;

import com.example.wakiserver.response.ResponseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.wakiserver.response.ResponseTemplateStatus.*;

@RestController
public class TokenExceptionController {
    @GetMapping("/exception/entrypoint")
    public void entryPoint() throws ResponseException {
        throw new ResponseException(NO_LOGIN);
    }

    @GetMapping("/exception/access")
    public void denied() throws ResponseException {
        throw new ResponseException(NO_ADMIN);
    }
}

