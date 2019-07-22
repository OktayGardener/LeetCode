# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def inorderTraversal(self, root: TreeNode) -> List[int]:
        if root == None or root == []:
            return []
        return self.inorderTraversalHelp(root, [])

    def inorderTraversalHelp(self, root: TreeNode, l: List[int]) -> List[int]:
        if root == None:
            return None

        self.inorderTraversalHelp(root.left, l)
        l.append(root.val)
        self.inorderTraversalHelp(root.right, l)

        return l
