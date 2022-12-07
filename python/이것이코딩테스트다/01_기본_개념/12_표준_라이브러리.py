# 실전에서 유용한 표준 라이브러리

"""

1) 내장함수
= 기본 입출력 함수 ~ 정렬 함수까지의 기본적인 함수 제공

2) itertools
= 반복되는 형태의 데이터를 처리하기 위한 유용한 기능 제공 (순열/조합 라이브러리 등)

3) heapq
= 힙 자료구조를 제공 (우선순위 큐 기능 구현시에 사용)

4) bisect
= 이진탐색 기능 제공

5) collections
= 덱, 카운터 등 유용한 자료구조 포함

6) math
= 필수적인 수학적 기능 제공

"""

# sum(), min(), max()
# eval() : 계산 결과를 숫자 형태로 리턴해주는 함수
# sorted(), sorted() with key


# 순열 : 서로 다른 n 개에서 서로 다른 r 개를 선택하여 일렬로 나열하는 것
# ex) {'A', 'B', 'C'} => 'ABC', 'ACB', 'BAC', 'BCA', 'CAB', 'CBA'

# 조합 : 서로 다른 n개에서 순서에 상관 없이 서로 다른 r개를 선택하는 것
# ex) {'A', 'B', 'C'} : 'AB', 'AC', 'BC'


# 순열 라이브러리
from itertools import permutations

data = ['A', 'B', 'C']

result = list(permutations(data, 3))  # 모든 순열 구하기
print(result)

# 조합 라이브러리
from itertools import combinations

data = ['A', 'B', 'C']

result = list(combinations(data, 2))
print(result)

# 중복 순열
from itertools import product

data = ['A', 'B', 'C']

result = list(product(data, repeat=2))
print(result)

# 중복 조합
from itertools import combinations_with_replacement

data = ['A', 'B', 'C']

result = list(combinations_with_replacement(data, 2))
print(result)

# Counter : 등장 횟수를 세는 기능 제공
# 내부의 원소가 몇번씩 등장했는지 알려준다.

from collections import Counter

counter = Counter(['red', 'red', 'blue'])

print(counter['blue'])  # 1
print(counter['red'])  # 2
print(dict(counter))  # 사전자료형으로 반환 : {'red': 2, 'blue': 1}


# 최대공약수/최대공배수 구하기
import math


# 최대 공배수 구하기
def lcm(a, b):
    return a * b // math.gcd(a, b)  # gcd 함수: 최대공약수 얻기





