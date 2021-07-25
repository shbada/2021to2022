# https://leetcode.com/problems/array-partition-i/

# min(a, b)의 합으로 만들 수 있는 가장 큰수
# 정렬
# 앞에서 2개씩 묶기
# 작은겄기리 묶고 큰것끼리 묶어야 최소값의 합이 가장 커진다
# [1, 4, 3, 2] -> [1, 2, 3, 4]
# 앞에서부터 2개씩 묶었을때 짝수번째의 원소만 추출하면 된다.

nums = [1, 4, 3, 2]

nums.sort()

result = 0

# 반복문 인덱스를 사용하여 짝수번째 원소만 추출
for i, a in enumerate(nums):
    if i % 2 == 0:
        result += a

print(result)
