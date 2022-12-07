package com.designpattern.report._20_state.step1_before;

import java.util.ArrayList;
import java.util.List;

public class OnlineCourse {

    public enum State {
        DRAFT, PUBLISHED, PRIVATE
    }

    private State state = State.DRAFT;

    private List<String> reviews = new ArrayList<>();

    private List<Student> students = new ArrayList<>();

    /**
     * 상태값에 따라 특정 로직(operation) 다르게 동작
     * -> 이런 경우 상태 패턴을 적용하면 좋다.
     *
     * Context (OnlineCouse.java 처럼 상태에 따라 달라지는 행위들을 원래 가지고있는 클래스)
     * State (Context 가 변경될 수 있는 여러 상태들에 대한 공통 인터페이스를 만들어놓은 것, 존재 필수)
     *
     * Context 가 State 에 상태 변경 operation 을 위임하고 이걸 concreteState 에서 수행
     */
    public void addReview(String review, Student student) {
        if (this.state == State.PUBLISHED) {
            this.reviews.add(review);
        } else if (this.state == State.PRIVATE && this.students.contains(student)) {
            // PRIVATE 상태일때는 해당 강의를 듣고있는 학생만 리뷰를 쓸 수 있다.
            this.reviews.add(review);
        } else {
            throw new UnsupportedOperationException("리뷰를 작성할 수 없습니다.");
        }
    }

    public void addStudent(Student student) {
        if (this.state == State.DRAFT || this.state == State.PUBLISHED) {
            this.students.add(student);
        } else if (this.state == State.PRIVATE && availableTo(student)) {
            this.students.add(student);
        } else {
            throw new UnsupportedOperationException("학생을 해당 수업에 추가할 수 없습니다.");
        }

        if (this.students.size() > 1) { // 2명 이상이면 PRIVATE 상태가 된다.
            this.state = State.PRIVATE;
        }
    }

    public void changeState(State newState) {
        this.state = newState;
    }

    public State getState() {
        return state;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public List<Student> getStudents() {
        return students;
    }

    private boolean availableTo(Student student) {
        return student.isEnabledForPrivateClass(this);
    }


}
