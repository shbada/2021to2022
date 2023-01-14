package com.mileage.domain.mileagehistory;

import com.mileage.common.util.DateUtil;
import com.mileage.domain.Mileage;
import com.mileage.domain.MileageHistory;
import com.mileage.domain.enums.MileageManageType;
import com.mileage.domain.mileage.MileageCommand;
import com.mileage.infrastructure.MileageHistoryRepository;
import com.mileage.infrastructure.MileageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MileageHistoryServiceTest {
    @Autowired
    private MileageHistoryService mileageHistoryService;

    @Autowired
    private MileageRepository mileageRepository;

    @Autowired
    private MileageHistoryRepository pointHistoryRepository;

    @Test
    void 포인트_내역을_저장한다() {
        // given
        int point = 20000;

        /* 포인트 등록 */
        Mileage mileage = createMileage(0);

        MileageCommand.SavePoint savePoint = new MileageCommand.SavePoint();
        savePoint.setPoint(point);

        // when
        Long idx = mileageHistoryService.savePointHistory(savePoint, mileage);

        // then
        Optional<MileageHistory> byId = pointHistoryRepository.findById(idx);
        assertThat(byId.isPresent()).isTrue();
        assertThat(byId.get().getPoint()).isEqualTo(point);
    }

    @Test
    void 포인트_내역을_검색한다() {
        // given
        /* 포인트 등록 */
        Mileage mileage = createMileage(0);

        MileageHistoryCommand.RegisterPointHistory registerPointHistory = new MileageHistoryCommand.RegisterPointHistory();
        registerPointHistory.setMileageIdx(mileage.getIdx());
        registerPointHistory.setPoint(10000);
        registerPointHistory.setMileageManageType(MileageManageType.CHARGE);

        MileageHistory mileageHistory = new MileageHistory();
        mileageHistory.createPointHistory(registerPointHistory, mileage);

        MileageHistory newMileageHistory = pointHistoryRepository.save(mileageHistory);

        String paramDate = DateUtil.dateToString(LocalDate.now());

        MileageHistorySearchCommand.SearchPointHistory searchPointHistory = new MileageHistorySearchCommand.SearchPointHistory();
        searchPointHistory.setStartDate(paramDate);
        searchPointHistory.setEndDate(paramDate);
        searchPointHistory.setMileageIdx(mileage.getIdx());

        // when
        List<MileageHistory> mileageHistoryList = mileageHistoryService.getPointHistoryList(searchPointHistory);

        // then
        Optional<MileageHistory> first = mileageHistoryList.stream()
                .filter(info -> mileage.getIdx().equals(info.getMileage().getIdx()))
                .findFirst();

        assertThat(first.isPresent()).isTrue();
    }

    /**
     * Mileage 생성
     */
    private Mileage createMileage(int chargePoint) {
        Mileage newMileage = new Mileage();
        newMileage.charge(chargePoint);

        return mileageRepository.save(newMileage);
    }
}