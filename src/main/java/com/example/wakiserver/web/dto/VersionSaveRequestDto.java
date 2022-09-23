package com.example.wakiserver.web.dto;

import com.example.wakiserver.domain.version.Version;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VersionSaveRequestDto {
    private String currentVersion;
    private String isForcedCheck;
    private String isCheck;

    @Builder
    public Version toEntity(){
        return Version.builder()
                .currentVersion(currentVersion)
                .isForcedCheck(isForcedCheck)
                .isCheck(isCheck)
                .build();
    }
}
