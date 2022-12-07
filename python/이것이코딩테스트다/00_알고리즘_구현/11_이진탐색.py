# 이진탐색 : 정렬되어있는 리스트에서 탐색 범위를 절반씩 좁혀가며 데이터를 탐색하는 방법 (시작점, 끝점, 중간점을 이용하여 탐색 범위를 설정)

# 이진탐색에 좋은 탐색 라이브러리
# bisect_left(a, x) : 정렬된 순서를 유지하면서 배열 a에 x를 삽입할 가장 왼쪽 인덱스를 반환
# bisect_right(a, x) : 정렬된 순서를 유지하면서 배열 a에 x를 삽입할 가장 오른쪽 인덱스를 반환

# 파라메트릭 서치 = 최적화 문제를 결정문제("예", "아니오") 로 바꾸어 해결하는 기법이다.
# -> 특정한 조건을 만족하는 가장 알맞은 값을 빠르게 찾는 최적화 문제
# 이진 탐색을 이용하여 해결할 수 있다.

"""1. 재귀함수로 구현"""
def binary_search(array, target, start, end):
    if start > end:
        return None
    mid = (start + end) // 2

    # 찾은 경우 중간 인덱스 반환
    if array[mid] == target:
        return mid
    elif array[mid] > target:
        # 중간점의 값보다 찾고자하는 값이 작은 경우는 왼쪽을 확인하므로 마지막 인덱스를 mid - 1로 지정
        return binary_search(array, target, start, mid - 1)
    else:
        # 중간점의 값보다 찾고자하는 값이 큰 경우 오른쪽 확인하므로 시작 인덱스를 mid + 1로 지정
        return binary_search(array, target, mid + 1, end)


# n(원소의 개수)와 target(찾고자하는 문자열)을 입력받기
n, target = list(map(int, input().split()))

# 전체 원소 입력받기
array = list(map(int, input().split()))

# 이진 탐색 수행 결과 출력
result = binary_search(array, target, 0, n - 1)

if result is None:
    print("원소가 존재하지 않습니다.")
else:
    print(result + 1)


""" 반복문으로 구현"""
def binary_search_for(array, target, start, end):
    while start <= end:
        mid = (start + end) // 2

        # 찾은 경우 중간점 인덱스 반환
        if array[mid] == target:
            return mid
        # 중간점의 값보다 찾고자하는 값이 작은 경우 왼쪽 확인
        elif array[mid] > target:
            end = mid - 1
        # 중간점의 값보다 찾고자하는 값이 큰 경우 오른쪽 확인
        else:
            start = mid + 1


"""라이브러리 사용"""
from bisect import bisect_left, bisect_right

a = [1, 2, 4, 4, 8]
x = 4

print(bisect_left(a, x))  # 2
print(bisect_right(a, x))  # 4