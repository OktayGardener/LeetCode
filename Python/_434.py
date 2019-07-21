class Solution:
    def countSegments(self, s: str) -> int:
        return len([x for x in s.split(' ') if x != ''])
