# Implement Trie (Prefix Tree)

[208. 实现 Trie (前缀树) - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/implement-trie-prefix-tree/)

## 分析

Trie，又称前缀树或字典树，是一棵有根树，其每个节点包含以下字段：

*   指向子节点的指针数组children。对于本题而言，数组长度为26，即小写英文字母的数量。此时children[0] 对应小写字母 a，children[1] 对应小写字母b，…，children[25] 对应小写字母z。
*   布尔字段isEnd，表示该节点是否为字符串的结尾。	

#### 1. 插入字符串

从字典树的根节点开始，插入字符串。对于当前遍历的节点，对应的字符为w，有两种情况：

*   children[w]即子节点存在，指针移动到下个节点。
*   children[w]不存在，新建子节点在对应位置上children[w] = new Trie()，指针移动到下个节点。

重复执行以上步骤，直到插入字符串最后一个字符，并将末尾节点isENd置true。

#### 2. 查找字符串

从字典树的根节点出发，查找节点。对于当前遍历的节点，对应的字符为w，有以下情况：

*   children[w]存在，指针移动到下个节点；
*   children[w]不存在，表示没有这个字符串，返回false。

重复执行以上步骤，直到查找到字符串最后一个字符，返回末尾结点的isEnd。

#### 3. 查找prefix

从字典树的根节点出发，查找节点。对于当前遍历的节点，对应的字符为w，有以下情况：

*   children[w]存在，指针移动到下个节点；
*   children[w]不存在，表示没有这个字符串，返回false。

重复执行以上步骤，直到查找到字符串最后一个字符，返回true。

初始化为O(1)，插入、查找时间复杂度均为O(n)。

```java
class Trie {
    private Trie[] children;
    private boolean isEnd;

    /** Initialize your data structure here. */
    public Trie() {
        this.children = new Trie[26];
        this.isEnd = false;
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Trie node = this;
        for (char w: word.toCharArray()) {
            int index = w >= 97? w - 'a': w - 'A';
            if (node.children[index] == null) node.children[index] = new Trie();
            node = node.children[index];
        }
        node.isEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie node = this;
        for (char w: word.toCharArray()) {
            int index = w >= 97? w - 'a': w - 'A';
            if (node.children[index] != null) node = node.children[index];
            else return false;
        }
        return node.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie node = this;
        for (char w: prefix.toCharArray()) {
            int index = w >= 97? w - 'a': w - 'A';
            if (node.children[index] != null) node = node.children[index];
            else return false;
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
```

