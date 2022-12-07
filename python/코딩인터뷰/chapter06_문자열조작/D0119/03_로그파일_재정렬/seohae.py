# https://leetcode.com/problems/reorder-data-in-log-files/

# 문자일 경우 숫자보다 우선순위
# 문자가 동일하면 식별자로 우선순위
# 숫자일 경우 입력 순서로 우선순위

# 문자, 숫자를 각 리스트에 담고 합치자.
# 맨 앞 단어는 식별자이다.
test_list = ["dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"]

letters_list = []
digits_list = []

for text in test_list:
    print(text.split()[0])  # dig1
    print(text.split()[1])  # 8

    if text.split()[1].isdigit():  # 숫자일 경우
        digits_list.append(text)
    else:
        letters_list.append(text)

# 정렬 필요
digits_list.sort(key=lambda x: (x.split()[1:], x.split()[0]))  # 정렬 1순위(x.split()[1:]), 2순위(x.split()[0])

print(letters_list + digits_list)


