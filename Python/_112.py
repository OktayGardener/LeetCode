# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def hasPathSum(self, root: TreeNode, sum: int) -> bool:
        # return self.hasPathSumR(root, sum)
        if root == None:
            return False

        if sum - root.val == 0 and root.left == None and root.right == None:
            return True

        return self.hasPathSum(root.left, sum - root.val) or self.hasPathSum(root.right, sum - root.val)
