class Solution(object):
    def sortedSquares(self, A):
        """
        :type A: List[int]
        :rtype: List[int]
        """
        squared = [x**2 for x in A]
        return sorted(squared)
