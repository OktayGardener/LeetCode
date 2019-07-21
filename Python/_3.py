class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        n = len(s)
        seq = set()
        i = 0
        j = 0
        mmax = 0
        while i < n and j < n:
            if s[j] not in seq:
                seq.add(s[j])
                j += 1
                mmax = max(mmax, j - i)
            else:
                seq.remove(s[i])
                i += 1

        return mmax
