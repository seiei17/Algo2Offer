## 3Sum Smaller

## 分析

依然是一个外层循环+内层双指针的思路。

首先对数组排序，然后外层循环i从0：len-2。为什么是len-2，因为三数之和需要三个数。

**循环内首先判断临近三个数的和是否大于等于target，即nums[i] + nums[i + 1] + nums[i + 2] >= target，是的话直接break。**因为数组递增排序，如果临近i, i+1, i+2的和已经大于等于target，那么无论此时low和high指针在何位置，三数之和都会大于等于target。

在内层双指针的循环中，如果三数和大于等于target，则表示和应该减小，那么high指针左移（high--）。如果三数和小于target，则表示满足条件，**对于此时相应的low指针和high指针，当第三个数满足[low + 1, high]条件，那么三数之和都满足条件（因为high左移的时候和只会更小，所以high在这个范围内的数都满足条件），所以count += high - low**，当前low对应的数已经计算完毕，low右移（low++）。

最后返回count即可。

### Java代码

```java
class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        int len = nums.length;
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < len - 2; i ++) {
            if (nums[i] + nums[i + 1] + nums[i + 2] >= target) break;
            int low = i + 1, high = len - 1;
            while (low < high) {
                int sum = nums[i] + nums[low] + nums[high];
                if (sum >= target) high --;
                else {
                    count += high - low;
                    low ++;
                }
            }
        }
        return count;
    }
}
```