class Solution:
    def isPalindrome(self, s: str) -> bool:
        strs = []

        for char in s:
            # 문자 여부 체크
            if char.isalnum():
                strs.append(char.lower())

        # 팰린드롬 여부 판별
        while len(strs) > 1:
            # pop(0) : 왼쪽부터 꺼내기
            if strs.pop(0) != strs.pop():
                return False

        return True