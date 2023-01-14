# coding=utf-8

# 큰따옴표 안에 들어 있는 작은따옴표는 문자열을 나타내기 위한 기호로 인식되지 않는다.
food = "Python's favorite food is perl"
print(food)  # Python's favorite food is perl

# 큰따옴표(")가 포함된 문자열 -  문자열을 작은따옴표(')로 둘러싼다.
say = '"Python is very easy." he says.'
print(say)  # "Python is very easy." he says.

# 백슬래시(\)를 사용해서 작은따옴표(')와 큰따옴표(")를 문자열에 포함시키기
food2 = 'Python\'s favorite food is perl'
say2 = "\"Python is very easy.\" he says."

print(food2)  # Python's favorite food is perl
print(say2)  # "Python is very easy." he says.

# 줄을 바꾸기 위한 이스케이프 코드 \n 삽입하기
multiline = "Life is too short\nYou need python"
print(multiline)

# \n 대신 줄 바꿈 처리하기
multiline2 = '''
Life is too short
You need python
'''
print("줄 바꿈 처리하기 : " + multiline2)