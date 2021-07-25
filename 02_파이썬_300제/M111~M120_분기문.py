# 111. 사용자로부터 입력받은 문자열을 두번 출력하세요
user = input("입력: ")
print(user * 2)

# 112. 사용자로부터 하나의 숫자를 입력받고, 입력받은 숫자에 10을 더해 출력하세요
user = input("입력: ")
print(int(user) + 10)

# 113. 사용자로부터 하나의 숫자를 입력받고 짝수/홀수를 판별하세요
num = input("입력: ")
if int(num) % 2 == 0:
    print("짝수")
else:
    print("홀수")

# 114. 사용자로부터 값을 입력받은 후 해당 값에 20을 더한 값을 출력하고, 255를 초과하면 255를 출력하세요
num = input("입력: ")
if int(num) > 255:
    print(255)
else:
    print(int(num))

# 115. 사용자로부터 값을 입력받은 후 해당 값에 20을 더한 값을 출력하고, 0보다 작으면 0, 255를 초과하면 255를 출력하세요
num = input("입력: ")
if int(num) > 255:
    print(255)
elif int(num) < 0:
    print(0)
else:
    print(int(num))

# 116. 사용자로부터 입력받은 값이 정각인지 판별하세요
time = input("입력: ")
if time[-2:] == "00":
    print("정각")
else:
    print("정각이 아닙니다")

# 117. 사용자로부터 입력받은 단어가 리스트에 포함되어있는지 확인하세요
name = input("입력: ")
list = ["사과", "포도", "바나나"]

if name in list:
    print("포함되어있습니다")
else:
    print("없습니다")

# 118. 사용자로부터 입력받은 단어가 리스트에 포함되어있는지 확인하세요
name = input("입력: ")
list = [1, 2, 3, 4, 5]

if int(num) in list:
    print("포함되어있습니다")
else:
    print("없습니다")

# 119. 사용자로부터 입력받은 단어가 딕셔너리 key에 포함되어있는지 확인하세요
name = input("입력: ")
dic = {"a": "A", "b": "B", "c": "C"}

if name in dic:
    print("포함되어있습니다")
else:
    print("없습니다")

# 120. 사용자로부터 입력받은 단어가 딕셔너리 value에 포함되어있는지 확인하세요
name = input("입력: ")
dic = {"a": "A", "b": "B", "c": "C"}

if name in dic.values():
    print("포함되어있습니다")
else:
    print("없습니다")
