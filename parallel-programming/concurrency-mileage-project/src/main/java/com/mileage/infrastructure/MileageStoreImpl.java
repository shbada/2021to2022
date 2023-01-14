package com.mileage.infrastructure;

import com.mileage.domain.mileage.MileageStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MileageStoreImpl implements MileageStore {
    private final MileageRepository mileageRepository;

}
