class Solution:
    def intToRoman(self, num: int) -> str:
        romans = ['I', 'IV', 'V', 'IX', 'X', 'XL', 'L', 'XC', 'C', 'CD', 'D', 'CM', 'M']
        arabic = [1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000]

        roman = ''

        for i in range(len(arabic) - 1, -1, -1):
            if arabic[i] <= num:
                if num <= 0:
                    break

                count = num // arabic[i]
                num -= count * arabic[i]
                roman += romans[i] * count

        return roman
