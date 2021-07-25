from typing import List


class Solution:
    def arrayPairSum(self, nums: List[int]) -> int:
        # 정렬 후 2개씩 건너띄며 합하기
        return sum(sorted(nums)[::2])
