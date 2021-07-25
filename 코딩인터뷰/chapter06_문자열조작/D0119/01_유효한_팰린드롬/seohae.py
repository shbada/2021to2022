# https://leetcode.com/problems/valid-palindrome/

# 한글, 영문, 숫자만
# 공백 제거
# 콤마(,) 등 특수문자 제거
# 소문자로 모두 변환
import re

test = "A man, a plan, a canal: Panama"

# 문자열에서 한글, 영문, 숫자만 추출
test = re.sub('[^0-9a-zA-Zㄱ-힗]', '', test)
test = test.lower()

print(test)
print(test[::-1])  # 뒤집기

if test == test[::-1]:
    print('True')
else:
    print('False')
