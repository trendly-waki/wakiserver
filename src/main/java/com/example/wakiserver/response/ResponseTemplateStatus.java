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
    SAME_EMAIL(false, "동일한 이메일이 존재합니다.", 2001),
    NO_USER(false, "없는 사용자입니다.", 2002),
    INVALID_PWD(false,  "비밀번호가 일치하지 않습니다.", 2003),
    NO_LOGIN(false,  "로그인이 필요합니다", 2004),
    NO_ADMIN(false,  "권한이 없는 사용자입니다", 2005),

    BAD_REQUEST(false,  "잘못된 요청입니다.", 3000);

    private final boolean isSuccess;
    private final int code;
    private final String message;

    private ResponseTemplateStatus(boolean isSuccess, String message, int code) { //BaseResponseStatus 에서 각 해당하는 코드를 생성자로 맵핑
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
