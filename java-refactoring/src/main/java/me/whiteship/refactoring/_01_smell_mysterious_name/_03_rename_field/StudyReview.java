package me.whiteship.refactoring._01_smell_mysterious_name._03_rename_field;

/**
 * record 키워드
 *
 * 생성자를 만들어주고, getter 역할 메서드를 생성해주고, equals, toString 모두 자동으로 만들어준다.
 */
public record StudyReview(String reviewer, String review) {
    // 메서드 생성 가능
}
