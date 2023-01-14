package com.designpattern.report._20_state.step2_after.state;

import com.designpattern.report._20_state.step2_after.OnlineCourse;
import com.designpattern.report._20_state.step2_after.Student;

public class Published implements State {

    private OnlineCourse onlineCourse;

    public Published(OnlineCourse onlineCourse) {
        this.onlineCourse = onlineCourse;
    }

    @Override
    public void addReview(String review, Student student) {
        this.onlineCourse.getReviews().add(review);
    }

    @Override
    public void addStudent(Student student) {
        this.onlineCourse.getStudents().add(student);
    }
}
