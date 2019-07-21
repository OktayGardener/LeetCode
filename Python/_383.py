class Solution(object):
    def canConstruct(self, ransomNote, magazine):
        """
        :type ransomNote: str
        :type magazine: str
        :rtype: bool
        """
        d = dict()
        for x in ransomNote:
            if x not in d:
                d[x] = 1
            else:
                d[x] += 1

        d2 = dict()
        for x in magazine:
            if x not in d2:
                d2[x] = 1
            else:
                d2[x] += 1

        for k, v in d.items():
            if k not in d2:
                return False
            if d2[k] < v:
                return False

        return True
