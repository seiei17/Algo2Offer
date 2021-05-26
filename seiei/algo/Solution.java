package algo;

import java.util.*;

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1;
        int cursor = m + n - 1;
        while (i >= 0 && j >= 0) {
            nums1[cursor --] = nums1[i] > nums2[j]? nums1[i --]: nums2[j --];
        }
        while (j >= 0) nums1[cursor --] = nums2[j --];
    }
}