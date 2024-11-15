package org.example;

import java.util.HashMap;
import java.util.Map;

/**
 * Use hashmap and doubly linked list for implementation
 */
public class LRUCache {

    private int size;
    private Map<Integer, Node> cache;
    public Node head;
    public Node tail;

    public LRUCache(int size) {
        this.size = size;
        cache = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }


    public void put(Integer key, Integer value) {
        if (cache.containsKey(key)) {
            Node item = cache.get(key);
            item.setValue(value);
            cache.put(key, item);
            removeAndAddLast(item);
        } else {
            if (cache.size() >= size) {
                Node eldestNode = head.next;
                removeHead(eldestNode);
                if (eldestNode != null) {
                    cache.remove(eldestNode.getKey());
                }
            }
            Node item = new Node(key, value);
            cache.put(key, item);
            addToTail(item);
        }

    }

    /**
     * once we have more nodes which is beyond the capacity, remove the first node
     *
     * @param itemToAdd
     */
    public void removeHead(Node itemToAdd) {
        //remove head data element;
        Node first = head.next;
        Node next = head.next.next;
        if (next != null) {
            head.next = next;
            next.prev = head;
        }
    }

    /**
     * add item to tail but before tail dummy node
     *
     * @param item
     */
    public void addToTail(Node item) {
        Node temp = tail.prev;
        temp.next = item;
        item.prev = temp;
        item.next = tail;
        tail.prev = item;
    }

    //remove existing item in doubly linkedlist and append to last but before tail dummy node
    public void removeAndAddLast(Node item) {
        Node prev = item.prev;
        Node next = item.next;
        prev.next = next;
        next.prev = prev;
        Node temp = tail.prev;
        if (temp != null) {
            temp.next = item;
            item.prev = temp;
            item.next = tail;
            tail.prev = item;
        }
    }

    public Integer get(Integer key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        //remove and add to last
        Node item = cache.get(key);
        removeAndAddLast(item);
        return item.getValue();
    }

    static class Node {
        private Integer key;
        private Integer value;
        public Node next;
        public Node prev;

        public Node(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1);
        lRUCache.put(2, 2);
        lRUCache.get(1);
        lRUCache.put(3, 3);
        lRUCache.get(2);
        lRUCache.put(4, 4);
        lRUCache.get(1);
        lRUCache.get(3);
        lRUCache.get(4);
    }
}





