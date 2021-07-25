# https://leetcode.com/problems/3sum/

# 세수의 합으로 0 만들기
# 리스트 순서대로 탐색
# i, j, k -> j = i+1, k = i+2

nums = [-1, 0, 1, 2, -1, -4]
results = []

for i, value in enumerate(nums[:-2]):
    # print(nums)
    for j, value2 in enumerate(nums[i + 1:-1]):
        # print(nums[i + 1:])
        for k, value3 in enumerate(nums[i + 2:]):
            # print(nums[i + 2:])
            if value + value2 + value3 == 0:
                results.append([value, value2, value3])
                print(results)
                break

# 문제점발생 : 중복을 없앨 수 없다.


