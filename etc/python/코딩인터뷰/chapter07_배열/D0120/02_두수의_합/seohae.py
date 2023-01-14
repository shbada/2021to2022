# https://leetcode.com/problems/two-sum/

# 반복문
# 반복문 요소와 어떤 값을 더해야 target 이 되는가?

# 입력 [2, 7, 11, 15], target = 9
# 출력 [0, 1] -> nums[0] + nums[1] = 9

nums = [2, 7, 11, 15]
target = 9

for a in nums:
    value = target - a
    print(value)
    print(nums[a - 1:])

    if value in nums[a - 1:]:
        print(nums.index(a), nums.index(value))
        break



