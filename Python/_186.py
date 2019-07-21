class Solution:
    def reverseWords(self, s: List[str]) -> None:
        """
        Do not return anything, modify str in-place instead.
        """
        s[:] = ' '.join(''.join(s).split(' ')[::-1])
