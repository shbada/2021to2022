# 031. 문자열 합치기 "34" 출력하기
a = "3"
b = "4"
print(a + b)

# 032. 문자열 곱하기 "HiHiHi" 출력하기
a = "Hi"
print(a * 3)

# 033. "-"를 80갸 출력하기
print("-" * 80)

# 034. 문자열 더하기, 곱셈을 사용하여 문자열 출력
t1 = "python"
t2 = "java"
print((t1 + ' ' + t2 + ' ') * 4)

# 035. % formatting을 사용해서 출력하기
name1 = "김민수"
age1 = 10
name2 = "이철희"
age2 = 13
print("이름: %s 나이: %d" % (name1, age1))
print("이름: %s 나이: %d" % (name2, age2))

# 036. format() 메서드를 사용하여 출력하기
print("이름: {} 나이: {}".format(name1, age1))
print("이름: {} 나이: {}".format(name2, age2))

# 037. f-string 사용하여 출력하기
print(f"이름: {name1} 나이 : {age1}")
print(f"이름: {name2} 나이 : {age2}")

# 038. "1,350,000" 에서 콤마를 제거하고 정수 타입으로 변환
num1 = "1,350,000"
print(int(num1.replace(",", "")))

# 039. "2020/03(E) (IFRS연결)" 에서 '2020/03'만 출력
string = "2020/03(E) (IFRS연결)"
print(string[:7])

# 040. 문자열의 좌우의 공백이 있을때 공백 제거하여 출력
data = "       삼성전자       "
data.strip()
print(data)
strip_data = data.strip()
print(strip_data)