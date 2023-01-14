package com.api.plan.api.controller;

import com.api.plan.api.repository.CategoryRepository;
import com.api.plan.api.service.CategoryService;
import com.api.plan.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/category")
public class CategoryController {
    private final CommonResponse commonResponse;
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;
}
