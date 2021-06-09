## Flip Game

你和朋友玩一个叫做「翻转游戏」的游戏。游戏规则如下：

给你一个字符串 currentState ，其中只含 '+' 和 '-' 。你和朋友轮流将 连续 的两个 "++" 反转成 "--" 。当一方无法进行有效的翻转时便意味着游戏结束，则另一方获胜。

计算并返回 一次有效操作 后，字符串 currentState 所有的可能状态，返回结果可以按 任意顺序 排列。如果不存在可能的有效操作，请返回一个空列表 [] 。

 

示例 1：

    输入：currentState = "++++"
    输出：["--++","+--+","++--"]

示例 2：
    
    输入：currentState = "+"
    输出：[]


提示：

    1 <= currentState.length <= 500
    currentState[i] 不是 '+' 就是 '-'

## 分析

遍历字符串，判断当前位置的字符：

1.  如果是'-'，continue；
2.  如果是'+'，判断下个字符是否是'+'，如果不是，continue；否则，替换i和i+1位置的"++"为"--"。

**注意：**在拼接字符串的时候，使用StringBuilder比直接用+号拼接更快。

```java
class Solution {
    public List<String> generatePossibleNextMoves(String currentState) {
        int n = currentState.length();
        if (n <= 1) return new ArrayList<>();
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder(currentState);
        for (int i = 0; i< n - 1; i ++) {
            if (currentState.charAt(i) == '-') continue;
            if (currentState.charAt(i + 1) == '+') {
                res.add(sb.replace(i, i + 2, "--").toString());
                sb.replace(i, i + 2, "++");
            }
        }
        return res;
    }
}
```

