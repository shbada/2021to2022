package com.batch.order.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Enumerated(EnumType.STRING)
    private Level level = Level.NORMAL;

    /* user 가 저장되면서 order 도 같이 저장하도록 PERSIST 로 지정 */
    @OneToMany(cascade = CascadeType.PERSIST) /* 1 : N 관계로 매핑 */
    @JoinColumn(name = "user_id")
    private List<Orders> orders;

    private LocalDate updatedDate;

    @Builder /* 아래 생성자 기준으로 빌더 객체 제공받음 */
    public User(String username, List<Orders> orders) {
        this.username = username;
        this.orders = orders;
    }

    /**
     * 레벨업 대상 유저 여부 체크
     * @return
     */
    public boolean availableLevelUp() {
        return Level.availableLevelUp(this.getLevel(), this.getTotalAmount());
    }

    /**
     * total amount 조회
     * @return
     */
    private int getTotalAmount() {
        return this.orders.stream()
                .mapToInt(Orders::getAmount)
                .sum();
    }

    /**
     * 레벨업
     * @return
     */
    public Level levelUp() {
        Level nextLevel = Level.getNextLevel(this.getTotalAmount());

        this.level = nextLevel;
        this.updatedDate = LocalDate.now();

        return nextLevel;
    }

    public enum Level {
        VIP(500_000, null),
        GOLD(500_000, VIP),
        SILVER(300_000, GOLD),
        NORMAL(200_000, SILVER);

        private final int nextAmount;
        private final Level nextLevel;

        Level(int nextAmount, Level nextLevel) {
            this.nextAmount = nextAmount;
            this.nextLevel = nextLevel;
        }

        /**
         * 레벨업 여부 체크
         * @param level
         * @param totalAmount
         * @return
         */
        private static boolean availableLevelUp(Level level, int totalAmount) {
            if (Objects.isNull(level)) { /* level 이 비어져있으면 대상X */
                return false;
            }

            if (Objects.isNull(level.nextLevel)) { /* nextLevel 이 비어져있으면 대상X */
                return false;
            }

            return totalAmount >= level.nextAmount;
        }

        /**
         * 다음 레벨 조회
         * @param totalAmount
         * @return
         */
        private static Level getNextLevel(int totalAmount) {
            if (totalAmount >= Level.VIP.nextAmount) {
                return VIP; // 최대 레벨
            }

            if (totalAmount >= Level.GOLD.nextAmount) {
                return GOLD.nextLevel; // GOLD 다음 레벨
            }

            if (totalAmount >= Level.SILVER.nextAmount) {
                return SILVER.nextLevel; // SILVER 다음 레벨
            }

            if (totalAmount >= Level.NORMAL.nextAmount) {
                return NORMAL.nextLevel; // NORMAL 다음 레벨
            }

            return NORMAL;
        }
    }
}
