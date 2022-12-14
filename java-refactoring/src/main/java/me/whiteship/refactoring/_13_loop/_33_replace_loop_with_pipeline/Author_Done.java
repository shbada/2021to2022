package me.whiteship.refactoring._13_loop._33_replace_loop_with_pipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Author_Done {

    private String company;

    private String twitterHandle;

    public Author_Done(String company, String twitterHandle) {
        this.company = company;
        this.twitterHandle = twitterHandle;
    }

    static public List<String> TwitterHandles(List<Author_Done> authors, String company) {
        return authors.stream()
                .filter(a -> a.company.equals(company))
                .map(a -> a.twitterHandle)
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
