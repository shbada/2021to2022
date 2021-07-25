# queue
class QueueList:
    def __init__(self, queue_size):
        self.front = -1
        self.rear = -1
        self.queue_size = queue_size
        self.param_list = []

    # 큐가 비어있는지 체크
    def is_empty(self) -> bool:
        if self.front == self.rear:
            self.front = -1
            self.rear = -1

        return self.front == self.rear

    # 큐가 꽉 찼는지 체크
    def is_full(self) -> bool:
        full = False

        if self.rear == self.queue_size - 1:
            full = True

        return full

    # 큐 enqueue
    def enqueue(self, value) -> None:
        if QueueList.is_full(self):
            raise Exception("is_full")
        else:
            self.rear += 1
            self.param_list.insert(self.rear, value)

    # 큐 dequeue
    def dequeue(self) -> int:
        if QueueList.is_empty(self):
            raise Exception("is_empty")
        else:
            self.front += 1
            dequeue_idx = self.param_list[self.front]

        return dequeue_idx

    # 큐 print
    def print(self) -> None:
        if QueueList.is_empty(self):
            raise Exception("is_empty")
        else:
            for i in range(self.front + 1, self.rear + 1):
                print(self.param_list[i])


queue_list = QueueList(5)

queue_list.enqueue(1)
queue_list.enqueue(2)
queue_list.enqueue(3)
queue_list.enqueue(4)
queue_list.enqueue(5)

queue_list.print()

idx = 0

print(queue_list.dequeue())  # 1
print(queue_list.dequeue())  # 2
print(queue_list.dequeue())  # 3
print(queue_list.dequeue())  # 4
print(queue_list.dequeue())  # 5
