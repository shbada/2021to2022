from typing import List


class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        nums_map = {}

        # 키와 값을 바꿔서 딕셔너리로 저장
        for i, num in enumerate(nums):
            nums_map[num] = i

        # 타겟에서 첫 번째 수를 뺀 결과를 키로 조회
        for i, num in enumerate(nums):
            # num + ( ) = tartget 일때 ( ) 값이 딕셔너리에 있고, 자기 자신이 아닐 경우
            # nums_map[( )] 은 index 를 가지고있다. (위에서 key를 원소로, value를 인덱스로 바꿨기 때문)
            if target - num in nums_map and i != nums_map[target - num]:
                return [i, nums_map[target - num]]
