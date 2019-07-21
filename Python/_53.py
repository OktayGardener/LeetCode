from typing import List


class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        dp = []
        dp.append(nums[0])
        mmax = nums[0]
        for i in range(1, len(nums)):
            dp.append(nums[i] + (dp[i - 1] if dp[i - 1] > 0 else 0))
            mmax = max(mmax, dp[i])
        return mmax


if __name__ == "__main__":
    s = Solution()
    print(s.maxSubArray(nums=[1, -2, 3, 3, 3 - 4, 5 - 43, -3]))
