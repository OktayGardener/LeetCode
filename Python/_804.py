class Solution(object):
    def uniqueMorseRepresentations(self, words):
        """
        :type words: List[str]
        :rtype: int
        """
        morse_code_alphabet = [
            ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---",
            ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."
        ]
        alphabet = list(map(chr, range(97, 123)))
        d = dict(zip(alphabet, morse_code_alphabet))

        retwords = []
        for word in words:
            curr = ''
            for c in word:
                if c in d:
                    curr += d[c]
            retwords.append(curr)

        return len(set(retwords))
