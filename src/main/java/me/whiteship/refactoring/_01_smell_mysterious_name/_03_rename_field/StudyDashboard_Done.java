package me.whiteship.refactoring._01_smell_mysterious_name._03_rename_field;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueComment;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 리팩토링 3. 필드 이름 바꾸기
 *
 * Record 자료 구조의 필드 이름은 프로그램 전반에 걸쳐 참조될 수 있기 때문에 매우 중요하다.
 * Record 자료구조 : 특정 데이터와 관련있는 필드를 묶어놓은 자료 구조
 * 파이썬의 Dictionary (dicts)
 * c#의 Record
 * 자바 14버전부터 지원되는 record 키워드
   > Immutable한 클래스 (final 등) 으로 기존에도 만들 수는 있었다.
   > record 키워드를 사용으로 많은 코드를 대체할 수 있다.
 * 자바에서는 Getter, Stter 메서드 이름도 필드의 이름과 비슷하게 간주할 수 있다.
 */
public class StudyDashboard_Done {

    // usernames -> reviewer가 더 낫지않을까?
        private Set<StudyReview> studyReviews = new HashSet<>();

//    private Set<StudyReview> reviews = new HashSet<>();

    /**
     * 스터디 리뷰 이슈에 작성되어 있는 리뷰어 목록과 리뷰를 읽어옵니다.
     * @throws IOException
     */
    private void loadReviews() throws IOException {
        GitHub gitHub = GitHub.connect();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        GHIssue issue = repository.getIssue(30);

        List<GHIssueComment> reviews = issue.getComments();
        for (GHIssueComment review : reviews) {
            StudyReview studyReview = new StudyReview(review.getUserName(), review.getBody());

            // get 메서드는 아니지만 get 메서드의 역할을 해준다.
            System.out.println(studyReview.reviewer());
            System.out.println(studyReview.review());

            studyReviews.add(studyReview);
        }
    }

    public Set<StudyReview> getStudyReviews() {
        return studyReviews;
    }

    //    public Set<String> getReviewer() {
//        return reviewer;
//    }
//
//    public Set<String> getReviews() {
//        return reviews;
//    }

    public static void main(String[] args) throws IOException {
        StudyDashboard_Done studyDashboard = new StudyDashboard_Done();
        studyDashboard.loadReviews();
        studyDashboard.getStudyReviews().forEach(System.out::println);
    }
}
