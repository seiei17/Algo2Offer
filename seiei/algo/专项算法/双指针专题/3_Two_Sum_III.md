## Two Sum III

设计一个接收整数流的数据结构，该数据结构支持检查是否存在两数之和等于特定值。

实现 TwoSum 类：

* TwoSum() 使用空数组初始化 TwoSum 对象
* void add(int number) 向数据结构添加一个数 number
* boolean find(int value) 寻找数据结构中是否存在一对整数，使得两数之和与给定的值相等。如果存在，返回 true ；否则，返回 false 。

示例：

    输入：
    ["TwoSum", "add", "add", "add", "find", "find"]
    [[], [1], [3], [5], [4], [7]]
    输出：
    [null, null, null, null, true, false]
    
    解释：
    TwoSum twoSum = new TwoSum();
    twoSum.add(1);   // [] --> [1]
    twoSum.add(3);   // [1] --> [1,3]
    twoSum.add(5);   // [1,3] --> [1,3,5]
    twoSum.find(4);  // 1 + 3 = 4，返回 true
    twoSum.find(7);  // 没有两个整数加起来等于 7 ，返回 false

提示：

* -105 <= number <= 105
* -231 <= value <= 231 - 1
* 最多调用 5 * 104 次 add 和 find

[题目页面: https://leetcode-cn.com/problems/two-sum-iii-data-structure-design/](https://leetcode-cn.com/problems/two-sum-iii-data-structure-design/)

## 分析

#### 1. 双指针

使用双指针需要数组经过排序。

对于题目，add方法调用次数远多余find方法，应该减少add方法的时间复杂度，所以将排序步骤放置find方法中。

而find操作也可能连续调用，即数组没有改变，则不需要再次排序。设置flag防止重复排序。

时间复杂度：

* add(): O(1)
* find(): 最坏的情况下O(nlogn)

```java
class TwoSum {
    private List<Integer> list;
    // 用于判断是否需要排序
    boolean is_sorted;

    /** Initialize your data structure here. */
    public TwoSum() {
        list = new ArrayList<>();
        is_sorted = false;
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        list.add(number);
        is_sorted = false;
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        if (!is_sorted) {
            list.sort((x, y) -> x - y);
            is_sorted = true;
        }
        int low = 0, high = list.size() - 1;
        while (low < high) {
            int sum = list.get(low) + list.get(high);
            if (sum < value) low ++;
            else if (sum > value) high --;
            else return true;
        }
        return false;
    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */
```

#### 2. 哈希表

使用哈希表来查找是否有满足条件的元素。

* 如果使用数组储存数据，则在find()中每次都需要重建哈希表，时间复杂度过高。
* 直接使用哈希表储存元素，在find()中在原有哈希表中查找。

选择第二种方法。

* 初始化一个哈希表储存元素Map<Integer, Integer> map = new HashMap<>()，key为元素的值，value为元素的个数。
* add(): 如果表中没有当前元素，则直接put(value, 1)；否则个数加一，replace(value, get(value) + 1)。
* find(): 直接在哈希表中查找。

时间复杂度：

* add(): O(1)
* find(): O(n)

```java
class TwoSum {
    private Map<Integer, Integer> map;

    /** Initialize your data structure here. */
    public TwoSum() {
        map = new HashMap<>();
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        if (map.containsKey(number)) map.replace(number, map.get(number) + 1);
        else map.put(number, 1);
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            int complement = value - entry.getKey();
            if (complement != entry.getKey()) {
                if (map.containsKey(complement)) return true;
            }
            else if (entry.getValue() > 1) return true;
        }
        return false;
    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */
```

#### 3. 双哈希set

同理，使用两个哈希set也可以，本质上和哈希map是一样的。

```java
class TwoSum {
    private Set<Integer> single;
    private Set<Integer> duplicate;

    /** Initialize your data structure here. */
    public TwoSum() {
        single = new HashSet<>();
        duplicate = new HashSet<>();
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        if (single.contains(number)) duplicate.add(number);
        else single.add(number);
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for (int num: single) {
            int complement = value - num;
            if (complement == num) {
                if (duplicate.contains(num)) return true;
            } else {
                if (single.contains(complement)) return true;
            }
        }
        return false;
    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */
```

