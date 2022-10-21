package com.example.wakiserver.web.dto;

import com.example.wakiserver.domain.report.Report;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportSaveRequestDto {
    private String mode;
    private Double avgSpeed;
    private Double topSpeed;

    @Builder
    public Report toEntity(){
        return Report.builder()
                .mode(mode)
                .avgSpeed(avgSpeed)
                .topSpeed(topSpeed)
                .build();
    }
}
