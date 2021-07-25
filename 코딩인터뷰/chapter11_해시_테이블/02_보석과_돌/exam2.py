"""
J는 보석이다.
S는 갖고있는 돌이다.

S에는 보석이 몇 개나 있을까? 대소문자는 구분한다.

입력
J = "aA", S = "aAAbbbb"

출력: 3
"""
import collections


def newJeweIsInStones(self, J: str, S: str) -> int:
    # 키 존재 여부에 상관없는 defaultdict 사용 ->
    freqs = collections.defaultdict(int)

    count = 0

    # 비교 없이 돌(S) 빈도 수 계산
    for char in S:
        freqs[char] += 1

    # 비교 없이 보석(J) 빈도 수 합산
    for char in J:
        count += freqs[char]