package com.designpattern.report._09_decorator.step1_before;

public class Client {

    private CommentService commentService;

    public Client(CommentService commentService) {
        this.commentService = commentService;
    }

    private void writeComment(String comment) {
        commentService.addComment(comment);
    }

    public static void main(String[] args) {
        // SpamFilteringCommentService
        // 컴파일타임에 구현클래스가 지정됨
        // 만약에 다른 필터링을 사용해야 한다면..?
        // 계속해서 CommentService 를 상속하는 자식클래스가 생성되어야한다.
        // TrimmingCommentService 랑 SpamFilteringCommentService 를 둘다 하려면??
        // 계속 확장해나가기가 너무 불편함
        Client client = new Client(new SpamFilteringCommentService());
        client.writeComment("오징어게임");
        client.writeComment("보는게 하는거 보다 재밌을 수가 없지...");
        client.writeComment("http://whiteship.me");
    }

}
