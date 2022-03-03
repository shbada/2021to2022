package com.designpattern.report._16_iterator.step2_after;

import java.util.Iterator;
import java.util.List;

/**
 * 이터레이터(Iterator) 패턴
 * 집합 객체 내부 구조를 노출시키지 않고 순회하는 방법을 제공하는 패턴
 *
 * Client -> Iterator, Aggregate
 * ConcreteIterator (=board)
 * ConcreteAggregate
 *
 * [장점]
 * - 집합 객체에 손쉽게 접근 가능
 * - iterator 을 제공했을때 iterator 가 제공하는 정보만 알면됨
 * (리스트인지, Set 인지 등 내부 구현체를 몰라도 된다)
 * 그저 hasNext(), next()만 알면 순회가 가능하다
 *
 * [단점]
 * - 원래 있던 구조에 비해 Iterator 을 만드는게 나은건지 판단
 * 내부의 집합객체가 변경될 가능성이 있을 경우 내부를 숨기는 방법으로 iterator 사용이
 * 좋은 방법이 될 수 있다.
 */
public class Client {
    // Iterator 주입받는 형식이라면 좀더 좋을 수도

    public static void main(String[] args) {
        Board board = new Board();
        board.addPost("디자인 패턴 게임");
        board.addPost("선생님, 저랑 디자인 패턴 하나 학습하시겠습니까?");
        board.addPost("지금 이 자리에 계신 여러분들은 모두 디자인 패턴을 학습하고 계신 분들입니다.");

        // TODO 들어간 순서대로 순회하기
        List<Post> posts = board.getPosts();

        // iterator
        Iterator<Post> iterator = posts.iterator();
        System.out.println(iterator.getClass()); // ArrayList$Itr

        for (int i = 0 ; i < posts.size() ; i++) {
            Post post = posts.get(i);
            System.out.println(post.getTitle());
        }

        // TODO 가장 최신 글 먼저 순회하기
        Iterator<Post> recentPostIterator = board.getRecentPostIterator();
        while(recentPostIterator.hasNext()) {
            System.out.println(recentPostIterator.next().getTitle());
        }
    }

}
