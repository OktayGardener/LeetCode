from collections import Counter


class Solution:
    def mostCommonWord(self, paragraph: str, banned: List[str]) -> str:
        return self.mostCommonWord2(paragraph, banned)

    def mostCommonWord2(self, paragraph: str, banned: List[str]) -> str:
        banned = set(banned)
        counts = dict()

        paragraph = paragraph.replace("!", "").replace("?", "").replace("'", "").replace(", ", " ").replace(
            ",", " "
        ).replace(";", "").replace(".", "").lower().split(' ')

        for w in paragraph:
            if w not in banned:
                counts[w] = counts.get(w, 0) + 1
        return sorted(list(counts.items()), reverse=True, key=lambda x: x[1])[0][0]
