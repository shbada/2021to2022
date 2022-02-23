package com.westssun.designpatterns._03_behavioral_patterns._16_iterator._01_before;

import java.util.Collections;
import java.util.List;

/**
 * 집합 객체를 순회
 * 내부 구현을 드러내지 않고 클라이언트가 내부 안에 들어있는 집합 객체를 순회하는 방법
 *
 * 기본적으로 사용한 Iterator.java 인터페이스가 대표적
 *
 */
public class Client {

    public static void main(String[] args) {
        Board board = new Board();
        board.addPost("디자인 패턴 게임");
        board.addPost("선생님, 저랑 디자인 패턴 하나 학습하시겠습니까?");
        board.addPost("지금 이 자리에 계신 여러분들은 모두 디자인 패턴을 학습하고 계신 분들입니다.");

        /**
         * board 가 어떤 구조로 되어있는지를 클라이언트가 알아야한다. (List 등)
         * 만약에 board 가 List -> Set 으로 변경되면?
         * 클라이언트가 board 변경에 따른 영향을 받는다.
         */
        // TODO 들어간 순서대로 순회하기
        List<Post> posts = board.getPosts();
        for (int i = 0 ; i < posts.size() ; i++) {
            Post post = posts.get(i);
            System.out.println(post.getTitle());
        }

        // TODO 가장 최신 글 먼저 순회하기
        Collections.sort(posts, (p1, p2) -> p2.getCreatedDateTime().compareTo(p1.getCreatedDateTime()));
        for (int i = 0 ; i < posts.size() ; i++) {
            Post post = posts.get(i);
            System.out.println(post.getTitle());
        }
    }

}
