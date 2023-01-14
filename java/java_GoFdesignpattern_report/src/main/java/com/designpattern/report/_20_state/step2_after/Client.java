package com.designpattern.report._20_state.step2_after;

import com.designpattern.report._20_state.step2_after.state.Private;

/**
 * 상태패턴
 * [장점]
 * - 각각 개별적으로 관리할 수 있다.
 * - 클래스는 늘어나지만, 상태패턴이 적용되어야하는 복잡한 경우에 단위별로 관리/테스트할 수 있어서 편리함
 * - OnlineCourse 입장에서 의존성을 낮출 수 있다.
 *
 * [단점]
 * - 상태를 나누는것 자체가 불필요함에도 나누는 경우가 있다면 그땐 좀 과도한 엔지니어링
 */
public class Client {

    public static void main(String[] args) {
        OnlineCourse onlineCourse = new OnlineCourse();
        Student student = new Student("whiteship");
        Student keesun = new Student("keesun");
        keesun.addPrivate(onlineCourse);

        onlineCourse.addStudent(student);

        // STATE 변경 (draft -> private)
        onlineCourse.changeState(new Private(onlineCourse));

        onlineCourse.addReview("hello", student);

        onlineCourse.addStudent(keesun); // 들어가려면 keesun.addPrivate(onlineCourse); 필요

        // addStudent 다음으로
        onlineCourse.addReview("hello keesun", keesun);

        System.out.println(onlineCourse.getState()); // Private
        System.out.println(onlineCourse.getReviews()); // hello
        System.out.println(onlineCourse.getStudents()); // [Student{name='whiteship'}, Student{name='keesun'}]
    }
}
