package com.test.technology.controllers;

import com.test.technology.commons.Output;
import com.test.technology.services.DeptService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(tags = {"DeptController"})
@RestController
@RequestMapping("/dept")
@Slf4j
public class DeptController {

    private final Output output;
    private final DeptService deptService;

    public DeptController(Output output, DeptService deptService) {
        this.output = output;
        this.deptService = deptService;
    }

    @GetMapping("")
    public ResponseEntity<?> getDept(HttpServletRequest request) {
        return output.send(deptService.getDeptList());
    }
}