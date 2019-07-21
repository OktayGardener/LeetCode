# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution(object):
    def getIntersectionNode(self, headA, headB):
        """
        :type head1, head1: ListNode
        :rtype: ListNode
        """
        headAA = headA
        headBB = headB
        lA = 0
        lB = 0

        while headAA is not None:
            lA += 1
            headAA = headAA.next

        while headBB is not None:
            lB += 1
            headBB = headBB.next

        while lA > lB:
            headA = headA.next
            lA -= 1

        while lB > lA:
            headB = headB.next
            lB -= 1

        while headA is not None and headA is not headB:
            headA = headA.next
            headB = headB.next

        return headA
