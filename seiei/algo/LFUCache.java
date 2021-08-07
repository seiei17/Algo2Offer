package algo;

import java.util.HashMap;
import java.util.Map;

public class LFUCache<K, V> {
    private Node<K, V> head, tail;
    private final Map<K, Node<K, V>> map;
    private final int capacity;

    LFUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
    }

    /** 宏观方法 **/

    public void put(K key, V value) {
        Node<K, V> node = map.get(key);
        if (node == null) {
            if (isFull()) {
                K removedKey = removeNode(head);
                map.remove(removedKey);
            }
            node = new Node<>(key, value);
            addNode(node);
            map.put(key, node);
        } else {
            node.value = value;
            refreshNode(node);
        }
    }

    public V get(K key) {
        Node<K, V> node = map.get(key);
        if (node == null) return null;
        refreshNode(node);
        return node.value;
    }

    public void printAll() {
        Node<K, V> p = head;
        System.out.print("[");
        while (p != null) {
            System.out.print("{" + p.key + ", " + p.value + "}");
            if (p.next != null) System.out.print(", ");
            p = p.next;
        }
        System.out.println("]");
    }

    /** 细节方法 **/

    /**
     * 刷新节点。将频率加一，寻找到合适的位置，删除链表中的旧节点，再插入。
     * @param node 刷新的节点
     */
    private void refreshNode(Node<K, V> node) {
        Node<K, V> p = node.next;
        node.freq++;
        while (p != null && p.freq <= node.freq) p = p.next;
        removeNode(node);
        addNodeAtChosen(node, p);
    }

    /**
     * 添加新节点。新节点的频率为 0，先寻找到合适的位置，再插入。
     * @param node 新加入的节点
     */
    private void addNode(Node<K, V> node) {
        if (tail == null) {
            head = node;
            tail = node;
            tail.next = null;
        } else {
            Node<K, V> p = head;
            while (p != null && p.freq <= node.freq) p = p.next;
            addNodeAtChosen(node, p);
        }
    }

    /**
     * 指定位置插入节点。
     * @param node 待插入的节点。
     * @param p 插入位置的next节点。
     */
    private void addNodeAtChosen(Node<K, V> node, Node<K, V> p) {
        if (p == head) {
            head.prev = node;
            node.next = head;
            node.prev = null;
            head = node;
        } else if (p == null) {
            tail.next = node;
            node.prev = tail;
            tail = node;
            tail.next = null;
        } else {
            node.prev = p.prev;
            p.prev.next = node;
            node.next = p;
            p.prev = node;
        }
    }

    private K removeNode(Node<K, V> node) {
        if (node == head) {
            head = head.next;
            if (head != null) head.prev = null;
        } else if (node == tail) {
            tail = tail.prev;
            tail.next = null;
        } else {
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }
        return node.key;
    }

    private boolean isFull() {
        return map.size() >= capacity;
    }
}

class Node<K, V> {
    K key;
    V value;
    int freq;
    Node<K, V> prev, next;
    Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.freq = 0;
    }
}