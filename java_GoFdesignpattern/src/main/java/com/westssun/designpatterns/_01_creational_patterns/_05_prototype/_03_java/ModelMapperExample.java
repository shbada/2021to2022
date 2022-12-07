package com.westssun.designpatterns._01_creational_patterns._05_prototype._03_java;

import com.westssun.designpatterns._01_creational_patterns._05_prototype._02_after.GithubIssue;
import com.westssun.designpatterns._01_creational_patterns._05_prototype._02_after.GithubRepository;
import org.modelmapper.ModelMapper;

public class ModelMapperExample {

    public static void main(String[] args) {
        GithubRepository repository = new GithubRepository();
        repository.setUser("whiteship");
        repository.setName("live-study");

        GithubIssue githubIssue = new GithubIssue(repository);
        githubIssue.setId(1);
        githubIssue.setTitle("1주차 과제: JVM은 무엇이며 자바 코드는 어떻게 실행하는 것인가.");

        // 리플렉션 원리
        // githubIssue 가 갖고있는 데이터를 리플렉션으로 알아내서, 매핑하여 githubIssueData 인스턴스를 리턴
        // 깊은복사
        ModelMapper modelMapper = new ModelMapper();
        GithubIssueData githubIssueData = modelMapper.map(githubIssue, GithubIssueData.class);
        githubIssue.setId(2);

        System.out.println(githubIssue.getId()); // 2
        System.out.println(githubIssueData); // GithubIssueData(id=1, ...
    }
}
