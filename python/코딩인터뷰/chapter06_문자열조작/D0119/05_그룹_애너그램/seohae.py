# https://leetcode.com/problems/group-anagrams/

# 리스트 순서대로 단어 꺼내기
# 꺼낸 단어를 알파벳 순으로 정렬하기 (이렇게되면 같은 알파벳의 단어는 동일한 값으로 비교 가능하다)

test_list = ["eat", "tea", "tan", "ate", "nat", "bat"]
test_dict = {}

for text in test_list:
    alpha_list = list(text)
    alpha_list.sort()

    print(alpha_list)

    sort_text = "".join(alpha_list)

    if sort_text in test_dict:
        # test_dict[sort_text] = test_dict[sort_text].append(text)  -> 결과값은 None 이다.
        test_dict[sort_text].append(text)
    else:
        test_dict[sort_text] = [text]

print(test_dict)

