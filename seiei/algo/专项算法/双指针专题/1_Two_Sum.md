## Two Sum

给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出**和为目标值**的那两个整数，并返回它们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。

你可以按任意顺序返回答案。

示例 1：

```
输入：nums = [2,7,11,15], target = 9
输出：[0,1]
解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
```

示例 2：

```
输入：nums = [3,2,4], target = 6
输出：[1,2]
示例 3：
```

```
输入：nums = [3,3], target = 6
输出：[0,1]
```

提示：

* 2 <= nums.length <= 10^3
* -10^9 <= nums[i] <= 10^9
* -10^9 <= target <= 10^9
* 只会存在一个有效答案

## 分析

找出数组中nums[i] + nums[j] = target的一组(i, j)。即找nums[j] = target - nums[i]是否存在。

那么遍历数组[:i]，每次取得nums[i]查询map中是否存在此key，如果存在表示数组之前有过target-nums[i]的数出现，即nums[j]；否则，储存此key和下标i。

## Java代码

```java
public class Solution {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            // 说明不存在互补的值
            if (!map.containsKey(nums[i])) {
                map.put(target - nums[i], i);
                // 说明存在互补的值
            } else {
                return new int[]{map.get(nums[i]), i};
            }
        }
        return null;
    }
}
```

