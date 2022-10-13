package com.example.wakiserver.web.dto;

import com.example.wakiserver.domain.report.Report;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportSaveRequestDto {
    //private Time walkTime;
    private String mode;
    private Double avgSpeed;
    private Double topSpeed;

    @Builder
    public Report toEntity(){
        return Report.builder()
                //.walkTime(walkTime)
                .mode(mode)
                .avgSpeed(avgSpeed)
                .topSpeed(topSpeed)
                .build();
    }
}
