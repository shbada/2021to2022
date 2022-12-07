# 입력 : [7, 1, 5, 3, 6, 4]
# 출력 : 5

# 최소 값일때 구매
# 최대 값일떄 판매
# 최대 - 최소 출력 (이것이 최대 이익)

list = [7, 1, 5, 3, 6, 4]

min_value = min(list)  # 최소값 저장
min_index = list[min_value]  # 최소값 인덱스 저장

print(list[min_value])  # 최소 값의 인덱스 찾기

if min_index == len(list) - 1:
    print(0)
else:
    # 반복문 실행
    for i, v in enumerate(list):
        if i < min_index:  # 최소값 인덱스보다 왼쪽은 pop (이유: 최소값일때 사는게 이득이고, 그 이전의 최대는 의미가 없다.)
            list.pop(i)

    # pop 으로 정리된 리스트 중 최대값 얻기
    max_value = max(list)

    print(max_value - min_value)

"""
오답.
사유 : 만약 리스트가 [2, 4, 1]일 경우 최소 값이 1인데 이때는 2일때 사서 4일때 파는게 이득이다.
결국, 반복문을 통해 리스트를 순회하면서 min, max를 계산해야한다.
"""

