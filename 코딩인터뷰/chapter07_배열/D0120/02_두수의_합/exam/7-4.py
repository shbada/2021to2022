from typing import List


class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        nums_map = {}

        # 하나의 `for`문으로 통합
        for i, num in enumerate(nums):
            # 들어간 num_map 중에 값을 포함할때까지 찾는다.
            if target - num in nums_map:
                return [nums_map[target - num], i]

            # key 에 원소를, value 에 인덱스를 넣는다.
            nums_map[num] = i
