package com.designpattern.report._20_state.step2_after.state;

import com.designpattern.report._20_state.step2_after.Student;

public interface State {

    void addReview(String review, Student student);

    void addStudent(Student student);
}
