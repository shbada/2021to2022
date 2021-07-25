# 201. "비트코인" 문자열을 화면에 출력하는 print_coin() 함수를 정의
def print_coin():
    print("비트코인")


# 202. 201번에서 정의한 함수를 호출하라.
print_coin()

# 203. 201번에서 정의한 print_coin 함수를 100번호출하라.
for i in range(100):
    print_coin()


# 204. "비트코인" 문자열을 100번 화면에 출력하는 print_coins() 함수를 정의하라.
def print_coins():
    for i in range(100):
        print("비트코인", i)


# 205. 아래의 에러가 발생하는 이유에 대해 설명하라.
# hello()  > 함수가 정의되기 전에 호출되어서 에러가 발생합니다.
def hello():
    print("Hi")


# 206. 아래 코드의 실행 결과를 예측하라. ABCAB
def message():
    print("A")
    print("B")


message()
print("C")
message()

# 207. 아래 코드의 실행 결과를 예측하라. ACB
print("A")


def message():
    print("B")


print("C")
message()

# 208. 아래 코드의 실행 결과를 예측하라. ACBED
print("A")


def message1():
    print("B")


print("C")


def message2():
    print("D")


message1()
print("E")
message2()


# 209. 아래 코드의 실행 결과를 예측하라. BA
def message1():
    print("A")


def message2():
    print("B")
    message1()


message2()


# 210. 아래 코드의 실행 결과를 예측하라. BCBCBCA
def message1():
    print("A")


def message2():
    print("B")


def message3():
    for i in range(3):
        message2()
        print("C")
    message1()


message3()
