# https://leetcode.com/problems/most-common-word/

# split 하여 많이 나온 단어 count++
# 소문자 전환
# 문자만 추출
import operator
import re

test = "Bob hit a ball. the hit Ball flew"
result_list = test.split()
print(result_list)

add_list = []

for text in result_list:
    text = re.sub('[^a-zA-Zㄱ-힗]', '', text.lower())
    add_list.append(text)

print(add_list)

result_dict = {}
count = 0

for text in add_list:
    if text in result_dict:
        result_dict[text] = result_dict[text] + 1
    else:
        result_dict[text] = 1

print(result_dict)
print(max(result_dict.items(), key=operator.itemgetter(1))[0])



