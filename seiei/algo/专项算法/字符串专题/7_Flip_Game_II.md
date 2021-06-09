# Flip Game II

你和朋友玩一个叫做「翻转游戏」的游戏。游戏规则如下：

给你一个字符串 currentState ，其中只含 '+' 和 '-' 。你和朋友轮流将 连续 的两个 "++" 反转成 "--" 。当一方无法进行有效的翻转时便意味着游戏结束，则另一方获胜。

请你写出一个函数来判定起始玩家 是否存在必胜的方案 ：如果存在，返回 true ；否则，返回 false 。


示例 1：

    输入：currentState = "++++"
    输出：true
    解释：起始玩家可将中间的 "++" 翻转变为 "+--+" 从而得胜。

示例 2：

    输入：currentState = "+"
    输出：false


提示：
    
    1 <= currentState.length <= 60
    currentState[i] 不是 '+' 就是 '-'


进阶：请推导你算法的时间复杂度。

## 博弈论 SG定理

对于翻转游戏的SG定理阐述：[Google的“+-游戏”最优策略下的O(N^2) DP解法|一亩三分地刷题版 (1point3acres.com)](https://www.1point3acres.com/bbs/thread-137953-1-1.html)。

对于SG定理的证明：[Google的“+-游戏”最优策略下的O(N^2) DP解法|一亩三分地刷题版 (1point3acres.com)](https://www.1point3acres.com/bbs/thread-137953-1-1.html)。

实现：

使用一个map来储存对应的SG值，避免重复计算，提高效率。

使用set来帮助计算FMN值，减少排序的开销。

```java
class Solution {
    private Map<Integer, Integer> map;
    public boolean canWin(String currentState) {
        int n = currentState.length();
        map = new HashMap<>();
        map.put(0, 0);
        map.put(1, 0);
        List<Integer> list = new ArrayList<>();
        int len = 0;
        int max = 0;
        for (int i = 0; i < n; i ++) {
            if (currentState.charAt(i) == '+') len ++;
            else {
                list.add(len);
                max = Math.max(len, max);
                len = 0;
            }
        }
        list.add(len);
        max = Math.max(len, max);
        if (max <= 1) return false;
        int res = 0;
        for (int l: list) res ^= sg(l);
        return res != 0;
    }

    public int sg(int x) {
        if (map.containsKey(x)) return map.get(x);
        Set<Integer> set = new HashSet<>();
        set.add(sg(x - 2));
        int bound = (int) Math.ceil(x / 2);
        for (int i = 1; i < bound; i ++) {
            set.add(sg(i) ^ sg(x - 2 - i));
        }
        int res = FMN(set);
        map.put(x, res);
        return res;
    }

    public int FMN(Set<Integer> set) {
        int res = 0;
        while (true) {
            if (set.contains(res)) res ++;
            else return res;
        }
    }
}
```



## 回溯

思路：一直递归翻转，如果对方失败，则自己成功。

```java
class Solution {
    public boolean canWin(String currentState) {
        int n = currentState.length();
        StringBuilder sb = new StringBuilder(currentState);
        return reallyCanWin(sb, n);
    }

    private boolean reallyCanWin(StringBuilder sb, int n) {
        for (int i = 0; i < n - 1; i ++) {
            if (sb.charAt(i) == '+' && sb.charAt(i + 1) == '+') {
                // 将"++"替换为"--"再递归让下一个选手翻转
                boolean win = reallyCanWin(sb.replace(i, i + 2, "--"), n);
                // 回溯，需要复原字符串
                sb.replace(i, i + 2, "++");
                // 如果下一个选手失败，则此次翻转成功。
                if (!win) return true;
            }
        }
        return false;
    }
}
```

## 使用记忆化搜索加速

举例："++++++"。

当选手1遍历到i=0，翻转后为"--++++"。选手2遍历到i=2，翻转后为"----++"。

当选手1遍历到i=2，翻转后为"++--++"。选手2遍历到i=0，翻转后为"----++"。

两种情况结果是一样的，那么通过将结果储存，可以减少递归的次数。

通过一个Map<String, Boolean> map储存相应结果。

```java
class Solution {
    public boolean canWin(String currentState) {
        int n = currentState.length();
        StringBuilder sb = new StringBuilder(currentState);
        Map<String, Boolean> map = new HashMap<>();
        return reallyCanWin(sb, map, n);
    }

    private boolean reallyCanWin(StringBuilder sb, Map<String, Boolean> map, int n) {
        if (map.containsKey(sb.toString())) return map.get(sb.toString());
        for (int i = 0; i < n - 1; i ++) {
            if (sb.charAt(i) == '+' && sb.charAt(i + 1) == '+') {
                boolean win = reallyCanWin(sb.replace(i, i + 2, "--"), map, n);
                map.put(sb.toString(), win);
                sb.replace(i, i + 2, "++");
                if (!win) return true;
            }
        }
        return false;
    }
}
```
