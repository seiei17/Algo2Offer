package algo;

import java.util.*;

class Solution {
    public long wonderfulSubstrings(String word) {
        int[] map = new int[1024];
        map[0] = 1;
        int mask = 0;
        long count = 0;
        for (char c: word.toCharArray()) {
            mask ^= 1 << c - 'a';
            count += map[mask];
            for (int i = 0; i < 10; i++) {
                count += map[mask ^ 1 << i];
            }
            map[mask]++;
        }
        return count;
    }

    public static void main(String[] args) {
        int a = -12;
        int num = 0;
        while (a != 0) {
            num = num * 10 + a % 10;
            a /= 10;
        }
        System.out.println(num);
    }
}