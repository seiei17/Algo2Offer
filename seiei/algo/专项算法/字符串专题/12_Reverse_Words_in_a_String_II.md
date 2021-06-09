# Reverse Words in a String II

给定一个字符串，逐个翻转字符串中的每个单词。

示例：

    输入: ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
    输出: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]

注意：

*   单词的定义是不包含空格的一系列字符
*   输入字符串中不会包含前置或尾随的空格
*   单词与单词之间永远是以单个空格隔开的

**进阶：使用 O(1) 额外空间复杂度的原地解法。**

## 分析

先翻转字符串，再翻转单词。

时间复杂度为O(n)，空间复杂度为O(1)。

```java
class Solution {
    public void reverseWords(char[] s) {
        int len = s.length;
        if (len <= 0) return;
        int head = 0, tail = len;
        // 先翻转字符串
        reverse(s, head, tail);

        // 再翻转单词
        head = 0;
        tail = 0;
        while (tail < len) {
            if (s[tail] == ' ') {
                reverse(s, head, tail);
                head = tail + 1;
            }
            tail++;
        }
        reverse(s, head, tail);
    }

    private void reverse(char[] s, int wordHead, int wordTail) {
        wordTail--;
        while (wordHead < wordTail) {
            char temp = s[wordHead];
            s[wordHead] = s[wordTail];
            s[wordTail] = temp;
            wordHead++;
            wordTail--;
        }
    }
}
```

