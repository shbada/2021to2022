"""
큐를 이용하여 다음 연산을 지원하는 스택을 구현하라.

push(x) : 요소 x를 스택에 삽입하라
pop() : 스택의 첫번째 요소를 삭제한다.
top() : 스택의 첫번째 요소를 가져온다.
empty() : 스택이 비어있는지 여부를 리턴한다.
"""
import collections


class MySatck:
    def __init__(self):
        self.q = collections.deque()

    def push(self, x):
        self.q.append(x)

        # 요소 삽입 후 맨 앞에 두는 상태로 재정렬
        for _ in range(len(self.q) - 1):
            self.q.append(self.q.popleft())

    def pop(self):
        return self.q.popleft()

    def top(self):
        return self.q[0]

    def empty(self):
        return len(self.q) == 0
