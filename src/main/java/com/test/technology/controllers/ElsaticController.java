package com.test.technology.controllers;

import com.test.technology.commons.HttpConnection;
import com.test.technology.commons.Output;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(tags = {"Elasticontroller"})
@Slf4j
@RestController
@RequestMapping("/elastic")
public class ElsaticController {
    @Value("${elastic.host}")
    private String host;

    @Value("${elastic.port}")
    private int port;

    private final Output output;
    private final HttpConnection httpConnection;

    public ElsaticController(Output output, HttpConnection httpConnection) {
        this.output = output;
        this.httpConnection = httpConnection;
    }

    @PutMapping("/index")
    public ResponseEntity<?> addElasticIndex(HttpServletRequest request) {
        // url
        String indexName = "test";
        String url = "http://" + host + ":" + port + "/" + indexName;

        // body
        String jsonParam = "{\n" +
                "    \"settings\" : {\n" +
                "        \"number_of_shards\" : 1\n" +
                "    },\n" +
                "    \"mappings\" : {\n" +
                "        \"properties\" : {\n" +
                "            \"test\" : { \"type\" : \"text\" }\n" +
                "        }\n" +
                "    }\n" +
                "}";

        log.info(jsonParam);
        httpConnection.syncPut(url, jsonParam);


        return output.send("success");
    }

    @PostMapping("/index")
    public ResponseEntity<?> addData(HttpServletRequest request, @RequestParam String indexName) {
        String type = "test";
        int id = 1;
        // url
        String url = "http://" + host + ":" + port + "/" + indexName + type + id;

        // body
        String jsonParam = "{ \"test\" : \"seohae\" }";

        log.info(jsonParam);
        httpConnection.syncPut(url, jsonParam);

        return output.send("success");
    }
}
