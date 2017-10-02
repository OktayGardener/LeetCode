package Java.Tree;
import java.util.*;

class Trees {

    public static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { this.val = x; }
    }

    // 270. Closest Binary Search Tree Value
    /*
    Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

    Note:
    Given target value is a floating point.
    You are guaranteed to have only one unique value in the BST that is closest to the target.
    */

    public int closestValueInBST(TreeNode root, double target) {
        int a = root.val;
        TreeNode child = target < a ? root.left : root.right;
        if(child == null) return a;
        int b = closestValueInBST(child, target);
        return Math.abs(a - target) < Math.abs(b - target) ? a : b;
    }


    /*
    235. Lowest Common Ancestor of a Binary Search Tree
    Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

    According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

            _______6______
           /              \
        ___2__          ___8__
       /      \        /      \
       0      _4       7       9
             /  \
             3   5
    */

    public TreeNode lcaIteratively(TreeNode root, TreeNode l1, TreeNode l2) {
        while(root != null) {
            if(root.val < l1.val && root.val < l2.val) {
                root = root.right;
            } else if(root.val > l1.val && root.val > l2.val) {
                root = root.left;
            } else { break; }
        }
        return root;
    }

    public TreeNode lcaRecursively(TreeNode root, TreeNode l1, TreeNode l2) {
        if(root.val < l1.val && root.val < l2.val) {
            return lcaRecursively(root.right, l1, l2);
        } else if(root.val > l1.val && root.val > l2.val) {
            return lcaRecursively(root.left, l1, l2);
        } else {
            return root;
        }
    }

    /*
    101. Symmetric Tree

    Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

    For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

        1
       / \
      2   2
     / \ / \
    3  4 4  3
    But the following [1,2,2,null,3,null,3] is not:
        1
       / \
      2   2
       \   \
       3    3
    */

    public boolean isSymmetricTree(TreeNode root) {
        return root == null || isSymmetricTree(root.left, root.right);
    }

    public boolean isSymmetricTree(TreeNode left, TreeNode right) {
        if(left == null || right == null) return left == right;
        if(left.val != right.val) return false;
        return isSymmetricTree(left.left, right.right) && isSymmetricTree(left.right, right.left);
    }


    /*
    112. Path Sum
    Given a binary tree and a sum, determine if the tree has a
    root-to-leaf path such that adding up all the values along the path equals the given sum.

    For example:
    Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
        return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
    */

    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        if(root.left == null && root.right == null && sum - root.val == 0) return true;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    /* 654. Maximum Binary Tree
    Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

    The root is the maximum number in the array.
    The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
    The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
    Construct the maximum tree by the given array and output the root node of this tree.

    Example 1:
    Input: [3,2,1,6,0,5]
    Output: return the tree root node representing the following tree:

          6
        /   \
       3     5
        \    /
         2  0
           \
            1
    */

    public TreeNode constructMaxBinTree(int[] nums) {
        return constructMaxBinTree(nums, 0, nums.length);
    }

    public TreeNode constructMaxBinTree(int[] nums, int l, int r) {
        if(l == r) return null;
        int max_i = maxValIndex(nums, l, r);
        TreeNode root = new TreeNode(nums[max_i]);
        root.left = constructMaxBinTree(nums, l, max_i);
        root.right = constructMaxBinTree(nums, max_i + 1, r);
        return root;
    }

    public int maxValIndex(int[] nums, int l, int r) {
        int max_i = l;
        for(int i = l; i < r; i++) {
            if(nums[i] > nums[max_i]) max_i = i;
        }
        return max_i;
    }


    // 513. Find Bottom Left Tree Value
    /*
    Given a binary tree, find the leftmost value in the last row of the tree.

    Example 1:
    Input:

        2
       / \
      1   3

    Output:
    1
    */


    /* 94. Binary Tree Inorder Traversal
    Given a binary tree, return the inorder traversal of its nodes' values.

    For example:
    Given binary tree [1,null,2,3],
       1
        \
         2
        /
       3
    return [1,3,2].

    Note: Recursive solution is trivial, could you do it iteratively?

    */


    public List<Integer> inorderTraversal(TreeNode root, boolean iter) {
        List<Integer> vals = new ArrayList<Integer>();
        if(root == null) return vals;
        if(iter == true) {
            inorderTraversalIteratively(root, vals);
        } else {
            inorderTraversalRecursively(root, vals);
        }
        return vals;
    }

    public void inorderTraversalRecursively(TreeNode root, List<Integer> vals) {
        if(root != null) {
            inorderTraversalRecursively(root.left, vals);
            vals.add(root.val);
            inorderTraversalRecursively(root.right, vals);
        }
    }

    public void inorderTraversalIteratively(TreeNode root, List<Integer> vals) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode current = root;
        while(current != null || !stack.isEmpty()) {
            while(current != null) {
                stack.add(current);
                current = current.left;
            }

            current = stack.pop();
            vals.add(current.val);
            current = current.right;
        }
    }

    public int findBottomLeftTreeValue(TreeNode root) {
        if(root == null) return -1;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        while(!q.isEmpty()) {
            root = q.poll();
            if(root.right != null) q.add(root.right);
            if(root.left != null) q.add(root.left);
        }
        return root.val;
    }


    public static void main(String[] args) {
        System.out.println("hellow");
        TreeNode root = new TreeNode(10);
        Trees tree = new Trees();
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(2);
        root.right.right = new TreeNode(20);
        System.out.println(tree.closestValueInBST(root, 13));
        System.out.println(tree.hasPathSum(root, 17));
        System.out.println(tree.findBottomLeftTreeValue(root));
        System.out.println(tree.inorderTraversal(root, true));
        System.out.println(tree.inorderTraversal(root, false));
    }
}
