package com.example.wakiserver.service;

import com.example.wakiserver.auth.PrincipalDetails;
import com.example.wakiserver.domain.report.Report;
import com.example.wakiserver.domain.report.ReportRepository;
import com.example.wakiserver.domain.user.Role;
import com.example.wakiserver.domain.user.User;
import com.example.wakiserver.domain.user.UserRepository;
import com.example.wakiserver.response.ResponseException;
import com.example.wakiserver.response.ResponseTemplate;
import com.example.wakiserver.web.dto.UserLoginDto;
import com.example.wakiserver.web.dto.UserResponseDto;
import com.example.wakiserver.web.dto.UserSaveDto;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static com.example.wakiserver.response.ResponseTemplateStatus.*;


@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final ReportRepository reportRepository;

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
        User user = userRepository.findByEmail(userLoginDto.getEmail())
                .orElseThrow(() -> new ResponseException(NO_USER));
        if (!bCryptPasswordEncoder.matches(userLoginDto.getPassword(), user.getPassword())) {
            throw new ResponseException(INVALID_PWD);
        }
        return user;
    }

    public String getUserEmail(){
        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principalDetails instanceof PrincipalDetails){
            return principalDetails.getUserEmail();
        } else {
            return principalDetails.toString();
        }
    }

    public UserResponseDto loadUser(String email) throws ResponseException{
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseException(NO_USER));
        if(user.getPoint() == null){
            user.applyPoint(0L);
        }
        UserResponseDto userResponseDto = new UserResponseDto(user);
        Report report = reportRepository.findFirstByUserOrderByReportIdxDesc(user);
        if(report != null){
            userResponseDto.setMode(report.getMode());
        } else userResponseDto.setMode("-");
        return userResponseDto;
    }

    public String applyPoints(String email, Long point) throws ResponseException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseException(NO_USER));
        user.applyPoint(point);
        return "적용이 완료되었습니다";
    }

}
