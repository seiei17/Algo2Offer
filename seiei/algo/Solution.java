package algo;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Solution {

    @Test
    public void test() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int r = new Random().nextInt(1000);
            list.add(r);
        }
        list.forEach(System.out::print);
        System.out.println();

        int[] nums = list.stream().mapToInt(i -> i).toArray();

        new HeapSort().sort(nums);
        for (int num: nums) {
            System.out.print(num + " ");
        }
    }
}

class HeapSort {
    public void sort(int[] nums) {
        // 从第一个非叶子结点开始建堆
        for (int index = nums.length / 2 - 1; index >= 0; index--) {
            adjust(nums, index, nums.length);
        }
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[0];
            nums[0] = nums[nums.length - 1 - i];
            nums[nums.length - 1 - i] = temp;
            adjust(nums, 0, nums.length - i - 1);
        }
    }

    /**
     * 调整堆
     * @param nums 输入数组
     * @param index 开始的节点
     * @param end 数组终点
     */
    private void adjust(int[] nums, int index, int end) {
        int num = nums[index];
        // 依次遍历它的孩子节点，替换较大的
        for (int k = 2 * index + 1; k < end; k = 2 * k + 1) {
            if (k + 1 < end && nums[k] < nums[k + 1]) k += 1;
            if (num < nums[k]) {
                nums[index] = nums[k];
                index = k;
            }
        }
        nums[index] = num;
    }
}