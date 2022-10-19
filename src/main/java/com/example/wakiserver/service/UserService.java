package com.example.wakiserver.service;

import com.example.wakiserver.domain.user.Role;
import com.example.wakiserver.domain.user.User;
import com.example.wakiserver.domain.user.UserRepository;
import com.example.wakiserver.response.ResponseException;
import com.example.wakiserver.response.ResponseTemplate;
import com.example.wakiserver.web.dto.UserLoginDto;
import com.example.wakiserver.web.dto.UserSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static com.example.wakiserver.response.ResponseTemplateStatus.*;


@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public Long join(UserSaveDto userSaveDto) throws ResponseStatusException {
        try{
            userSaveDto.setPassword(bCryptPasswordEncoder.encode(userSaveDto.getPassword()));
            userSaveDto.setRole(Role.ROLE_USER);
            return userRepository.save(userSaveDto.toEntity()).getId();
        } catch (ResponseStatusException e){
            throw new ResponseStatusException(e.getStatus());
        }
    }

    //로그인
    public User login(UserLoginDto userLoginDto) throws ResponseException {
        try {
            User user = userRepository.findByEmail(userLoginDto.getEmail())
                    .orElseThrow(() -> new ResponseException(NO_USER));
            if (!bCryptPasswordEncoder.matches(userLoginDto.getPassword(), user.getPassword())) {
                throw new ResponseException(INVALID_PWD);
            }
            return user;
        } catch (Exception e){
            throw new ResponseException(BAD_REQUEST);
        }
    }
}
