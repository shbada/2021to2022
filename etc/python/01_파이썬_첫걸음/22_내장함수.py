# coding=utf-8

# abs : 모두 참이여야 True (and)
print(abs(3))  # 3
print(abs(-3))  # 3
print(abs(-1.2))  # 1.2

# all : 하나라도 참이 있을 경우 True (or)
print(all([1, 2, 3]))  # True
print(all([1, 2, 3, 0]))  # False (0 : 거짓이 포함되어있으므로)
print(all([]))  # 인수가 빈 값인 경우에는 True를 리턴

# any
print(any([1, 2, 3, 0]))  # True
print(any([0, ""]))  # False (모두 거짓이므로)
print(any([]))  # False (빈 값인 경우)

# chr : 아스키코드 출력
print(chr(97))  # 'a'
print(chr(48))  # '0'

# dir : 객체가 자체적으로 가지고 있는 변수나 함수 출력
print(dir([1, 2, 3]))  # ['append', 'count', 'extend', 'index', 'insert', 'pop',...]

# divmod : a를 b로 나눈 몫과 나머지를 튜플 형태로 돌려주는 함수
divmod(7, 3) # (2, 1)

# enumerate : 순서가 있는 자료형(리스트, 튜플, 문자열)을 입력으로 받아 인덱스 값을 포함하는 enumerate 객체 리턴
for i, name in enumerate(['body', 'foo', 'bar']):
    print(i, name)
"""
0 body
1 foo
2 bar
"""

# eval : 실행 가능한 문자열(1+2, 'hi' + 'a' 같은 것)을 입력으로 받아 문자열을 실행한 결괏값
print(eval('1+2'))  # 3
print(eval("'hi' + 'a'"))  # 'hia'
print(eval('divmod(4, 3)'))  # (1, 1)


# filter
# 첫 번째 인수 : 함수 이름
# 두 번째 인수 : 그 함수에 차례로 들어갈 반복 가능한 자료형을 받는다.
# 반환 값이 참인 것만 묶어서(걸러 내서) 돌려준다.
def positive(l):
    result = []
    for i in l:
        if i > 0:
            result.append(i)
    return result

print(positive([1,-3,2,0,-5,6])) # [1, 2, 6] : 양수값만 걸러내는 함수 호출하여 리턴됨

# 위 코드를 아래 filter을 사용하여 수정 가능
def positive(x):
    return x > 0

print(list(filter(positive, [1, -3, 2, 0, -5, 6])))

# 람다 + filter 사용 가능
print(list(filter(lambda x: x > 0, [1, -3, 2, 0, -5, 6])))  # [1, 2, 6]

# hex : 16진수 출력
print(hex(234))  # '0xea'
print(hex(3))  # '0x3'

# id : 객체를 입력받아 객체의 고유 주소 값(레퍼런스)을 돌려주는 함수
a = 3
print(id(3))  # 135072304

# input : 사용자 입력을 받는 함수
a = input()
print(a)

b = input("Enter: ")
print(b)

# int : 문자열 형태의 숫자나 소수점이 있는 숫자 등을 정수 형태로 돌려주는 함수
print(int('3'))  # 3
print(int('11', 2))  # 3 (2진수로 표현된 11의 10진수 값)
print(int('1A', 16))  # 26 (16진수로 표현된 1A의 10진수 값)


# isinstance : 인스턴스가 그 클래스의 인스턴스인지를 판단
# 첫 번째 인수 : 인스턴스
# 두 번째 인수 : 클래스 이름
class Person: pass

a = Person()
print(isinstance(a, Person))  # True

# len : 입력값 s의 길이(요소의 전체 개수)를 돌려주는 함수
print(len("python"))  # 6
print(len([1, 2, 3]))  # 3
print(len((1, 'a')))  # 2

# list : 반복 가능한 자료형 s를 입력받아 리스트로 만들어 돌려주는 함수
print(list("python"))  # ['p', 'y', 't', 'h', 'o', 'n']
print(list((1, 2, 3)))  # [1, 2, 3]

# 리스트 복사
a = [1, 2, 3]
b = list(a)
print(b)  # [1, 2, 3]


# map : 입력받은 자료형의 각 요소를 함수 f가 수행한 결과를 묶어서 돌려주는 함수
def two_times(numberList):
    result = [ ]
    for number in numberList:
        result.append(number*2)
    return result

result = two_times([1, 2, 3, 4])
print(result) # 각 요소에 2를 곱한 결괏값을 돌려준다

# 위 코드를 map 사용으로 변환
def two_times(x):
    return x*2


print(list(map(two_times, [1, 2, 3, 4])))  # [2, 4, 6, 8]

# 람다
print(list(map(lambda a: a * 2, [1, 2, 3, 4])))  # [2, 4, 6, 8]

# max : 최댓값을 돌려주는 함수
print(max([1, 2, 3]))  # 3
print(max("python"))  # 'y'

# min : 최솟값을 돌려주는 함수
print(min([1, 2, 3]))  # 1
print(min("python"))  # 'h'

# oct : 정수 형태의 숫자를 8진수 문자열로 바꾸어 돌려주는 함수
print(oct(34))  # '0o42'
print(oct(12345))  # '0o30071'

# open : "파일 이름"과 "읽기 방법"을 입력받아 파일 객체를 돌려주는 함수
f = open("binary_file", "rb")

# ord : 문자의 아스키 코드 값을 돌려주는 함수
print(ord('a'))  # 97
print(ord('0'))  # 48

# pow : x의 y 제곱한 결괏값을 돌려주는 함수
print(pow(2, 4))  # 16
print(pow(3, 3))  # 27

# range : 입력받은 숫자에 해당하는 범위 값을 반복 가능한 객체로 만들어 돌려준다
print(list(range(5)))  # [0, 1, 2, 3, 4]

print(list(range(5, 10)))  # [5, 6, 7, 8, 9]
print(list(range(1, 10, 2)))  # [1, 3, 5, 7, 9]
print(list(range(0, -10, -1)))  # [0, -1, -2, -3, -4, -5, -6, -7, -8, -9]

# round : 숫자를 입력받아 반올림해 주는 함수
print(round(4.6))  # 5
print(round(4.2))  # 4
print(round(5.678, 2))  # 5.68 (실수 5.678을 소수점 2자리까지만 반올림)

# sorted : 입력값을 정렬한 후 그 결과를 리스트로 돌려주는 함수
# sort와는 다르다 (sort 함수는 리스트 객체 그 자체를 정렬)
print(sorted([3, 1, 2]))  # [1, 2, 3]
print(sorted(['a', 'c', 'b']))  # ['a', 'b', 'c']
print(sorted("zero"))  # ['e', 'o', 'r', 'z']
print(sorted((3, 2, 1)))  # [1, 2, 3]

# str : 문자열 형태로 객체를 변환하여 돌려주는 함수
print(str(3))  # '3'
print(str('hi'))  # 'hi'
print(str('hi'.upper()))  # 'HI'

# sum : 입력받은 리스트나 튜플의 모든 요소의 합을 돌려주는 함수
print(sum([1, 2, 3]))  # 6
print(sum((4, 5, 6)))  # 15

# tuple : 반복 가능한 자료형을 입력받아 튜플 형태로 바꾸어 돌려주는 함수
print(tuple("abc"))  # ('a', 'b', 'c')
print(tuple([1, 2, 3]))  # (1, 2, 3)
print(tuple((1, 2, 3)))  # (1, 2, 3)

# type : 입력값의 자료형이 무엇인지 알려 주는 함수
print(type("abc"))  # <class 'str'>
print(type([]))  # <class 'list'>
print(type(open("test", 'w')))  # <class '_io.TextIOWrapper'>

# zip : 동일한 개수로 이루어진 자료형을 묶어 주는 역할을 하는 함수
# zip(*iterable) : *iterable은 반복 가능(iterable)한 자료형 여러 개를 입력할 수 있다는 의미
print(list(zip([1, 2, 3], [4, 5, 6])))  # [(1, 4), (2, 5), (3, 6)]
print(list(zip([1, 2, 3], [4, 5, 6], [7, 8, 9])))  # [(1, 4, 7), (2, 5, 8), (3, 6, 9)]
print(list(zip("abc", "def")))  # [('a', 'd'), ('b', 'e'), ('c', 'f')]
