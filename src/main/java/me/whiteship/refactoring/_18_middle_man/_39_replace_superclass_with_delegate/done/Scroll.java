package me.whiteship.refactoring._18_middle_man._39_replace_superclass_with_delegate.done;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * 주기적으로 청소해줘야하는 Scroll
 * 특정한 하나의 아이템
 * 카테고리는 아님. -> 상속 구조를 유지하긴 힘들어보인다.
 */
public class Scroll { // 상속 제거

    private LocalDate dateLastCleaned;
    private CategoryItem categoryItem; // 상위 타입을 필드로 선언

    public Scroll(Integer id, String title, List<String> tags, LocalDate dateLastCleaned) {
//        super(id, title, tags);
        this.dateLastCleaned = dateLastCleaned;
        // 상속 구조를 끊으면서 이 필드를 바라보면된다.
        this.categoryItem = new CategoryItem(id, title, tags);
    }

    public long daysSinceLastCleaning(LocalDate targetDate) {
        return this.dateLastCleaned.until(targetDate, ChronoUnit.DAYS);
    }
}
