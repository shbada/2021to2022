package com.designpattern.report._05_prototype.step1_before;

import lombok.Getter;
import lombok.Setter;

public class GithubIssue {

    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String title;

    @Getter
    private GithubRepository repository;

    public GithubIssue(GithubRepository repository) {
        this.repository = repository;
    }

    public String getUrl() {
        return String.format("https://github.com/%s/%s/issues/%d",
                repository.getUser(),
                repository.getName(),
                this.getId());
    }
}
