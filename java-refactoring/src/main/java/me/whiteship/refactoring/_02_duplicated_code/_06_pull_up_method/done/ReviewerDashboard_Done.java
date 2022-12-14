package me.whiteship.refactoring._02_duplicated_code._06_pull_up_method.done;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ReviewerDashboard_Done extends Dashboard_Done {

    /*
        파라미터를 추가한다.
     */
    public void printReviewers() throws IOException {
        printUsernames(30);
    }

    /* 상위 클래스로 올리기 */
//    private static void printUsernames(int eventId) throws IOException {
//        // Get github issue to check homework
//        Dashboard_Done.printUsernames(eventId);
//    }

}
