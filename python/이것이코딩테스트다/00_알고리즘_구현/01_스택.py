stack = []

stack.append(5)  # stack push 5
stack.append(2)  # stack push 2
stack.append(3)  # stack push 3
stack.append(7)  # stack push 7
stack.pop()      # stack pop (7)
stack.append(1)  # stack push 1
stack.append(4)  # stack push 4
stack.pop()      # stack pop (4)

# stack 출력
print(stack)

# stack 최상단부터 출력
print(stack[::-1])
