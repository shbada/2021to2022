package com.mileage.infrastructure;

import com.mileage.domain.Mileage;
import com.mileage.domain.mileage.MileageReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MileageReaderImpl implements MileageReader {
    private final MileageRepository mileageRepository;

    @Override
    public Optional<Mileage> findById(Long mileageIdx) {
        return mileageRepository.findById(mileageIdx);
    }
}
