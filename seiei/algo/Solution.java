package algo;

import org.junit.Test;

import java.util.Stack;

public class Solution {

    @Test
    public void test() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.pop());
    }
}