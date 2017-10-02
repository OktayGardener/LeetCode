package Java.Arrays;

import Java.Common.Common;
import java.util.*;

class Arrays {

    /* 258. Add Digits

    Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

    For example:

    Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.

    Follow up:
    Could you do it without any loop/recursion in O(1) runtime?

    Credits:
    Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.

    For base b (decimal case b = 10), the digit root of an integer is:

    dr(n) = 0 if n == 0
    dr(n) = (b-1) if n != 0 and n % (b-1) == 0
    dr(n) = n mod (b-1) if n % (b-1) != 0
    or

    dr(n) = 1 + (n - 1) % 9

    */

    public int addDigits(int n) {
        return 1 + (n - 1) % 9;
    }

    // Excel Sheet Column Number
    /*

    Given a column title as appear in an Excel sheet, return its corresponding column number.

    For example:

        A -> 1
        B -> 2
        C -> 3
        ...
        Z -> 26
        AA -> 27
        AB -> 28
    */

    public int titleToNumber(String s) {
        int res = 0;
        for(int i = 0; i < s.length(); i++) res = res * 26 + (s.charAt(i) - 'A' + 1);
        return res;
    }

    // 268. Missing Number
    /*
    Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

    For example,
    Given nums = [0, 1, 3] return 2.

    Note:
    Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?

    Credits:
    */

    public int missingNumber(int[] nums) {
        int xor = 0;
        for(int i = 0; i <= nums.length; i++) xor ^= i;
        for(int n : nums) xor ^= n;
        return xor;
    }

    // 121. Best Time to Buy and Sell Stock
    /*
    Say you have an array for which the ith element is the price of a given stock on day i.

    If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.

    Example 1:
    Input: [7, 1, 5, 3, 6, 4]
    Output: 5

    max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
    Example 2:
    Input: [7, 6, 4, 3, 1]
    Output: 0

    In this case, no transaction is done, i.e. max profit = 0.
    */

    public int maxProfit(int[] prices) {
        int max = 0, currMax = 0;

        for(int i = 1; i < prices.length; i++) {
            currMax = Math.max(0, currMax += prices[i] - prices[i - 1]);
            max = Math.max(max, currMax);
        }
        return max;
    }

    // 53. Maximum Subarray
    /*
    Find the contiguous subarray within an array
    (containing at least one number) which has the largest sum.

    For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
    the contiguous subarray [4,-1,2,1] has the largest sum = 6. */
    public int maxSubarray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];

        for(int i = 1; i < nums.length; i++) {
            dp[i] = nums[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    /*
    26. Remove Duplicates from Sorted Array

    Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

    Do not allocate extra space for another array, you must do this in place with constant memory.

    For example,
    Given input array nums = [1,1,2],

    Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the new length.
    */

    public int removeDuplicatesFromArray(int[] A) {
        int idx = 0;
        for(int n : A) {
            if(idx == 0 || n > A[idx]) {
                A[idx++] = n;
            }
        }
        return idx;
    }


    /*
    1. Two Sum

    Given an array of integers, return indices of the two numbers such that they add up to a specific target.

    You may assume that each input would have exactly one solution, and you may not use the same element twice.

    Example:
    Given nums = [2, 7, 11, 15], target = 9,

    Because nums[0] + nums[1] = 2 + 7 = 9,
    return [0, 1].
    */

    public int[] twoSum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])) {
                return new int[]{map.get(nums[i]), i};
            }
            map.put(k - nums[i], i);
        }
        return new int[2];
    }


    /*
    88. Merge Sorted Array
    Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

    Note:
    You may assume that nums1 has enough space
    (size that is greater or equal to m + n) to hold additional elements from nums2.
    The number of elements initialized in nums1 and nums2 are m and n respectively.
    */

    public void mergeArrays(int[] A, int m, int[] B, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;
        while(i >= 0 && j >= 0) A[k--] = (A[i] > B[j]) ? A[i--] : B[j--];
        while(j >= 0) A[k--] = B[j--];
    }


    /* 204. Count Primes
    Count the number of prime numbers less than a non-negative number, n.
    */

    public int countPrimes(int n) {
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for(int i = 2; i < n; i++) {
            if(notPrime[i] == false) {
                count++;
                for(int j = 2; i * j <= n; j++) {
                    notPrime[i * j] = true;
                }
            }
        }
        return count;
    }

    /* 189. Rotate Array
    Rotate an array of n elements to the right by k steps.

    For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to
    [5,6,7,1,2,3,4].

    Note:
    Try to come up as many solutions as you can,
    there are at least 3 different ways to solve this problem.
    */

    public void rotate(int[] nums, int k) {
        if(nums == null || nums.length < 2) return;
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int i, int j) {
        while(i <= j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }

    /* 238. Product of Array Except Self

    Given an array of n integers where n > 1, nums,
    return an array output such that output[i] is equal
    to the product of all the elements of nums except nums[i].

    Solve it without division and in O(n).

    For example, given [1,2,3,4], return [24,12,8,6].

    Follow up:
    Could you solve it with constant space complexity?
    (Note: The output array does not count as extra space
    for the purpose of space complexity analysis.)
    */

    public int[] productArray(int[] A) {
        int[] res = new int[A.length];

        for(int i = 0, temp = 1; i < A.length; i++) {
            res[i] = temp;
            temp *= A[i];
        }

        for(int i = A.length - 1, temp = 1; i <= 0; i++) {
            res[i] *= temp;
            temp *= A[i];
        }

        return res;
    }

    /*
    651. 4 Keys Keyboard
    Imagine you have a special keyboard with the following keys:

    Key 1: (A): Print one 'A' on screen.

    Key 2: (Ctrl-A): Select the whole screen.

    Key 3: (Ctrl-C): Copy selection to buffer.

    Key 4: (Ctrl-V): Print buffer on screen appending it after what has already been printed.

    Now, you can only press the keyboard for N times (with the above four keys), find out the maximum numbers of 'A' you can print on screen.

    Example 1:
    Input: N = 3
    Output: 3
    Explanation:
    We can at most get 3 A's on screen by pressing following key sequence:
    A, A, A
    Example 2:
    Input: N = 7
    Output: 9
    Explanation:
    We can at most get 9 A's on screen by pressing following key sequence:
    A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V
    */

    // recursive def
    public int maxARec(int n) {
        int max = n;
        for(int i = 1; i <= n - 3; i++) {
            max = Math.max(max, maxA(i) * maxA(n - i - 1));
        }
        return max;
    }

    public int maxA(int n) {
        int[] dp = new int[n + 1];
        for(int i = 0; i <= n; i++) {
            dp[i] = i;
            for(int j = 1; j <= i - 3; j++) {
                dp[i] = Math.max(dp[i], dp[j] * (j - i - 1));
            }
        }
        return dp[n];
    }


    /* 46. Permutations

    Given a collection of distinct numbers, return all possible permutations.

    For example,
    [1,2,3] have the following permutations:
    [
      [1,2,3],
      [1,3,2],
      [2,1,3],
      [2,3,1],
      [3,1,2],
      [3,2,1]
    ]

    */

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> permutations = new ArrayList();
        if(nums.length == 0) return permutations;
        permute(nums, permutations, new ArrayList<>());
        return permutations;
    }

    public void permute(int[] nums, List<List<Integer>> permutations, List<Integer> temp) {
        if(temp.size() == nums.length) {
            permutations.add(new ArrayList<>(temp));
        } else {
            for(int i = 0; i < nums.length; i++) {
                if(temp.contains(nums[i])) continue;
                temp.add(nums[i]);
                permute(nums, permutations, temp);
                temp.remove(temp.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Arrays arr = new Arrays();
        Common c = new Common();

        System.out.println(arr.addDigits(5));
        System.out.println(arr.titleToNumber("AB"));
        int[] nums = new int[]{3, 2, 5, 1, 0};
        int[] nums2 = new int[]{0, 1, 3};
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};

        System.out.println("missingNumber from:");
        c.printIntArray(nums);
        System.out.println(arr.missingNumber(nums));

        System.out.println("missingNumber from:");
        c.printIntArray(prices);
        System.out.println(arr.missingNumber(nums2));

        System.out.println("maxProfit:");
        System.out.println(arr.maxProfit(prices));
        int[] subarrays = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(arr.maxSubarray(subarrays));

        subarrays = new int[]{4, 4, 5, 6};

        System.out.println(arr.removeDuplicatesFromArray(subarrays));

        c.printIntArray(arr.twoSum(prices, 7));
        System.out.println(arr.permute(nums2));
    }
}
