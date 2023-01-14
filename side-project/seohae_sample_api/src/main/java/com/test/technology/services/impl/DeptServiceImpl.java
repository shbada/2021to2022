package com.test.technology.services.impl;

import com.test.technology.entity.DeptEntity;
import com.test.technology.repository.DeptRepository;
import com.test.technology.services.DeptService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    private final DeptRepository deptRepository;

    public DeptServiceImpl(DeptRepository deptRepository) {
        this.deptRepository = deptRepository;
    }

    @Override
    public List<DeptEntity> getDeptList() {
        return deptRepository.findAll();
    }
}
