package com.designpattern.report._09_decorator.step2_after.client;

import com.designpattern.report._09_decorator.step2_after.component.CommentService;

public class Client {
    private CommentService commentService;

    public Client(CommentService commentService) {
        this.commentService = commentService;
    }

    public void writeComment(String comment) {
        commentService.addComment(comment);
    }
}
