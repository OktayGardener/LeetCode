package Java.LinkedList;
import java.util.*;

class LinkedLists {

    // 237. Delete Node in a Linked List

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) { this.val = x; }
    }

    /* 237. Delete Node in a Linked List
    Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
    Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3,
    the linked list should become 1 -> 2 -> 4 after calling your function.
    */

    public void deleteNode(ListNode node) {
        if(node == null || node.next == null) return;
        node.val = node.next.val;
        node.next = node.next.next;
    }

    // 206. Reverse Linked List

    // Recursively
    public ListNode reverseRecursively(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode next = head.next;
        ListNode prev = reverseRecursively(next);
        next.next = head;
        head.next = null;
        return prev;
    }

    // Other recursive approach
    public ListNode reverseRecursively2(ListNode head) {
        return reverseRecursively2(head, null);
    }

    public ListNode reverseRecursively2(ListNode head, ListNode prev) {
        if(head == null) return prev;
        ListNode next = head.next;
        head.next = prev;
        return reverseRecursively2(next, head);
    }

    public ListNode reverseIteratively(ListNode head) {
        ListNode prev = null;
        while(head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }

        return prev;
    }


    // 21. Merge Two Sorted Lists
    /*
    Merge two sorted linked lists and return it as a new list.
    The new list should be made by splicing together the nodes of the first two lists.
    */

    public ListNode mergeLinkedList(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;

        ListNode head;
        if(l1.val < l2.val) {
            head = l1;
            head.next = mergeLinkedList(l1.next, l2);
        } else {
            head = l2;
            head.next = mergeLinkedList(l1, l2.next);
        }

        return head;
    }

    public void printLinkedList(ListNode head) {
        while(head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println("");
    }

    /*
    141. Linked List Cycle
    Given a linked list, determine if it has a cycle in it.

    Follow up:
    Can you solve it without using extra space?
    */

    public boolean hasCycle(ListNode head) {
        if(head == null) return false;

        ListNode fast = head, slow = head;

        while(fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) return true;
        }

        return false;
    }


    /*
    160. Intersection of Two Linked Lists
    Write a program to find the node at which the intersection of two singly linked lists begins.
        For example, the following two linked lists:

        A:          a1 → a2
                           ↘
                             c1 → c2 → c3
                           ↗
        B:     b1 → b2 → b3

        begin to intersect at node c1.

    */

    public ListNode getIntersection(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null) return null;
        ListNode a = l1, b = l2;

        while(a != b) {
            a = a == null ? l2 : a.next;
            b = b == null ? l1 : b.next;
        }

        return a;
    }


    /* 445. Add Two Numbers II
    You are given two non-empty linked lists representing two non-negative integers.
    The most significant digit comes first and each of their nodes contain a single digit.
    Add the two numbers and return it as a linked list.

    You may assume the two numbers do not contain any leading zero, except the number 0 itself.

    Follow up:
    What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

    Example:

    Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
    Output: 7 -> 8 -> 0 -> 7
    */

    public ListNode addTwoLinkedLists(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();

        while(l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }

        while(l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }

        int sum = 0;
        ListNode tail = new ListNode(0);
        while(!s1.isEmpty() || !s2.isEmpty()) {
            if(!s1.isEmpty()) sum += s1.pop();
            if(!s2.isEmpty()) sum += s2.pop();

            tail.val = sum % 10;
            ListNode head = new ListNode(sum / 10);
            head.next = tail;
            tail = head;
            sum /= 10;
        }

        return tail.val == 0 ? tail.next : tail;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    Stack<Integer> s1 = new Stack<Integer>();
    Stack<Integer> s2 = new Stack<Integer>();

    while(l1 != null) {
        s1.push(l1.val);
        l1 = l1.next;
    };
    while(l2 != null) {
        s2.push(l2.val);
        l2 = l2.next;
    }

    int sum = 0;
    ListNode list = new ListNode(0);
    while (!s1.empty() || !s2.empty()) {
        if (!s1.empty()) sum += s1.pop();
        if (!s2.empty()) sum += s2.pop();
        list.val = sum % 10;
        ListNode head = new ListNode(sum / 10);
        head.next = list;
        list = head;
        sum /= 10;
    }

    return list.val == 0 ? list.next : list;
}


    public static void main(String[] args) {
        LinkedLists ll = new LinkedLists();
        ListNode l = new ListNode(10);
        l.next = new ListNode(20);
        l.next.next = new ListNode(30);
        l.next.next.next = new ListNode(40);

        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(11);
        l2.next.next = new ListNode(23);
        l2.next.next.next = new ListNode(39);


        ll.printLinkedList(l);

        l = ll.reverseRecursively(l);
        ll.printLinkedList(l);

        ListNode l1 = ll.reverseRecursively2(l);
        ll.printLinkedList(l);

        l = ll.reverseIteratively(l);
        ll.printLinkedList(l);

        ListNode merged = ll.mergeLinkedList(l1, l2);
        ll.printLinkedList(merged);
        System.out.println(ll.hasCycle(l1));
        l1.next.next.next = l1.next;
        System.out.println(ll.hasCycle(l1));

        // ll.addTwoLinkedLists(l, l2);
        ll.addTwoNumbers(l, l2);
    }
}
