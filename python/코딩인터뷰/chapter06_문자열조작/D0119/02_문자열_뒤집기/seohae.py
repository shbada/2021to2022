# https://leetcode.com/problems/reverse-string/

# swap 실행 필요
# left (왼쪽부터 시작)
# right (오른쪽부터 시작)
# 종료의 조건
# 1) left == right or left > right => left >= right 일 경우

test_list = ["h", "e", "l", "l", "o"]

left = 0
right = len(test_list) - 1

while left < right:  # right 가 left 보다 작거나 같아지면 모든 원소를 뒤집은 것이다.
    temp = test_list[left]
    test_list[left] = test_list[right]
    test_list[right] = temp

    left = left + 1
    right = right - 1

print(test_list)
