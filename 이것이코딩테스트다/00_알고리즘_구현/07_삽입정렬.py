array = [7, 5, 9, 0, 3, 1, 6, 2, 4, 8]

for i in range(1, len(array)):
    for j in range(i, 0, -1):  # 현재 원소의 왼쪽을 봐야하기 때문에 인덱스 i 부터 1까지 감소하며 반복하는 문법
        if array[j] < array[j - 1]:
            # swap
            array[j], array[j - 1] = array[j - 1], array[j]
        else:
            break

print(array)
