"""
k번 이상 등장하는 요소를 추출하라.

입력
nums = [1, 1, 1, 2, 2, 3]
k = 2

출력
[1, 2]
"""
import collections

S = [1, 1, 1, 2, 2, 3]
k = 2

result = []

freqa = collections.Counter(S)
count = 0

print(freqa)

for f in freqa:
    if freqa[f] > 1:
        result.append(f)

print(result)