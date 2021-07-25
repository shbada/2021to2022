from typing import List


class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        # left 는 0부터 시작, right 는 맨 마지막 원소부터 시작
        left, right = 0, len(nums) - 1

        # left 와 right 가 같지 않을때 까지 (가운데에서 만났을때 같을 것이다)
        while not left == right:
            # 합이 타겟보다 크면 오른쪽 포인터를 왼쪽으로
            if nums[left] + nums[right] < target:
                left += 1
            # 합이 타겟보다 작으면 왼쪽 포인터를 오른쪽으로
            elif nums[left] + nums[right] > target:
                right -= 1
            else:  # 위 if, elif 를 만족하지 않은 경우는 target 이 되었다는 경우 (정답)
                return [left, right]
