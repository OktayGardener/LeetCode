class Solution:
    def gcdOfStrings(self, str1: str, str2: str) -> str:
        largest = ""
        min_str = (str1 if len(str1) < len(str2) else str2)
        max_str = (str1 if len(str1) >= len(str2) else str2)

        for k in range(len(min_str), 0, -1):
            if len(max_str) % len(min_str[:k]) == 0 and len(min_str) % len(min_str[:k]) == 0:
                if (len(max_str) // len(min_str[:k])) * min_str[:k] == max_str:
                    return min_str[:k]

        return ""
