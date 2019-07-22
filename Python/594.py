class Solution:
    def findLHS(self, nums: List[int]) -> int:
        d = {}
        res = 0

        for i in range(len(nums)):
            d[nums[i]] = d.get(nums[i], 0) + 1

        for k in d.keys():
            if k + 1 in d:
                res = max(res, d.get(k) + d.get(k + 1))

        return res
