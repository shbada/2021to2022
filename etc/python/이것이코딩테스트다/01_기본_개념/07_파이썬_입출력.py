# 기본 입출력
# 모든 프로그램은 적절한 입출력 양식을 가지고있다.

# input() : 한줄의 문자열을 입력받는 함수
# map() : 리스트의 모든 원소에 각각 특정한 함수를 적용할대 사용

n = int(input())
print(n)

b = input()
print(b)
print(b.split())  # ['1', '3', '5']

data = list(map(int, b.split()))  # [1, 3, 5] => int형 자료형으로 변환해준다.
print(data)


# 입력 데이터 개수가 정해져있을 경우 사용할것
a, b, c = map(int, b.split())  # 개수가 다르다면 에러가 발생한다.
print(a)
print(b)
print(c)


# 입력을 빠르게 받기 (라이브러리 sys > sys.stdin.readline())
# 엔터가 줄 바꿈 기호로 입력되므로 rstrip() 메서드를 함께 사용해야한다.

import sys

data = sys.stdin.readline().rstrip()
print(data)


# 파이썬의 기본 출력은 print()
# 여러개의 데이터를 콤마(,)를 이용하면 띄어쓰기 구분하여 순차적으로 출력 가능하다.
# 줄바꿈이 된다. => 줄바꿈을 원하지 않는 경우 'end' 속성을 정의한다.

a = 1
b = 2
print(a, b)

print(7, end=" ")
print(8, end=" ")

answer = 7
print("정답은 " + str(answer) + "입니다.")  # 문자열 + 정수형을 하기 위해서는 형변환이 필요하다.


# f-string
# 문자열 앞에 f를 붙여 사용한다.

print(f"정답은 {answer}입니다.")  # 변수명을 기입하여 간단히 문자열과 정수를 함께 넣을 수 있다.


