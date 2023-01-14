package com.studyolle.modules.zone.service;

import com.studyolle.entity.Zone;
import com.studyolle.modules.zone.repository.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ZoneService {
    private final ZoneRepository zoneRepository;

    /**
     * Zone 초기 데이터 적재
     * @throws IOException
     */
    @EventListener(ApplicationReadyEvent.class)
    public void initZoneData() throws IOException {
        /* 적재된 데이터가 없는 경우에만 수행 */
        if (zoneRepository.count() == 0) {
            Resource resource = new ClassPathResource("zones_kr.csv");

            List<Zone> zoneList = Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8).stream()
                    .map(line -> {
                        String[] split = line.split(","); // , 구분으로 배열화
                        return Zone.builder().city(split[0]).localNameOfCity(split[1]).province(split[2]).build();
                    }).collect(Collectors.toList());

            // 리스트 전체 저장
            zoneRepository.saveAll(zoneList);
        }
    }
}
