package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class SinglyLinkedList<E extends Comparable<? super E>> implements List<E> {

    private static class Node<E> {

        private final E element;            // reference to the element stored at this node

        /**
         * A reference to the subsequent node in the list
         */
        private Node<E> next;         // reference to the subsequent node in the list

        /**
         * Creates a node with the given element and next node.
         *
         * @param e the element to be stored
         * @param n reference to a node that should follow the new node
         */
        public Node(E e, Node<E> n) {
            this.element = e;
            this.next = n;
        }

        // Accessor methods

        /**
         * Returns the element stored at the node.
         *
         * @return the element stored at the node
         */
        public E getElement() {
            return element;
        }

        /**
         * Returns the node that follows this one (or null if no such node).
         *
         * @return the following node
         */
        public Node<E> getNext() {
            return next;
        }

        // Modifier methods

        /**
         * Sets the node's next reference to point to Node n.
         *
         * @param n the node that should follow this one
         */
        public void setNext(Node<E> n) {
            this.next = n;
        }
    } //----------- end of nested Node class -----------

    /**
     * The head node of the list
     */
    private Node<E> head = null;               // head node of the list (or null if empty)


    /**
     * Number of nodes in the list
     */
    private int size = 0;                      // number of nodes in the list

    public SinglyLinkedList() {
    }              // constructs an initially empty list

    //@Override
    public int size() {
        return size;
    }

    //@Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int position) {
        Node<E> curr = head;
        for (int i = 0; i < position; i++) {
            curr = curr.getNext();
        }
        return curr.getElement();
    }

    @Override
    public void add(int position, E e) {
        if (position == 0) {
            addFirst(e);
        } else {
            Node<E> curr = head;
            for (int i = 0; i < position - 1; i++) {
                curr = curr.getNext();
            }
            curr.setNext(new Node<>(e, curr.getNext()));
            size++;
        }
    }


    @Override
    public void addFirst(E e) {
        head = new Node<>(e, head);
        size++;
    }

    @Override
    public void addLast(E e) {
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()) {
            head = newest;
        }
        else {
            Node<E> curr = head;
            while (curr.getNext() != null) {
                curr = curr.getNext();
            }
            curr.setNext(newest);
        }
        size++;
    }

    @Override
    public E remove(int position) {
        if (position == 0) {
            return removeFirst();
        } else {
            Node<E> node = head;
            for (int i = 0; i < position - 1; i++) {
                node = node.getNext();
            }
            Node<E> nodeToRemove = node.getNext();
            E element = nodeToRemove.getElement();
            node.setNext(nodeToRemove.getNext());
            size--;
            return element;
        }
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        E element = head.getElement();
        head = head.getNext();
        size--;
        return element;
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (size == 1) {
            return removeFirst();
        } else {
            Node<E> node = head;
            while (node.getNext().getNext() != null) {
                node = node.getNext();
            }
            E element = node.getNext().getElement();
            node.setNext(null);
            size--;
            return element;
        }
    }

    public Node<E> sortedMerge(Node<E> l1, Node<E> l2) {
        // head.next is the first element
        Node<E> head = new Node<>(null, null);
        Node<E> curr = head;

        while (l1 != null && l2 != null) {
            if (l1.getElement().compareTo(l2.getElement()) <= 0) {
                curr.setNext(l1);
                l1 = l1.getNext();
            } else {
                curr.setNext(l2);
                l2 = l2.getNext();
            }
            curr = curr.getNext();
        }

        if (l1 != null) {
            curr.setNext(l1);
        }
        else if (l2 != null) {
            curr.setNext(l2);
        }


        return head.getNext();
    }

    public void reverse() {
        Node<E> prev = null;
        Node<E> curr = head;
        Node<E> next;
        while(curr != null) {
            next = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    public SinglyLinkedList<E> copy() {
        SinglyLinkedList<E> twin = new SinglyLinkedList<E>();
        Node<E> tmp = head;
        while (tmp != null) {
            twin.addLast(tmp.getElement());
            tmp = tmp.next;
        }
        return twin;
    }

    //@Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<E>();
    }

    private class SinglyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public E next() {
            E res = curr.getElement();
            curr = curr.next;
            return res;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr != null) {
            sb.append(curr.getElement());
            if (curr.getNext() != null)
                sb.append(", ");
            curr = curr.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        System.out.println("ll " + ll + " isEmpty: " + ll.isEmpty());
        //LinkedList<Integer> ll = new LinkedList<Integer>();

        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addLast(-1);
        //ll.removeLast();
        //ll.removeFirst();
        //System.out.println("I accept your apology");
        //ll.add(3, 2);
        System.out.println(ll);
        ll.remove(5);
        System.out.println(ll);

    }
}
