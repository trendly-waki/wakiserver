package com.example.wakiserver.web.dto;

import com.example.wakiserver.domain.report.Report;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class ReportResponseDto {
    private String username;
    private String mode;
    private Double avgSpeed;
    private Double topSpeed;
    private Timestamp createdTime;

    public ReportResponseDto(Report entity){
        this.username = entity.getUser().getUsername();
        this.mode = entity.getMode();
        this.avgSpeed = entity.getAvgSpeed();
        this.topSpeed = entity.getTopSpeed();
        this.createdTime = entity.getCreateTime();
    }
}
