class Solution:
    def confusingNumber(self, N: int) -> bool:
        confusing = {'6': '9', '9': '6', '8': '8', '1': '1', '0': '0'}

        nums = str(N)

        res = ''

        for x in nums:
            if x not in confusing:
                return False
            res += confusing[x]

        return res[::-1] != ''.join(nums)
