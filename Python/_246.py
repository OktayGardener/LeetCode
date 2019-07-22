class Solution:
    def isStrobogrammatic(self, num: str) -> bool:
        m = {9: 6, 6: 9, 0: 0, 1: 1, 8: 8}
        r = list(map(int, list(reversed(num))))
        mapped = list(map(str, map(m.get, r)))
        mapped = ''.join(mapped)
        print(mapped)
        return mapped == num
