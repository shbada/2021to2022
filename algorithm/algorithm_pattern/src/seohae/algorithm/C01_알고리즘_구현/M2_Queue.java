package seohae.algorithm.C01_알고리즘_구현;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Queue
 */
public class M2_Queue {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(5); // push 5
        queue.offer(3); // push 3
        queue.offer(2); // push 2
        queue.poll();  // pop 5
        queue.offer(1); // push 1
        queue.poll(); // pop 3

        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }
}
