# SUBSTRING_INDEX, INSTR, LEFT

* SUBSTRING_INDEX(原字符串，依据字符串，位置)，截取字符串。

  如截取字符串为“15，151，152，16”。

  依据字符串可以为“，”。

  SUBSTRING_INDEX(origin, ',', 1)截取15:

  SUBSTRING_INDEX(origin, ',', 2)截取15，151；

  SUBSTRING_INDEX(origin, ',', -1)截取16。

* INSTR(原字符串，搜索字符串，搜索开始位置，出现位置)，在字符串中搜索指定字符，返回发现字符的位置，没有找到返回0，不可能为负数。。

  搜索开始位置、出现位置默认为1。

* LEFT(原字符串，位置)

  取位置左边的字符串。

  如LEFT('FOOBARBAR', 5)，取到'FOOBA'。

```
【Java3y简单】快乐学习
【Java3y简单】快乐学习渣渣
【Java3y通俗易懂】简单学
【Java3y通俗易懂】简单学芭芭拉
【Java3y平易近人】无聊学
【Java3y初学者】枯燥学
【Java3y初学者】枯燥学呱呱
【Java3y大数据】欣慰学
【Java3y学习】巴拉巴拉学
【Java3y学习】巴拉巴拉学哈哈
【Java3y好】雨女无瓜学
```

对于这样一组数据，如果想取括号中的元素，可以采用：

```sql
SELECT SUBSTRING_INDEX(LEFT(name, INSTR(name, '】') - 1), '【', -1) AS res
FROM `table`;

# 为了避免有其他干扰数据，先取出】前面的值，即LEFT(name, INSTR(name, '】') - 1)。
# 再使用SUBSTRING_INDEX()截取'【'之后的值。
```

