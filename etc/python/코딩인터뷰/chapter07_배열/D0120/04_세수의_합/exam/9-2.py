from typing import List


class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        results = []

        # 정렬
        nums.sort()

        for i in range(len(nums) - 2):
            # 중복된 값 건너뛰기
            if i > 0 and nums[i] == nums[i - 1]:
                continue

            # 간격을 좁혀가며 합 `sum` 계산
            # left 는 i + 1번째로, right 는 맨마지막 원소로
            left, right = i + 1, len(nums) - 1

            # 반복문 실행 (left 가 right 보다 같거나 커진다는 것은 리스트를 모두 탐색했다는 의미)
            while left < right:
                sum = nums[i] + nums[left] + nums[right]

                if sum < 0:  # sum 이 0 보다 작으면 left + 1 을 해줌으로써 더 큰 값을 더한다.
                    left += 1
                elif sum > 0:  # sum 이 0 보다 면 right - 1 을 해줌으로써 더 작은 값을 더한다.
                    right -= 1
                else:
                    # `sum = 0`인 경우이므로 정답 및 스킵 처리
                    results.append([nums[i], nums[left], nums[right]])

                    # 아래 로직은 중복 제거를 위한 로직이다.

                    # left 가 right 보다 작을때
                    # nums[left] 가 다음 원소와 같을때 다음 원소 차례로 left 를 바꿔준다.
                    while left < right and nums[left] == nums[left + 1]:
                        left += 1

                    # left 가 right 보다 작을때
                    # nums[right] 가 right 의 다음 원소(왼쪽)와 같을때 다음(왼쪽) 원소 차례로 right 를 바꿔준다.
                    while left < right and nums[right] == nums[right - 1]:
                        right -= 1

                    # 다음 for 문의 left, right 를 지정해주기 위함이다.
                    left += 1
                    right -= 1

        return results


f = Solution()

# [['eat', 'tea', 'ate'], ['tan', 'nat'], ['bat']]
print(Solution.threeSum(f, [-1, 0, 1, 2, -1, -4]))