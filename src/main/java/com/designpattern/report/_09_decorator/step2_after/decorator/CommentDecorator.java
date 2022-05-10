package com.designpattern.report._09_decorator.step2_after.decorator;

import com.designpattern.report._09_decorator.step2_after.component.CommentService;

/**
 * Decorator
 * Decorator 가 Component 를 감싸고있다.
 */
public class CommentDecorator implements CommentService {
    private final CommentService commentService; // Component

    public CommentDecorator(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public void addComment(String comment) {
        commentService.addComment(comment);
    }
}
