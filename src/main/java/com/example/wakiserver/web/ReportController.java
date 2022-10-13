package com.example.wakiserver.web;

import com.example.wakiserver.domain.user.UserRepository;
import com.example.wakiserver.service.ReportService;
import com.example.wakiserver.web.dto.ReportResponseDto;
import com.example.wakiserver.web.dto.ReportSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ReportController {
    private final ReportService reportService;
    private final UserRepository userRepository;

    @PostMapping("/report/{idx}")
    public ResponseEntity<Long> saveReport(@PathVariable Long idx, @RequestBody ReportSaveRequestDto requestDto){
        Long userIdx = userRepository.findById(idx).get().getId();
        Long result = reportService.saveReport(userIdx, requestDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/report/{reportIdx}")
    public ResponseEntity<ReportResponseDto> loadReport(@PathVariable Long reportIdx){
        return new ResponseEntity<>(reportService.loadReport(reportIdx), HttpStatus.OK);
    }
}
