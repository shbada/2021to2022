"""
스택을 이용해 다음 연산을 지원하는 큐를 구현하라.

push(x) : 요소 x를 큐 마지막에 삽입한다.
pop() : 큐 처음에 있는 요소를 제거한다.
peek() : 큐 처음에 있는 요소를 조회한다.
empty() : 큐가 비어있는지 여부를 리턴한다.
"""

class MyQueue:
    def __init__(self):
        self.input = []
        self.output = []

    def push(self, x):
        self.input.append(x)

    def pop(self):
        self.peek()
        return self.output.pop()

    def peek(self):
        # output이 없으면 모두 재입력
        if not self.output:
            while self.input:
                self.output.append(self.input.pop())
        return self.output[-1]

    def empty(self):
        return self.input == [] and self.output == []
