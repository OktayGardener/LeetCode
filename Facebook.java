/* LeetCode Solutions */
import java.util.*;

class Facebook {
    /*******************
    Numbers
    ****************/
    // Implement int sqrt(int x).
    // Compute and return the square root of x.

    public static int sqrtBinarySearch(int x) {
        if(x == 0) return 0;
        int left = 1, right = Integer.MAX_VALUE;

        while(true) {
            int mid = left + (right - left) / 2;
            System.out.println(mid);
            if(mid > x / mid) {
                right = mid - 1;
            } else {
                if(mid + 1 > x / (mid + 1)) return mid;
                left = mid + 1;
            }
        }
    }

    public static int sqrtNewton(int x) {
        long r = x;
        while(r * r > x) {
            r = (r + x / r) / 2;
        }
        return (int) r;
    }

    /*******************
    BIT MANIPULATION
    ****************/

    // Hamming Distance
    /*
    The Hamming distance between two integers is the number of positions
    at which the corresponding bits are different.

    Given two integers x and y, calculate the Hamming distance.
    */
    public static int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

    public int hammingWeight(int n) {
        int weight = 0;
        for(int i = 0; i < 32; i++) weight += (n >> i & 1) == 1 ? 1 : 0;

        return weight;
    }    

    public int hammingDistance2(int x, int y) {
        int xor = x ^ y, count = 0;
        // int = 32-bits
        for(int i = 0; i < 32; i++) count += (xor >> i & 1) == 1 ? 1 : 0;
        return count;
    }

    // Add Binary
    // Given two binary strings, return their sum (also binary)
    // Example:
    // a = "11"
    // b = "1"
    // return "100"
    public static String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1, carry = 0;

        while(i >= 0 || j >= 0) {
            int sum = carry;
            if(i >= 0) sum += a.charAt(i--) - '0';
            if(j >= 0) sum += b.charAt(j--) - '0';
            sb.append(sum % 2);
            carry = sum / 2;
        }

        if(carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }

    // Expression Add Operators
    // Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

    // Examples:
    // "123", 6 -> ["1+2+3", "1*2*3"]
    // "232", 8 -> ["2*3+2", "2+3*2"]
    // "105", 5 -> ["1*0+5","10-5"]
    // "00", 0 -> ["0+0", "0-0", "0*0"]
    // "3456237490", 9191 -> []


    public static List<String> addOperators(String num, int target) {
        List<String> rst = new ArrayList<String>();
        if(num == null || num.length() == 0) return rst;
        addOperators(rst, "", num, target, 0, 0, 0);
        return rst;
    }

    public static void addOperators(List<String> rst, String path, String num, int target, int pos, long eval, long multed) {
        if(pos == num.length()) {
            if(target == eval) rst.add(path);
            return;
        }

        for(int i = pos; i < num.length(); i++) {
            if(i != pos && num.charAt(pos) == '0') break;
            long cur = Long.parseLong(num.substring(pos, i+1));
            if(pos == 0) {
                addOperators(rst, path + cur, num, target, i+1, cur, cur);
            } else {
                addOperators(rst, path + "+" + cur, num, target, i + 1, eval + cur, cur);
                addOperators(rst, path + "-" + cur, num, target, i + 1, eval - cur, -cur);
                addOperators(rst, path + "*" + cur, num, target, i + 1, eval - multed + multed * cur, multed * cur);
            }
        }
    }


    /******************* ARRAYS
    ****************/

    // Move Zeroes
    /*
    Given an array nums, write a function to move all 0's to the end
    of it while maintaining the relative order of the non-zero elements.

    For example, given nums = [0, 1, 0, 3, 12],
    after calling your function, nums should be [1, 3, 12, 0, 0].

    1 You must do this in-place without making a copy of the array.
    2 Minimize the total number of operations.
    */
    public static int[] moveZeroes(int[] nums) {
        if(nums == null || nums.length == 0) return nums;

        printArray(nums); // 0 1 0 3 12
        int insertPos = 0;
        for(int num : nums) {
            if(num != 0) nums[insertPos++] = num;
        }

        printArray(nums); // 1 3 12 3 12

        while(insertPos < nums.length) {
            nums[insertPos++] = 0;
        }

        return nums;
    }

    // Idea: Swap if we find non zero, push all zeros to back
    public static int[] moveZeroes2(int[] nums) {
        int insert = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != 0) {
                int temp = nums[insert];
                nums[insert] = nums[i];
                nums[i] = temp;
                insert++;
            }
            printArray(nums);
        }
        return nums;
    }

    // Sort colors
    /*
    Given an array with n objects colored red, white or blue,
    sort them so that objects of the same color are adjacent,
    with the colors in the order red, white and blue.
    */
    // Idea: The idea is to sweep all 0s to the left and all 2s to the right,
    // then all 1s are left in the middle.
    // O(2n)
    public static void sortColors(int[] A, int n) {
        int second = n - 1, zero = 0;
        for(int i = 0; i <= second; i++) {
            while(A[i] == 2 && i < second) swap(A, A[i], A[second--]);
            while(A[i] == 0 && i > zero) swap(A, A[i], A[zero++]);
        }
    }

    // Best Time to Buy and Sell Stock

    /* Say you have an array for which the ith element is the price of a given stock on day i.
    If you were only permitted to complete at most one transaction
    (ie, buy one and sell one share of the stock),
    design an algorithm to find the maximum profit.

    Input: [7, 1, 5, 3, 6, 4]
    Output: 5

    max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
    */

    public static int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE, max = 0;
        for(int i = 0; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            if(prices[i] - min > max) max = prices[i] - min;
        }
        return max;
    }

    /*
    Here, the logic is to calculate the difference
    (maxCur += prices[i] - prices[i-1]) of the original array,
    and find a contiguous subarray giving maximum profit.
    If the difference falls below 0, reset it to zero.
    */
    public static int maxProfitKadane(int[] prices) {
        int currMax = 0, max = 0;
        for(int i = 1; i < prices.length; i++) {
            currMax = Math.max(0, currMax += prices[i] - prices[i - 1]);
            System.out.println("currMax " + currMax);
            max = Math.max(max, currMax);
            System.out.println("max " + max);
        }
        return max;
    }

    // Max profit with penalty
    public static int maxPenalty(int[] prices, int fee) {
        int rt = 0, k = prices.length - 1;
        int[] sells = new int[k + 1];
        int[] buys = new int[k + 1];
        for(int p : prices) {
            for(int i = k; i > 0; i--) {
                // p + buys[i] is the profit - fee
                sells[i] = Math.max(sells[i], p + buys[i] - fee);
                buys[i] = Math.max(buys[i], sells[i - 1] - p);
            }
        }
        int max = Integer.MIN_VALUE;
        for(int i : sells) max = Math.max(max, i);
        return max;
    }

    // Reverse words in a char array
    public static char[] reverseOrderOfWords(char[] words) {
        StringBuffer current = new StringBuffer();
        StringBuffer result = new StringBuffer();

        for(int i = words.length - 1; i >= 0; i--) {
            if(words[i] != ' ') current.append(words[i]);

            if(words[i] == ' ' || i == 0) {
                result.append(current.reverse()).append(" ");
                current = new StringBuffer();
            }
        }
        return result.toString().toCharArray();
    }


    // Remove Duplicates from Sorted Array
    // Given a sorted array, remove the duplicates in place such that each element
    // appear only once and return the new length.
    // Example: Given input array nums = [1,1,2], return 2.

    public static int removeDuplicates(int[] nums) {
        int i = 0;
        // Swoop array, keep track of last seen duplicate
        // increment when found bigger value, since sorted
        for(int n : nums) {
            if(i == 0 || n > nums[i]) nums[i++] = n;
        }
        return i;
    }

    // Two Sum:
    // Given an array of integers, return indices of the two numbers such that they add up to a specific target.
    // You may assume that each input would have exactly one solution, and you may not use the same element twice.
    /*
    Given nums = [2, 7, 11, 15], target = 9,
    Because nums[0] + nums[1] = 2 + 7 = 9,
    return [0, 1].
    */

    // Add difference as key, value: index of other,
    // try to look up key, if found, return
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> diffs = new HashMap<Integer, Integer>();

        for(int i = 0; i < nums.length; i++) {
            if(diffs.containsKey(nums[i])) {
                return new int[] { diffs.get(nums[i]), i };
            }
            diffs.put(target - nums[i], i);
        }
        return new int[2];
    }

    // Merge Sorted Array

    // Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
    // You may assume that nums1 has enough space (size that is greater or equal to m + n)
    // to hold additional elements from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.

    public static void mergeSortedArrays(int[] A, int m, int[] B, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;

        while(i >= 0 && j >= 0) A[k--] = (A[i] > B[j]) ? A[i--] : B[j--];
        printArray(A);
        while(j >= 0) A[k--] = B[j--];
        printArray(A);
    }

    // Find celebrity
    /*
    Suppose you are at a party with n people (labeled from 0 to n - 1) and among them,
     there may exist one celebrity.
     The definition of a celebrity is that all the other n - 1
     people know him/her but he/she does not know any of them.

    Now you want to find out who the celebrity is or verify that there is not one.
    The only thing you are allowed to do is to ask questions like:
    "Hi, A. Do you know B?" to get information of whether A knows B.
    You need to find out the celebrity (or verify there is not one)
    by asking as few questions as possible (in the asymptotic sense).

    You are given a helper function bool knows(a, b) which tells
    you whether A knows B.
    Implement a function int findCelebrity(n),
    your function should minimize the number of calls to knows.
    */
    public static int findCelebrity(int n) {
        int x = 0;
        for(int i = 1; i < n; i++) if(knows(x, i)) x = i;
        for(int i = 0; i < n; i++) {
            //     celeb knows someone || someone doesnt know of them
            if(i != x && (knows(x, i) || !knows(i, x))) return -1;
        }

        return x;
    }
    /*
    Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

    For example,
    Given [100, 4, 200, 1, 3, 2],
    The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

    Your algorithm should run in O(n) complexity.
    */

    public static int longestConsecutiveSequence(int[] num) {
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for(int n : num) {
            if(!map.containsKey(n)) {
                int left = (map.containsKey(n - 1)) ? map.get(n - 1) : 0;
                int right = (map.containsKey(n + 1)) ? map.get(n + 1) : 0;
                int sum = left + right + 1;

                map.put(n, sum);
                res = Math.max(res, sum);

                // put this sum for the other numbers in series
                map.put(n - left, sum);
                map.put(n + right, sum);
            }
        }
        return res;
    }


    // Product of array except itself
    /* Given an array of n integers where n > 1, nums,
    return an array output such that output[i] is equal to the product of
    all the elements of nums except nums[i].

    Solve it without division and in O(n).
    For example, given [1,2,3,4], return [24,12,8,6]. */
    public static int[] productExceptItself(int[] nums) {
        int[] res = new int[nums.length];
        for(int i = 0, temp = 1; i < nums.length; i++) {
            res[i] = temp;
            temp *= nums[i];
        }

        for(int i = nums.length - 1, temp = 1; i >= 0; i--) {
            res[i] *= temp;
            temp *= nums[i];
        }

        return res;
    }

    public static int[] productExceptItself2(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;

        for(int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }

        for(int i = n - 1, temp = 1; i >= 0; i--) {
            res[i] *= temp;
            temp *= nums[i];
        }

        return res;
    }

    // Merge Intervals

    //    Given a collection of intervals, merge all overlapping intervals.

    // For example,
    // Given [1,3],[2,6],[8,10],[15,18],
    // return [1,6],[8,10],[15,18].

    public static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }


    public static List<Interval> merge(List<Interval> intervals) {
        if(intervals.size() <= 1) return intervals;

        // O(n log n)
        intervals.sort((i1, i2) -> Integer.compare(i1.start, i2.start));

        List<Interval> ret = new ArrayList<Interval>();
        int start = ret.get(0).start, end = ret.get(0).end;

        for(Interval i : intervals) {
            if(end >= start) {
                end = Math.max(end, i.end);
            } else {
                ret.add(new Interval(start, end));
                start = i.start;
                end = i.end;
            }
        }

        ret.add(new Interval(start, end));
        return ret;
    }


    // Kth Largest Element in an Array
    // Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

    // For example,
    // Given [3,2,1,5,6,4] and k = 2, return 5.

    // Approaches:
    // O(N log N) + O(1) memory
    public static int findKthLargest(int[] nums, int k) {
        final int N = nums.length;
        Arrays.sort(nums);
        return nums[N - k];
    }

    // Use max heap, always keep max k elements -> top = k.
    // O(N) best case / O(N^2) worst case running time + O(1) memory
    public static int findKthLargest2(int[] nums, int k) {
        final PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int val : nums) {
            pq.offer(val);
            if(pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    // Selection sort + Partition
    // For guaranteed O(N) running time: Randomize input!
    public static int findKthLargest3(int[] nums, int k) {
        k = nums.length - k;
        int lo = 0;
        int hi = nums.length - 1;
        while(lo < hi) {
            final int j = partition(nums, lo, hi);
            if(j < k) {
                lo = j + 1;
            } else if(j > k) {
                hi = j - 1;
            } else {
                break;
            }
        }
        return nums[k];
    }

    public static int partition(int[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        while(true) {
            while(i < hi && less(a[++i], a[lo]));
            while(j > lo && less(a[lo], a[--j]));
            if(i >= j) break;
            swap(a, i, j);
        }
        swap(a, lo, j);
        return j;
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static boolean less(int v, int w) {
        return v < w;
    }

    /*
    Given a set of distinct integers, nums, return all possible subsets.

    Note: The solution set must not contain duplicate subsets.

    For example,
    If nums = [1,2,3], a solution is:

    [
    [3],
    [1],
    [2],
    [1,2,3],
    [1,3],
    [2,3],
    [1,2],
    []
    ]
    */

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums); // not necessary
        backtrackSubsets(list, new ArrayList<>(), nums, 0);
        return list;
    }

    // Backtracking can be solved always as follows:
    //
    // Pick a starting point.
    // while(Problem is not solved)
    //     For each path from the starting point.
    //         check if selected path is safe, if yes select it
    //         and make recursive call to rest of the problem
    //         before which undo the current move.
    //     End For
    // If none of the move works out, return false, NO SOLUTON.

    public static void backtrackSubsets(List<List<Integer>> list, List<Integer> temp, int[] nums, int start) {
        list.add(new ArrayList<>(temp));
        for(int i = start; i < nums.length; i++) {
            // skip duplicates in array:
            // if(i > start && nums[i] == nums[i - 1]) continue;
            temp.add(nums[i]);
            backtrackSubsets(list, temp, nums, i + 1);
            temp.remove(temp.size() - 1);
        }
    }

    /* Given an array of strings, group anagrams together.

    For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
    Return:

    [
    ["ate", "eat","tea"],
    ["nat","tan"],
    ["bat"]
    ]
    */

    public static List<List<String>> groupAnagrams(String[] strs) {
        if(strs == null || strs.length == 0) return new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for(String s : strs) {
            char[] cs = s.toCharArray();
            Arrays.sort(cs);
            String key = String.valueOf(cs);
            if(!map.containsKey(key)) map.put(key, new ArrayList<String>());
            map.get(key).add(s);
        }
        return new ArrayList<List<String>>(map.values());
    }


        public boolean isOneEditDistance(String s, String t) {
        if(Math.abs(s.length() - t.length()) > 1) return false;
        if(s.length() == t.length()) return oneModify(s, t);
        if(s.length() > t.length()) return oneDelete(s, t);
        return oneDelete(t, s);
    }

    public boolean oneModify(String s, String t) {
        int diff = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != t.charAt(i)) diff++;
        }
        return diff == 1;
    }

    public boolean oneDelete(String s, String t) {
        for(int i = 0, j = 0; i < s.length() && j < t.length(); i++, j++) {
            if(s.charAt(i) != t.charAt(j)) {
                return s.substring(i + 1).equals(t.substring(j));
            }
        }
        return true;
    }

    /*******************
    GRID MATRIX
    ****************/
    /*
    Given a 2d grid map of '1's (land) and '0's (water),
    count the number of islands. An island is surrounded by water
    and is formed by connecting adjacent lands horizontally or vertically.
    You may assume all four edges of the grid are all surrounded by water.

    Example 1:

    11110
    11010
    11000
    00000
    Answer: 1
    */

    /**
    * Given a 2d grid map of '1's (land) and '0's (water),
    * count the number of islands.
    *
    * This method approaches the problem as one of depth-first connected
    * components search
    * @param grid, the given grid.
    * @return the number of islands.
    */
    public static int numIslands(char[][] grid) {
        int row = grid.length;
        if(row == 0) return 0;
        int count = 0;
        int col = grid[0].length;

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == '1') {
                    DFSMarking(grid, i, j, row, col);
                    count++;
                }
            }
        }
        return count;
    }

    public static void DFSMarking(char[][] grid, int i, int j, int row, int col) {
        if(i < 0 || j < 0 || i >= row || j >= col || grid[i][j] != '1') return;
        grid[i][j] = '0';
        DFSMarking(grid, i + 1, j, row, col);
        DFSMarking(grid, i - 1, j, row, col);
        DFSMarking(grid, i, j + 1, row, col);
        DFSMarking(grid, i, j - 1, row, col);
    }

    // Search in Rotated Sorted Array
    /*  Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
    (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
    You are given a target value to search. If found in the array return its index, otherwise return -1.
    You may assume no duplicate exists in the array. */

    public static int searchRotatedArray(int[] A, int n, int target) {
        int lo = 0, hi = n - 1;
        while(lo < hi) {
            int mid = (lo + hi) / 2;
            if(A[mid] > A[hi]) lo = mid + 1;
            else hi = mid;
        }

        int rot = lo;
        lo = 0;
        hi = n - 1;

        while(lo <= hi) {
            int mid = (lo + hi) / 2;
            int realmid = (mid + rot) % n;
            if(A[realmid] == target) return realmid;
            if(A[realmid] < target) lo = mid + 1;
            else hi = mid - 1;
        }
        return -1;
    }




/*

Implement TRIE

*/

class TrieNode {
    public char val;
    public boolean isWord;
    public TrieNode[] children = new TrieNode[26];
    public TrieNode() {}
    TrieNode(char c){
        TrieNode node = new TrieNode();
        node.val = c;
    }
}

public class Trie {
    private TrieNode root;
    public Trie() {
        root = new TrieNode();
        root.val = ' ';
    }

    public void insert(String word) {
        TrieNode ws = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(ws.children[c - 'a'] == null){
                ws.children[c - 'a'] = new TrieNode(c);
            }
            ws = ws.children[c - 'a'];
        }
        ws.isWord = true;
    }

    public boolean search(String word) {
        TrieNode ws = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(ws.children[c - 'a'] == null) return false;
            ws = ws.children[c - 'a'];
        }
        return ws.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode ws = root;
        for(int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if(ws.children[c - 'a'] == null) return false;
            ws = ws.children[c - 'a'];
        }
        return true;
    }
}


    /*******************
    STRINGS
    ****************/

    // Count and Say
    /*
    The count-and-say sequence is the sequence of integers with the first five terms as following:

    1.     1
    2.     11
    3.     21
    4.     1211
    5.     111221

    Given an integer n, generate the nth term of the count-and-say sequence.

    Note: Each term of the sequence of integers will be represented as a string.

    Example 1:
    Input: 1
    Output: "1"

    Example 2:
    Input: 4
    Output: "1211"

    */

    public String countAndSay(int n) {
        StringBuilder curr = new StringBuilder("1");
        StringBuilder prev;
        int count;
        char say;

        for(int i = 1; i < n; i++) {
            prev = curr;
            curr = new StringBuilder();
            count = 1;
            say = prev.charAt(0);

            for(int j = 1, len = prev.length(); j < len; j++) {
                if(prev.charAt(j) != say) {
                    curr.append(count).append(say);
                    count = 1;
                    say = prev.charAt(j);
                } else {
                    count++;
                }
            }
            curr.append(count).append(say);
        }
        return curr.toString();
    }

    /*
    Implement strStr().
    Returns the index of the first occurrence of needle in haystack,
    or -1 if needle is not part of haystack.
    */

    public int strStr(String haystack, String needle) {
        for(int i = 0; ; i++) {
            for(int j = 0; ; j++) {
                if(j == needle.length()) return i;
                if(i + j == haystack.length()) return -1;
                if(needle.charAt(j) != haystack.charAt(i + j)) break;
            }
        }
    }

    public int strStr2(String haystack, String needle) {
        int l1 = haystack.length(), l2 = needle.length();
        if(l1 < l2) return -1;
        else if(l2 == 0) return 0;

        int limit = l1 - l2;

        for(int i = 0; i <= limit; i++) {
            if(haystack.substring(i, i + l2).equals(needle)) return i;
        }

        return -1;
    }


    /* Word Ladder
    Given two words (beginWord and endWord), and a dictionary's word list,
    find the length of shortest transformation sequence from beginWord to endWord, such that:

    Only one letter can be changed at a time.
    Each transformed word must exist in the word list.
    Note that beginWord is not a transformed word.
    For example,

    Given:
    beginWord = "hit"
    endWord = "cog"
    wordList = ["hot","dot","dog","lot","log","cog"]
    */

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> beginSet = new HashSet<String>(), endSet = new HashSet<String>();

        int len = 1;
        int strLen = beginWord.length();
        HashSet<String> visited = new HashSet<String>();

        beginSet.add(beginWord);
        endSet.add(endWord);

        while(!beginSet.isEmpty() && !endSet.isEmpty()) {
            if(beginSet.size() > endSet.size()) {
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }

            Set<String> temp = new HashSet<String>();

            for(String w : beginSet) {
                char[] cs = beginWord.toCharArray();
                for(int i = 0; i < cs.length; i++) {
                    for(char c = 'a'; c <= 'z'; c++) {

                        char old = cs[i];
                        cs[i] = c;
                        String target = String.valueOf(cs);

                        if(endSet.contains(target)) {
                            return len + 1;
                        }
                        // if we havent seen this word and this word appears in wordlist, add it
                        if(!visited.contains(target) && wordList.contains(target)) {
                            temp.add(target);
                            visited.add(target);
                        }

                        cs[i] = old;
                    }
                }
            }
            beginSet = temp;
            len++;
        }

        return 0;
    }





    /* Valid Palindrome
    Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

    For example,
    "A man, a plan, a canal: Panama" is a palindrome.
    "race a car" is not a palindrome.

    Note:
    Have you consider that the string might be empty? This is a good question to ask during an interview.
    For the purpose of this problem, we define empty string as valid palindrome.
    */

    public boolean isValidPalindrome(String s) {
        String noWhites = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String rev = new StringBuffer(noWhites).reverse().toString();
        return noWhites.equals(rev);
    }

    public boolean isValidPalindrome2(String s) {
        char[] noWhites = s.replaceAll("[A-Za-z0-9]", "").toLowerCase().toCharArray();
        int n = noWhites.length - 1;
        for(int i = 0; i < n; i++) {
            if(noWhites[i] != noWhites[n - i]) return false;
        }
        return true;
    }

    // Excel Sheet Column Title
    public static String convertToTitle(int n) {
        StringBuilder res = new StringBuilder();

        while(n > 0) {
            n--;
            res.insert(0, (char)('A' + n % 26));
            n /= 26;
        }

        return res.toString();
    }

    public static String convertToTitleOneLine(int n) {
        return n == 0 ? "" : convertToTitleOneLine(--n / 26) + (char)('A' + (n % 26));
    }


    // Letter Combinations of a Phone Number
    /*
    Given a digit string, return all possible letter combinations that the number could represent.
    A mapping of digit to letters (just like on the telephone buttons) is given below.
    */

    // Input:Digit string "23"
    // Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].''

    public List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno",
        "pqrs", "tuv", "wxyz"};
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

    /* Word break
    Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
    determine if s can be segmented into a space-separated sequence of one or more dictionary words.
    You may assume the dictionary does not contain duplicate words.

    For example, given
    s = "leetcode",
    dict = ["leet", "code"].
    Return true because "leetcode" can be segmented as "leet code".


    Check window if there's substring
    if there is, true there.
    if end is true = we can break words
    */
    public static boolean wordBreak(String s, List<String> wordDict) {
        boolean[] f = new boolean[s.length() + 1];
        f[0] = true;

        // leetcode
        for(int i = 1; i <= s.length(); i++) {
            for(int j = 0; j < i; j++) {
                if(f[j] && wordDict.contains(s.substring(j, i))) {
                    f[i] = true;
                    break;
                }
            }
        }
        return f[s.length()];
    }


 // H-Index H index
// Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.
public int hIndex(int[] citations) {
        int length = citations.length;
        if (length == 0) {
        	return 0;
        }

        int[] array2 = new int[length + 1];
        for (int i = 0; i < length; i++) {
        	if (citations[i] > length) {
        		array2[length] += 1;
        	} else {
        		array2[citations[i]] += 1;
        	}
        }
        int t = 0;
        int result = 0;

        for (int i = length; i >= 0; i--) {
        	t = t + array2[i];
        	if (t >= i) {
        		return i;
        	}
        }
        return 0;
    }

    /*
    Given a 2D board and a word, find if the word exists in the grid.

    The word can be constructed from letters of sequentially adjacent cell,
    where "adjacent" cells are those horizontally or vertically neighboring.
    The same letter cell may not be used more than once.

    For example,
    Given board =

    [
      ['A','B','C','E'],
      ['S','F','C','S'],
      ['A','D','E','E']
    ]
    word = "ABCCED", -> returns true,
    word = "SEE", -> returns true,
    word = "ABCB", -> returns false.
    */




    // Pow(x, n)
    // Implement pow(x, n)
    // TODO: WRONG

    public static double pow(double x, int n) {
        if(n == 0) return 1;
        if(n < 0) {
            n = -n;
            x = 1/x;
        }
        return (n % 2 == 0) ? pow(x*x, n/2) : x * pow(x*x, n/2);
    }


    // 3Sum
    /*
    Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

    Note: The solution set must not contain duplicate triplets.

    For example, given array S = [-1, 0, 1, 2, -1, -4],

    A solution set is:
    [
      [-1, 0, 1],
      [-1, -1, 2]
    ]
    */

    public static List<List<Integer>> threeSum(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> res = new LinkedList<>();

        for(int i = 0; i < num.length - 2; i++) {
            if(i == 0 || (i > 0 && num[i] != num[i-1])) {
                int lo = i + 1, hi = num.length - 1, sum = 0 - num[i];
                while(lo < hi) {
                    if(num[lo] + num[hi] == sum) {
                        res.add(Arrays.asList(num[i], num[lo], num[hi]));
                        while(lo < hi && num[lo] == num[lo + 1]) lo++;
                        while(lo < hi && num[hi] == num[hi + 1]) hi--;
                        lo++;
                        hi--;
                    } else if(num[lo] + num[hi] < sum) lo++;
                    else hi--;
                }
            }
        }
        return res;
    }

    // Decode Ways
    /*

    A message containing letters from A-Z is being encoded to numbers using the following mapping:

    'A' -> 1
    'B' -> 2
    ...
    'Z' -> 26

    Given an encoded message containing digits, determine the total number of ways to decode it.

    For example,
    Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

    The number of ways decoding "12" is 2.
    */

    public int numDecodings(String s) {
        int n = s.length();
        if(n == 0) return 0;

        int[] memo = new int[n + 1];
        memo[n] = 1;
        memo[n - 1] = s.charAt(n - 1) == '0' ? 0 : 1;

        for(int i = n - 2; i >= 0; i--) {
            if(s.charAt(i) == '0') continue;
            else memo[i] = (Integer.parseInt(s.substring(i, i+2)) <= 26) ? memo[i+1] + memo[i+2] : memo[i+1];
        }

        return memo[0];
    }


    public int numDecodings(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        for(int i = 2; i <= n; i++) {
            int first = Integer.valueOf(s.substring(i-1, i));
            int second = Integer.valueOf(s.substring(i-2, i));
            if(first >= 1 && first <= 9) {
               dp[i] += dp[i-1];
            }
            if(second >= 10 && second <= 26) {
                dp[i] += dp[i-2];
            }
        }
        return dp[n];
    }

    public int numDecodings2(String s) {
        if(s.length() == 0 || s == null) return 0;

        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == 0 ? '0' : 1;

        for(int i = 2; i <= s.length(); i++) {
            int first = Integer.valueOf(s.substring(i - 1, i));
            int second = Integer.valueOf(s.substring(i - 2, i));
            if(first >= 1 && first <= 9) dp[i] += dp[i - 1];
            if(second >= 10 && second <= 26) dp[i] += dp[i - 2];
        }
        return dp[s.length()];
    }

    public static int binarySearch(int[] A, int x) {
        int lo = 0, hi = A.length;
        while(lo < hi) {
            int mid = (lo + hi) / 2;
            if(A[mid] > x) {
                hi = mid - 1;
            } else if(A[mid] < x) {
                lo = mid + 1;
            } else {
                return A[mid];
            }
        }
        return -1;
    }


    // Decode Ways II

    /*

    A message containing letters from A-Z is being encoded to numbers using the following mapping way:

    'A' -> 1
    'B' -> 2
    ...
    'Z' -> 26
    Beyond that, now the encoded string can also contain the character '*',
    which can be treated as one of the numbers from 1 to 9.

    Given the encoded message containing digits and the character '*',
    return the total number of ways to decode it.

    Also, since the answer may be very large, you should return the output mod 109 + 7.

    Example 1:
    Input: "*"
    Output: 9
    Explanation: The encoded message can be decoded to the string:
    "A", "B", "C", "D", "E", "F", "G", "H", "I".

    */




    /*******************
    STACK
    ****************/

    // Valid Parantheses
    // Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
    // The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

    public static boolean isValidParan(String s) {
        Stack<Character> stack = new Stack<Character>();
        for(char c : s.toCharArray()) {
            if(c == '(') {
                stack.push(')');
            } else if(c == '[') {
                stack.push(']');
            } else if(c == '{') {
                stack.push('}');
            } else if(c != stack.pop() || stack.isEmpty()) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    // Remove invalid Parantheses http://www.geeksforgeeks.org/remove-invalid-parentheses/

    public static List<String> removeInvalidParan(String s) {
        List<String> ans = new ArrayList<String>();
        removeInvalidParan(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }

    public static void removeInvalidParan(String s, List<String> ans, int last_i, int last_j, char[] par) {
        for(int stack = 0, i = last_i; i < s.length(); i++) {
            if(s.charAt(i) == par[0]) stack++;
            if(s.charAt(i) == par[1]) stack--;
            if(stack >= 0) continue;

            for(int j = last_j; j <= i; j++) {
                if(s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1])) {
                    removeInvalidParan(s.substring(0, j) + s.substring(j + 1, s.length()), ans, 0, 0, new char[]{'(', ')'});
                }
            }
            return;
        }
        String reversed = new StringBuilder(s).reverse().toString();
        if(par[0] == '(') removeInvalidParan(reversed, ans, 0, 0, new char[]{')', '('});
        else ans.add(reversed);
    }


    // Remove Invalid Parentheses
    /*

    Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

    Note: The input string may contain letters other than the parentheses ( and ).

    Examples:
    "()())()" -> ["()()()", "(())()"]
    "(a)())()" -> ["(a)()()", "(a())()"]
    ")(" -> [""]
    */

    public static List<String> removeInvalidParanFromString(String s) {
        List<String> ans = new ArrayList<>();
        removeInvalidParanFromString(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }

    public static void removeInvalidParanFromString(String s, List<String> ans, int last_i, int last_j, char[] par) {
        for(int stack = 0, i = last_i; i < s.length(); i++) {
            if(s.charAt(i) == par[0]) stack++;
            if(s.charAt(i) == par[1]) stack--;
            if(stack >= 0) continue;

            for(int j = last_j; j <= i; j++) {
                if(s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1]))  {
                    removeInvalidParanFromString(s.substring(0, j) + s.substring(j + 1), ans, i, j, par);
                }
            }
            return;
        }
        String reversed = new StringBuilder(s).reverse().toString();
        if(par[0] == '(') { // finished left to right
            removeInvalidParanFromString(reversed, ans, 0, 0, new char[]{')', '('});
        } else {
            ans.add(reversed);
        }
    }

    public static List<String> removeInvalidParenthesesBFS(String s) {
        List<String> res = new ArrayList<>();
        if(s == null) return res;
        Set<String> visited = new HashSet<>();
        LinkedList<String> queue = new LinkedList<>();

        queue.add(s);
        visited.add(s);

        boolean found = false;

        while(!queue.isEmpty()) {
            s = queue.poll();

            if(hasValidParan(s)) {
                res.add(s);
                found = true;
            }

            if(found) continue;

            for(int i = 0; i < s.length(); i++) {
                if(s.charAt(i) != '(' && s.charAt(i) != ')') continue;
                String t = s.substring(0, i) + s.substring(i + 1);

                if(!visited.contains(t)) {
                    queue.add(t);
                    visited.add(t);
                }
            }
        }
        return res;
    }

    // check if a string has valid num of parans
    public static boolean hasValidParan(String s) {
        int count = 0;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(') count++;
            if(c == ')' && count-- == 0) return false;
        }
        return count == 0;
    }



    /**************************************
    BINARY TREE
    ***********************************/

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    // Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
    //     _______6______
    //    /              \
    // ___2__          ___8__
    // /      \        /      \
    // 0      _4       7       9
    //      /  \
    //      3   5
    // LCA of 2 & 8 = 6

    // By definition, BST: left <(=) root < right

    // Iterative
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while(root != null) {
            if(root.val > p.val && root.val > q.val) {
                root = root.left;
            } else if(root.val < p.val && root.val < q.val) {
                root = root.right;
            } else {
                break;
            }
        }
        return root;
    }

    // Recursive
    public TreeNode lowestCommonAncestorRec(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val > p.val && root.val > q.val) {
            return lowestCommonAncestorRec(root.left, p, q);
        } else if(root.val < p.val && root.val < q.val) {
            return lowestCommonAncestorRec(root.right, p, q);
        } else {
            return root;
        }
    }

    // Binary Search Tree Iterator
    /*  Implement an iterator over a binary search tree (BST).
    Your iterator will be initialized with the root node of a BST.

    Calling next() will return the next smallest number in the BST.
    Note: next() and hasNext() should run in average O(1) time and uses O(h)
    memory, where h is the height of the tree.

    */

    public class BSTIterator {
        private Stack<TreeNode> stack = new Stack<TreeNode>();

        public BSTIterator(TreeNode root) {
            pushAll(root);
        }

        public boolean hasNext() {
            return stack.isEmpty();
        }

        public int next() {
            TreeNode temp = stack.pop();
            pushAll(temp.right);
            return temp.val;
        }

        private void pushAll(TreeNode node) {
            // for (; node != null; stack.push(node), node = node.left);
            while(node != null) {
                stack.push(node);
                node = node.left;
            }
        }
    }


    // Binary Tree Level Order Traversal
    /*
    Given a binary tree, return the level order traversal of its nodes' values.
    (ie, from left to right, level by level).
    */

    public static List<List<Integer>> levelOrderRec(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        levelOrderHelp(ret, root, 0);
        return ret;
    }

    public static void levelOrderHelp(List<List<Integer>> ret, TreeNode root, int level) {
        if(root == null) return;

        if(level >= ret.size()) {
            ret.add(new ArrayList<Integer>());
        }

        ret.get(level).add(root.val);

        levelOrderHelp(ret, root.left, level + 1);
        levelOrderHelp(ret, root.right, level + 1);
    }

    public static List<List<Integer>> levelOrderIter(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        LinkedList<TreeNode> q = new LinkedList<TreeNode>();
        if(root == null) return ret;

        q.add(root);

        while(!q.isEmpty()) {
            int level = q.size();
            List<Integer> sublist = new ArrayList<Integer>();
            for(int i = 0; i < level; i++) {
                TreeNode c = q.removeFirst();
                if(c.left != null) q.add(c.left);
                if(c.right != null) q.add(c.right);
                sublist.add(c.val);
            }
            ret.add(sublist);
        }
        return ret;
    }

    // rotated array

    public int search(int[] A, int target) {
        int lo = 0;
        int hi = A.length - 1;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (A[mid] == target) return mid;
            if (A[lo] <= A[mid]) {
                if (target >= A[lo] && target < A[mid]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else {
                if (target > A[mid] && target <= A[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }
        return A[lo] == target ? lo : -1;
    }


    int search(int A[], int n, int target) {
    int lo=0,hi=n-1;
    // find the index of the smallest value using binary search.
    // Loop will terminate since mid < hi, and lo or hi will shrink by at least 1.
    // Proof by contradiction that mid < hi: if mid==hi, then lo==hi and loop would have been terminated.
    while(lo<hi){
        int mid=(lo+hi)/2;
        if(A[mid]>A[hi]) lo=mid+1;
        else hi=mid;
    }
    // lo==hi is the index of the smallest value and also the number of places rotated.
    int rot=lo;
    lo=0;hi=n-1;
    // The usual binary search and accounting for rotation.
    while(lo<=hi){
        int mid=(lo+hi)/2;
        int realmid=(mid+rot)%n;
        if(A[realmid]==target)return realmid;
        if(A[realmid]<target)lo=mid+1;
        else hi=mid-1;
    }
    return -1;
}


    // group anagrams
    /*
    Complexity Analysis

    Time Complexity: O(NK \log (K) )O(NKlog(K)), where NN is the length of strs, and KK is the maximum length of a string in strs. The outer loop has complexity O(N)O(N) as we iterate through each string. Then, we sort each string in O(K \log K)O(KlogK) time.
    O(N∗K), the total information content stored in ans.
    */
    public List<List<String>> groupAnagrams(String[] strs) {
    if (strs.length == 0) return new ArrayList();
    Map<String, List> ans = new HashMap<String, List>();
    for (String s : strs) {
        char[] ca = s.toCharArray();
        Arrays.sort(ca);
        String key = String.valueOf(ca);
        if (!ans.containsKey(key)) ans.put(key, new ArrayList());
        ans.get(key).add(s);
    }
    return new ArrayList(ans.values());
}

    // Serialize and Deserialize Binary Tree
    /* Serialization is the process of converting a data structure or object
    into a sequence of bits so that it can be stored in a file or memory buffer,
    or transmitted across a network connection link to be reconstructed later in
    the same or another computer environment.

    Design an algorithm to serialize and deserialize a binary tree.
    There is no restriction on how your serialization/deserialization algorithm should work.
    You just need to ensure that a binary tree can be serialized to a string and this string
    can be deserialized to the original tree structure.

    For example, you may serialize the following tree

        1
       / \
      2   3
         / \
        4   5
    */

    public class Codec {
        private static final String splitter = ",";
        private static final String NN = "X";

        // Encodes a tree to a single string
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            buildString(root, sb);
            return sb.toString();
        }

        private void buildString(TreeNode node, StringBuilder sb) {
            if(node == null) {
                sb.append(NN).append(splitter);
            } else {
                sb.append(node.val).append(splitter);
                buildString(node.left, sb);
                buildString(node.right, sb);
            }
        }

        public TreeNode deserialize(String data) {
            Deque<String> nodes = new LinkedList<>();
            nodes.addAll(Arrays.asList(data.split(splitter)));
            return buildTree(nodes);
        }

        private TreeNode buildTree(Deque<String> nodes) {
            String val = nodes.remove();
            if(val.equals(NN)) return null;
            else {
                TreeNode node = new TreeNode(Integer.valueOf(val));
                node.left = buildTree(nodes);
                node.right = buildTree(nodes);
                return node;
            }
        }
    }



    /**************************************
    LINKED LISTS
    ***********************************/

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    // Palindrome Linked List
    // Given a singly linked list, determine if it is a palindrome.
    // 1 Reverse second half of list 2 compare to other half
    public boolean linkedListIsPalindrome(ListNode head) {
        ListNode fast = head, slow = head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        if(fast != null) {
            slow = slow.next;
        }

        slow = reverseListIteratively(slow);
        fast = head;

        while(slow != null) {
            if(slow.val != fast.val) {
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }

        return true;
    }


    // Reverse a singly linked list.
    public static ListNode reverseListIteratively(ListNode head) {
        ListNode prev = null;

        while(head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }

        return prev;
    }

    public static ListNode reverseListRecHelp(ListNode head) {
        return reverseListRec(head, null);
    }

    public static ListNode reverseListRec(ListNode head, ListNode prev) {
        if(head == null) return prev;
        ListNode next = head.next;
        head.next = prev;
        return reverseListRec(next, head);
    }

    public static ListNode reverseListRec2(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode next = head.next;
        ListNode prev = reverseListRec2(next); // newhead

        next.next = head;
        head.next = null;

        return prev;
    }


    // Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
    public static ListNode mergeKLists(List<ListNode> lists) {
        if(lists == null || lists.size() == 0) return null;

        Comparator<ListNode> comp = new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        };
        // Comparator<ListNode> comp2 = (ListNode l1, ListNode l2) -> l1.val - l2.val;

        // PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.length, (ListNode l1, ListNode l2) -> l1.val - l2.val);

        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.size(), comp);

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        for(ListNode node : lists) {
            if(node != null) queue.add(node);
        }

        while(!queue.isEmpty()) {
            tail.next = queue.poll();
            tail = tail.next;

            if(tail.next != null) {
                queue.add(tail.next);
            }
        }

        return dummy.next;
    }

    public static void printArray(int[] A) {
        for(int i : A) System.out.print(i + " ");
        System.out.println("");
    }

    public static void main(String[] args) {
        int x = 10;
        int y = 3;
        // System.out.println(hammingDistance(x, y));
        int[] arr = new int[]{0, 1, 0, 3, 12};
        int[] arr2 = new int[]{7, 1, 5, 3, 6, 4};
        // arr = moveZeroes2(arr);
        // System.out.println(maxProfit(arr2));
        // printArray(arr2);
        // System.out.println(maxProfitKadane(arr2));
        // System.out.println(isValidParan("()[]{}"));
        // System.out.println(isValidParan("([]}"));
        // System.out.println(removeInvalidParan("([hello]}"));

        // System.out.println(addBinary("001", "100"));
        int[] A = new int[]{1,5,6,7,0,0,0};
        int[] B = new int[]{2,3,6};
        int k = 3;
        // mergeSortedArrays(A, A.length - 4, B, B.length);
        // System.out.println(sqrtBinarySearch(64));
        // System.out.println(sqrtNewton(64));
        // System.out.println(convertToTitleOneLine(30));
        // System.out.println(convertToTitle(30));
        // printArray(productExceptItself(B));
        subsets(B);
        // for(List l : subsets(B)) {
        //     System.out.println(l);
        // }
        // printArray(arr);
        // int[] lcs = new int[]{100, 4, 200, 1, 3, 2};
        // System.out.println(longestConsecutiveSequence(lcs));
        int[] arr1 = new int[]{1, 3, 7, 5, 10, 3};
        int fee1 = 3;
        System.out.println(maxPenalty(arr1, fee1));
        char[] w = new char[]{'p', 'e', 'r', 'f', 'e', 'c', 't', ' ', 'm', 'a', 'k', 'e', 's', ' ', 'p', 'r', 'a', 'c', 't', 'i', 'c', 'e'};
        System.out.println(reverseOrderOfWords(w));
    }

}
