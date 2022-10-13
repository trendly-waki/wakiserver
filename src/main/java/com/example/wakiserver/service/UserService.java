package com.example.wakiserver.service;

import com.example.wakiserver.domain.user.Role;
import com.example.wakiserver.domain.user.User;
import com.example.wakiserver.domain.user.UserRepository;
import com.example.wakiserver.web.dto.UserSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
}
