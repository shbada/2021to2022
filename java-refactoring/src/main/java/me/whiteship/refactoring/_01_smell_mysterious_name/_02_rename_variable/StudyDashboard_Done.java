package me.whiteship.refactoring._01_smell_mysterious_name._02_rename_variable;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueComment;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 리팩토링 2. 변수 이름 바꾸기
 * - 더 많이 사용되는 변수일수록 그 이름이 더 중요하다.
 * > 람다식에서 사용하는 변수(좁은 Scope) vs 함수의 매개변수(범위가 넓은 Scope)
 * - 다이나믹 타입을 지원하는 언어에서는 타입을 이름에 넣기도 한다.
   > 코드를 읽는 사람이 타입을 추론하기 어렵기 때문에 타입을 변수 이름에 넣을 수도 있다.
 * - 여러 함수에 걸쳐 쓰이는 필드 이름에는 더 많이 고민하고 이름을 짓는다.
 */
public class StudyDashboard_Done {

    private Set<String> usernames = new HashSet<>();

    private Set<String> reviews = new HashSet<>();

    /**
     * 스터디 리뷰 이슈에 작성되어 있는 리뷰어 목록과 리뷰를 읽어옵니다.
     * @throws IOException
     */
    private void loadReviews() throws IOException {
        // 로컬 변수 gitHub
        GitHub gitHub = GitHub.connect();

        // 로컬 변수 repository
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        GHIssue issue = repository.getIssue(30);

        // 로컬변수 comments
        // 리뷰에 달려있는 코멘트는 맞는데, 결국 리뷰니깐 reviews로 바꾸면 어떨까?
        // reviews를 읽어오는 함수에 comments 는 약간 부자연스럽다.
        List<GHIssueComment> reviews = issue.getComments();
        for (GHIssueComment review : reviews) {
            usernames.add(review.getUserName());
            this.reviews.add(review.getBody());
        }
    }

    public Set<String> getUsernames() {
        return usernames;
    }

    public Set<String> getReviews() {
        return reviews;
    }

    public static void main(String[] args) throws IOException {
        // 로컬 변수 studyDashboard
        StudyDashboard_Done studyDashboard = new StudyDashboard_Done();
        studyDashboard.loadReviews();

        // 람다식 name (좁은 Scope)
        // name : n, review : r 로 간추려도 되고, 좀더 명시적으로 해도 되고, 메서드 참조 방식으로 변경해도된다.
        studyDashboard.getUsernames().forEach(System.out::println);
        studyDashboard.getReviews().forEach(System.out::println);
//        studyDashboard.getUsernames().forEach(name -> System.out.println(name));
//        studyDashboard.getReviews().forEach(review -> System.out.println(review));
    }
}
