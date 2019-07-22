import sys
import re


class Solution:
    def isHappy(self, n):
        def cal(n):
            sum = 0
            while n:
                sum += (n % 10)**2
                n //= 10
            return sum

        if n <= 0:
            return False
        s = set()

        while n != 1:
            if n in s:
                return False
            s.add(n)
            n = cal(n)

        return True
