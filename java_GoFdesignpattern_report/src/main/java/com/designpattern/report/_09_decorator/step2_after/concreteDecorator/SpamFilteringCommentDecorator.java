package com.designpattern.report._09_decorator.step2_after.concreteDecorator;

import com.designpattern.report._09_decorator.step2_after.component.CommentService;
import com.designpattern.report._09_decorator.step2_after.decorator.CommentDecorator;

public class SpamFilteringCommentDecorator extends CommentDecorator {
    public SpamFilteringCommentDecorator(CommentService commentService) {
        super(commentService);
    }

    @Override
    public void addComment(String comment) {
        System.out.println("SpamFilteringCommentDecorator.addComment");
        if (isNotSpam(comment)) {
            super.addComment(comment);
        }
    }

    private boolean isNotSpam(String comment) {
        return !comment.contains("http");
    }
}
