# coding=utf-8

# 변수 선언
a = 1
b = "python"
c = [1,2,3]

# 변수 메모리 주소 출력
a = [1, 2, 3]
print(id(a))  # 4303029896

# 리스트 복사
a = [1,2,3]
b = a

print(a)  # [1, 2, 3]
print(b)  # [1, 2, 3]

# is (동일한 객체인가?)
print(a is b)  # True

# a 리스트의 요소를 바꾸면 b도 함께 변경됨
a[1] = 4
print(a)  # [1, 4, 3]
print(b)  # [1, 4, 3]

# 위와 다르게, a 와 주소를 다르게 복사하기
a = [1, 2, 3]
b = a[:]

a[1] = 4
print(a)  # [1, 4, 3]
print(b)  # [1, 2, 3] (b는 바뀌지 않았다.)

# copy 모듈 사용하기
c = [5, 6, 7]

from copy import copy
d = copy(c)

c[1] = 4

print(c)  # [5, 4, 7]
print(d)  # [5, 6, 7] (b는 바뀌지 않았다.)

# c, d는 같은 객체인가?
print(b is a)  # False

# 튜플 만들기
a, b = ('python', 'life')
(a, b) = 'python', 'life' # 괄호 생략 가능

# 리스트 만들기
[a,b] = ['python', 'life']

# 여러개 변수에 값 대입
a = b = 'python'

# 두 변수의 값 바꾸기
a = 3
b = 5

a, b = b, a
print(a)  # 5
print(b)  # 3

