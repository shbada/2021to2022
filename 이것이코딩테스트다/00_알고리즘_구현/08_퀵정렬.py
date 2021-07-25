array = [5, 7, 9, 0, 3, 1, 6, 2, 4, 8]


def quick_sort(array, start, end):
    if start >= end:  # 원소가 1개라면 종료
        return

    pivot = start  # 피벗은 첫번째 원소

    left = start + 1
    right = end

    while left <= right:  # left 가 right 과 같거나 right 보다 커진다면 엇갈렸다는 의미로 반복문 탈출
        # 피벗보다 큰 데이터를 찾을 때까지 반복
        while left <= end and array[left] <= array[pivot]:  # left 에서는 피벗보다 큰 수를 찾아내므로, 작다면 그 다음 원소로 넘어간다.
            left += 1

        while right > start and array[start] >= array[pivot]:  # right 에서는 피벗보다 작은 수를 찾아내므로, 크다면 그 다음 원소로 넘어간다.
            right -= 1

        if left > right:  # 엇갈렸다면 작은 데이터와 피벗 교체
            array[right], array[pivot] = array[pivot], array[right]
        else:  # 엇갈리지 않았다면 작은 데이터와 큰 데이터 교체
            array[left], array[right] = array[right], array[left]

    # 분할 이후 왼쪽 부분과 오른쪽 부분에서 각각 정렬 수행 (처음과 동일한 정렬 수행이므로 재귀 방식)
    quick_sort(array, start, right - 1)
    quick_sort(array, right + 1, end)


quick_sort(array, 0, len(array) - 1)
print(array)

""" 
파이썬 장점을 살린 코드
"""


def quick_sort_python(array):
    # 리스트가 하나 이하의 원소만 담고있다면 종료
    if len(array) <= 1:
        return array

    pivot = array[0]  # 피벗은 첫번째 원소
    tail = array[1:]  # 피벗을 제외한 새로운 리스트 생성

    left_side = [x for x in tail if x <= pivot]  # 왼쪽 부분
    right_side = [x for x in tail if x > pivot]  # 오른쪽 부분

    # 분할 이후 왼쪽 부분, 오른쪽 부분에서 각각 정렬을 수행하고 전체 리스트를 반환한다
    return quick_sort_python(left_side) + [pivot] + quick_sort_python(right_side)


print(quick_sort_python(array))

