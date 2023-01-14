# 순차 탐색
# 순차탐색 : 리스트 안에 있는 특정한 데이터 찾기 위해 앞에서부터 데이터를 하나씩 확인한다.

# 순차탐색 소스코드 구현
def sequential_search(n, target, array):
    for i in range(n):  # 각 원소를 순차적으로 하나씩 확인
        if array[i] == target:  # 현재의 원소가 찾고자하는 원소와 동일한 경우
            return i + 1


print("생성할 원소 개수를 입력한 다음 한칸 띄고 찾을 문자열을 입력하세요.")
input_data = input().split()
n = int(input_data[0])  # 원소의 개수
target = input_data[1]  # 찾고자 하는 문자열

print("앞서 적은 개수만큼 문자열을 입력하세요. 구분은 띄어쓰기 한칸으로 합니다.")
array = input().split()

# 순차탐색 수행 결과 출력
print(sequential_search(n, target, array))
