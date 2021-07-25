# stack
class StackList:
    def __init__(self, stack_size):
        self.top = -1
        self.stack_size = stack_size
        self.param_list = []

    # 스택이 비어있는지 체크
    def is_empty(self) -> bool:
        empty = False

        if self.top == -1:
            empty = True

        return empty

    # 스택이 꽉 찼는지 체크
    def is_full(self) -> bool:
        full = False

        if self.top >= self.stack_size - 1:
            full = True

        return full

    # 스택 push
    def push(self, value) -> None:
        if StackList.is_full(self):
            raise Exception("is_full")
        else:
            self.top += 1
            self.param_list.insert(self.top, value)

    # 스택 pop
    def pop(self) -> int:
        pop_idx = 0

        if StackList.is_empty(self):
            raise Exception("is_empty")
        else:
            pop_idx = self.param_list[self.top]
            self.top -= 1

        return pop_idx

    # 스택 peek
    def peek(self) -> int:
        peek_idx = 0

        if StackList.is_empty(self):
            raise Exception("is_empty")
        else:
            peek_idx = self.param_list[self.top]

        return peek_idx

    # 스택 출력
    def print(self) -> None:
        if StackList.is_empty(self):
            raise Exception("is_empty")
        else:
            for i in range(0, self.top + 1):
                print(self.param_list[i])

    # 스택 초기화
    def clear(self) -> None:
        if StackList.is_empty(self):
            raise Exception("is_empty")
        else:
            self.top = -1
            self.param_list = []


stack_list = StackList(5)

stack_list.push(1)  # [1]
stack_list.push(2)  # [1, 2]
stack_list.push(3)  # [1, 2, 3]
stack_list.push(4)  # [1, 2, 3, 4]
stack_list.push(5)  # [1, 2, 3, 4, 5]

stack_list.print()

idx = 0

print(stack_list.peek())  # 5
stack_list.print()

print(stack_list.pop())  # 5
print(stack_list.pop())  # 4
print(stack_list.pop())  # 3
print(stack_list.pop())  # 2
print(stack_list.pop())  # 1

# stack_list.print()

