package com.designpattern.report._09_decorator.step2_after.concreteCompoenent;

import com.designpattern.report._09_decorator.step2_after.component.CommentService;

/**
 * Concrete Component
 */
public class DefaultCommentService implements CommentService {
    @Override
    public void addComment(String comment) {
        System.out.println("DefaultCommentService.addComment");
        System.out.println(comment);
    }
}
