# 041. 문자열 소문자를 대문자로 변경
ticker = "btc_krw"
ticker_upper = ticker.upper();
print(ticker_upper)

# 042. 문자열 대문자를 소문자로 변경
ticker = "BTC_KRW"
ticker_lower = ticker.lower()
print(ticker_lower)

# 043. hello를 Hello로 변경
string = 'hello'
string_capitalize = string.capitalize()
print(string_capitalize)

# 044. 문자열 끝 "xlsx"인지 확인
file_name = "보고서.xlsx"
file_name_end = file_name.endswith("xlsx")  # xlsx를 포함하면 true, 아니면 false
print(file_name_end)

# 045. 이문자열 끝 "xlsx, xls"인지 확인
file_name = "보고서.xlsx"
file_name_end = file_name.endswith(("xlsx", "xls"))
print(file_name_end)

# 046. 이문자열 시작이 "2020"인지 확인
file_name = "2020_보고서_xlsx"
file_name_first = file_name.startswith("2020")
print(file_name_first)

# 047. 문자열을 기준을 정해서 나누기
a = "hello world"
print(a.split())

# 048. btc_krw 문자열을 _ 기준으로 나누기
a = "btc_krw"
print(a.split("_"))

# 049. "yyyy-MM-dd" 를 년/월/일로 나누기
date = "2020-05-01"
print(date.split("-"))

# 050. 문자열 오른쪽 공백 제거하기
data = "12345      "
print(data.rstrip())