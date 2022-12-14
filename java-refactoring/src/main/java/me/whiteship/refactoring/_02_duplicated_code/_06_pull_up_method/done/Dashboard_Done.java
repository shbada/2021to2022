package me.whiteship.refactoring._02_duplicated_code._06_pull_up_method.done;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 리팩토링 6. 메서드 올리기 (Pull up Method)
 * - 중복 코드는 당장은 잘 동작하더라도 미래에 버그를 만들어낼 빌미를 제공한다.
 * - 여러 하위 클래스에 동일한 코드가 있다면 손쉽게 이 방법을 적용할 수 있다. (상위 클래스로 올리기)
 * - 비슷하지만 일부 값만 다른 경우라면, "함수 매개변수화하기" 리팩토리을 적용한 이후에, 이 방법을 사용할 수 있다.
 * - 하위 클래스에 있는 코드가 사위 클래스가 아닌 하위 클래스 기능에 의존하고있다면 "필드 올리기"를 적용한 이후에
 *   이 방법을 적용할 수 있다.
 * - 두 메서드가 비슷한 절차를 따르고 있다면, "템플릿 메서드 패턴"을 고려할 수 있다.
 */
public class Dashboard_Done {

    public static void main(String[] args) throws IOException {
        ReviewerDashboard_Done reviewerDashboard = new ReviewerDashboard_Done();
        reviewerDashboard.printReviewers();

//        ParticipantDashboard_Done participantDashboard = new ParticipantDashboard_Done();
        printUsernames(15);
    }

    /**
     * 하위클래스 -> 상위클래스로 메서드 올리기
     * - 단축키 : 메서드명 > Refactor > Pull Members Up (상위 클래스로 올리기)
     */
    public static void printUsernames(int eventId) throws IOException {
        // Get github issue to check homework
        GitHub gitHub = GitHub.connect();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        GHIssue issue = repository.getIssue(eventId);

        // Get participants
        Set<String> participants = new HashSet<>();
        issue.getComments().forEach(c -> participants.add(c.getUserName()));

        // Print participants
        participants.forEach(System.out::println);
    }
}
