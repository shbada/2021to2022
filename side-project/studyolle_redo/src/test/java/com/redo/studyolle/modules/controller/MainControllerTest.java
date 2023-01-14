package com.redo.studyolle.modules.controller;

import com.redo.studyolle.infra.MockMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@MockMvcTest
class MainControllerTest {
    @Autowired
    MockMvc mockMvc;
}