class Solution:
    def sortArrayByParity(self, A: List[int]) -> List[int]:
        A_0 = [x for x in A if x % 2 == 0]
        A_1 = [x for x in A if x % 2 != 0]
        return [*A_0, *A_1]
