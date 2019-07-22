# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution:
    def isSymmetric(self, root: TreeNode) -> bool:
        # return self.isMirror(root, root)
        return self.isMirrorIterative(root)

    def isMirror(self, t1: TreeNode, t2: TreeNode):
        if t1 == None and t2 == None:
            return True

        if t1 == None or t2 == None:
            return False

        return (t1.val == t2.val) and self.isMirror(t1.left, t2.right) and self.isMirror(t1.right, t2.left)

    def isMirrorIterative(self, root: TreeNode):
        from collections import deque
        q = deque([
            root,
            root,
        ])

        while q:
            t1, t2 = q.popleft(), q.popleft()

            if t1 == None and t2 == None:
                continue
            if t1 == None or t2 == None:
                return False
            if t1.val != t2.val:
                return False

            q.append(t1.left)
            q.append(t2.right)
            q.append(t1.right)
            q.append(t2.left)
        return True
