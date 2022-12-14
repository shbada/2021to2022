package me.whiteship.refactoring._02_duplicated_code._05_slide_statements;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 *  리팩토링 5. 코드 정리하기 (Slide Statements)
 *  - 관련있는 코드끼리 묶여있어야 코드를 더 쉽게 이해할 수 있다.
 *  > 라인 조정으로 동일한 코드로 만들고 함수로 추출할 수도 있다.
 *  - 함수에서 사용할 변수를 상단에 미리 정의하기 보다는, 해당 변수를 사용하는 코드 바로 위에서 선언하자.
 *  - 관련있는 코드끼리 묶은 다음, 함수 추출하기 (Extract Function)를 사용해서 더 깔끔하게 분리할 수 있다.
 */
public class StudyDashboard_Done {

    private void printParticipants(int eventId) throws IOException {
        /*
           지역변수의 위치
           - 어떤 변수가 사용되는 바로 직전에 선언된느 것을 선호한다.
           - 그래야지, 하나의 블럭으로 보기가 쉽다.
           - 그만큼의 블럭을 함수로 추출할 수 있는 시야를 준다.
         */
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

    private void printReviewers() throws IOException {
        // Get github issue to check homework
        GitHub gitHub = GitHub.connect();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        GHIssue issue = repository.getIssue(30);

        // Get reviewers
        /* 위치 조정 - 사용 직전에 변수를 선언한다. */
        Set<String> reviewers = new HashSet<>();
        issue.getComments().forEach(c -> reviewers.add(c.getUserName()));

        // Print reviewers
        reviewers.forEach(System.out::println);
    }

    public static void main(String[] args) throws IOException {
        StudyDashboard_Done studyDashboard = new StudyDashboard_Done();
        studyDashboard.printReviewers();
        studyDashboard.printParticipants(15);
    }




}
