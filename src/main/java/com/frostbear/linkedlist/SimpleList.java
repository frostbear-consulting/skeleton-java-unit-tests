package com.frostbear.linkedlist;

public class SimpleList {

    protected Node head;

    public SimpleList() {
        this.head = null;
    }

    public boolean isEmpty() {
        return null == this.head;
    }

    public void addFirst(Object value) {
        this.head = new Node(value, this.head);
    }

    public Object removeFirst() {
        if (null == this.head) {
            throw new IllegalStateException("List is empty");
        }

        var value = this.head.value;

        this.head = this.head.next;

        return value;
    }

    public void addLast(Object value) {
        if (this.isEmpty()) {
            this.addFirst(value);
            return;
        }

        var last = this.head;
        while (null != last.next) {
            last = last.next;
        }

        last.next = new Node(value, null);
    }

    public Object getFirst() {
        if (this.isEmpty()) {
            throw new IllegalStateException("List is empty");
        }

        return this.head.value;
    }

    public Object getLast() {
        if (this.isEmpty()) {
            throw new IllegalStateException("List is empty");
        }

        var last = this.head;
        while (null != last.next) {
            last = last.next;
        }

        return last.value;
    }

    public Object removeLast() {
        if (this.isEmpty()) {
            throw new IllegalStateException("List is empty");
        }

        Object value = null;

        if (null == this.head.next) {
            value = this.head.value;
            this.head = null;
        } else {
            var secondToLast = this.head;

            while (null != secondToLast.next.next) {
                secondToLast = secondToLast.next;
            }

            value = secondToLast.next.value;
            secondToLast.next = null;
        }

        return value;
    }
}