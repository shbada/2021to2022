package com.designpattern.report._20_state.step2_after;

import com.designpattern.report._20_state.step2_after.state.Draft;
import com.designpattern.report._20_state.step2_after.state.State;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OnlineCourse {

    // State
    private State state = new Draft(this); // 별도 메서드로 두어, 의존성을 낮추는 방법도 있다.

    private List<Student> students = new ArrayList<>();

    private List<String> reviews = new ArrayList<>();

    public void addStudent(Student student) {
        this.state.addStudent(student);
    }

    public void addReview(String review, Student student) {
        this.state.addReview(review, student);
    }

    public void changeState(State state) {
        this.state = state;
    }
}
