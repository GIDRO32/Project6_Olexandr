package Part1;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T> {

    private Node<T> first;
    private Node<T> last;
    private int size;

    public void add(T element) {
        addLast(element);
    }

    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            addFirst(element);
        } else if (index == size) {
            addLast(element);
        } else {
            Node<T> node = getNode(index);
            Node<T> newNode = new Node<>(node.prev, element, node);
            node.prev.next = newNode;
            node.prev = newNode;
            size++;
        }
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> node = getNode(index);
        return node.element;
    }

    public void removeFirst(T element) {
        Node<T> node = first;
        while (node != null) {
            if (node.element.equals(element)) {
                removeNode(node);
                return;
            }
            node = node.next;
        }
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> node = getNode(index);
        removeNode(node);
    }

    public void removeAll(T element) {
        Node<T> node = first;
        while (node != null) {
            Node<T> nextNode = node.next;
            if (node.element.equals(element)) {
                removeNode(node);
            }
            node = nextNode;
        }
    }

    public void addFirst(T element) {
        Node<T> newNode = new Node<>(null, element, first);
        if (size == 0) {
            first = last = newNode;
        } else {
            first.prev = newNode;
            first = newNode;
        }
        size++;
    }

    public void addLast(T element) {
        Node<T> newNode = new Node<>(last, element, null);
        if (size == 0) {
            first = last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> node = first;
        while (node != null) {
            sb.append(node.element);
            if (node.next != null) {
                sb.append(", ");
            }
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }

    private Node<T> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> x = first;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }

    private void removeNode(Node<T> node) {
        if (node.next == null) {
            last = node.prev;
        } else {
            node.next.prev = node.prev;
        }
        node.prev = node.next = null;
        size--;
    }

    private static class Node<T> {
        private Node<T> prev;
        private T element;
        private Node<T> next;

        public Node(Node<T> prev, T element, Node<T> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {
        private Node<T> current = first;
        private Node<T> lastReturned = null;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = current;
            current = current.next;
            return lastReturned.element;
        }

        @Override
        public void remove() {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            removeNode(lastReturned);
            lastReturned = null;
        }
    }
    class Stack<T> extends LinkedList<T> {

        public void push(T item) {
            add(item);
        }

        public T pop() {
            if (isEmpty()) {
                throw new EmptyStackException();
            }
            T item = get(size - 1);
            remove(size - 1);
            return item;
        }
        public boolean isEmpty() {
            return size == 0;
        }
    }
    class Queue<T> extends LinkedList<T> {

        public void enqueue(T item) {
            addLast(item);
        }

        public T dequeue() {
            if (isEmpty()) {
                throw new EmptyStackException();
            }
            T item = get(0);
            remove(0);
            return item;
        }

        public boolean isEmpty() {
            return getSize() == 0;
        }
    }
}
