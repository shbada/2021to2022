package com.mileage.domain.mileagehistory;

import com.mileage.common.util.DateUtil;
import com.mileage.domain.Mileage;
import com.mileage.domain.MileageHistory;
import com.mileage.domain.mileage.MileageCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MileageHistoryService {
    private final MileageHistoryStore mileageHistoryStore;
    private final MileageHistoryReader mileageHistoryReader;

    /**
     * 포인트 내역 저장
     * @param savePoint
     */
    public Long savePointHistory(MileageCommand.SavePoint savePoint, Mileage getMileage) {
        MileageHistoryCommand.RegisterPointHistory registerPointHistory = new MileageHistoryCommand.RegisterPointHistory();
        registerPointHistory.setPoint(savePoint.getPoint());
        registerPointHistory.setMileageManageType(savePoint.getMileageManageType());

        MileageHistory mileageHistory = new MileageHistory();
        mileageHistory.createPointHistory(registerPointHistory, getMileage);

        return mileageHistoryStore.save(mileageHistory).getIdx();
    }

    /**
     * 포인트 내역 검사
     * @param pointHistorySearchCommand
     * @return
     */
    public List<MileageHistory> getPointHistoryList(MileageHistorySearchCommand.SearchPointHistory pointHistorySearchCommand) {
        LocalDateTime startDateTime = null;
        LocalDateTime endDateTime = null;

        if (!StringUtils.isEmpty(pointHistorySearchCommand.getStartDate())) {
            LocalDate startDate = DateUtil.stringToDate(pointHistorySearchCommand.getStartDate());
            startDateTime = LocalDateTime.of(startDate, LocalDateTime.MIN.toLocalTime());
        }

        if (!StringUtils.isEmpty(pointHistorySearchCommand.getEndDate())) {
            LocalDate endDate = DateUtil.stringToDate(pointHistorySearchCommand.getEndDate());
            endDateTime = LocalDateTime.of(endDate, LocalDateTime.MAX.toLocalTime());
        }

        return mileageHistoryReader.searchPointHistoryList(startDateTime, endDateTime, pointHistorySearchCommand.getMileageIdx());
    }
}
