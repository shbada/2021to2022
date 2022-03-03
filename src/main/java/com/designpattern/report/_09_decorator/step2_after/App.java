package com.designpattern.report._09_decorator.step2_after;

import com.designpattern.report._09_decorator.step2_after.client.Client;
import com.designpattern.report._09_decorator.step2_after.component.CommentService;
import com.designpattern.report._09_decorator.step2_after.concreteCompoenent.DefaultCommentService;
import com.designpattern.report._09_decorator.step2_after.concreteDecorator.SpamFilteringCommentDecorator;
import com.designpattern.report._09_decorator.step2_after.concreteDecorator.TrimmingCommentDecorator;

/**
 * 데코레이터 패턴
 * 기존 코드를 변경하지 않고 부가 기능을 추가하는 패턴
 *
 * 상속이 아닌 위임을 사용해서 보다 유연하게 런타임에 부가기능을 추가하는 것도 가능하다.
 *
 * [장점]
 * - 새로운 클래스를 만들지 않고 기존 기능을 조합할 수 있다.
 * - 컴파일 타임이 아닌 런타임에 동적으로 기능을 변경할 수 있다.
 *
 * [단점]
 * - 데코레이터를 조합하는 코드가 복잡할 수 있다.
 */
public class App {

    private static boolean enabledSpamFilter = true;

    private static boolean enabledTrimming = true;

    public static void main(String[] args) {
        // commentService 는 동적으로 바뀔 수 있다.
        // 아래 필드 값에 따라서 바뀐다.
        CommentService commentService = new DefaultCommentService();

        // 둘다 수행해야할때 굳이 새로운 클래스를 생성하지 않아도 된다.
        if (enabledSpamFilter) {
            commentService = new SpamFilteringCommentDecorator(commentService);
        }

        if (enabledTrimming) {
            commentService = new TrimmingCommentDecorator(commentService);
        }

        /*
        TrimmingCommentDecorator.addComment
        SpamFilteringCommentDecorator.addComment
        DefaultCommentService.addComment

        오징어게임

        TrimmingCommentDecorator.addComment
        SpamFilteringCommentDecorator.addComment
        DefaultCommentService.addComment

        보는게 하는거 보다 재밌을 수가 없지

        TrimmingCommentDecorator.addComment
         */
        Client client = new Client(commentService);
        client.writeComment("오징어게임");
        client.writeComment("보는게 하는거 보다 재밌을 수가 없지...");
        client.writeComment("http://whiteship.me");
    }
}
