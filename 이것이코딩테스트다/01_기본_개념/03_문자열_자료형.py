# 문자열 자료형
# 문자열 변수를 초기화할때는 큰따옴표("") 또는 작은따옴표('')를 이용한다.

data = 'Hello World'
print(data)

data = "Don't you know \"python\"?"
print(data)


# 문자열 변수에 덧셈(+)을 이용하여 문자열 연결이 가능하다.
# 문자열의 인덱싱과 슬라이싱을 이용할 수 있다. 그러나 특정 인덱스의 값을 변경할 수 없다. (immutable)

a = "Hello"
b = "World"

print(a + " " + b)  # Hello World

a = "String"
print(a * 3)  # StringStringString

a = "ABCDEF"
print(a[2:4])  # CD

# 불가능: a[2] = 'a'

