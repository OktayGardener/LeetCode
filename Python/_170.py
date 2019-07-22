# class TwoSum:

#     def __init__(self):
#         """
#         Initialize your data structure here.
#         """
#         self.a = []
#         self.sums = []

#     def add(self, number: int) -> None:
#         """
#         Add the number to an internal data structure..
#         """
#         self.sums.extend(
#             [number + a for a in self.a]
#         )
#         self.a.append(number)

#     def find(self, value: int) -> bool:
#         """
#         Find if there exists any pair of numbers which sum is equal to the value.
#         """
#         return value in self.sums


class TwoSum:
    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.d = dict()

    def add(self, number: int) -> None:
        """
        Add the number to an internal data structure..
        """
        if number in self.d:
            self.d[number] += 1
        else:
            self.d[number] = 1

    def find(self, value: int) -> bool:
        """
        Find if there exists any pair of numbers which sum is equal to the value.
        """
        for k, v in self.d.items():
            if value - k in self.d:
                if value - k != k:
                    return True
                else:
                    if v >= 2:
                        return True
        return False


# Your TwoSum object will be instantiated and called as such:
# obj = TwoSum()
# obj.add(number)
# param_2 = obj.find(value)
