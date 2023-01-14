list = [8, 3, 7, 9, 2]
M = 3
check_list = [5, 7, 9]

# 함수 실행
def check(list, value):
    start = 0
    end = len(list) - 1

    while start <= end:
        mid = (start + end) // 2

        if list[mid] < value:
            start = mid + 1

        elif list[mid] > value:
            end = mid - 1
        else:
            return mid
    return None


list.sort()  # 정렬

for x in check_list:
    result = check(list, x)

    if result is None:
        print('no', end=' ')
    else:
        print('yes', end=' ')
