package com.example.wakiserver.web.dto;

import com.example.wakiserver.domain.user.Role;
import com.example.wakiserver.domain.user.User;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class UserSaveDto {

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Size(min = 2, max = 10, message = "invalid")
    private String username;


    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, max = 20, message = "invalid")
    private String password;


    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "invalid")
    private String email;


    @NotBlank(message = "전화번호를 입력해주세요.")
    @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "invalid")
    private String phoneNumber;

    private Role role;

    @Builder
    public User toEntity(){
        return User.userDetailRegister()
                .username(username)
                .password(password)
                .phoneNumber(phoneNumber)
                .email(email)
                .role(role)
                .build();
    }
}
