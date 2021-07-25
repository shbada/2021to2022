"""
J는 보석이다.
S는 갖고있는 돌이다.

S에는 보석이 몇 개나 있을까? 대소문자는 구분한다.

입력
J = "aA", S = "aAAbbbb"

출력: 3
"""
import collections


def numJewelsInStones(self, J: str, S: str) -> int:
    freqa = collections.Counter(S)
    count = 0

    for char in J:
        count += freqa[char]

    return count

