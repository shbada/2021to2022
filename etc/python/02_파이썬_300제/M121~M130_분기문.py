# 121. 소문자일 경우 대문자로, 대문자일 경우 소문자로 변경하세요
value = "a"
if value.islower():
    print(value.upper())
else:
    print(value.lower())

# 122. score가 90인 경우 A를 출력하세요
score = 90
if 89 <= score <= 100:
    print("A")
elif 60 <= score <= 88:
    print("B")
else:
    print("C")

# 123. "100 달러"를 환율에 맞게 변환하여 출력하세요
string = "100 달러"
num, currency = string.split()
환율 = {"달러" : 1167, "엔": 1.096, "유로": 1257, "위안": 171}

print(float(num) * 환율[currency], "원")

# 124. 3개의 숫자 중 가장 큰 수를 출력하세요
a = 10
b = 9
c = 20

if a > b and a > c:
    print(a)
elif b > a and b > c:
    print(b)
else:
    print(c)

# 125. 휴대폰번호로 통신사를 출력하세요
number = "011-1234-1234"
num = number.split("-")[0]

if num == "011":
    print("SKT")
else:
    print("LGT")

# 126. 리스트에 포함되어있는지 분기처리 하세요
우편번호 = "01400"
우편번호 = 우편번호[0:3]
if 우편번호 in ["010", "011", "012"]:
    print("강북구")
elif 우편번호 in ["014", "015"]:
    print("도봉구")
else:
    print("노원구")

# 127. 주민번호 뒷자리 7자리 중 첫째자리로 성별을 판별하세요 (1,3: 남자 / 2,4: 여자)
rsg_no = "980101-2123456"
rsg_no = rsg_no.split("-")[1]

if rsg_no[0] == "1" or rsg_no[0] == "3":
    print("남자")
else:
    print("여자")

# 128. 주민번호 뒷자리 8지리 중 두번째, 세번째가 지역코드를 의미한다. (00~08: 서울 / 09~12: 부산)
rsg_no = "980101-2123456"
rsg_no = rsg_no.split("-")[1]

if 0 <= int(rsg_no[1:3]) <= 8:
    print("서울")
else:
    print("부산")

# 129 ~ 130. 생략
