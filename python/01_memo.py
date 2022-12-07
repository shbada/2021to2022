# 문자열을 한 글자씩 뺄 수 있다.
import collections
from typing import Deque, re

param = "abced"

for char in param:
    pass

# 영문/숫자/한글인지 체크할 수 있다.
for char in param:
    if char.isalnum():
        pass

# 리스트에서 pop(0)과 pop()으로 꺼내기 (꺼냄과 동시에 삭제)
# pop(0) : 앞에서부터 꺼내기
# pop() : 뒤에서부터 꺼내기
param_list = [1, 2, 3, 4]

while len(param_list) > 1:
    if param_list.pop(0) != param_list.pop():
        pass

# Deque 사용 (자료형)
# popleft() : 앞에서부터 꺼내기
param_list: Deque = collections.deque()

while len(param_list) > 1:
    if param_list.popleft() != param_list.pop():
        pass

# 리스트 뒤집기
param_list = [1, 2, 3, 4, 5, 6, 7]
param_reverse_list = param_list[::-1]
param_list.reverse()

# 숫자인지 체크하기
for char in param:
    if char.isdigit():
        pass

# defaultdict 딕셔너리 - {"key" : value}
counts = collections.defaultdict(int)  # default 가 int
max(counts, key=counts.get) # 딕셔너리 안에서 max 값 구하기

anagrams = collections.defaultdict(list)
# defaultdict(<class 'list'>, {'aet': ['eat', 'tea', 'ate'], 'ant': ['tan', 'nat'], 'abt': ['bat']})
anagrams[''.join(sorted("ate"))].append("ate")  # 정렬된 값이 key로 데이터가 들어간다. key가 같으면 value에 추가된다.

# 정규표현식을 사용하여 단어 문자인치 체크
paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
banned = ["hit"]

# 단어 문자가 아닌 모든 문자를 공백으로 치환한다.
words = [word for word in re.sub(r'[\W]', ' ', paragraph)
    .lower().split()
         if word not in banned]  # 금지단어 제외

# Counter 을 사용하여 리스트에 있는것을 key 값 : 개수 로 변환하기
counts = collections.Counter(words)
print(counts)  # Counter({'ball': 2, 'bob': 1, 'a': 1, 'the': 1, 'flew': 1, 'far': 1, 'after': 1, 'it': 1, 'was': 1})
# most_common(1) : 가장 흔하게 등장하는 단어의 첫 번째 값 추출 : [('ball', 2)]
counts.most_common(1)[0][0]  # ball

# 리스트를 인덱스, 값으로 꺼내는 방법
for i, n in enumerate(param_list):
    pass





