package com.mileage.infrastructure;

import com.mileage.domain.MileageHistory;
import com.mileage.domain.mileagehistory.MileageHistoryReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MileageHistoryReaderImpl implements MileageHistoryReader {
    private final MileageHistoryRepository pointHistoryRepository;

    @Override
    public List<MileageHistory> searchPointHistoryList(LocalDateTime startDate, LocalDateTime endDate, Long mileageIdx) {
        return pointHistoryRepository.getPointHistoryList(startDate, endDate, mileageIdx);
    }

}
