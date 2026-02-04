package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class DoublyLinkedList<E> implements List<E> {

    private static class Node<E> {
        private final E data;
        private Node<E> next;
        private Node<E> prev;

        public Node(E e, Node<E> p, Node<E> n) {
            data = e;
            prev = p;
            next = n;
        }

        public E getData() {
            return data;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrev() {
            return prev;
        }

    }

    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    public DoublyLinkedList() {
        head = new Node<E>(null, null, null);
        tail = new Node<E>(null, head, null);
        head.next = tail;
    }

    private void addBetween(E e, Node<E> pred, Node<E> succ) {
        Node<E> newNode = new Node<>(e,pred,succ);
        pred.next = newNode;
        succ.prev = newNode;
        size++;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) {
        if (isEmpty()){
            return null;
        }
        Node<E> curr = head;

        for (int j = 0; j < i; j++) {
            curr = curr.getNext();
        }

        return curr.getNext().getData();
    }

    @Override
    public void add(int i, E e) {
        if (isEmpty()){
            addLast(e);
        }

        Node<E> curr = head;
        for (int j = 0; j < i; j++){
            curr = curr.getNext();
        }

        addBetween(e,curr,curr.getNext());

    }

    @Override
    public E remove(int i) {
        Node<E> curr = head;
        for (int j = 0; j < i; j++) {
            curr = curr.getNext();
        }
        Node<E> nodeToRemove = curr.getNext();
        curr.next = nodeToRemove.getNext();
        nodeToRemove.getNext().prev = curr;

        return nodeToRemove.getData();
    }

    private class DoublyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head.next;

        @Override
        public boolean hasNext() {
            return curr != tail;
        }

        @Override
        public E next() {
            E res = curr.data;
            curr = curr.next;
            return res;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new DoublyLinkedListIterator<E>();
    }

    private E remove(Node<E> n) {
        n.getPrev().next = n.getNext();
        n.getNext().prev = n.getPrev();
        return n.getData();
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return head.getNext().getData();
    }

    public E last() {
        if (isEmpty()) {
            return null;
        }

        return tail.getPrev().getData();
    }

    @Override
    public E removeFirst() {
        Node<E> nodeToRemove = head.getNext();
        head.next = nodeToRemove.getNext();
        nodeToRemove.getNext().prev = head;

        size--;
        return nodeToRemove.getData();
    }

    @Override
    public E removeLast() {
        Node<E> nodeToRemove = tail.getPrev();
        nodeToRemove.getPrev().next = tail;
        tail.prev = nodeToRemove.getPrev();
        size--;
        return nodeToRemove.getData();
    }

    @Override
    public void addLast(E e) {
        addBetween(e, tail.prev, tail);
    }

    @Override
    public void addFirst(E e) {
        addBetween(e, head, head.getNext());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head.next;
        while (curr != tail) {
            sb.append(curr.data);
            curr = curr.next;
            if (curr != tail) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addLast(-1);
        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }
    }
}