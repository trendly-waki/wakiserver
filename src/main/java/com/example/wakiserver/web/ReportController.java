package com.example.wakiserver.web;

import com.example.wakiserver.domain.user.UserRepository;
import com.example.wakiserver.response.ResponseException;
import com.example.wakiserver.response.ResponseTemplate;
import com.example.wakiserver.service.ReportService;
import com.example.wakiserver.service.UserService;
import com.example.wakiserver.web.dto.ReportResponseDto;
import com.example.wakiserver.web.dto.ReportSaveRequestDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ReportController {
    private final ReportService reportService;
    private final UserService userService;

    @ApiOperation(value = "결과레포트 저장", notes="결과 레포트를 저장합니다. JWT 입력 필수! 반환값(data) : reportIdx")
    @PostMapping("/report")
    public ResponseTemplate<Long> saveReport(@RequestBody ReportSaveRequestDto requestDto) throws ResponseException {
        String email = userService.getUserEmail();
        Long result = reportService.saveReport(email, requestDto);
        return new ResponseTemplate<>(result);
    }

    @ApiOperation(value="결과레포트 불러오기", notes="결과레포트를 불러옵니다. JWT 입력 필수!")
    @ApiImplicitParam(name="reportIdx", value="결과레포트 식별자")
    @GetMapping("/report/{reportIdx}")
    public ResponseEntity<ReportResponseDto> loadReport(@PathVariable Long reportIdx){
        return new ResponseEntity<>(reportService.loadReport(reportIdx), HttpStatus.OK);
    }
}
