package com.example.wakiserver.web;

import com.example.wakiserver.auth.JwtTokenProvider;
import com.example.wakiserver.domain.user.Role;
import com.example.wakiserver.domain.user.User;
import com.example.wakiserver.response.ResponseException;
import com.example.wakiserver.response.ResponseTemplate;
import com.example.wakiserver.response.ResponseTemplateStatus;
import com.example.wakiserver.service.UserService;
import com.example.wakiserver.web.dto.UserLoginDto;
import com.example.wakiserver.web.dto.UserResponseDto;
import com.example.wakiserver.web.dto.UserSaveDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final JwtTokenProvider jwtTokenProvider;

    @ApiOperation(value="회원 가입", notes="회원가입을 합니다. 반환값(data) : userIdx")
    @PostMapping("/join")
    public ResponseTemplate<Long> join(@Valid @RequestBody UserSaveDto userSaveDto) throws ResponseException {
        return new ResponseTemplate<>(userService.join(userSaveDto));

    }

    @ApiOperation(value="로그인", notes="로그인을 합니다. 반환값(data) : jwt 토큰")
    @PostMapping("/login")
    public ResponseTemplate<String> login(@RequestBody UserLoginDto userLoginDto, HttpServletResponse response) throws ResponseException {
        User user = userService.login(userLoginDto);
        String userEmail = user.getEmail();
        Role role = user.getRole();

        String token = jwtTokenProvider.createToken(userEmail, role);
        response.setHeader("JWT", token);

        return new ResponseTemplate<>(token);
    }

    @ApiOperation(value = "메인화면 유저 정보 불러오기", notes = "메인화면에 필요한 유저 정보를 불러옵니다. JWT 입력 필수!")
    @GetMapping("main/user")
    public ResponseTemplate<UserResponseDto> loadUser() throws ResponseException {
        String email = userService.getUserEmail();
        UserResponseDto userResponseDto = userService.loadUser(email);
        return new ResponseTemplate<>(userResponseDto);
    }

    @ApiOperation(value = "유저 포인트 변경", notes = "유저 포인트를 변경합니다. JWT 입력 필수!")
    @PatchMapping("/user/points")
    public ResponseTemplate<String> applyPoints(Long point) throws ResponseException {
        String email = userService.getUserEmail();
        String result = userService.applyPoints(email, point);
        return new ResponseTemplate<>(result);
    }

    @ApiOperation(value = "이메일 중복 체크", notes = "이메일 중복 체크를 합니다. true:중복, false:중복X")
    @GetMapping("/exists/{email}")
    public ResponseTemplate<String> checkDuplicate(@PathVariable String email){
        if(userService.CheckDuplicateEmail(email)){
            return new ResponseTemplate<>(ResponseTemplateStatus.SAME_EMAIL);
        } else return new ResponseTemplate<>("사용 가능한 이메일입니다.");
    }


//    // !!!! OAuth로 로그인 시 이 방식대로 하면 CastException 발생함
//    @GetMapping("/form/loginInfo")
//    @ResponseBody
//    public String formLoginInfo(Authentication authentication, @AuthenticationPrincipal PrincipalDetails principalDetails){
//
//        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
//        User user = principal.getUser();
//        System.out.println(user);
//        //User(id=2, username=11, password=$2a$10$m/1Alpm180jjsBpYReeml.AzvGlx/Djg4Z9/JDZYz8TJF1qUKd1fW, email=11@11, role=ROLE_USER, createTime=2022-01-30 19:07:43.213, provider=null, providerId=null)
//
//        User user1 = principalDetails.getUser();
//        System.out.println(user1);
//
//        return user.toString();
//    }


//    @GetMapping("/oauth/loginInfo")
//    @ResponseBody
//    public String oauthLoginInfo(Authentication authentication, @AuthenticationPrincipal OAuth2User oAuth2UserPrincipal){
//        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
//        Map<String, Object> attributes = oAuth2User.getAttributes();
//        System.out.println(attributes);
//        // PrincipalOauth2UserService의 getAttributes내용과 같음
//
//        Map<String, Object> attributes1 = oAuth2UserPrincipal.getAttributes();
//        // attributes == attributes1
//
//        return attributes.toString();     //세션에 담긴 user가져올 수 있음
//    }
}

