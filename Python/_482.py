class Solution:
    def licenseKeyFormatting(self, S: str, K: int) -> str:
        S = "".join([x for x in S if x != '-']).upper()
        L = len(S)
        t = L % K

        if t == 0:
            t = K

        T = S[0:t] + '-'
        for i in range(t, L, K):
            T = T + S[i:i + K] + '-'

        return (T[0:-1])
