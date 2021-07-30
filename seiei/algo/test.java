package algo;

/**
 * @program: Algo2Offer
 * @description: test
 * @author: Seiei
 * @create: 2021-07-29 19:29
 **/
public class test {

    public static void main(String[] args) {
        int[] nums = {6, 21, 2, 1, 30};
        sort(nums);
        for (int n: nums) System.out.print(n + " ");
    }

    private static void sort(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            int pivot = nums[i];
            for (int j = i - 1; j >= -1; j--) {
                if (j == -1 || nums[j] < nums[i]) {
                    int k = i;
                    while (k > j + 1) {
                        nums[k] = nums[k - 1];
                        k--;
                    }
                    nums[j + 1] = pivot;
                    break;
                }
            }
        }
    }
}
