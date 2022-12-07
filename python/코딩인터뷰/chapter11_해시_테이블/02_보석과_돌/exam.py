"""
J는 보석이다.
S는 갖고있는 돌이다.

S에는 보석이 몇 개나 있을까? 대소문자는 구분한다.

입력
J = "aA", S = "aAAbbbb"

출력: 3
"""

freqa = {}
J = "aA"
S = "aAAbbbb"

# 돌 (S) 의 빈도 수 계산
for char in S:
    if char not in freqa:
        freqa[char] = 1
    else:
        freqa[char] += 1


count = 0

# 보석 (J)의 빈도 수 합산
for char in J:
    if char in freqa:
        count += freqa[char]


print(count)
