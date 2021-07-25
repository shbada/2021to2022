from typing import List


class Solution:
    def arrayPairSum(self, nums: List[int]) -> int:
        sum = 0
        pair = []

        # 정렬
        nums.sort()

        for n in nums:
            # 앞에서 부터 오름차순으로 페어를 만들어 합 계산
            pair.append(n)

            # pair 리스트에 2개씩 쌓일때마다 실행한다는 의미
            if len(pair) == 2:
                sum += min(pair)
                pair = []  # 2개가 쌓였으면 다시 리셋하여 그 다음 2개 쌓일때를 기다림

        return sum
