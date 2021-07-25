import collections
import re
from typing import List


class Solution:
    def mostCommonWord(self, paragraph: str, banned: List[str]) -> str:
        # 이 코드를 해석해야한다.
        print(re.sub(r'[^\w]', ' ', paragraph))  # Bob hit a ball  the hit Ball flew
        print(re.sub(r'[^\w]', ' ', paragraph).lower())  # bob hit a ball  the hit ball flew
        print(re.sub(r'[^\w]', ' ', paragraph).lower().split())  # ['bob', 'hit', 'a', 'ball', 'the', 'hit', 'ball', 'flew']

        # 문자만 추출, 소문자 변환 후 금지된 단어 제외한 리스트로 변환
        words = [word for word in re.sub(r'[^\w]', ' ', paragraph)
            .lower().split()
                 if word not in banned]

        print(words)  # ['bob', 'a', 'ball', 'the', 'ball', 'flew']

        counts = collections.Counter(words)
        print(counts)  # Counter({'ball': 2, 'bob': 1, 'a': 1, 'the': 1, 'flew': 1})

        # 가장 흔하게 등장하는 단어의 첫 번째 인덱스 리턴
        print(counts.most_common(1))  # [('ball', 2)]
        print(counts.most_common(1)[0])  # ('ball', 2)
        print(counts.most_common(1)[0][0])  # ball

        return counts.most_common(1)[0][0]


f = Solution()
Solution.mostCommonWord(f, "Bob hit a ball. the hit Ball flew", ["hit"])
