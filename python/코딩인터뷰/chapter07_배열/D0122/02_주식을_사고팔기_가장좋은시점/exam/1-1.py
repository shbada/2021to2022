# 브루트 포스로 계산
# 이중 for문 사용 (사고팔고를 반복하여 마지막 최대 이익을 산출한다.)
from typing import List


def maxProfix(self, prices: List[int]) -> int:
    max_price = 0

    # 해당 풀이로는 타임아웃 문제가 발생할 것
    for i, price in enumerate(prices):
        for j in range(i, len(prices)):
            # 계속 사고 팔고를 하며 최대값을 추출한다.
            max_price = max(prices[j] - price, max_price)

    return max_price

