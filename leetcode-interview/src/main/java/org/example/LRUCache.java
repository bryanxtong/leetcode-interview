package org.example;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private int size;
    private Map<Integer, Node> map;
    public Node head;
    public Node tail;

    public LRUCache(int size) {
        this.size = size;
        map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }


    public void put(Integer key, Integer value) {
        if (map.containsKey(key)) {
            Node item = map.get(key);
            item.setValue(value);
            removeExistingNode(item);
        } else {
            if (map.size() < size) {
                Node item = new Node(key, value);
                //add to tail
                addToTail(item);
                map.put(key, item);
            } else {
                Node item = new Node(key, value);
                Node first = head.next;
                if (first != null) {
                    map.remove(first.key);
                }
                map.put(key, item);
                //remove head data element and append to tail
                removeHeadAndAppendToTail(item);
            }
        }

    }

    /**
     * once we have more nodes which is beyond the capacity, remove the first node and append to the tail
     *
     * @param itemToAdd
     */
    public void removeHeadAndAppendToTail(Node itemToAdd) {
        //remove head data element;
        Node first = head.next;
        Node next = head.next.next;
        if (next != null) {
            head.next = next;
            next.prev = head;
        }
        //add the new node
        Node temp = tail.prev;
        temp.next = itemToAdd;
        itemToAdd.prev = temp;
        itemToAdd.next = tail;
        tail.prev = itemToAdd;
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

    //remove existing item in doubly linkedlist and append to last but before tail
    public void removeExistingNode(Node item) {
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
        if (map.containsKey(key)) {
            //remove item in doubly linkedlist
            Node item = map.get(key);
            removeExistingNode(item);
            return map.get(key).getValue();
        }
        return -1;

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





