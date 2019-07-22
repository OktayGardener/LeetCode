class Solution:
    def numPairsDivisibleBy60(self, time: List[int]) -> int:
        mp = dict()
        res = 0

        for t in time:
            t = t % 60
            fnd = (60 - t) % 60

            if fnd in mp:
                res += mp[fnd]

            mp[t] = mp.get(t, 0) + 1

        return res
