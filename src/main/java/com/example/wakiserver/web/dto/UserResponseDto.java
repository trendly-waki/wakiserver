package com.example.wakiserver.web.dto;

import com.example.wakiserver.domain.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UserResponseDto {
    String username;
    Long point;

    @Setter
    String mode;
    public UserResponseDto(User entity){
        this.username = entity.getUsername();;
        this.point = entity.getPoint();
    }
}
