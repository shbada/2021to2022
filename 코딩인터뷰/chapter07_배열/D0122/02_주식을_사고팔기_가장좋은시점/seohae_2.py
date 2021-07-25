# 입력 : [7, 1, 5, 3, 6, 4]
# 출력 : 5

# 최소 값일때 구매
# 최대 값일떄 판매
# 최대 - 최소 출력 (이것이 최대 이익)

"""
첫번째 오답.
사유 : 만약 리스트가 [2, 4, 1]일 경우 최소 값이 1인데 이때는 2일때 사서 4일때 파는게 이득이다.
결국, 반복문을 통해 리스트를 순회하면서 min, max를 계산해야한다.
"""
import sys

list = [2, 4, 1]

min_value = sys.maxsize
result = 0

for value in list:
    min_value = min(value, min_value)
    result = max(result, value - min_value)

print(result)
