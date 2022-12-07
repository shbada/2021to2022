
# Deque 라이브러리 사용
from collections import deque

queue = deque()

queue.append(5)  # queue push 5
queue.append(2)  # queue push 2
queue.append(3)  # queue push 3
queue.append(7)  # queue push 7
queue.popleft()  # queue pop (5)
queue.append(1)  # queue push 1
queue.append(4)  # queue push 4
queue.popleft()  # queue pop (2)

# queue 먼저 들어온 데이터부터 출력하기
print(queue)

# queue 나중에 들어온 데이터부터 출력하기
queue.reverse()
print(queue)
