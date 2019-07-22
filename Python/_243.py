class Solution:
    def shortestDistance(self, words: List[str], word1: str, word2: str) -> int:
        dist = float('inf')
        i1, i2 = -1, -1
        for i in range(len(words)):
            if words[i] == word1:
                i1 = i
            elif words[i] == word2:
                i2 = i

            if i1 != -1 and i2 != -1:
                dist = min(dist, abs(i1 - i2))

        return dist
