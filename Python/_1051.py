class Solution:
    def heightChecker(self, heights: List[int]) -> int:
        sheights = sorted(heights)

        return sum([1 for x, y in zip(sheights, heights) if x != y])
