# 1) N에서 1을 뺀다
# 2) N을 k로 나눈다.

# 1) N/K 가 나누어 떨어지는 경우 나눗셈 실행 (우선실행해야 횟수를 최소한으로 할 수 있다.)
# 2) 나누어떨어지지 않은 경우 -1 실행

N = 17
K = 4

count = 0

while N != 1:
    if N % K != 0:
        N = N - 1
        count += 1

    else:
        N = N / K
        count += 1


print(count)
