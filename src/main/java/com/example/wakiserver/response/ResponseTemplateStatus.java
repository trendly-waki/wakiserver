package com.example.wakiserver.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseTemplateStatus {
    /**    private final Boolean isSuccess;
     private final String message;
     private final int code; //내부 코드
     private T data;
     *
     * 1000: 요청 성공
     */
    SUCCESS(true,"요청 성공", 1000),

    /**
    * 2000: 로그인 오류
    */
    ADMIN_TOKEN(false, "관리자 암호가 일치하지않습니다", 2000),
    NO_USER(false, "없는 사용자입니다.", 2002),
    WRONG_PWD(false,  "비밀번호가 일치하지 않습니다.", 2003),
    NO_LOGIN(false,  "로그인이 필요합니다", 2004),
    NO_ADMIN(false,  "권한이 없는 사용자입니다", 2005),

    /**
     * 3000 : 회원가입 오류
     * */
    EMPTY_EMAIL(false, "이메일을 입력해주세요", 3000),
    SAME_EMAIL(false, "동일한 이메일이 존재합니다.", 3001),
    INVALID_EMAIL(false, "유효하지 않은 이메일입니다.", 3002),
    EMPTY_PWD(false, "비밀번호를 입력해주세요.", 3003),
    INVALID_PWD(false, "비밀번호를 8자 이상 20자 이하로 작성해주세요.", 3004),
    EMPTY_NAME(false, "닉네임을 입력해주세요.", 3005),
    INVALID_NAME(false, "닉네임은 2자 이상 10자 이하로 작성해주세요.", 3006),
    EMPTY_PHONE(false, "전화번호를 입력해주세요.", 3007),
    INVALID_PHONE(false, "유효하지 않은 전화번호입니다.", 3008),


    BAD_REQUEST(false,  "잘못된 요청입니다.", 4000);

    private final boolean isSuccess;
    private final int code;
    private final String message;

    private ResponseTemplateStatus(boolean isSuccess, String message, int code) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
