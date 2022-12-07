# coding=utf-8
a = [1, 2, 3]
print(a[0])  # 1
print(a[1])  # 2
print(a[2])  # 3
print(a[0] + a[2])  # 1 + 3 = 4
print(a[-1])  # 마지막 요소값

a = [1, 2, 3, ['a', 'b', 'c']]
print(a[0])  # 1
print(a[-1])  # ['a', 'b', 'c']
print(a[3])  # ['a', 'b', 'c']

print(a[-1][0])  # 'a' (['a', 'b', 'c'] 에서 0번째 요소)
print(a[-1][2])  # 'c'

a = [1, 2, ['a', 'b', ['Life', 'is']]]
print(a[2])  # ['a', 'b', ['Life', 'is']]
print(a[2][2])  # ['Life', 'is']
print(a[2][2][0])  # 'Life'

# 리스트의 슬라이싱
a = [1, 2, 3, 4, 5]
print(a[0:2])  # [1, 2]

# 중첩된 리스트에서 슬라이싱
a = [1, 2, 3, ['a', 'b', 'c'], 4, 5]
print(a[2:5])  # [3, ['a', 'b', 'c'], 4]
print(a[3])  # ['a', 'b', 'c']
print(a[3][:2])  # ['a', 'b']

# 리스트 더하기(+)
a = [1, 2, 3]
b = [4, 5, 6]
print(a + b)  # [1, 2, 3, 4, 5, 6]

# 리스트 반복하기(*)
a = [1, 2, 3]
print(a * 3)  # [1, 2, 3, 1, 2, 3, 1, 2, 3]

# 리스트 길이 구하기
a = [1, 2, 3]
print(len(a))  # 3

# 리스트 연산 오류
a = [1, 2, 3]
# print a[2] + "hi" 에러발생 (숫자형 + 문자열 불가능)
print(str(a[2]) + "hi")  # 3hi

# 리스트의 수정과 삭제
a = [1, 2, 3]
a[2] = 4
print(a)  # [1, 2, 4]

a = [1, 2, 3]
del a[1]
print(a)  # [1, 3]

a = [1, 2, 3, 4, 5]
del a[2:]
print(a)  # [1, 2]

# append : 요소 추가
a = [1, 2, 3]
a.append(4)
print(a)  # [1, 2, 3, 4]

a.append([5,6])
print(a)  # [1, 2, 3, 4, [5, 6]]

# sort : 정렬
a = [1, 4, 3, 2]
a.sort()
print(a)  # [1, 2, 3, 4]

a = ['a', 'c', 'b']
a.sort()
print(a)  # ['a', 'b', 'c']

# reverse : 뒤집기
a = ['a', 'c', 'b']
a.reverse()
print(a)  # ['b', 'c', 'a']

# index : 위치 반환
a = [1,2,3]
print(a.index(3))  # 2
print(a.index(1))  # 0
print(a.index(0))  # 0은 찾을 수 없으므로 에러발생

# insert : 리스트 요소 삽입
a = [1, 2, 3]
a.insert(0, 4) # 0번째 위치에 4를 삽입
print(a)  # [4, 1, 2, 3]

# remove : 리스트 요소 제거
a = [1, 2, 3, 1, 2, 3]
a.remove(3)
print(a)  # [1, 2, 1, 2, 3] a가 3이라는 값을 2개 가지고 있을 경우 첫 번째 3만 제거된다.

# pop : 리스트 요소 끄집어내기 (리스트의 x번째 요소를 돌려주고 그 요소는 삭제)
a = [1,2,3]
a.pop() # 3
print(a)  # [1, 2]

# count : 리스트에 포함된 x의 개수세기
a = [1,2,3,1]
print(a.count(1))  # 2

# extend : 리스트 확장 (x에는 리스트만 올 수 있으며 원래의 a 리스트에 x 리스트를 더하게 된다.)
a = [1,2,3]
a.extend([4,5])
print(a)  # [1, 2, 3, 4, 5]

b = [6, 7]
a.extend(b)
print(a)  # [1, 2, 3, 4, 5, 6, 7]


