import java.util.*;

class FacebookSaturday {

    // Hamming distance
    int hammingDistance(int x, int y) {
        int count = 0, xor = x ^ y;
        for(int i = 0; i < 32; i++) {
            count += (xor >> i & 1) == 1 ? 1 : 0;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("Hello");
        
    }
}