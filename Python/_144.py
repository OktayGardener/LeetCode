# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def preorderTraversal(self, root: TreeNode) -> List[int]:
        if root == None or root == []:
            return []
        return self.preorderTraversalHelp(root, [])

    def preorderTraversalHelp(self, root: TreeNode, l: List[int]) -> List[int]:
        if root == None:
            return None

        l.append(root.val)
        self.preorderTraversalHelp(root.left, l)
        self.preorderTraversalHelp(root.right, l)

        return l
