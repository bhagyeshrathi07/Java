package com.practice.java.dataStructuresFromScratch.linkedList;

import java.util.ArrayList;

public class LinkedList {
    private Node head;
    private Node tail;

    public LinkedList() {
        this.head = new Node(-1);
        this.tail = this.head;
    }

    public int get(int index) {
        Node curr = head.next;
        int i = 0;
        while(curr != null) {
            if(i == index) {
                return curr.val;
            }
            i++;
            curr = curr.next;
        }
        return -1;
    }

    public void insertHead(int val) {
        Node node = new Node(val);
        node.next = this.head.next;
        this.head.next = node;
        if(node.next == null) {
            tail = node;
        }
    }

    public void insertTail(int val) {
        Node node = new Node(val);
        tail.next= node;
        tail = tail.next;
    }

    public boolean remove(int index) {
        Node curr = head.next;
        int i = 0;
        while(i < index && curr != null) {
            curr = curr.next;
            i++;
        }
        if(curr != null && curr.next != null) {
            if(curr.next == tail) {
                this.tail = curr;
            }
            curr.next = curr.next.next;
            return true;
        }
        return false;
    }

    public ArrayList<Integer> getValues() {
        ArrayList<Integer> output = new ArrayList<>();
        Node curr = head;
        while(curr != null) {
            output.add(curr.val);
            curr = curr.next;
        }
        return output;
    }
}
