class Solution(object):
    def majorityElement(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        d = dict()
        mmax = float('-inf')
        mmax_elem = float('-inf')

        for n in nums:
            d[n] = d.get(n, 0) + 1

        for k, v in d.items():
            if v > mmax:
                mmax = v
                mmax_elem = k

        return mmax_elem
