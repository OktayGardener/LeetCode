class Solution:
    def compareVersion(self, version1: str, version2: str) -> int:
        v = list(map(int, version1.split('.')))
        v2 = list(map(int, version2.split('.')))

        max_len = max(len(v), len(v2))
        v.extend([0] * (max_len - len(v)))
        v2.extend([0] * (max_len - len(v2)))

        for i, x in enumerate(v):
            if x > v2[i]:
                return 1
            elif x < v2[i]:
                return -1
        return 0
