from typing import List


class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        results = []

        # 정렬
        nums.sort()

        # 브루트 포스 n^3 반복
        for i in range(len(nums) - 2):
            # 중복된 값 건너뛰기
            # 정렬을 했기 때문에 같은 숫자일 경우 차례로 붙어있다.
            # 현재의 차례의 원소와 이전 원소를 비교하여 같으면 넘어간다. -> 이전 원소로 이미 진행되었기 때문 (중복제거)
            if i > 0 and nums[i] == nums[i - 1]:
                continue

            for j in range(i + 1, len(nums) - 1):
                if j > i + 1 and nums[j] == nums[j - 1]:
                    continue

                for k in range(j + 1, len(nums)):
                    if k > j + 1 and nums[k] == nums[k - 1]:
                        continue

                    if nums[i] + nums[j] + nums[k] == 0:
                        results.append([nums[i], nums[j], nums[k]])

        return results
