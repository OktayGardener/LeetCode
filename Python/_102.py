# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
from collections import deque


class Solution:
    def levelOrder(self, root: TreeNode) -> List[List[int]]:
        if root == [] or root == None:
            return []

        queue = deque([
            root,
        ])
        levels = []

        level = 0
        while queue:
            level_length = len(queue)
            levels.append([])

            for _ in range(level_length):
                node = queue.popleft()
                levels[level].append(node.val)

                if node.left:
                    queue.append(node.left)
                if node.right:
                    queue.append(node.right)

            level += 1
        return levels
