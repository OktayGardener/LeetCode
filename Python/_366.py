# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def findLeaves(self, root: TreeNode) -> List[List[int]]:
        res = []

        def dfs(node):
            if not node:
                return -1
            i = max(dfs(node.left), dfs(node.right)) + 1
            if i >= len(res):
                res.append([])
            res[i].append(node.val)
            return i

        dfs(root)
        return res
