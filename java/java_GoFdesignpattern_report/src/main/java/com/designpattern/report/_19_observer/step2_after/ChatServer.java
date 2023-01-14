package com.designpattern.report._19_observer.step2_after;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Subject
 */
public class ChatServer {
    private Map<String, List<Subscriber>> subscribers = new HashMap<>();

    /**
     * subscribe
     * @param subject
     * @param subscriber
     */
    public void register(String subject, Subscriber subscriber) {
        if (this.subscribers.containsKey(subject)) {
            this.subscribers.get(subject).add(subscriber);
        } else {
            List<Subscriber> list = new ArrayList<>();
            list.add(subscriber);
            this.subscribers.put(subject, list); // List.of()는 immutable 이므로 원소 추가가 불가능하다.
        }
    }

    /**
     * unsubscribe
     * @param subject
     * @param subscriber
     */
    public void unregister(String subject, Subscriber subscriber) {
        if (this.subscribers.containsKey(subject)) {
            this.subscribers.get(subject).remove(subscriber); // 언제 remove 될지 예상 불가능
        }
    }

    /**
     * notify
     * @param user
     * @param subject
     * @param message
     */
    public void sendMessage(User user, String subject, String message) {
        if (this.subscribers.containsKey(subject)) {
            String userMessage = user.getName() + ": " + message;

            // 구독한 user 에게 메시지를 보낸다.
            this.subscribers.get(subject).forEach(s -> s.handleMessage(userMessage));
        }
    }

}
