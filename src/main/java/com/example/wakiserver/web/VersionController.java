package com.example.wakiserver.web;

import com.example.wakiserver.domain.version.Version;
import com.example.wakiserver.service.VersionService;
import com.example.wakiserver.web.dto.VersionResponseDto;
import com.example.wakiserver.web.dto.VersionSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VersionController {
    private final VersionService versionsService;

    @PostMapping("/version")
    public ResponseEntity<Version> save(@RequestBody VersionSaveRequestDto currentVersion){
        Version version = versionsService.save(currentVersion);
        return new ResponseEntity<>(version, HttpStatus.OK);
    }

    @GetMapping("/version")
    public ResponseEntity<VersionResponseDto> load(){
        VersionResponseDto versionResponseDto = versionsService.load();
        return new ResponseEntity<>(versionResponseDto,HttpStatus.OK);
    }

}
