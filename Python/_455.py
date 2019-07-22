class Solution:
    def failedPivotIndex(self, nums: List[int]) -> int:
        lo = 0
        hi = len(nums)

        while lo <= hi:
            mid = lo + hi // 2
            left = sum(nums[:mid])
            right = sum(nums[mid:])
            if left == right:
                return mid
            elif left > right:
                hi = mid + 1
            elif right > left:
                lo = mid - 1

        return -1

    def pivotIndex(self, nums: List[int]) -> int:
        S = sum(nums)
        leftsum = 0

        for i, x in enumerate(nums):
            if leftsum == (S - leftsum - x):
                return i
            leftsum += x

        return -1
