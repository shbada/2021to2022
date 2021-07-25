# 211. 함수의 호출 결과를 예측하라. 안녕\nHi
def 함수(문자열):
    print(문자열)


함수("안녕")
함수("Hi")


# 212. 함수의 호출 결과를 예측하라. 7 15
def 함수(a, b):
    print(a + b)


함수(3, 4)
함수(7, 8)


# 213. 아래와 같은 에러가 발생하는 원인을 설명하라.
def 함수(문자열):
     print(문자열)


# 함수()  > 함수에 정의와 맞는 파라미터를 호출해야한다.


# 214. 아래와 같은 에러가 발생하는 원인을 설명하라.
def 함수(a, b):
    print(a + b)


# 함수("안녕", 3)  > 해당 함수에서 "문자열" + 숫자를 실행하므로 에러가 발생한다.


# 215. 하나의 문자를 입력받아 문자열 끝에 ":D" 스마일 문자열을 이어 붙여 출력하는 print_with_smile 함수를 정의하라.
def print_with_smile (string):
    print (string + ":D")


# 216. 215에서 정의한 함수를 호출하라. 파라미터는 "안녕하세요"로 입력하라.
print_with_smile("안녕하세요")


# 217. 현재 가격을 입력 받아 상한가 (30%)를 출력하는 print_upper_price 함수를 정의하라.
def print_upper_price(price):
    print(price * 1.3)


# 218. 두 개의 숫자를 입력받아 두 수의 합을 출력하는 print_sum 함수를 정의하라.
def print_sum (a, b):
    print (a + b)


# 219. 두 개의 숫자를 입력받아 합/차/곱/나눗셈을 출력하는 print_arithmetic_operation 함수를 작성하라.
def print_arithmetic_operation(a, b):
    print(a, "+", b, "=", a + b)
    print(a, "-", b, "=", a - b)
    print(a, "*", b, "=", a * b)
    print(a, "/", b, "=", a / b)


# 220. 세 개의 숫자를 입력받아 가장 큰수를 출력하는 print_max 함수를 정의하라. 단 if 문을 사용해서 수를 비교하라.
def print_max(a, b, c):
    max_val = 0
    if a > max_val:
        max_val = a

    if b > max_val:
        max_val = b

    if c > max_val:
        max_val = c

    print(max_val)
