package com.study.batch.entity.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BatchStepExecutionDto {
    private Long stepExecutionId;
    private Long version;
    private String stepName;
    private Long jobExecutionId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime  startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime endTime;
    private String status;
    private Long commitCount;
    private Long readCount;
    private Long filterCount;
    private Long writeCount;
    private Long readSkipCount;
    private Long writeSkipCount;
    private Long processSkipCount;
    private Long rollbackCount;
    private String exitCode;
    private String exitMessage;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime lastUpdated;
}
