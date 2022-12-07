# 큐
# 선입선출

from collections import deque

queue = deque()

# stack push
queue.append(1)
queue.append(3)
queue.append(5)
queue.append(7)
queue.append(9)

# stack pop
queue.popleft()  # 1
queue.popleft()  # 3

print(queue)