from typing import List


class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        # i(인덱스), n(원소)
        for i, n in enumerate(nums):
            # n + complement = tartget 을 만족하는 complement 값 지정
            complement = target - n

            # 지정된 값이 리스트에 존재하는가? (자기자신을 제외)
            if complement in nums[i + 1:]:
                # 존재한다면, n 의 index 와 현재 원소를 제외한 리스트 중 complement의 index
                # 여기서 의문점 (i + 1) 을 왜할까?
                """
                    아래에서 i+1: 이기 때문에 i+1 인덱스를 시작점으로 탐색한다.
                    그렇게 되었을때, 원래 리스트의 인덱스와 찾아지는 인덱스가 달라진다.
                    달라지는 만큼 더해줘야한다. ((i + 1) 만큼 더해줘야한다.)
                    
                    만약, nums[i + 1:] 에 모두 첫번재 원소에 있다고 가정하자.
                    i = 0) 0 -> 원래의 리스트에서는 1 ( (i+1) 만큼 더해줘야한다 ) 
                    i = 1) 0 -> 원래의 리스트에서는 2 ( (i+1) 만큼 더해줘야한다 ) 
                    i = 2) 0 -> 원래의 리스트에서는 3 ( (i+1) 만큼 더해줘야한다 ) 
                """
                return [nums.index(n), nums[i + 1:].index(complement) + (i + 1)]
