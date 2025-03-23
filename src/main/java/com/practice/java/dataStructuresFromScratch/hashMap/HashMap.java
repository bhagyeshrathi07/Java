package com.practice.java.dataStructuresFromScratch.hashMap;

class Node {
    int key;
    int value;
    Node next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}

public class HashMap {
    private int capacity;
    private int size;
    private Node[] table;

    public void put(int key, int value) {
        int index = hashFunction(key);
        Node node = table[index];
        if(node == null) {
            table[index] = new Node(key, value);
            this.size++;
        }
        else {
            Node prev = null;
            while(node != null) {
                if(node.key == key) {
                    node.value = value;
                    return;
                }
                prev = node;
                node = node.next;
            }
            prev.next = new Node(key, value);
            this.size++;
        }
        if((double)size / capacity >= 0.5) resize();
    }

    public int get(int key) {
        int index = hashFunction(key);
        Node node = table[index];
        while(node != null) {
            if(node.key == key) {
                return node.value;
            }
            node = node.next;
        }
        return -1;
    }

    public boolean remove(int key) {
        int index = hashFunction(key);
        Node node = table[index];
        Node prev = null;
        while(node != null) {
            if(node.key == key) {
                if(prev != null) {
                    prev.next = node.next;
                }
                else {
                    table[index] = node.next;
                }
                this.size--;
                return true;
            }
            prev = node;
            node = node.next;
        }
        return false;
    }

    public int hashFunction(int key) {
        return key % this.capacity;
    }

    public void resize() {
        int newCapacity = 2 * this.capacity;
        Node[] newTable = new Node[newCapacity];

        for(Node node : table) {
            while(node != null) {
                int newIndex = node.key % newCapacity;
                if(newTable[newIndex] == null) {
                    newTable[newIndex] = new Node(node.key, node.value);
                }
                else {
                    Node newNode = newTable[newIndex];
                    while(newNode.next != null) {
                        newNode = newNode.next;
                    }
                    newNode.next = new Node(node.key, node.value);
                }
                node = node.next;
            }
            this.capacity = newCapacity;
            this.table = newTable;
        }
    }

}