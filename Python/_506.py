class Solution:
    def findRelativeRanks(self, nums: List[int]) -> List[str]:
        sortednums = sorted(nums, reverse=True)
        l = ['Gold Medal', 'Silver Medal', 'Bronze Medal']
        l = {x: l[i] if i < 3 else str(i + 1) for i, x in enumerate(sortednums)}
        return [l[x] for x in nums]
