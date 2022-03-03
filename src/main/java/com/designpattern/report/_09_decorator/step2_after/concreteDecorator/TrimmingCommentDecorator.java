package com.designpattern.report._09_decorator.step2_after.concreteDecorator;

import com.designpattern.report._09_decorator.step2_after.component.CommentService;
import com.designpattern.report._09_decorator.step2_after.decorator.CommentDecorator;

public class TrimmingCommentDecorator extends CommentDecorator {

    public TrimmingCommentDecorator(CommentService commentService) {
        super(commentService);
    }

    @Override
    public void addComment(String comment) {
        System.out.println("TrimmingCommentDecorator.addComment");
        super.addComment(trim(comment));
    }

    private String trim(String comment) {
        return comment.replace("...", "");
    }
}
