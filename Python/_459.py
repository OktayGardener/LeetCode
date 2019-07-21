class Solution:
    def repeatedSubstringPattern(self, s: str) -> bool:
        for i in range(len(s) // 2 + 1):
            substring = s[:i]
            if len(substring) is not 0:
                if (str(substring) * (len(s) // len(substring)) == s):
                    return True
        return False
