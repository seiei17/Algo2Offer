package algo;

import java.util.*;

class Solution {
    public int maxProduct(String[] words) {
        int len = words.length;
        int maxProduct = 0;

        int[] lens = new int[len];
        int[] masks = new int[len];
        for (int index = 0; index < len; index++) {
            int l = 0;
            int mask = 0;
            for (char c: words[index].toCharArray()) {
                l++;
                mask |= 1 << ((int)c - (int)'a');
            }
            lens[index] = l;
            masks[index] = mask;
        }

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if ((masks[i] & masks[j]) == 0)
                    maxProduct = Math.max(maxProduct, lens[i] * lens[j]);
            }
        }
        return maxProduct;
    }
}