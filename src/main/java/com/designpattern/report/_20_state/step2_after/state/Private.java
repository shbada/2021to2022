package com.designpattern.report._20_state.step2_after.state;

import com.designpattern.report._20_state.step2_after.OnlineCourse;
import com.designpattern.report._20_state.step2_after.Student;

public class Private implements State {

    private OnlineCourse onlineCourse;

    public Private(OnlineCourse onlineCourse) {
        this.onlineCourse = onlineCourse;
    }

    @Override
    public void addReview(String review, Student student) {
        if (this.onlineCourse.getStudents().contains(student)) {
            // . (dot)이 2번 넘게 사용되었으면,, 리팩토링 필요한가를 감지할 수 있다.
            // 이는 메서드 하나로 리팩토링 할수도 있다. 명시적인 이름으로.
            this.onlineCourse.getReviews().add(review);
        } else {
            throw new UnsupportedOperationException("프라이빗 코스를 수강하는 학생만 리뷰를 남길 수 있습니다.");
        }
    }

    @Override
    public void addStudent(Student student) {
        if (student.isAvailable(this.onlineCourse)) { // 수강 가능해야지만
            this.onlineCourse.getStudents().add(student);
        } else {
            throw new UnsupportedOperationException("프라이빛 코스를 수강할 수 없습니다.");
        }
    }
}
