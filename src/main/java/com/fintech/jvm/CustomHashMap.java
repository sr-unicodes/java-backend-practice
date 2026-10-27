package com.fintech.jvm;

public class CustomHashMap<K, V> {

    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private Node<K, V>[] buckets;
    private int capacity = 4;


    public CustomHashMap() {
        buckets = new Node[capacity];
    }


    private int getBucketIndex(K key) {
        int hashCode = key.hashCode();
        return (hashCode & 0x7FFFFFFF) % capacity;
    }

    public void put(K key, V value) {
        int index = getBucketIndex(key);
        Node<K, V> head = buckets[index];


        Node<K, V> current = head;
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = head;
        buckets[index] = newNode;

        System.out.println("Inserted: [" + key + "=" + value + "] -> Bucket Index: " + index);
    }

    public V get(K key) {
        int index = getBucketIndex(key);
        Node<K, V> head = buckets[index];

        Node<K, V> current = head;
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public void remove(K key) {
        int index = getBucketIndex(key);
        Node<K, V> head = buckets[index];

        if (head == null) {
            System.out.println("Key [" + key + "] not found.");
            return;
        }

        if (head.key.equals(key)) {
            buckets[index] = head.next;
            System.out.println("Removed HEAD key: [" + key + "]");
            return;
        }

        Node<K, V> prev = null;
        Node<K, V> current = head;

        while (current != null) {
            if (current.key.equals(key)) {
                prev.next = current.next;
                System.out.println("Removed middle/tail key: [" + key + "]");
                return;
            }
            prev = current;
            current = current.next;
        }
        System.out.println("Key [" + key + "] not found.");
    }

    public static void main(String[] args) {
        CustomHashMap<String, Integer> myMap = new CustomHashMap<>();

        System.out.println("=== INSERTING TRANSACTIONS ===");
        myMap.put("Alice", 100);
        myMap.put("Bob", 200);
        myMap.put("Charlie", 300);
        myMap.put("Diana", 400);
        myMap.put("Eve", 500);

        System.out.println("\n=== RETRIEVING TRANSACTIONS ===");
        System.out.println("Bob's balance: " + myMap.get("Bob"));
        System.out.println("Eve's balance: " + myMap.get("Eve"));
        System.out.println("Unknown's balance: " + myMap.get("Unknown"));

        System.out.println("\n=== REMOVING TRANSACTIONS ===");
        myMap.remove("Eve");
        System.out.println("Eve's balance after removal: " + myMap.get("Eve"));
    }
}