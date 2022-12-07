# 함수 (Function)
# 특정한 작업을 하나의 단위로 묶어놓는것

# 내장 함수 : 파이썬이 기본적으로 제공하는 함수
# 사용자 정의 함수 : 개발자가 직접 정의하여 사용할 수 있는 함수

# 매개변수 : 함수 내부에서 사용할 변수
# 반환값 : 함수에서 처리된 결과를 반환

"""
def 함수명(매개변수):
    실행할 소스코드
    return 반환값
"""

# global 키워드로 함수 바깥에 선언된 변수를 참조해야한다.
a = 0


def func():
    global a  # global 키워드를 쓰지않으면 변수 a를 찾지못한다.
    a += 1


for i in range(10):
    func()

print(a)


# 리스트의 경우
array = [1, 2, 3, 4, 5]


def func():
    # 리스트는 global 없이 사용이 가능하다.
    array.append(6)
    print(array)


func()
print(array)


# 만약 동일한 변수명이 함수 내부에 변수로 존재할 경
array = [1, 2, 3, 4, 5]


def func():
    array = [3, 4, 5]
    array.append(6)  # 함수 내부의 지역변수 참조: [3, 4, 5, 6]
    print(array)


func()
print(array)  # 전역변수 참조: [1, 2, 3, 4, 5]


# global 전역변수 값 변
array = [1, 2, 3, 4, 5]


def func():
    # 만약 동일한 변수명이 함수 내부에 변수로 존재한다면
    global array  # 전역변수를 참조
    array = [3, 4, 5]  # 전역변수의 값을 바꾼다.
    array.append(6)  # 함수 내부의 지역변수 참조: [3, 4, 5, 6]
    print(array)


func()
print(array)  # 전역변수 참조 (함수에서 이미 변경됨) : [3, 4, 5, 6]경


# 파이썬은 여러개의 반환이 가능하다.

def test():
    return 1, 2, 3, 4

a, b, c, d = test()
print(a, b, c, d)

