package Java.Numbers;
import Java.Common.Common;

class Numbers {
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


    /* 13. Roman to Integer
    Given a roman numeral, convert it to an integer.

    Input is guaranteed to be within the range from 1 to 3999.

    */

    public int romanToInt(String s) {
        int sum = 0;
        if(s.indexOf("IV")!=-1) sum-=2;
        if(s.indexOf("IX")!=-1) sum-=2;
        if(s.indexOf("XL")!=-1) sum-=20;
        if(s.indexOf("XC")!=-1) sum-=20;
        if(s.indexOf("CD")!=-1) sum-=200;
        if(s.indexOf("CM")!=-1) sum-=200;

        char c[] = s.toCharArray();
        int count = 0;

        for(; count <= s.length() - 1; count++) {
            if(c[count]=='M') sum+=1000;
            if(c[count]=='D') sum+=500;
            if(c[count]=='C') sum+=100;
            if(c[count]=='L') sum+=50;
            if(c[count]=='X') sum+=10;
            if(c[count]=='V') sum+=5;
            if(c[count]=='I') sum+=1;
        }
        return sum;
    }

    // 191. Number of 1 Bits
    /*
    Write a function that takes an unsigned integer and returns the
    number of ’1' bits it has (also known as the Hamming weight).

    For example, the 32-bit integer
    ’11' has binary representation 00000000000000000000000000001011,
    so the function should return 3.

    Credits:
    Special thanks to @ts for adding this problem and creating all test cases.
    */

    public int numberOfBits(int n) {
        int count = 0;
        for(int i = 0; i < 32; i++) count += (n >> i & 1) == 1 ? 1 : 0;
        return count;
    }


    /* 672. Bulb Switcher II
    There is a room with n lights which are turned on initially and 4 buttons on the wall. After performing exactly m unknown operations towards buttons, you need to return how many different kinds of status of the n lights could be.

    Suppose n lights are labeled as number [1, 2, 3 ..., n], function of these 4 buttons are given below:

    Flip all the lights.
    Flip lights with even numbers.
    Flip lights with odd numbers.
    Flip lights with (3k + 1) numbers, k = 0, 1, 2, ...
    */

    public int flipLights(int n, int m) {
        if(m == 0) return 1;
        if(n <= 0 || m < 0) return 0;
        if(n == 1) return 2;
        else if(n == 2) return (m == 1) ? 3 : 4;
        else return (m == 1) ? 4 : ((m == 2) ? 7 : 8);
    }



    public static void main(String[] args) {
        Numbers arr = new Numbers();
        Common c = new Common(); // ratchet but ok

        System.out.println(arr.addDigits(5));
        System.out.println(arr.titleToNumber("AB"));

        int[] A = new int[]{1,3,4,5};
        c.printIntArray(A);
        System.out.println(arr.numberOfBits(5));
    }
}
