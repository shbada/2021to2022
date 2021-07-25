# 가장 큰 수를 k번 반복
# 두번째로 작은 수를 k번 반복 후 넣고 또다시 가장 큰수 반복
# 위를 실행하며 M개 미만인지 체크해야한다.

test = [2, 4, 5, 4, 6]

m = 8  # 개수 제한
k = 3  # 반복 개수 제한

# 가장 큰 수
max_value = max(test)

# 가장 큰수 제거
test.pop(test.index(max_value))
print(test)

# 가장 큰수가 제거된 리스트에서 가장 큰 수 (처음 리스트의 두번째 큰 수)
max_second = max(test)

result = 0

# k 를 담은 변수 선언
copy_k = k

for i, v in enumerate(range(m)):
    # 가장큰 수를 k번 더했을때 두번째로 가장 큰 수를 더하고 copy_k 를 다시 k로 셋팅
    if copy_k == 0:
        result += max_second
        copy_k = k
    else:
        # 가장 큰 수를 더한다.
        result += max_value
        copy_k = copy_k - 1


print(result)
