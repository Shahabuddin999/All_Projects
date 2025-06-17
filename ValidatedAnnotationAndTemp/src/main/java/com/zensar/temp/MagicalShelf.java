package com.zensar.temp;
import java.util.HashMap;

public class MagicalShelf {

    private class Node {
        int key;
        String value;
        Node prev, next;

        Node(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private HashMap<Integer, Node> map;
    private Node head, tail;

    public MagicalShelf(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(0, ""); // dummy head
        this.tail = new Node(0, ""); // dummy tail
        head.next = tail;
        tail.prev = head;
    }

    public String get(int key) {
        if (!map.containsKey(key)) return "-1";
        Node node = map.get(key);
        moveToHead(node);
        return node.value;
    }

    public void put(int key, String value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            if (map.size() == capacity) {
                Node lru = tail.prev;
                removeNode(lru);
                map.remove(lru.key);
            }
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addToHead(newNode);
        }
    }

    // Helper methods

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
}