# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def findTarget(self, root: TreeNode, k: int) -> bool:
        s = set()

        return self.find(root, k, s)

    def find(self, root: TreeNode, k: int, s) -> bool:
        if root == None:
            return False
        if k - root.val in s:
            return True
        s.add(root.val)

        return self.find(root.left, k, s) or self.find(root.right, k, s)
