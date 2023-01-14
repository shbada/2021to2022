# 가능한 연산 목록
# 1) 5로 나눌 수 있다.
# 2) 3으로 나눌 수 있다.
# 3) 2로 나눌 수 있다.
# 4) -1 할 수 있다.

value = 26
result = 0

while value != 1:
    # a: 몫
    # b: 나머지
    a, b = divmod(5, value)

    if b != 0:
        value = value - b
        result = b + result
    else:
        value = value // 5
        result += 1



print(result)

