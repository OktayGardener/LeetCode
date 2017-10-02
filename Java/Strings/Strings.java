package Java.Strings;

class Strings {

    // 387. First Unique Character in a String
    /*

    Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

    Examples:

    s = "leetcode"
    return 0.

    s = "loveleetcode",
    return 2.

    */
    public int firstUniqueChar(String s) {
        int chs[] = new int[26];
        for(int i = 0; i < s.length(); i++) chs[s.charAt(i) - 'a']++;
        for(int i = 0; i < s.length(); i++) if(chs[s.charAt(i) - 'a'] == 1) return i;
        return -1;
    }


    /*
    28. Implement strStr()
    DescriptionHintsSubmissionsDiscussSolution
    Discuss Pick One
    Implement strStr().
    Returns the index of the first occurrence of needle in haystack,
    or -1 if needle is not part of haystack.
    */

    public int strStr(String needle, String haystack) {
        int l1 = haystack.length(), l2 = needle.length();
        if(l1 < l2) return -1;
        else if(l2 == 0) return 0;

        int t = l1 - l2;
        for(int i = 0; i <= t; i++) {
            if(needle.substring(i, i + l2).equals(needle)) return true;
        }

        return -1;
    }


    /* 125. Valid Palindrome

    Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

    For example,
    "A man, a plan, a canal: Panama" is a palindrome.
    "race a car" is not a palindrome.

    Note:
    Have you consider that the string might be empty? This is a good question to ask during an interview.

    For the purpose of this problem, we define empty string as valid palindrome.
    */

    // easy way
    public boolean isValidPalindrome(String s) {
        String noWhites = s.replaceAll("[^A-Za-z0-9]").toLowerCase();
        String rev = new StringBuffer(noWhites).reverse().toString();
        return noWhites.equals(rev);
    }

    public boolean isValidPalindrome(String s) {
        if(s.length() == 0 || s.isEmpty()) return false;
        int head = 0, tail = s.length() - 1;
        char cHead, cTail;

        while(head <= tail) {
            cHead = s.charAt(head);
            cTail = s.charAt(tail);
            if(!Character.isLetterOrDigit(cHead)) {
                cHead++;
            } else if(!Character.isLetterOrDigit(cTail)) {
                cTail++;
            } else if(cHead != cTail) {
                return false;
            } else {
                head++;
                tail--;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Strings s = new Strings();
        System.out.println(s.firstUniqueChar("leetcode"));
        System.out.println(s.firstUniqueChar("loveleetcode"));


    }
}
