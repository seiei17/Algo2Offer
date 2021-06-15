# Maximum Product of Word Lengths

[318. 最大单词长度乘积 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/maximum-product-of-word-lengths/)

## 分析

考虑最暴力的方法： 

    直接通过两层循环分别遍历，每次计算maxProduct进行比较，再返回最大的那个即可。

那么这个步骤可以划分为两步：

1. 双层循环遍历，找最大值；
2. 每次判断比较的两个单词是否含有相同字符。

于是，可以从两个方面去优化。

#### 1. 判断是否有相同字符。

##### 直接暴力判断

```java
public boolean noCommonLetters(String s1, String s2){
  for (char ch : s1.toCharArray())
    if (s2.indexOf(ch) != -1) return false;
  return true;
}
```

这种方法达到了O(m*n)的时间复杂度。

##### 使用Set进行判断

遍历第一个单词，将字符填入set；再遍历第二个单词，判断字符是否在set中。

时间复杂度为O(m + n)。

```java
class Solution {
    public int maxProduct(String[] words) {
        int len = words.length;
        int maxProduct = 0;
        for (int i = 0; i < len; i++) {
            Set<Character> seen = new HashSet<>();
            int len1 = 0;
            for (char c: words[i].toCharArray()) {
                len1 ++;
                seen.add(c);
            }
            for (int j = i + 1; j < len; j++) {
                int len2 = 0;
                for (char c: words[j].toCharArray()) {
                    if (seen.contains(c)) break;
                    len2 ++;
                }
                if (len2 < words[j].length()) continue;
                maxProduct = Math.max(maxProduct, len1 * len2);
            }
        }
        return maxProduct;
    }
}
```

##### 使用位运算进行判断

单词中只存在小写字母，所以范围是\[0, 26)。可以用一个26位的二进制数来表示一个单词中存在哪些字母。

存在某个字母，就按照在字母表中的顺序，在相应的位上置1，否则置0。如\'a'代表了数字1，\'b'代表了数字2。

用index表示一个字母在字母表中的顺序：index = c - \'a'。那么计算二进制掩码为:bitmask |= 1 \<< index。

最后，将两个字母代表的二进制掩码取并，如果结果为0，表示没有相同的字母；否则存在相同的字母。

时间复杂度也为O(m + n)。

但是每次判断都要计算一次bitmask。

```java
    private boolean noCommonLetter(String word1, String word2) {
        int bitmask1 = 0, bitmask2 = 0;
        for (char c: word1.toCharArray()) {
            int index = (int)c - (int)'a';
            bitmask1 |= 1 << index;
        }
        for (char c: word2.toCharArray()) {
            int index = (int)c - (int)'a';
            bitmask2 |= 1 << index;
        }
        return (bitmask1 & bitmask2) == 0;
    }
```

##### 使用位运算+预处理进行判断

在开始之前，对words数组进行预处理，将每个单词的mask值和长度计算并储存在数组中。

在进行比较的时候，只需要花费**O(1)**的时间复杂度进行取用即可。

```java
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
                if (noCommonLetter(i, j, masks))
                    maxProduct = Math.max(maxProduct, lens[i] * lens[j]);
            }
        }
        return maxProduct;
    }

    private boolean noCommonLetter(int i, int j, int[] masks) {
        return (masks[i] & masks[j]) == 0;
    }
}
```

所以总的时间复杂度为O(len \^ 2 + L)，其中L是所有单词的总长度。

#### 2. 减少比较次数

对于拥有同样mask的单词，在Map中进行储存，只保留最长的单词。

比较时从map中取出进行比较。

在Java中改善不明显。