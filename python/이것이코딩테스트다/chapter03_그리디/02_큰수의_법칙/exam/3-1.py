# N, M, K를 공백으로 구분하여 입력받기
n, m, k = map(int, input().split())

# N개의 수를 공백으로 구분하여 입력받기
data = list(map(int, input().split()))

data.sort()  # 입력 받은 수 정렬

first = data[n - 1]  # 마지막 원소 = 제일 큰 수
second = data[n - 2]  # 두번째로 큰 수 = 마지막에서 두번재 수

result = 0

while True:
    # k 번 만큼 가장 큰 수 덧셈 실행
    for i in range(k):
        if m == 0:
            break
        result += first
        m -= 1

    # 두번재 수를 더하기 전에 m 이 0인지 체크 (m 이 0이라면 m 번 실행했다는 의미로 반복문 탈출한다.)
    if m == 0:
        break

    # k 번을 더했으므로 두번째 수를 더하기
    result += second
    m -= 1

print(result)
