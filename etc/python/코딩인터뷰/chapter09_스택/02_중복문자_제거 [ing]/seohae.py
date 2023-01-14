# 중복된 문자를 제외하고 사전식 순서로 나열하라.

# 입력: bcabc
# 출력: abc

class Solution:
    def removeDuplicateLetters(self, s: str) -> str:
        # str -> list
        value_list = list(s)

        stack = []
        for value in value_list:
            if value not in stack:
                # stack push
                stack.append(value)








f = Solution()
print(Solution.removeDuplicateLetters(f, "bcabc"))
