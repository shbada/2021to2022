# 021. letters가 바인딩하는 문자열에서 첫번째와 세번째 문자 출력
letters = 'python'
print(letters[0], letters[2])

# 022. 뒤에 4자리만 출력
license_plate = "24가 2210"
print(license_plate[-4:])

# 023. 문자열에서 '홀'만 출력
string = '홀짝홀짝홀짝'
print(string[::2])  # 시작인덱스:끝인덱스:오프셋

# 024. 문자열을 거꾸로 뒤집어서 출력
string = "PYTHON"
print(string[::-1])

# 025. 하이푼 '-'을 제거하고 출력하세요
phone_number = "010-1111-2222"
print(phone_number.replace("-", " "))

# 026. 하이푼 '-'을 제거하고 공백없이 출력하세요
phone_number = "010-1111-2222"
print(phone_number.replace("-", ""))

# 027. url 에 저장된 웹페이지 주소에서 도메인을 출력하세요
url = "http://github.com"
print(url.split(".")[-1])  # 마지막 요소 -1

# 028. 파이썬에서 문자열은 immutable 이다.
lang = 'python'
# lang[0] = 'P' 문자열을 변경하려고하면 에러 발생

# 029. 소문자 'a'를 대문자 'A' 변경하세요
string = 'abcdfe2a354a32a'
string = string.replace('a', 'A')
print(string)

# 030. replace 함수 사용시 주의할점
string2 = 'abcdfe2a354a32a'
print(string2.replace('a', 'A'))  # 문자열을 변경할 수 없는 자로형이기 때문에 원본은 그대로이다.
result = string2.replace('a', 'A')  # 새로운 문자열 객체를 리턴해준다.
print(result)