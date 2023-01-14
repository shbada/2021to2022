# coding=utf-8

# for-in 문
test_list = ['one', 'two', 'three']
for i in test_list:
    print(i)

# 리스트 (리스트 안의 요소값 - 튜플)
a = [(1,2), (3,4), (5,6)]
for (first, last) in a:
    print(first + last)

# "총 5명의 학생이 시험을 보았는데 시험 점수가 60점이 넘으면 합격이고 그렇지 않으면 불합격이다. 합격인지 불합격인지 결과를 보여 주시오."
marks = [90, 25, 67, 45, 80]

number = 0
for mark in marks:
    number = number +1

    if mark >= 60:
        print("%d번 학생은 합격입니다." % number)
    else:
        print("%d번 학생은 불합격입니다." % number)

# for-continue
marks = [90, 25, 67, 45, 80]

number = 0
for mark in marks:
    number = number +1
    if mark < 60:
        continue
    print("%d번 학생 축하합니다. 합격입니다. " % number)

# range(10) : 0부터 10 미만의 숫자를 포함하는 range 객체 생
a = range(10)
print(a)  # range(0, 10) 0 이상 10 미만

add = 0
for i in range(1, 11):  # 1 이상 11 미만
    add = add + i  # 1 ~ 10까지의 합
    print(add)

# len
marks = [90, 25, 67, 45, 80]
for number in range(len(marks)):
    if marks[number] < 60:
        continue
    print("%d번 학생 축하합니다. 합격입니다." % (number+1))

# 구구단
"""
for i in range(2,10):        # ①번 for문
    for j in range(1, 10):   # ②번 for문
        print(i * j, end=" ") # 매개변수 end : 해당 결괏값을 출력할 때 다음줄로 넘기지 않고 그 줄에 계속해서 출력 실
        print('')
"""

# 리스트 내포 사용
a = [1,2,3,4]
result = []

for num in a:
    result.append(num*3)
    print(result) # [3, 6, 9, 12]

a = [1,2,3,4]
result = [num * 3 for num in a] # 리스트 내포
print(result) # [3, 6, 9, 12]

a = [1,2,3,4]
result = [num * 3 for num in a if num % 2 == 0] # 조건문까지
print(result) # [6, 12]

# 이중 for문
"""
[표현식 for 항목1 in 반복가능객체1 if 조건문1
        for 항목2 in 반복가능객체2 if 조건문2
        ...
        for 항목n in 반복가능객체n if 조건문n]

"""

result = [x*y for x in range(2,10)
                for y in range(1,10)]
print(result)
