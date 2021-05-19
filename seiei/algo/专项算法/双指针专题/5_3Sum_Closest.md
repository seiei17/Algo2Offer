## 3Sum Closest

[题目见力扣：https://leetcode-cn.com/problems/3sum-closest/](https://leetcode-cn.com/problems/3sum-closest/)

## 分析

```
使用双指针的办法枚举三数之和，同样，需要数组满足排序。

外层循环start从0开始，len-2结束。因为必须要三数之和，所以start=len-2是最后一个元组。返回值为SumClosest，返回值与target的距离为DisClosest。

内部循环采用双指针，low=start+1，high=len-1：
	计算当前和Sum,当前距离DisSum。
	如果当前距离DisSum小于最小距离DisClosest：替换。
	如果当前和Sum小于target，则应该增大Sum，所以low ++右移。
	如果当前和Sum大于target，则应该减小Sum，所以high --左移。
	如果当前和Sum等于target，则是最小距离，直接返回target。
返回SumClosest。
```

## Java代码

```java
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int len = nums.length;
        Arrays.sort(nums);
        // 最接近和、最近和的距离
        int SumClosest = nums[0] + nums[1] + nums[2];
        int DisClosest = Math.abs(SumClosest - target);
        // 外层循环只到len - 2的位置，因为必须满足有三个数
        for (int start = 0; start < len - 2; start ++) {
            int low = start + 1, high = len - 1;
            while (low < high) {
                int sum = nums[start] + nums[low] + nums[high];
                int DisSum = Math.abs(sum - target);
                // 如果当前距离比最小距离小，交换
                if (DisSum < DisClosest) {
                    DisClosest = DisSum;
                    SumClosest = sum;
                }
                // 如果当前值比目标值小，low右移
                // 如果当前值比目标值大，high左移
                // 如果当前值等于目标值，返回
                if (sum < target) low ++;
                else if (sum > target) high --;
                else return target;
            }
        }
        return SumClosest;
    }
}
```

