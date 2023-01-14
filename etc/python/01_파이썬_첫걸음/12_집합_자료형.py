# coding=utf-8

# set : 집합 자료형 생성
# 1) 중복 허용하지 않는다
# 2) 순서가 없
s1 = set([1,2,3])
print(s1)  # set([1, 2, 3])

s2 = set("Hello")
print(s2)  # set(['H', 'e', 'l', 'o'])

s1 = set([1,2,3])
l1 = list(s1)
print(l1)  # [1, 2, 3]
print(l1[0])  # 1

t1 = tuple(s1)
print(t1)  # (1, 2, 3)
print(t1[0])  # 1

# 교집합, 합집합
s1 = set([1, 2, 3, 4, 5, 6])
s2 = set([4, 5, 6, 7, 8, 9])

# &: 교집합
print(s1 & s2)  # set([4, 5, 6])

# | : 합집합
print(s1 | s2)  # {1, 2, 3, 4, 5, 6, 7, 8, 9}
print(s1.union(s2))  # {1, 2, 3, 4, 5, 6, 7, 8, 9}

print(s1 - s2)  # {1, 2, 3}
print(s2 - s1)  # {8, 9, 7}

print(s1.difference(s2))  # {1, 2, 3}
print(s2.difference(s1))  # {8, 9, 7}

# add : 값 추가 (1개)
s1 = set([1, 2, 3])
s1.add(4)
print(s1)  # {1, 2, 3, 4}

# update : 값 추가 (여러개)
s1 = set([1, 2, 3])
s1.update([4, 5, 6])
print(s1)  # {1, 2, 3, 4, 5, 6}

# remove : 값 삭제
s1 = set([1, 2, 3])
s1.remove(2)
print(s1)  # {1, 3}








