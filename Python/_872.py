# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
from typing import List


class Solution:
    def leafSimilar(self, root1: TreeNode, root2: TreeNode) -> bool:
        leafs1 = self.leafSimilarHelper(root1, [])
        leafs2 = self.leafSimilarHelper(root2, [])
        return leafs1 == leafs2

    def leafSimilarHelper(self, root: TreeNode, l: List) -> List[int]:
        if root == None:
            return
        if root.left == None and root.right == None:
            l.append(root.val)

        self.leafSimilarHelper(root.left, l)
        self.leafSimilarHelper(root.right, l)
        return l
