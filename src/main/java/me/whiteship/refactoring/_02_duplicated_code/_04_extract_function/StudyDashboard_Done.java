package me.whiteship.refactoring._02_duplicated_code._04_extract_function;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 리팩토링 4. 함수 추출하기
 * - 의도와 구현 분리하기
 * > "의도" : 코드가 어떤 일을 하는지 잘 "이해"가 되는가?
 * > "구현" : 코드가 어떤 일을 하는지 잘 "표현"하고 있는가?
 * - 무슨 일을 하는 코드인지 알아내려고 노력해야하는 코드라면 해당 코드를 함수로 분리하고,
 *   함수 이름으로 "무슨 일을 하는지" 표현할 수 있다.
 * - 한줄 짜리 메서드도 괜찮은가?
 * - 거대한 함수 안에 들어있는 주석은 추출한 함수를 찾는데 있어서 좋은 단서가 될 수 있다.
 */
public class StudyDashboard_Done {

    private void printParticipants(int eventId) throws IOException {
        /* 상당 부분이 구현이다. 코드가 잘 안읽힌다. */

        /* 메서드로 추출 완료 */
        GHIssue issue = getGhIssue(eventId);
        Set<String> participants = getUsernames(issue);
        print(participants);
    }

    private static void print(Set<String> participants) {
        participants.forEach(System.out::println);
    }

    private static Set<String> getUsernames(GHIssue issue) throws IOException {
        // participants -> usernames (usernames 를 추출하므로 usernames가 더 확실해보인다.)
        Set<String> usernames = new HashSet<>();
        issue.getComments().forEach(c -> usernames.add(c.getUserName()));
        return usernames;
    }

    private static GHIssue getGhIssue(int eventId) throws IOException {
        GitHub gitHub = GitHub.connect();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        GHIssue issue = repository.getIssue(eventId);
        return issue;
    }

    private void printReviewers() throws IOException {
        GHIssue issue = getGhIssue(30);
        Set<String> reviewers = getUsernames(issue);
        print(reviewers);
    }

    public static void main(String[] args) throws IOException {
        StudyDashboard_Done studyDashboard = new StudyDashboard_Done();
        studyDashboard.printReviewers();
        studyDashboard.printParticipants(15);
    }

}
