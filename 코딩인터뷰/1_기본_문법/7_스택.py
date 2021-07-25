# 스택 : LIFO (Last-in-First-Out: 후입선출)
# 파이썬은 스택 자료형을 별도로 제공하지는 않지만, 리스트가 사실상 스택의 모든 연산을 지원한다.

# push() : 요소를 컬렉션에 추가한다.
# pop() : 아직 제거되지 않은 가장 최근에 삽입된 요소를 제거한다.

"""
연결리스트를 이용한 스택 ADT (Abstact Data Type) 구현
"""


class Node:
    def __init__(self, item, next):
        self.item = item  # 값
        self.next = next  # 다음 노드를 가리키는 포인터


class Stack:
    def __init__(self):
        self.last = None

    def push(self, item):
        # 마지막 노드
        self.last = Node(item, self.last)

    def pop(self):
        # 마지막 노드의 값 꺼내기
        item = self.last.item
        # 마지막 노드 없애기
        # 포인터인 last 를 가장 마지막으로 이동시킨다.
        self.last = self.last.next

        return item


stack = Stack()

stack.push(1)
stack.push(2)
stack.push(3)
stack.push(4)
stack.push(5)

for _ in range(5):
    print(stack.pop())

# 큐 (Queue)

# 큐 : FIFO (First-In-First_out) 선입선출
# 큐를 사용하기 위해서는 리스트 보다는 데크(Deque)라는 별도의 자료형을 사용해야 좋은 성능을 낼 수 있다.
# 성능을 고려하지 않는다면 큐도 리스트로 구현이 가능하다.
