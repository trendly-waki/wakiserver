package com.example.wakiserver.web.dto;

import com.example.wakiserver.domain.user.User;
import lombok.*;


@NoArgsConstructor
@Getter
@ToString
public class UserLoginDto {
    private String email;

    private String password;

    @Builder
    public UserLoginDto(String email, String password){
        this.email = email;
        this.password = password;
    }

}
