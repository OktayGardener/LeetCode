# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

import heapq


class Solution:
    def findSecondMinimumValue(self, root: TreeNode) -> int:
        vals = []
        self.findSecondMinimumValueHelper(root, vals)
        prev = heapq.heappop(vals)
        while True:
            try:
                curr = heapq.heappop(vals)
            except IndexError:
                ret = prev
            if prev != curr:
                ret = curr
                break

        return ret if len(vals) else -1

    def findSecondMinimumValueHelper(self, root: TreeNode, vals: list) -> int:
        if root == None:
            return
        heapq.heappush(vals, root.val)
        self.findSecondMinimumValueHelper(root.left, vals)
        self.findSecondMinimumValueHelper(root.right, vals)
