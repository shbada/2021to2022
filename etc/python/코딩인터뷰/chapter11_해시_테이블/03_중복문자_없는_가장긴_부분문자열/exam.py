"""
중복 문자가 없는 가장 긴 부분 문자열 (substring)의 길이를 리턴하라.

입력
"abcabcbb"

출력
3

= 정답은 "abc"로 길이는 3이다.
"""
import collections

S = "abcabcbb"

# 키 존재 여부에 상관없는 defaultdict 사용 ->
freqs = collections.defaultdict(int)

count = 0

for char in S:
    if char not in freqs:
        freqs[char] = 1
        count += 1
    else:
        break

print(count)

