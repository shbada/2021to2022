# coding=utf-8

a = True
b = False

print(type(a))  # <type 'bool'>

print(1 == 1)  # True
print(2 < 1)  # False

# 빈문자열 검사
print(bool('python'))  # True ('python' 문자열은 빈 문자열이 아니므로 true)
print(bool(''))  # False ('' 문자열은 빈 문자열이므로 False)

print(bool([1, 2, 3]))  # True
print(bool([]))  # False
print(bool(0))  # False
print(bool(3))  # True

