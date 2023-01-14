package com.westssun.designpatterns._02_structural_patterns._09_decorator._02_after;

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
