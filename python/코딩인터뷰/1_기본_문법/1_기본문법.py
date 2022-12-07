# coding=utf-8

# 파이썬의 인덴트(Ident) : 스페이스바 공백 3칸 들여쓰기가 원칙
import sys

for i in range(0, 10):
    print(i)

# 네이밍 컨벤션(Naming Convention) : 스네이크 케이스(Snake Case) : _ 구분
coding_interview = 1
print(coding_interview)

# 타입 힌트
# 파이썬은 동적 타이핑 언어지만, 타입을 정할 수 있는 타입 힌트(Type Hint) 선언이 가능하다.
a: str = "1"
b: int = 1

"""
def fn(a): # 매개변수 a가 문자열 타입인지 숫자 타입인지알 수가 없고, 리턴 값도 알 수가 없다.
"""

"""
def fn(a: int) -> bool: # 매개변수 a의 타입과 리턴 값의 타입을 선언한다.
"""

# 파이썬은 동적으로 할당이 가능하므로 타입 변환에 주의하자.
a: str = 1
type(a) # <class 'int'> : 위 a는 문자열 타입인데, 숫자형 값으로 초기화하였다. 이런 현상을 주의하자.

# 리스트 컴프리헨션
print(list(map(lambda x: x + 10, [1,2,3]))) # [11, 12, 13]
print([n * 2 for n in range(1, 10 + 1) if n % 2 == 1]) # [2, 6, 10, 14, 18]

"""
a = {}
for key, value in original.items():
    a[key] = value

a = {key: value for key, value in original.items()}
"""

# 제너레이터
# 루프의 반복(Iteration) 동작을 제어할 수 있는 루틴 형테

# 예시1.
"""
암의의 조건으로 숫자 1억 개를 만들어내 계산하는 프로그램을 작성한다고 가정해보자.
제너레이터가 없다면, 메모리 어딘가에 만들어낸 숫자 1억 개를 보관하고 있어야한다.
그러나 제너레이터를 이용하면, 단순히 제너레이터만 생성해두고 필요할때 언제든 숫자를 만들어낼 수 있다.
만약에 1억개 중 100개 정도만 쓰인다면 차이는 더욱 클 것이다.
이때 yield 구문을 사용하면 제너레이터를 리턴할 수 있다. 
기존의 함수는 return 구문을 맞닥뜨리면 값을 리턴하고 모든 함수의 동작을 종료한다.
그러나 yield는 제너레이터가 여기까지 실행중이던 값을 내보낸다는 의미로, 중간 값을 리턴한 다음 함수는
종료되지 않고 계속해서 맨 끝에 도달할때까지 실행된다.
"""
def get_natural_number():
    n = 0
    while True:
        n += 1
        yield n

print(get_natural_number()) # <generator object get_natural_number at 0x108185a50>
# 다음 값을 생성하려면 next()를 호출한다.

g = get_natural_number()

for _ in range(0, 10):
    print(next(g))

def generator():
    yield 1
    yield 'string'
    yield True

g = generator()
print(next(g)) # 1
print(next(g)) # string
print(next(g)) # True

# range
print(list(range(0, 5))) # [0, 1, 2, 3, 4]
print(range(5)) # range(0, 5)
print(type(range(5))) # <class 'range'>

for i in range(5):
    print(i, end=' ') # 0 1 2 3 4

a = [n for n in range(10)]
b = range(10)

print(len(a)) # 10
print(len(b)) # 10
print(len(a) == len(b)) # True

# range 클래스를 이용하는 b 변수의 메모리 점유율이 훨씬 더 작다.
# 생성 조건만 보관하고 있기 때문이다.
# 인덱스로 접근시에는 바로 생성하도록 구현되어 있다.
print(sys.getsizeof(a)) # 184
print(sys.getsizeof(b)) # 48

# enumerate(열거하다)
# 순서가 있는 자료형 (list, set, tuple)을 인덱스를 포함한 enumerate 객체로 리턴한다.
a = [1,2,3,2,45,2,5]
print(a)

enumerate(a)
print(list(enumerate(a))) # [(0, 1), (1, 2), (2, 3), (3, 2), (4, 45), (5, 2), (6, 5)]

""" (1)
b = ['b1', 'b2', 'b3']
for i in range(len(b)):
    print(i, b[i]) 
"""

""" (2)
i = 0
for v in a:
    print(i, v)
    i += 1
"""

for i, v in enumerate(a):
    print(i, v)

# // 나눗셈 연산자
print(5/3) # 1.6666666666666667
print(5//3) # 1
print(5%3) # 2

# print
print('A1', 'B2') # A1 B2
print('A1', 'B2', sep=",") # A1,B2

a = ['A', 'B']
print(' '.join(a)) # A B

idx = 1
fruit = "Apple"
print('{0}: {1}'.format(idx + 1, fruit)) # 2: Apple

# f-string
print(f'{idx + 1}: {fruit}') # 2: Apple

# pass
class MyClass(object):
    def method_a(self):
        pass # 패스 (해당 로직은 아무것도 수행하지 않는다.)

    def method_b(self):
        pass

# locals
import pprint
pprint.pprint(locals()) # 디버깅 정보 조회에 좋다.

# 함수에서는 불변 객체를 사용할것
""" 잘못된 예
def foo(a, b=[]):
    pass
"""

""" 권장 예
def foo(a, b:None):
    if b is None:
        pass
"""

# fasle 체크
""" 잘못된 예
if foo != []:예
if len(users) == 0:
if foo is not None and not foo:
if not i % 10:
"""

""" 권장 
if foo:
if not users:
if foo == 0:
if i % 10 == 0:
"""