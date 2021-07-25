"""
k번 이상 등장하는 요소를 추출하라.

입력
nums = [1, 1, 1, 2, 3, 3
k = 2

출력
[1, 2]
"""
import collections
import heapq
from typing import List


def topKFrequent(self, nums: List[int], k: int):
    return list(zip(*collections.Counter(nums).most_common(k)))[0]

