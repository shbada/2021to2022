# coding=utf-8

# count : 문자열 개수 세기
a = "hobby"
print(a.count('b'))  # 5

# find : 위치 알려주기 (0부터 센다)
a = "Python is the best choice"
print(a.find('b'))  # 14
print(a.find('k'))  # -1 존재하지않음

# indx : 위치 알려주기
a = "Life is too short"
print(a.index('t'))  # 8
# print a.index('k') 존재하지않으면 에러 발생

# join : 문자열 삽입
print(",".join('abcd'))  # 'a,b,c,d'
print(",".join(['a', 'b', 'c', 'd']))  # 'a,b,c,d'

# upper : 대문자로 변환
a = "hi"
print(a.upper())  # 'HI'

# lower : 소문자로 변환
a = "HI"
print(a.lower())  # 'hi'

# lstrip : 왼쪽 공백 지우기
a = " hi "
print(a.lstrip())  # 'hi '

# rstrip : 오른쪽 공백 지우기
a = " hi "
print(a.rstrip())  # ' hi'

# strip : 양쪽 공백 지우기
a = " hi "
print(a.strip())  # 'hi'

# replace : 문자열 바꾸기
a = "Life is too short"
print(a.replace("Life", "Your leg"))  # 'Your leg is too short'

# split : 문자열 나누기
a = "Life is too short"
print(a.split())  # ['Life', 'is', 'too', 'short']

b = "a:b:c:d"
print(b.split(':'))# ['a', 'b', 'c', 'd']
