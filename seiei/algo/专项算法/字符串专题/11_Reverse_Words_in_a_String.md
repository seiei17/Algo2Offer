# Reverse Words In A String

[151. 翻转字符串里的单词 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/reverse-words-in-a-string/)

## 分析

#### 1. 使用split()方法，将字符串转变为字符串数组，再将 字符串数组组合为字符串。

时间复杂度为O(n)，空间复杂度为O(n)。

```java
class Solution {
    public String reverseWords(String s) {
        // 除去开头和末尾的空白字符
        s = s.trim();
        // 正则匹配连续的空白字符作为分隔符分割
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }
}
```

效率不高。虽然时间复杂度为O(n)，但是遍历了多次数组：trim()遍历一次，split()遍历一次，翻转遍历一次，组合遍历一次。

#### 2. 反向遍历字符串，添加到新字符串。

从末尾反向遍历字符串，将遇见的单词添加到StringBuilder当中即可。

注意要避免添加末尾的空格。

时间复杂度为O(n)，空间复杂度为O(n)。在c++等可以修改字符串的语言中，空间复杂度应为O(1)。

**只遍历了一次字符串**。

```java
class Solution {
    public String reverseWords(String s) {
        // 使用StringBuilder储存字符串，效率更高
        StringBuilder stringBuilder = new StringBuilder();
        // 单词头部指针，从末尾开始
        int wordHead = s.length() - 1;
        // 去除掉尾部的空格
        while (wordHead >= 0 && s.charAt(wordHead) == ' ') wordHead --;
        // 单词尾部指针，从尾部非空格元素开始
        int wordTail = wordHead + 1;

        while (wordHead >= 0) {
            // 当单词头部指针指向空格，表示已经遍历了一个单词
            if (s.charAt(wordHead) == ' ') {
                if (wordHead != wordTail) stringBuilder.append(s, wordHead + 1, wordTail).append(' ');
                // 去除掉单词间多余的空格
                while (wordHead >= 0 && s.charAt(wordHead) == ' ') wordHead --;
                wordTail = wordHead + 1;
            }
            wordHead --;
        }
        // 循环结束时候有两种情况：
        // 1. 如果字符串开头是非空格，表示少添加了一个单词，需要补上；
        // 2. 如果字符串开头是空格，表示循环中多添加了一个空格，需要删除。
        if (s.charAt(0) != ' ') stringBuilder.append(s, 0, wordTail);
        else stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
}
```

