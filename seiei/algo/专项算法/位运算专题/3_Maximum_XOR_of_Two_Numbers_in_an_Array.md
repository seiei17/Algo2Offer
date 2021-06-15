# Maximum XOR of Two Numbers in an Array

[421. 数组中两个数的最大异或值 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array/)

## 分析

异或运算为res = x ^ y。如果直接枚举数组每个异或结果，时间复杂度为O(n^2)。

考虑逆转异或运算：x = res ^ y。用res和y异或，如果得到的x在数组中，则表示当前res成立。从高到低考虑res的每一位，可以为0或者1。于是问题变为思考如何判断res的当前位能否取1。

#### 1. 使用哈希表判断

假设k为当前判断的位数，那么res可以表示为<img src="https://latex.codecogs.com/png.latex? r_{30}r_{29}r_{28}...r_{k+1}r_k...r_{1}r_{0}">，假设已经判断了k以前的位，即<img src="https://latex.codecogs.com/png.latex? r_{30}r_{29}r_{28}...r_{k+1}">已知，那么假设<img src="https://latex.codecogs.com/png.latex? r_k=1">，即当前<img src="https://latex.codecogs.com/png.latex? res=r_{30}r_{29}...r_{k+1}1">。于是，遍历数组，每个num右移k位作为<img src="https://latex.codecogs.com/png.latex? x=x_{30}x_{29}...x_{k+1}x_k">，并存入哈希表。再遍历数组，每个num右移k位作为<img src="https://latex.codecogs.com/png.latex? y=y_{30}y_{29}...y_{k+1}y_k">，再与假设的当前<img src="https://latex.codecogs.com/png.latex? res=r_{30}r_{29}...r_{k+1}1">异或，如果结果在哈希表中，表示x = res ^ y计算成立，则<img src="https://latex.codecogs.com/png.latex? r_k">可以取1；否则取0。

```java
class Solution {
    public int findMaximumXOR(int[] nums) {
        int res = 0;
        for (int i = 30; i >= 0; i--) {
            Set<Integer> visited = new HashSet<>();
            for (int num: nums) visited.add(num >> i);
            int next = res * 2 + 1;
            boolean found = false;
            for (int num: nums) {
                if (visited.contains(next ^ (num >> i))) {
                    found = true;
                    break;
                }
            }
            if (found) res = next;
            else res = next - 1;
        }
        return res;
    }
}
```

#### 2. 构造字典树判断

构造[208.前缀树](../../leetcode/208_Implement_Trie.md)判断。

每次遍历到第i个数，称为ni，通过前缀树查找前i-1个数和ni的异或最大值xor：

*   从高位开始，依次查找异或的值；
*   遍历到ni的第k位，此时值为0或者1，记为index。则如果节点的1-index的子节点存在，指针移到1-index，且xor的第k位置1；
*   如果节点的1-index子节点不存在，指针移到index。且xor的第k位置0。

解释上述步骤：因为要求异或的最大值，则从高位出发，如果当前位能取1，说明两个数在这个位置上不同。于是ni的第k位为index，那么就要查找前缀树中是否存在与index相反的节点，即1-index (index=0/1。如果存在，说明两者可以不同，则xor取1；否则只能取0。

```java
class Solution {
    public int findMaximumXOR(int[] nums) {
        int n = nums.length;
        Trie root = new Trie();
        int res = 0;
        for (int i = 1; i < n; i++) {
            root.insert(nums[i - 1]);
            res = Math.max(res, root.check(nums[i]));
        }
        return res;
    }

    private class Trie {
        private Trie[] children;

        public Trie() {
            this.children = new Trie[2];
        }

        public void insert(int num) {
            Trie node = this;
            for (int i = 30; i >= 0; i--) {
                int index = (num >> i) & 1;
                if (node.children[index] == null) node.children[index] = new Trie();
                node = node.children[index];
            }
        }

        public int check(int num) {
            Trie node = this;
            int xor = 0;
            for (int i = 30; i >= 0; i--) {
                int index = num >> i & 1;
                if (node.children[1 - index] != null) {
                    xor = xor * 2 + 1;
                    node = node.children[1 - index];
                }
                else {
                    xor *= 2;
                    node = node.children[index];
                }
            }
            return xor;
        }
    }
}
```

