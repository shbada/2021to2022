from typing import List


class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        # 첫번째 반복문
        for i in range(len(nums)):
            # 두번째 반복문 (i + 1로 시작점을 지정하여, i 다음 요소부터 탐색)
            for j in range(i + 1, len(nums)):
                # 알고리즘 조건 만족의 경우
                if nums[i] + nums[j] == target:
                    return [i, j]
