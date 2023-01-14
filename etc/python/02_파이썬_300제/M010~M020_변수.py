# 011. 삼성전자라는 변수로 50,000원을 바인딩하고 주식 10주를 보유하고있을때 총 평가금액 출력
삼성전자 = 50000
print(10 * 삼성전자)

# 012. 변수 바인딩
시가총액 = 298
현재가 = 50000
PER = 15.79
print(시가총액, 현재가, PER)

# 013. 변수 출력
s = "hello"
t = "python"

print(s+"!", t)

# 014. 2 + 2 * 3 출력
print(2 + 2 * 3)  # 8

# 015. 변수 type 알아내기
a = 128
b = "128"
print(type(a))  # <class 'int'>
print(type(b))  # <class 'str'>

# 016. 문자열 "720"을 정수형으로 변환
num_str = "720"
num = int(num_str)
print(num)
print(type(num))  # <class 'int'>

# 017. 정수를 문자열 100으로 변환
num2 = 100
num2_str = str(num2)
print(type(num2_str))  # <class 'str'>

# 018. 문자열을 실수로 변환
num3 = "2020"
num2_float = float(num3)
print(num2_float)  # 2020.0

# 019. 문자열을 정수로 변환
year = "2020"
print(int(year) - 3)  # 2017
print(int(year) - 2)  # 2018
print(int(year) - 1)  # 2019

# 020. 에어컨이 월 48,584원에 무이자 36개월 조건으로 판매되고 있을때 총 금액 출력
월 = 48584
개월 = 36
총금액 = 월 * 개월
print(총금액)
