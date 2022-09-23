package com.example.wakiserver.domain.version;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Version {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long versionIdx;

    @Column
    private String currentVersion;

    @Column
    private String isForcedCheck;

    @Column
    private String isCheck;


    @Builder
    public Version(String currentVersion, String isForcedCheck, String isCheck) {
        this.currentVersion = currentVersion;
        this.isForcedCheck = isForcedCheck;
        this.isCheck = isCheck;
    }
}
