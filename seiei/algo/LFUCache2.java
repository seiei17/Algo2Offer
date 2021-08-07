package algo;

import java.util.HashMap;
import java.util.Map;

public class LFUCache2 {

    private Node2 head; // 头结点 简化null判断
    private Node2 tail; // 尾结点 简化null判断
    private int capacity; // 容量限制
    private int size; // 当前数据个数
    private Map<Integer, Node2> map; // key和数据的映射

    public LFUCache2(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.head = new Node2(0, 0, 0);
        this.tail = new Node2(0, 0, 0);
        this.head.next = tail;
        this.tail.pre = head;
        this.map = new HashMap<>();
    }

    public int get(int key) {
        // 从哈希表中判断数据是否存在
        Node2 node = map.get(key);
        if (node == null) {
            return -1;
        }
        // 如果存在则增加该数据的访问频次
        freqPlus(node);
        return node.value;
    }

    public void put(int key, int value) {

        if (capacity <= 0) {
            return;
        }

        Node2 node = map.get(key);
        if (node != null) {
            // 如果存在则增加该数据的访问频次
            node.value = value;
            freqPlus(node);
        } else {
            // 淘汰数据
            eliminate();
            Node2 newNode = new Node2(key, value, 0);
            map.put(key, newNode);
            size++;

            // 将新数据插入到末尾
            Node2 tailPre = tail.pre;
            tail.pre = newNode;
            newNode.pre = tailPre;
            newNode.next = tail;
            tailPre.next = newNode;
            // 增加访问频次
            freqPlus(newNode);
        }
    }

    public void printAll() {
        Node2 p = head;
        System.out.print("[");
        while (p != null) {
            System.out.print("{" + p.key + ", " + p.value + "}");
            if (p.next != null) System.out.print(", ");
            p = p.next;
        }
        System.out.println("]");
    }

    private void freqPlus(Node2 node) {

        node.frequency++;
        Node2 temp = node.pre;
        int freq = node.frequency;
        while(temp != null) {

            // 使用大于号的原因是将最后访问的数据排在旧数据之前
            if (temp.frequency > freq  || temp == head) {
                node.pre.next = node.next;
                node.next.pre = node.pre;

                // 根据访问频次排序调整位置
                Node2 tempNext = temp.next;
                temp.next = node;
                tempNext.pre = node;
                node.next = tempNext;
                node.pre = temp;
                break;
            }
            temp = temp.pre;
        }
    }


    private void eliminate() {

        if (size < capacity) {
            return;
        }

        // 从尾结点的pre节点之间删除即可
        Node2 last = tail.pre;
        last.pre.next = tail;
        tail.pre = last.pre;
        map.remove(last.key);
        size--;
        last = null;
    }
}

class Node2 {
    int key;
    int value;
    int frequency;

    Node2 pre;
    Node2 next;

    Node2(int key, int value, int frequency) {
        this.key = key;
        this.value = value;
        this.frequency = frequency;
    }
}
