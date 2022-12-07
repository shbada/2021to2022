# 람다표현식
# 함수를 간단하게 작성할 수 있다.

def add (a, b):
    return a + b

# 람다표현식으로 구현
print(lambda a, b: a + b(3, 7))


# 내장함수에서 자주 사용되는 람다 함수
array = [('홍길동', 50), ('테스트', 32), ('아무개', 74)]

def my_key(x):
    return x[1]

print(sorted(array, key=my_key))
print(sorted(array, key=lambda x: x[1]))


# 여러개의 리스트에 적용
list1 = [1, 2, 3, 4, 5]
list2 = [6, 7, 8, 9, 10]

result = map(lambda  a, b: a + b, list1, list2)
print(list(result))  # [7, 9, 11, 13, 15]

