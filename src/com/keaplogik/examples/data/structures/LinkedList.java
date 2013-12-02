package com.keaplogik.examples.data.structures;

import java.util.Iterator;

/**
 *
 */
public class LinkedList<E extends Comparable> implements Iterable<E> {

    private ListNode<E> firstNode;
    private ListNode<E> lastNode;

    public LinkedList() {
    }

    class ListNode<E extends Comparable> {
        ListNode<E> prev;
        ListNode<E> next;
        E value;

        public ListNode(E value) {
            this.value = value;
        }
    }

    private class LinkListIterator<F extends Comparable> implements Iterator<F> {

        private ListNode<F> currentNode;

        public LinkListIterator(LinkedList<F> linkedList) {
            this.currentNode = (ListNode<F>) linkedList.firstNode;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public F next() {
            ListNode<F> returnNode = currentNode;
            currentNode = returnNode.next;
            return returnNode.value;
        }

        @Override
        public void remove() {
            ListNode<F> newCurrent = currentNode.prev;
            newCurrent.next = currentNode.next;
            currentNode.next.prev = newCurrent;
            currentNode = newCurrent;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkListIterator<E>(this);
    }


    public void add(E value) {
        final ListNode listNode = new ListNode<E>(value);
        this.addNodeAfter(this.lastNode, listNode);
    }

    public E get(int index) {
        return getNodeAt(index, this.firstNode).value;
    }

    private ListNode<E> getNodeAt(int index) {
        ListNode<E> node = this.firstNode;

        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private ListNode<E> getNodeAt(int index, ListNode<E> node) {
        if (index == 0) {
            return node;
        }
        return getNodeAt(index - 1, node.next);
    }


    private void addNodeAfter(ListNode prevNode, ListNode newNode) {
        if (this.firstNode == null) {
            this.firstNode = newNode;
            this.lastNode = newNode;
        } else {
            newNode.next = prevNode.next;
            prevNode.next = newNode;

            newNode.prev = prevNode;

            if (newNode.next == null) {
                this.lastNode = newNode;
            }
        }
    }


}
