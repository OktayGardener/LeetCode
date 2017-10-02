import java.util.*;
class PrevFacebook {

    // DFS in a Matrix
    // 463 Island Perimiter
    // each side of island (4) * num islands - neighbor (connected islands) * 2 (since remove one side of both)
    public int islandPerimiter(int[][] grid) {
        int islands = 0, neighbors = 0;

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == 1) {
                    islands++;
                    if(i < grid.length - 1 && grid[i + 1][j] == 1) neighbors++;
                    if(j < grid.length - 1 && grid[i][j + 1] == 1) neighbors++;
                }
            }
        }
        return islands * 4 - neighbors * 2;
    }

    // Number of Islands
    // count number of islands (num connected components in graph)
    public static int numIslands(int[][] grid) {
        int count = 0;
        int row = grid.length;
        if(row == 0) return 0;
        int col = grid[0].length;

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == 1) {
                    DFSMarking(grid, i, j, row, col);
                    count++;
                }
            }
        }

        return count;
    }

    public static void DFSMarking(int[][] grid, int i, int j, int row, int col) {
        if(i < 0 || j < 0 || i >= row || j >= col || grid[i][j] != 1) {
            return;
        }
        grid[i][j] = 0;
        DFSMarking(grid, i - 1, j, row, col);
        DFSMarking(grid, i + 1, j, row, col);
        DFSMarking(grid, i, j - 1, row, col);
        DFSMarking(grid, i, j + 1, row, col);
    }

    // Unique Paths
    public int uniquePaths(int m, int n) {
        Integer[][] map = new Integer[m][n];

        for(int i = 0; i < m; i++) map[i][0] = 1;
        for(int i = 0; i < n; i++) map[0][i] = 1;

        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                map[i][j] = map[i - 1][j] + map[i][j - 1];
            }
        }

        return map[m - 1][n - 1];
    }

    // Generate Strings for Phone number
    /*
    Input:Digit string "23"
    Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
    */
    // Add abc, then for each of those, add combo from other digit, etc.
    public static List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for(int i = 0; i < digits.length(); i++) {
            int x = Character.getNumericValue(digits.charAt(i));
            while(ans.peek().length() == i) {
                String t = ans.remove();
                for(char s : mapping[x].toCharArray()) {
                    ans.add(t + s);
                }
            }
        }
        return ans;
    }

    // Implement Binary Search

    public static int binarySearchRecursive(int[] A, int l, int r, int x) {
        if(l <= r) {
            int mid = (l + r) / 2;
            if(A[mid] == x) {
                return mid;
            } else if(A[mid] > x) {
                binarySearchRecursive(A, l, mid - 1, x);
            } else {
                return binarySearchRecursive(A, mid + 1, r, x);
            }
        }
        return -1;
    }

    public static int binarySearchIterative(int[] A, int l, int r, int x) {
        while(l <= r) {
            int mid = (l + r) / 2;
            if(A[mid] == x) {
                return mid;
            } else if(A[mid] > x) {
                r = mid - 1;
            } else if(A[mid] < x) {
                l = mid + 1;
            }
        }
        return -1;
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    // Return the sum of node values at each level of binary tree

    public static List<Integer> sumEachNode(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root == null) return null;
        sumEachNode(root, res);
        return res;
    }

    public static void sumEachNode(TreeNode root, List<Integer> res) {
        LinkedList<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        while(!q.isEmpty()) {
            int level = q.size();
            int sum = 0;
            for(int i = 0; i < level; i++) {
                TreeNode c = q.removeFirst();
                if(c.left != null) q.add(c.left);
                if(c.right != null) q.add(c.right);
                sum += c.val;
            }
            res.add(sum);
        }
    }

    public static boolean balanceParens(String str) {
        Stack<Character> s = new Stack();
        for(Character c : str.toCharArray()) {
            if(c == '(') {
                s.push(')');
            } else if(c == '[') {
                s.push(']');
            } else if(c == '{') {
                s.push('}');
            } else if(s.isEmpty() || s.pop() != c) {
                return false;
            }
        }
        return s.isEmpty();
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
            {1,1,1,1,0},
            {1,1,0,1,0},
            {1,1,0,0,0},
            {0,0,0,0,0},
        };

        System.out.println(numIslands(grid));
        List<String> letters = letterCombinations("23");
        System.out.println(letters);

        int[] A = new int[]{0,5,10,33,55,60,75,100};
        System.out.println(binarySearchRecursive(A, 0, A.length - 1, 33));
        System.out.println(binarySearchIterative(A, 0, A.length - 1, 33));
        TreeNode c = new TreeNode(10);
        c.left = new TreeNode(5);
        c.right = new TreeNode(15);
        c.left.left = new TreeNode(2);
        c.right.right = new TreeNode(20);
        System.out.println(sumEachNode(c));
        System.out.println(balanceParens("(()){"));
        System.out.println(balanceParens("(({}))"));
    }

}
