class Solution:
    # def toLowerCase(self, s: str) -> str:
    #     return s.lower()

    def toLowerCase(self, s: str) -> str:
        return s.lower()
        sa = []
        for c in s:
            if ord(c) >= 65 and ord(c) <= 90:
                c = c.lower()
            sa.append(c)

        return ''.join(sa)
