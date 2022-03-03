package com.designpattern.report._05_prototype.step1_before;

public class App {

    public static void main(String[] args) {
        GithubRepository repository = new GithubRepository();
        repository.setUser("whiteship");
        repository.setName("live-study");

        GithubIssue githubIssue = new GithubIssue(repository);
        githubIssue.setId(1);
        githubIssue.setTitle("1주차 과제: JVM은 무엇이며 자바 코드는 어떻게 실행하는 것인가.");

        String url = githubIssue.getUrl();
        System.out.println(url);

        // .clone() 은 Object 안에 있다.

        // 위 또 repository url 를 만들려면.. 위 로직을 또 반복?
        // 이미만든 githubIssue.clone()하여 복제하여 사용하고 싶다.

        // GithubIssue clone = githubIssue.clone();
        // 1) clone == githubIssue : 다른 인스턴스가 생성됬으므로 같지 않음 (false)
        // 2) clone.equals(githubIssue) : 그 안의 데이터는 같다 (true)
        // 3) clone.getClass == githubIssue.getClass : Type 도 같다. (true)
    }

}
