class Solution:
    def wiggleSort(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        # nums = sorted(nums)
        for i in range(0, len(nums) - 1):
            if i & 1 == (nums[i] < nums[i + 1]):
                nums[i], nums[i + 1] = nums[i + 1], nums[i]
