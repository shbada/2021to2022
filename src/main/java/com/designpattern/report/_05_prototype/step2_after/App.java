package com.designpattern.report._05_prototype.step2_after;

/**
 * 프로토타입 패턴
 * [장점]
 * -  복잡한 객체 생성 과정을 숨길 수 있다.
 * - 기존 객체를 복제하는 과정이 새 인스턴스를 만드는 것보다 비용(시간, 메모리)적인 면에서 효율적일 수도 있다.
 * - 추상적인 타입을 리턴할 수 있다.
 *
 * [단점]
 * - 복제한 객체를 만드는 과정 자체가 복잡할 수 있다. (특히, 순환참조가 있는 경우)
 */
public class App {

    public static void main(String[] args) throws CloneNotSupportedException {
        GithubRepository repository = new GithubRepository();
        repository.setUser("whiteship");
        repository.setName("live-study");

        GithubIssue githubIssue = new GithubIssue(repository);
        githubIssue.setId(1);
        githubIssue.setTitle("1주차 과제: JVM은 무엇이며 자바 코드는 어떻게 실행하는 것인가.");

        String url = githubIssue.getUrl();
        System.out.println(url);

        /** 위 동일한 작업의 반복이 필요없어졌다. lone() */
        /**
         * clone() : shallow copy / deep copy
         *
         * 자바에서 기본적으로 제공해주는건 shallow copy
         * - 얕읕 복사란?
         * githubIssue 를 clone 해서 만들었는데, 이때 githubIssue 가 가지고있는 repository 인스턴스를
         * 그대로 셋팅한다.
         * 그러므로, clone.getRepository() == githubIssue.getRepository() : true
         *
         * 그래서, clone 메서드를 재정의해서 deep copy 가 되도록 구현하면 된다.
         *
         *
         * - 깊은 복사란?
         * githubIssue 를 clone 해서 만들었는데, 이때 githubIssue 가 가지고있는 repository 인스턴스는
         * 새로 만들어서 그 안의 데이터들을 set 한다.
         */
        GithubIssue clone = (GithubIssue) githubIssue.clone();
        System.out.println(clone.getUrl());

        // shallow copy 일때는 동일한 객체므로, clone 도 함께 변한다.
        // deep copy일때는 다른 객체이므로 영향을 주지 않는다.
        repository.setUser("Keesun");

        System.out.println(clone != githubIssue);
        System.out.println(clone.equals(githubIssue));
        System.out.println(clone.getClass() == githubIssue.getClass());
        System.out.println(clone.getRepository() == githubIssue.getRepository());

        System.out.println(clone.getUrl());
    }

}
