package algo;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.CountDownLatch;

public class Solution {

    @Test
    public void test() {
        String str = "ab(cd(efg)hij)lkm";
        System.out.println(reverse1(str));
    }

    public String reverse1(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != ')') stack.push(c);
            else {
                LinkedList<Character> list = new LinkedList<>();
                while (stack.peek() != '(') list.offer(stack.pop());
                stack.pop();
                while (!list.isEmpty()) stack.push(list.poll());
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) sb.append(stack.pollLast());
        return sb.toString();
    }
}