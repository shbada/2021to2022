from typing import List


class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        min_value = min(prices)  # 최소값 저장
        min_index = prices.index(min_value)  # 최소값 인덱스 저장

        if min_index == len(prices) - 1:
            return 0

        # 반복문 실행
        for i, v in enumerate(prices):
            if i < min_index:  # 최소값 인덱스보다 왼쪽은 pop (이유: 최소값일때 사는게 이득이고, 그 이전의 최대는 의미가 없다.)
                prices.pop(i)

        # pop 으로 정리된 리스트 중 최대값 얻기
        max_value = max(prices)

        return max_value - min_value


solution = Solution()
print(Solution.maxProfit(solution, [2, 4, 1]))
