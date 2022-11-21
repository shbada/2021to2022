package com.mileage.infrastructure;

import com.mileage.domain.MileageHistory;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static com.mileage.domain.QMileageHistory.mileageHistory;

public class MileageHistoryCustomRepositoryImpl implements MileageHistoryCustomRepository {
    private final JPAQueryFactory queryFactory;

    public MileageHistoryCustomRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<MileageHistory> getPointHistoryList(LocalDateTime startDate, LocalDateTime endDate, Long mileageIdx) {
        List<MileageHistory> result = queryFactory
                .select(mileageHistory)
                .from(mileageHistory)
                .where(mileageIdxEq(mileageIdx), createdAtBetween(startDate, endDate))
                .fetch();

        return result;
    }

    private BooleanExpression mileageIdxEq(Long mileageIdx) {
        if (StringUtils.isEmpty(mileageIdx)) {
            return null;
        }

        return mileageHistory.mileage.idx.eq(mileageIdx);
    }

    private BooleanExpression createdAtBetween(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null && endDate == null) {
            return null;
        }

        if (startDate == null) {
            return mileageHistory.createdAt.before(endDate);
        }

        if (endDate == null) {
            return mileageHistory.createdAt.after(startDate);
        }

        return mileageHistory.createdAt.between(startDate, endDate);
    }
}
