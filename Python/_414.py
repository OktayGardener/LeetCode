class Solution:
    def thirdMax(self, nums: List[int]) -> int:
        import heapq
        heap = []
        nums = list(set(nums))
        if len(nums) < 3:
            return max(nums)
        for n in nums:
            heapq.heappush(heap, -n)

        for i in range(1, 3):
            heapq.heappop(heap)

        return -heapq.heappop(heap)
