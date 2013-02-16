package org.eslion.queue;

import org.apache.commons.lang.Validate;

import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings({"unchecked"})
public class Deque<E> implements Iterable<E> {
    private static final int DEFAULT_SIZE = 0x10;

    private int head;
    private int tail;
    private E[] container;

    public Deque() {
        container = (E[]) new Object[DEFAULT_SIZE];
    }

    public Deque(int size) {
        int pof2 = 1;
        while ((pof2 = pof2 << 1) < size);
        container = (E[]) new Object[pof2];
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return head - tail & (container.length - 1);
    }

    public void addFirst(E item) {
        checkItem(item);
        container[head = (head + 1) & (container.length - 1)] = item;
        if (head == tail)
            resizeArray();
    }

    public void addLast(E item) {
        checkItem(item);
        container[tail] = item;
        if (head == (tail = (tail - 1) & (container.length - 1)))
            resizeArray();
    }

    public E removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        E e = container[head];
        container[head] = null;
        head = (head - 1) & (container.length - 1);
        return e;
    }

    public E removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        E e = container[tail = (tail + 1) & (container.length - 1)];
        container[tail] = null;
        return e;
    }

    public Iterator<E> iterator() {
        return new DequeIterator<E>();
    }

    private void checkItem(E item) {
        if (item == null)
            throw new NullPointerException("Item is null");
    }

    private void resizeArray() {
        Validate.isTrue(tail == head);
        E[] prev = container;
        container = (E[]) new Object[prev.length * 2];
        int splitIndex = ++head;
        int rightElements = prev.length - splitIndex;
        System.arraycopy(prev, splitIndex, container, 0, rightElements);
        System.arraycopy(prev, 0, container, rightElements, splitIndex);
        head = prev.length - 1;
        tail = container.length - 1;
    }

    private class DequeIterator<E> implements Iterator<E> {
        int current = tail;

        @Override
        public boolean hasNext() {
            return current != head;
        }

        @Override
        public E next() {
            return (E) container[current = (current + 1) & container.length - 1];
        }

        @Override
        public void remove() {
            if (current >= tail) {
                int n = current - tail; // elements after tail
                System.arraycopy(container, tail, container, tail = (tail + 1) & container.length - 1, n);
            } else {
                int n = head - current; // elements before head
                System.arraycopy(container, current + 1, container, current, n + 1);
                current = (current - 1) & container.length - 1;
                head = (head - 1) & (container.length - 1);
            }
        }
    }

}