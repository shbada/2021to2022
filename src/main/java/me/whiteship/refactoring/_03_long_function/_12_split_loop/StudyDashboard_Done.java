package me.whiteship.refactoring._03_long_function._12_split_loop;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueComment;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 리팩토링 12. 반복문 쪼개기
 * = 하나의 반복문에서 여러 다른 작업을 하는 코드를 쉽게 찾아볼 수 있다.
 * = 해당 반복문을 수정할때 여러 작업을 모두 고려하며 코딩을 해야한다.
 * = 반복문을 여러개로 쪼개면 보다 쉽게 이해하고 수정할 수 있다.
 * = 성능 문제를 야기할 수 있지만, "리팩토링"은 "성능 최적화"와 별개의 작업이다. 리팩토링을 마친 이후에 성능 최적화 시도를 할 수 있다.
 */
public class StudyDashboard_Done {

    private final int totalNumberOfEvents;
    private final List<Participant> participants;
    private final Participant[] firstParticipantsForEachEvent;

    public StudyDashboard_Done(int totalNumberOfEvents) {
        this.totalNumberOfEvents = totalNumberOfEvents;

        // concurrency에 지원하는 객체
        // 새로운 element 가 추가될때 기존을 copy하여 새롭게 객체를 생성한다.
        participants = new CopyOnWriteArrayList<>();
        firstParticipantsForEachEvent = new Participant[this.totalNumberOfEvents];
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        StudyDashboard_Done studyDashboard = new StudyDashboard_Done(15);
        studyDashboard.print();
    }

    private void print() throws IOException, InterruptedException {
        GHRepository ghRepository = getGhRepository();

        // 필드 올리기
        // Refactoring > Introduce Field

        checkGithubIssues(ghRepository);

        new StudyPrinter(this.totalNumberOfEvents, this.participants).execute();
        printFirstParticipants();
    }

    private void checkGithubIssues(GHRepository ghRepository) throws InterruptedException {
        // 멀티 스레드 프로그래밍
        // 이슈의 모든 코멘트를 읽어올때 시간이 많이 소요된다.
        // 여러개의 스레드를 만든다.
        // 쓰레드를 8개를 만들어서 멀티 스레드 프로그래밍을 한다. (8개를 동시에 돌릴 수 있다.)
        // 15개의 이슈이므로, 15개 스레드가 돌면 좋다.
        // 풀에는 8개지만 이 풀에 있는 8개 스레드를 가지고 15개의 스레드를 만들어서 수행한다.
        ExecutorService service = Executors.newFixedThreadPool(8);
        CountDownLatch latch = new CountDownLatch(totalNumberOfEvents);

        for (int index = 1 ; index <= totalNumberOfEvents ; index++) {
            int eventId = index;
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        GHIssue issue = ghRepository.getIssue(eventId);
                        List<GHIssueComment> comments = issue.getComments();

                        checkHomework(comments, participants, eventId);

                        // 인라인 리팩토링
//                        Participant first = findFirst(comments);
                        firstParticipantsForEachEvent[eventId - 1] = findFirst(comments);
                        latch.countDown();
                    } catch (IOException e) {
                        throw new IllegalArgumentException(e);
                    }
                }
            });
        }

        latch.await();
        service.shutdown();
    }

    private Participant findFirst(List<GHIssueComment> comments) throws IOException {
        Date firstCreatedAt = null;
        Participant first = null;

        // 루프를 역할별로 쪼개보자.
        for (GHIssueComment comment : comments) {
            Participant participant = findParticipant(comment.getUserName(), participants);

            if (firstCreatedAt == null || comment.getCreatedAt().before(firstCreatedAt)) {
                firstCreatedAt = comment.getCreatedAt();
                first = participant;
            }
        }
        return first;
    }

    private void checkHomework(List<GHIssueComment> comments, List<Participant> participants, int eventId) {
        for (GHIssueComment comment : comments) {
            Participant participant = findParticipant(comment.getUserName(), participants);
            participant.setHomeworkDone(eventId);
        }
    }

    private void printFirstParticipants() {
        Arrays.stream(this.firstParticipantsForEachEvent).forEach(p -> System.out.println(p.username()));
    }

    private GHRepository getGhRepository() throws IOException {
        GitHub gitHub = GitHub.connect();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        return repository;
    }

    private Participant findParticipant(String username, List<Participant> participants) {
        return isNewParticipant(username, participants) ?
                createNewParticipant(username, participants) :
                findExistingParticipant(username, participants);
    }

    private Participant findExistingParticipant(String username, List<Participant> participants) {
        Participant participant;
        participant = participants.stream().filter(p -> p.username().equals(username)).findFirst().orElseThrow();
        return participant;
    }

    private Participant createNewParticipant(String username, List<Participant> participants) {
        Participant participant;
        participant = new Participant(username);
        participants.add(participant);
        return participant;
    }

    private boolean isNewParticipant(String username, List<Participant> participants) {
        return participants.stream().noneMatch(p -> p.username().equals(username));
    }

}
