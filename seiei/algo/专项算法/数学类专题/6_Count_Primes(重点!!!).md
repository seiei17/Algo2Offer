# Count Primes

[204. 计数质数 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/count-primes/)

## 分析

#### 1. 暴力遍历

遍历从2到n的数，找出质数的个数即可。

判断质数使用试除法：从2到根号n，如果有数能整除n，表示不是质数。

时间复杂度为O(n根号n)。

```java
class Solution {
    public int countPrimes(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) count += isPrime(i)? 1: 0;
        return count;
    }

    private boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
```

#### 2. 埃氏筛

[埃拉托斯特尼筛法 - 维基百科，自由的百科全书 (wikipedia.org)](https://zh.wikipedia.org/wiki/埃拉托斯特尼筛法)

对于n，找出根号n以内的素数<img src="https://latex.codecogs.com/svg.image?p_1,p_2,...,p_k" title="p_1,p_2,...,p_k" />。从2开始，用选择的素数去筛选数列，即把素数的倍数从数列中删掉。最后，剩下的数即全部都是素数。

时间复杂度为<img src="https://latex.codecogs.com/svg.image?O(n\log\log&space;n)" title="O(n\log\log n)" />。

算法伪代码：

```
Input: integer n > 1

bool[] flag = new bool[n]
flag[0] = flag[1] = true

for i = 2, 3, ..., sqrt(n):
	// flag[i]为false，表示是素数
	if flag[i] is false:
		for j = i * i, i * i + 1, ..., n:
			// j全是i的倍数，不是素数，将flag[j]置true
			flag[j] = true
			
Output: count of false in flag
```

```java
class Solution {
    public int countPrimes(int n) {
        boolean[] flag = new boolean[n];
        flag[0] = true;
        flag[1] = true;
        int count = 0;
        for (int i = 2; i * i < n; i++) {
            if (!flag[i]) {
                for (int j = i * i; j < n; j += i) flag[j] = true;
            }
        }
        for (int i = 2; i < n; i++) count += flag[i]? 0: 1;
        return count;
    }
}
```

#### 3. 线性筛

与**埃拉托斯特尼筛法**中选择质数来标记不同，线性筛让每一个数都参与标记。

埃氏筛法存在一个数被多次标记的问题，线性筛解决了这个问题。线性筛利用最小因子来表示标记元素，让被标记元素只会被标记一次。

令primes数组用于储存搜索过的素数，x表示当前用于标记的数。

每轮标记，使用x和primes中已经发现的素数相乘来表示标记的合数，当
<img src="https://latex.codecogs.com/svg.image?x&space;\space\%\space&space;primes_i&space;=&space;0" title="x \space\%\space primes_i = 0" />时，此轮标记结束。

**为什么选择这个标记终止条件：**

假设达成终止条件，即<img src="https://latex.codecogs.com/svg.image?x&space;\space\%\space&space;primes_i&space;=&space;0" title="x \space\%\space primes_i = 0" />。那么被<img src="https://latex.codecogs.com/svg.image?x&space;\times&space;primes_{i&space;&plus;&space;1}" title="x \times primes_{i + 1}" />标记的数，一定会在<img src="https://latex.codecogs.com/svg.image?y&space;=&space;{x&space;\over&space;primes_i}&space;\times&space;primes_{i&space;&plus;&space;1}" title="y = {x \over primes_i} \times primes_{i + 1}" />进行标记的时候被标记，即被<img src="https://latex.codecogs.com/svg.image?y&space;\times&space;primes_i" title="y \times primes_i" />标记。

所以此时终止标记，防止重复标记。

**Java代码**

```java
class Solution {
    public int countPrimes(int n) {
        if (n <= 1) return 0;
        List<Integer> primes = new ArrayList<>();
        boolean[] flag = new boolean[n];

        for (int i = 2; i < n; i++) {
            if (!flag[i]) primes.add(i);
            for (int j = 0; j < primes.size(); j++) {
                if (i * primes.get(j) >= n) break;
                flag[i * primes.get(j)] = true;
                if (i % primes.get(j) == 0) break;
            }
        }
        return primes.size();
    }
}
```