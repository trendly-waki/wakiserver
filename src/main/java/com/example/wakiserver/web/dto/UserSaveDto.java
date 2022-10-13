package com.example.wakiserver.web.dto;

import com.example.wakiserver.domain.user.Role;
import com.example.wakiserver.domain.user.User;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class UserSaveDto {
    private String username;

    private String password;

    private String email;

    private Role role;

    @Builder
    public User toEntity(){
        return User.userDetailRegister()
                .username(username)
                .password(password)
                .email(email)
                .role(role)
                .build();
    }
}
