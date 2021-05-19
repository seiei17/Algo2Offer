## 3Sum

[题目见LeetCode: https://leetcode-cn.com/problems/3sum/](https://leetcode-cn.com/problems/3sum/)

## 分析

排序 + 双指针。

首先，对输入数组进行排序，方便后续操作。

使用双指针嵌套枚举即可。

```
对于每个 n: nums，位置为i
	第一个指针 low = i + 1
	第二个指针 high = len - 1
	新的目标 target = 0 - n
	即，问题退化为，在剩余数组中寻找target = -n的数对
```

对于此问题，target=0，有一定的剪枝方法：

* 因为目标和为0，当遍历到nums[i] > 0的时候，可以退出循环。
* 为了减少循环次数和防止加入重复元组，应该满足如下条件：
  * 最外层循环，nums[i] != nums[i - 1]，即应该从不同的数出发。
  * 里层循环同理，nums[low] != nums[low - 1]，nums[high] != nums[high +1]。

时间复杂度为O(n^2)。

如果原地排序，空间复杂度为O(1)，否则为O(n)。

## Java代码

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        // 先对数组排序
        Arrays.sort(nums);
        
        // 外层循环
        for (int i = 0; i < len; i ++) {
            // 如果当前num的值大于0，可以退出循环
            if (nums[i] > 0) break;
            // 如果当前num和上一个值相同，直接查看下一个数
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            
            int target = - nums[i];
            int low = i + 1, high = len - 1;
            
            // 双指针查询
            while (low < high) {
                int sum = nums[low] + nums[high];
                if (sum > target) high --;
                else if (sum < target) low ++;
                else {
                    List<Integer> t = new ArrayList<>();
                    t.add(nums[i]);
                    t.add(nums[low]);
                    t.add(nums[high]);
                    res.add(t);
                    
                    // 循环查询下一个low、high
                    // 应该满足nums[low - 1] != nums[low]，nums[high + 1] != nums[high]
                    do {
                        low ++;
                        high --;
                    } while (low < high && nums[low - 1] == nums[low] && nums[high + 1] == nums[high]);
                }
            }
        }
        return res;
    }
}
```

