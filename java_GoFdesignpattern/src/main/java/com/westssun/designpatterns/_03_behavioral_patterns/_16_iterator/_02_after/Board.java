package com.westssun.designpatterns._03_behavioral_patterns._16_iterator._02_after;

import com.westssun.designpatterns._03_behavioral_patterns._16_iterator._01_before.Post;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ConcreteIterator
 * 인터페이스 상위에 1개를 두면, ConcreteIterator 에 따라서 유연하게 처리할 수도 있음
 */
public class Board {

    // Board : ConcreteIterator
    // List : Aggregate
    // ArrayList : ConcreteAggregate
    List<Post> posts = new ArrayList<>();

    public List<Post> getPosts() {
        return posts;
    }

    public void addPost(String content) {
        this.posts.add(new Post(content));
    }

    public Iterator<Post> getRecentPostIterator() {
        return new RecentPostIterator(this.posts);
    }


}
