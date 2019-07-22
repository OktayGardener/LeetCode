# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
from collections import deque


class Solution:
    def largestValues(self, root: TreeNode) -> List[int]:
        if root == None:
            return []

        q = deque()
        q.append(root)

        res = []

        while q:
            level_size = len(q)
            level_max = float('-inf')
            for _ in range(level_size):
                node = q.popleft()

                if level_max < node.val:
                    level_max = node.val

                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)

            res.append(level_max)

        return res
