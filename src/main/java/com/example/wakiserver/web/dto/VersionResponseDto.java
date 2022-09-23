package com.example.wakiserver.web.dto;

import com.example.wakiserver.domain.version.Version;
import lombok.Getter;
@Getter
public class VersionResponseDto {
    private Long versionIdx;
    private String currentVersion;
    private String isForcedCheck;
    private String isCheck;

    public VersionResponseDto(Version entity){
        this.versionIdx = entity.getVersionIdx();
        this.currentVersion = entity.getCurrentVersion();
        this.isForcedCheck = entity.getIsForcedCheck();
        this.isCheck = entity.getIsCheck();
    }
}
