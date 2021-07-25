# N, K공백을 기준으로 구분하여 입력 받기
n, k = map(int, input().split())

result = 0

while True:
    # N이 K로 나누어 떨어지는 수가 될 때까지만 1씩 빼기
    # n을 k로 나눈 몫에 k를 다시 곱한다 -> n이 k로 나눠떨어지지 않을때 가장 가까운 나눠 떨어지는 수를 구할 수 있다.
    target = (n // k) * k
    # 총 연산을 수행하는 횟수 (result) - target 은 1을 처리한 개수를 한번에 구한 것이다.
    result += (n - target)
    n = target

    # N이 K보다 작을 때 (더 이상 나눌 수 없을 때) 반복문 탈출
    if n < k:
        break

    # K로 나누기
    result += 1  # k를 나누는 연산을 수행하므로 1번 추가
    n //= k

# 마지막으로 남은 수에 대하여 1씩 빼기
result += (n - 1)

print(result)
