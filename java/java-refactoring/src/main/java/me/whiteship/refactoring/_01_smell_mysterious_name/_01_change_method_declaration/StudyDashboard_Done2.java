package me.whiteship.refactoring._01_smell_mysterious_name._01_change_method_declaration;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueComment;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1) 함수 선언 변경하기
 * - 함수 이름 변경, 메소드 이름 변경, 매개변수 추가, 매개변수 제거, 시그니처 변경
 * - 좋은 이름을 가진 함수는 코드를 보지 않아도 이름만 보고 구현 로직을 예측할 수 있다.
 * - 좋은 이름을 찾아내는 방법?
 * > 함수에 주석을 작성해본다. 그리고 주석을 함수 이름으로 만들어본다.
 * - 함수의 매개변수
 * > 함수 내부의 문맥을 결정한다.
 *      > 어떤 함수의 파라미터가 있고, 이 텍스트를 전화번호로 포맷팅 하는 함수
 *      > 텍스트를 받을거냐, Person, Phone 등의 객체를 받을거냐 어떤 타입으로 받을거냐? 에 따라 함수의 문맥이 달라진다.
 *      > 만약 Person 객체를 보내면, 이 함수는 Person 에서 공개하고 있는 모든 데이터에 접근할 수 있게된다.
 * > 의존성을 결정한다.
 *      > 만기일을 넘길거냐, 객체 Payment를 넘길거냐, 이는 상황에 따라 달라진다.
 *      > 만기일 필수 여부를 체크하려면 Payment를 받겠다.
 *      > Payment 정보를 참조해야하다면 Payment를 넘겨줘야한다.
 *      > 의존성을 최대한 낮추는 것이 좋다. 정답은 없다. 경우에 따라 다르다.
 */
public class StudyDashboard_Done2 {

    private Set<String> usernames = new HashSet<>();

    private Set<String> reviews = new HashSet<>();

    /* GHIssue 객체를 파라미터로 받는다. */

    /**
     * 스터디 리뷰 이슈에 작성되어있는 리뷰의 목록과 리뷰를 읽어옵니다.
     *
     * @param issue
     * @throws IOException
     */
    /* 이름을 바꿔보자. */
    // before : studyReviews
    // 메서드 이름 마우스 우측 > Refactor > reName (Shift + f6)
    // after : loadReviews

    /* 매개변수를 바꿔보자. */
    // before : GHIssue issue
    // 가져올 이슈는 오직 단건이고, main의 로직을 가져오면 매개변수를 안받아도 된다. (30번으로 정해져 있으므로)

    /* 매개변수를 다시 받아보자. (Command + f6) */
    private void loadReviews(GHIssue issue) throws IOException {
        List<GHIssueComment> comments = issue.getComments();

        for (GHIssueComment comment : comments) {
            usernames.add(comment.getUserName());
            reviews.add(comment.getBody());
        }
    }

    public Set<String> getUsernames() {
        return usernames;
    }

    public Set<String> getReviews() {
        return reviews;
    }

    public static void main(String[] args) throws IOException {
        GitHub gitHub = GitHub.connect();
        // repository 가져오기
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        // issue 가져오기
        GHIssue issue = repository.getIssue(30);

        StudyDashboard_Done2 studyDashboard = new StudyDashboard_Done2();
        studyDashboard.loadReviews(issue);

        // 출력
        studyDashboard.getUsernames().forEach(System.out::println);
        studyDashboard.getReviews().forEach(System.out::println);
    }
}
