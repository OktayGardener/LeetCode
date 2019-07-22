class Solution:
    def findTheDifference(self, s, t):
        """
        :type s: str
        :type t: str
        :rtype: str
        """
        counts = [0] * 26

        for i in range(len(t)):
            counts[ord(t[i]) - ord('a')] += 1

        for i in range(len(s)):
            counts[ord(s[i]) - ord('a')] -= 1

        for i in range(len(counts)):
            if counts[i] == 1:
                return chr(i + ord('a'))
