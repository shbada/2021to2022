# 061. 리스트에 날짜와 가격 정보가 저장되어있는데, 슬라이싱을 사용하여 날짜를 제외한 정보만 출력하세요
price = ['20210101', 100, 130, 140, 150, 160, 170]
print(price[1:])

# 062. 슬라이싱을 사용하여 홀수만 출력하세요
nums = [1, 2, 3, 4, 5, 6, 7, 8, 9]
print(nums[::2])

# 063. 슬라이싱을 사용하여 짝수만 출력하세요
print(nums[1::2])

# 064. 슬라이싱을 사용해서 리스트의 숫자를 역 방향으로 출력하세요
print(nums[::1])  # 정방향
print(nums[::-1])  # 역방향

# 065. ['삼성전자', 'LG전자', 'Naver'] 리스트에서 삼성전자 Naver 형태로 출력하세요
interest = ['삼성전자', 'LG전자', 'Naver']
print(interest[0], interest[2])

# 066. 리스트를 join 함수를 사용하여 " " 구분자로 문자열을 출력하세요
interest = ['삼성전자', 'LG전자', 'Naver']
print(" ".join(interest))

# 067. 리스트를 join 함수를 사용하여 "/" 구분자로 문자열을 출력하세요
print("/".join(interest))

# 068. 리스트를 join 함수를 사용하여 각 요소별 엔터처리된 모습으로 출력하세요
print("\n".join(interest))

# 069. 문자열을 "/" 구문자로 리스트로 만들기
string = "삼성전자/LG전자/Naver"
print(string.split("/"))

# 070. 리스트를 오름차순으로 정렬하세요
data = [2, 4, 6, 7, 10, 1]
print(sorted(data))
print("정렬 전: ", data)
data.sort()
print("정렬 후: ", data)