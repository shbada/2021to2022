package com.mileage.domain.mileage;

import com.mileage.domain.Mileage;

import java.util.List;
import java.util.Optional;

public interface MileageReader {
    Optional<Mileage> findById(Long mileageIdx);

    List<Mileage> findAll();
}
