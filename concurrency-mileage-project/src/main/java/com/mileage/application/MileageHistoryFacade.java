package com.mileage.application;

import com.mileage.domain.MileageHistory;
import com.mileage.domain.mileagehistory.MileageHistoryCommand;
import com.mileage.domain.mileagehistory.MileageHistorySearchCommand;
import com.mileage.domain.mileagehistory.MileageHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MileageHistoryFacade {
    private final MileageHistoryService mileageHistoryService;

    @Transactional
    public List<MileageHistoryCommand.Info> getPointHistoryList(MileageHistorySearchCommand.SearchPointHistory pointHistorySearchCommand) {
        /* search */
        List<MileageHistory> mileageHistoryList = mileageHistoryService.getPointHistoryList(pointHistorySearchCommand);

        return mileageHistoryList.stream()
                .map(MileageHistory::toCommand)
                .collect(Collectors.toList());
    }
}
