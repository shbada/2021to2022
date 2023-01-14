package com.redo.studyolle.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

// spring-boot-configuration-processor maven add
@Data
@Component
@ConfigurationProperties("app")
public class AppProperties {

    private String host;

}
