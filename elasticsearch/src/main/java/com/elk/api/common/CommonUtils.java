package com.elk.api.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CommonUtils {
    /**
     * json String to List
     * @param jsonString
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonToList(String jsonString) {
        List<T> resultList = new ArrayList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            resultList = objectMapper.readValue(jsonString, new TypeReference<List<T>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            log.error("json to List Error");
        }

        return resultList;
    }

    /**
     * resources/body 경로의 파일 내용 반환
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String getFileJsonParam(String fileName) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("body/" + fileName);
        InputStream inputStream = classPathResource.getInputStream();

        return IOUtils.toString(inputStream);
    }
}
