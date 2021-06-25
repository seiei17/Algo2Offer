# Best Meeting Point

[296. 最佳的碰头地点 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/best-meeting-point/)

## 分析

#### 1. 宽度遍历，暴力计算

直接宽度遍历整个图，以遍历的点作为约定地点计算距离，直到最后得到最小距离。

依次选择约定地点包含O(mn)的时间复杂度，而计算距离也有O(mn)的时间复杂度，总的时间复杂度为O(m^2 * n^2)。

```java
class Solution {
    public int minTotalDistance(int[][] grid) {
        int xLen = grid.length, yLen = grid[0].length;
        int minDist = Integer.MAX_VALUE / 2;
        for (int i = 0; i < xLen; i++) {
            for (int j = 0; j < yLen; j++) {
                int dist = calDist(grid, i, j);
                minDist = Math.min(minDist, dist);
            }
        }
        return minDist;
    }

    private int calDist(int[][] grid, int x, int y) {
        int xLen = grid.length, yLen = grid[0].length;
        int dist = 0;
        for (int i = 0; i < xLen; i++) {
            for (int j = 0; j < yLen; j++) {
                if (grid[i][j] == 1) {
                    dist += Math.abs(x - i) + Math.abs(y - j);
                }
            }
        }
        return dist;
    }
}
```

#### 2. 排序求中位数

考虑到曼哈顿距离实际上是分别在两个维度求的距离，即求最小的曼哈顿距离即分别在x和y两个维度上取最小距离。于是问题可以拆解为两个独立变量的子问题的和，即只需要分别计算在x和y两个维度上的最小距离并取和即可。

**在一维情况下的曼哈顿距离之和，存在住户的点的位置的中位数，就是最短相遇距离的点。**

于是，问题最终转化为，求各个住户到矩阵中分别在x、y方向上的中位数代表的点的距离之和。

以宽度优先的方法遍历grid，将遇到住户的点的row和col分别放入储存数组rows和cols。**因为是宽度优先遍历，所以收集到的rows一定是有序的。**但是cols不一定有序，所以需要对cols进行排序再取中位数。最后，再计算所有住户到这个点的距离。

收集点和计算距离的时间复杂度分别为O(mn)。对cols排序，因为极端情况下，cols中存在m*n个点，即排序的时间复杂度为O(mnlogmn)。所以总的时间复杂度为O(mnlogmn)。

```java
class Solution {
    public int minTotalDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        int row = rows.get(rows.size() / 2);
        cols.sort(Comparator.comparingInt(col -> col));
        int col = cols.get(cols.size() / 2);

        int minDist = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    minDist += Math.abs(i - row) + Math.abs(j - col);
                }
            }
        }

        return minDist;
    }
}
```

#### 3. 按顺序收集点

分别遍历两次grid用于收集点。第一次以宽度优先收集rows，第二次以深度优先收集cols。这样可以保证rows和cols均有序，不需要再排序，时间复杂度为O(2mn)。

于是总的时间复杂度为O(3mn)=O(mn)。
