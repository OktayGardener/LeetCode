class RandomizedCollection:
    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.s = []

    def insert(self, val: int) -> bool:
        """
        Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
        """
        self.s.append(val)

        if val in self.s:
            return True

        return False

    def remove(self, val: int) -> bool:
        """
        Removes a value from the collection. Returns true if the collection contained the specified element.
        """
        if val not in self.s:
            return False

        self.s.remove(val)
        return True

    def getRandom(self) -> int:
        """
        Get a random element from the collection.
        """
        return random.sample(self.s, 1)[0]


# Your RandomizedCollection object will be instantiated and called as such:
# obj = RandomizedCollection()
# param_1 = obj.insert(val)
# param_2 = obj.remove(val)
# param_3 = obj.getRandom()
