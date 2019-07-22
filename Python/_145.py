# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def postorderTraversal(self, root: TreeNode) -> List[int]:
        if root == None or root == []:
            return []
        return self.postorderTraversalHelp(root, [])

    def postorderTraversalHelp(self, root: TreeNode, l: List[int]) -> List[int]:
        if root == None:
            return None

        self.postorderTraversalHelp(root.left, l)
        self.postorderTraversalHelp(root.right, l)
        l.append(root.val)

        return l
