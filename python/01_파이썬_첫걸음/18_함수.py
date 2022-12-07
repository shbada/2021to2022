# coding=utf-8

# 결과값이 있는 함
def add(a, b):
    return a + b

# 결과값이 없는 함수
def voidAdd(a, b):
    print("%d, %d의 합은 %d입니다." % (a, b, a+b))

a = 3
b = 4
c = add(a, b)
print(c)
print(add(3, 4))  # 3, 4는 인수
print(voidAdd(1,2))

a1 = add(3, 4)
print(a1)
a2 = voidAdd(3, 4)
print(a2) # None

# 매개변수 지정하여 호출
result = add(a=3, b=7)  # a에 3, b에 7을 전달
print(result)

result = add(b=5, a=3)  # b에 5, a에 3을 전달 (순서 상관없이 변수에 전달 가능)
print(result) # 8

# 입력값 제한 없는 함수
def add_many(*args):
    result = 0

    for i in args:
        result = result + i
        return result
result = add_many(1,2,3)
print(result) # 6

result = add_many(1,2,3,4,5,6,7,8,9,10)
print(result) # 55

# 키워드 파라미터 kwargs (딕셔너리로 처리)
def print_kwargs(**kwargs):
    print(kwargs)

print_kwargs(a=1) # {'a': 1}
print_kwargs(name='foo', age=3) # {'age': 3, 'name': 'foo'}

# return
def add_and_mul(a,b):
    return a+b, a*b # 에러 발생하지 않음

result = add_and_mul(3,4) # 튜플을 갖게됨
print result # (7, 12)

# return (함수 탈출)
def say_nick(nick):
    if nick == "바보":
        return
    print("나의 별명은 %s 입니다." % nick)

say_nick('야호') # 나의 별명은 야호입니다.
say_nick('바보') # ''

# 함수 초깃값 설정
def say_myself(name, old, man=True):
    print("나의 이름은 %s 입니다." % name)
    print("나이는 %d살입니다." % old)
    if man:
        print("남자입니다.")
    else:
        print("여자입니다.")

say_myself("박응용", 27)
say_myself("박응용", 27, True)

# 매개변수 순서 주의
"""
def say_myself2(name, man=True, old):
    print("나의 이름은 %s 입니다." % name)
    print("나이는 %d살입니다." % old)
    if man:
        print("남자입니다.")
    else:
        print("여자입니다.")

say_myself2("박응용", 27) # 27을 man 변수와 old 변수 중 어느 곳에 대입해야 할지 알 수 없다.
"""

# 변수의 효력 범위
a = 1
def vartest(a):
    a = a +1
    print(a) # 여기서 a는 vertest 함수만의 변수이다.

vartest(a) # 2
print(a) # 1

# 함수 안에서 함수 밖의 변수 변경하기
a = 1
def vartest(a):
    a = a +1
    return a

a = vartest(a)
print(a)

# global
a = 1
def vartest():
    global a # 전역변수 a를 뜻함
    a = a+1

    print(a)

vartest() # 2
print(a) # 2

# lambda
# lambda는 함수를 생성할 때 사용하는 예약어로 def와 동일한 역할
add = lambda a, b: a+b # 매개변수 a,b 결과 a+b
result = add(3, 4)
print(result) # 7

def add(a, b):
    return a+b

result = add(3, 4) # 7
print(result) # 7


