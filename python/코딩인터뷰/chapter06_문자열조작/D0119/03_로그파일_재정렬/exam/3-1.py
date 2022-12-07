from typing import List


class Solution:
    def reorderLogFiles(self, logs: List[str]) -> List[str]:
        letters, digits = [], []

        # 반복문 실행
        for log in logs:
            # log 변수의 두번째 값이 숫자인지 체크 (digit0 2 1 이런식일때 2)
            if log.split()[1].isdigit():  # 숫자 리스트에 추가
                digits.append(log)
            else:  # 문자 리스트에 추가
                letters.append(log)

        # 두 개의 키를 람다 표현식으로 정렬
        # 문자가 동일하면 식별자로 정렬
        letters.sort(key=lambda x: (x.split()[1:], x.split()[0]))
        return letters + digits