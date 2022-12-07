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


def topKFrequent(self, nums: List[int], k: int) -> List[int]:
    freqs = collections.Counter(nums)
    freqs_heap = []

    # 힙에 음수로 삽입
    for f in freqs:
        heapq.heappush(freqs_heap, (-freqs[f], f))

    topk = list()


    # k번 만큼 추출, 최소 힙(Min Heap)이므로 가장 작은 음수 순으로 추출
    for _ in range(k):
        topk.append(heapq.heappop(freqs_heap)[1])

    return topk

