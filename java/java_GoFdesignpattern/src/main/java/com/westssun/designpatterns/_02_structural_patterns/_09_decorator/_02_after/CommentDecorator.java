package com.westssun.designpatterns._02_structural_patterns._09_decorator._02_after;

/**
 * Decorator
 * Decorator 가 Component 를 감싸고있다.
 */
public class CommentDecorator implements CommentService {

    private CommentService commentService;

    public CommentDecorator(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public void addComment(String comment) {
        commentService.addComment(comment);
    }
}
