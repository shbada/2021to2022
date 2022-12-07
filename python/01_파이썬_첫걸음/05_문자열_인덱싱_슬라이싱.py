# coding=utf-8

# 문자열 인덱싱 활용
a = "Life is too short, You need Python"
print(a[0])  # 'L'
print(a[-0])  # 'L'
print(a[12])  # 's'
print(a[-1])  # 'n'
print(a[-2])  # 'o' 뒤에서 2번째
print(a[-5])  # 'y' 뒤에서 5번째

# 문자열 슬라이싱
a = "Life is too short, You need Python"
b = a[0] + a[1] + a[2] + a[3]
print(b)  # 'Life'
print(a[0:4])  # 'Life' 0부터 4까지 (0 <= a < 4)
print(a[0:5])  # 공백도 문자로 포함
print(a[19:])  # 'You need Python' 19부터 끝까지
print(a[:17])  # 'Life is too short' 처음부터 17까지
print(a[:])  # 처음부터 끝까지
print(a[19:-7])  # 'You need' 19부터 뒤에서 7번째

# 문자열 슬라이싱으로 문자열 나누기
a = "20010331Rainy"
year = a[:4]
day = a[4:8]
weather = a[8:]
print(year)  # '2001'
print(day)  # '0331'
print(weather)  # 'Rainy'

a = "Pithon"
print(a[1])  # 'i'
# a[1] = 'y'
# print a[1] 에러발생 (문자열은 immutable한 자료형이기 때문에 변경할 수 없다.)

# 문자열 포맷팅
print("I eat %d apples." % 3)  # 'I eat 3 apples.'
print("I eat %s apples." % "five")  # 'I eat five apples.'

number = 3
print("I eat %d apples." % number)  # 'I eat 3 apples.'

day = "three"
print("I ate %d apples. so I was sick for %s days." % (number, day))  # 'I ate 10 apples. so I was sick for three days.'

# %s	문자열(String)
# %c	문자 1개(character)
# %d	정수(Integer)
# %f	부동소수(floating-point)
# %o	8진수
# %x	16진수
# %%	Literal % (문자 % 자체)

# %10s : 전체 길이가 10개인 문자열 공간에서 대입되는 값을 오른쪽으로 정렬하고 그 앞의 나머지는 공백으로 처리
print("%10s" % "hi")  # '        hi'

# %-10 : 전체 길이가 10개인 문자열 공간에서 대입되는 값을 왼쪽으로 정렬하고 그 앞의 나머지는 공백으로 처리
print("%-10sjane." % 'hi')  # 'hi        jane.'

print("I eat {0} apples".format(3))  # 'I eat 3 apples'
print("I eat {0} apples".format("five"))  # 'I eat five apples'

# 'I ate 10 apples. so I was sick for 3 days.'
print("I ate {number} apples. so I was sick for {day} days.".format(number=10, day=3))
print("I ate {0} apples. so I was sick for {day} days.".format(10, day=3))

# 소수점 표현하기
y = 3.42134234
print("{0:0.4f}".format(y))  # '3.4213' 소수점 4번째자리까지

# { 또는 } 문자 표현하기
print("{{ and }}".format())  # '{ and }'

# f 문자열 포매팅 TODO