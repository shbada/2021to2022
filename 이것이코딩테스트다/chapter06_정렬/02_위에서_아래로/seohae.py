n = int(input())

# 입력한 값을 배열로 담을 변수
array = []

for i in range(n):
    array.append(int(input()))

array = sorted(array, reverse=True)  # 내림차순 정렬

for i in array:
    print(i, end=' ')


