# 101. 파이썬에서 True, False를 갖는 데이터 타입
bool

# 102. False 출력하기
print(3 == 5)

# 103. True 출력하기
print(3 < 5)

# 104. True 출력하기
x = 4
print(1 < x < 5)

# 105. and 조건
print((3 == 3) and (4 != 3))  # True

# 106. 파이썬 지원하지 않는 연산자
# print(3 => 4)  > 에러 발생

# 107. 출력 결과를 예상하세요
if 4 < 3:
    print('hello')  # if문이 false 이므로 출력 결과가 없다

# 108. if-else 사용하기
if 4 < 3:
    print('hello')
else:  # 위 if문을 만족하지 않으므로 else 문을 출력한다
    print('seohae')

# 109. 출력 결과를 예상하세요
if True:
    print("1")  # 출력
    print("2")  # 출력
else:
    print("3")
print("4")  # 출력

# 110. 출력 결과를 예상하세요
if True:
    if False:
        print("1")
        print("2")
    else:
        print("3")  # 출력
else:
    print("4")
print("5")  # 출력
