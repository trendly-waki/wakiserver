package com.example.wakiserver.service;

import com.example.wakiserver.domain.version.Version;
import com.example.wakiserver.domain.version.VersionRepository;
import com.example.wakiserver.web.dto.VersionResponseDto;
import com.example.wakiserver.web.dto.VersionSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class VersionService {
    private final VersionRepository versionsRepository;

    @Transactional
    public Version save(VersionSaveRequestDto requestDto) throws ResponseStatusException {
        try{
            Version versions = Version.builder()
                    .currentVersion(requestDto.getCurrentVersion())
                    .isForcedCheck(requestDto.getIsForcedCheck())
                    .isCheck(requestDto.getIsCheck())
                    .build();
            Long curIdx = versionsRepository.save(versions).getVersionIdx();
            return versionsRepository.findById(curIdx)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        } catch (ResponseStatusException e){
            throw new ResponseStatusException(e.getStatus());
        }
    }

    @Transactional
    public VersionResponseDto load() throws ResponseStatusException {
        try {
            List<Version> entity = versionsRepository.findAll(Sort.by(Sort.Direction.DESC, "versionIdx"));
            Version curVersion = entity.get(0);
            VersionResponseDto versionsResponseDto = new VersionResponseDto(entity.get(0));
            return versionsResponseDto;
        } catch (ResponseStatusException e){
            throw new ResponseStatusException(e.getStatus());
        }
    }
}
