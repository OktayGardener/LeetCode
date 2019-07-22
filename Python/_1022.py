# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def sumRootToLeaf(self, root: TreeNode) -> int:
        """
        :type root: TreeNode
        :rtype: int
        """
        global total
        total = 0

        def dfs(root: TreeNode, val: int):
            global total
            if root == None:
                return

            curr_val = 2 * val + root.val

            if root.left is None and root.right is None:
                total += curr_val

            dfs(root.left, curr_val)
            dfs(root.right, curr_val)

        dfs(root, 0)
        return total
