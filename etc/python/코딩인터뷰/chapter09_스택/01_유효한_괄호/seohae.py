# 괄호로 된 입력 값이 올바른지 판별하라

# 입력 ()[]{}
# 출력 true

# 리스트로 구현해보자.

class Solution:
    def isValid(self, s: str) -> bool:
        dict = {"(": ")", "[": "]", "{": "}"}

        # 문자열을 리스트로 변환
        value_list = list(s)
        print(value_list)

        i = 0

        while i < len(value_list):
            key = value_list[i]

            # 존재한다면
            if dict.get(key) in value_list:
                value_list.remove(dict[key])
            else:
                return False

            i = value_list.index(key) + 1

        return True


f = Solution()
print(Solution.isValid(f, "([)]"))
