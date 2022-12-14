package com.example.wakiserver.service;

import com.example.wakiserver.domain.report.Report;
import com.example.wakiserver.domain.report.ReportRepository;
import com.example.wakiserver.domain.user.User;
import com.example.wakiserver.domain.user.UserRepository;
import com.example.wakiserver.response.ResponseException;
import com.example.wakiserver.response.ResponseTemplateStatus;
import com.example.wakiserver.web.dto.ReportResponseDto;
import com.example.wakiserver.web.dto.ReportSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final UserRepository userRepository;
    private final ReportRepository reportRepository;

    public Long saveReport(String email, ReportSaveRequestDto requestDto) throws ResponseException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new ResponseException(ResponseTemplateStatus.NO_USER));
        Report report = Report.builder()
                .user(user)
                .mode(requestDto.getMode())
                .topSpeed(requestDto.getTopSpeed())
                .avgSpeed(requestDto.getAvgSpeed())
                .build();
        return reportRepository.save(report).getReportIdx();
    }

    public ReportResponseDto loadReport(Long reportIdx){
        Report report = reportRepository.findById(reportIdx)
                .orElseThrow(() -> new NoSuchElementException());
        ReportResponseDto responseDto = new ReportResponseDto(report);
        return responseDto;
    }
}
