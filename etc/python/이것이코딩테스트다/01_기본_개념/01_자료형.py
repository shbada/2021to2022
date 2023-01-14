# 정수형(Integer) : 정수를 다루는 자로형 (양의정수, 음의정수, 0 포함)

a = 777
print(a)

a = -5
print(a)

a = a + 5
print(a)

# 실수형(Real Number) : 소수점 아래의 데이터를 포함하는 수 자료형

a = 157.93
print(a)

a = -1847.2
print(a)

a = 5.  # 0 생략 가능
print(a)

a = -.7  # 0 생략 가능
print(a)

# 지수 표현 방식 : e나 E 다음에 오는 수는 10의 지수부를 의미한다. ex) 1e9 (10의 9제곱) => 실수형 데이터

a = 1e9
print(a)
print(int(1e9))  # 정수형으로 변환

a = 75.25e1
print(a)

a = 4965e-3  # 음의 지수부
print(a)

# 2진수에서는 0.9를 정확히 표현할 방법이 없고, 컴퓨터는 최대한 0.9와 가깝게 표현하지만 미세한 오차가 발생하게된다.
a = 0.3 + 0.6
print(a)  # 0.8999999999999999

if a == 0.9:
    print(True)
else:
    print(False)  # False 출력

# => 실수값은 round() 함수를 사용하는 방법이 권장된다.
a = 0.3 + 0.6
print(round(a, 4))  # 0.9

if round(a, 4) == 0.9:
    print(True)  # True 출력
else:
    print(False)

# 파이썬은 나누기 연산자(/) 사용시 실수형으로 반환한다.
a = 4 / 2
print(a)  # 2.0
print(int(a))  # 2

# 연산
a = 7
b = 3

print(a / b)  # 나누기
print(a % b)  # 나머지
print(a // b)  # 몫
