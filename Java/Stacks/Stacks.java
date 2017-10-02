package Java.Stacks;
import java.util.*;
import Java.Common.Common;

class Stacks {

    public boolean isValidParanthesis(String s) {
        Stack<Character> stack = new Stack<Character>();
        for(char c : s.toCharArray()) {
            if(c == '(') {
                stack.push(')');
            } else if(c == '[') {
                stack.push(']');
            } else if(c == '{') {
                stack.push('}');
            } else if(stack.isEmpty() || stack.pop() != c) return false;
        }

        return stack.isEmpty();
    }


    public static void main(String[] args) {
        Stacks stack = new Stacks();
        System.out.println(stack.isValidParanthesis("()({)"));
        System.out.println(stack.isValidParanthesis("()({})"));
    }
}
