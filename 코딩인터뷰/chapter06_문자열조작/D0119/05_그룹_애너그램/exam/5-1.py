import collections
from typing import List


class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        # 딕셔너리 생성
        anagrams = collections.defaultdict(list)
        print(anagrams)  # defaultdict(<class 'list'>, {})

        for word in strs:
            # 정렬하여 딕셔너리에 추가
            anagrams[''.join(sorted(word))].append(word)

        print(anagrams)  # defaultdict(<class 'list'>, {'aet': ['eat', 'tea', 'ate'], 'ant': ['tan', 'nat'], 'abt': ['bat']})

        print(anagrams.values())  # dict_values([['eat', 'tea', 'ate'], ['tan', 'nat'], ['bat']])
        return list(anagrams.values())


f = Solution()

# [['eat', 'tea', 'ate'], ['tan', 'nat'], ['bat']]
print(Solution.groupAnagrams(f, ["eat", "tea", "tan", "ate", "nat", "bat"]))
