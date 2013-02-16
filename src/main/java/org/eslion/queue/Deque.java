package org.eslion.queue;

import org.apache.commons.lang.Validate;

import java.util.Arrays;
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

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return head - tail;
    }

    public void addFirst(E item) {
        checkItem(item);
        int h = (head + 1) & (container.length - 1);
        container[h] = item;
        head = h;
        if (head == tail)
            grow();
    }

    public void addLast(E item) {
        checkItem(item);
        int t = (tail - 1) & (container.length - 1);
        container[tail] = item;
        tail = t;
        if (head == tail)
            grow();
    }

    public E removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        int h = (head - 1) & (container.length - 1);
        E e = container[head];
        container[head] = null;
        head = h;
        return e;
    }

    public E removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        int t = (tail + 1) & (container.length - 1);
        E e = container[t];
        container[t] = null;
        tail = t;
        return e;
    }

    public Iterator<E> iterator() {
        return null;
    }

    private void checkItem(E item) {
        if (item == null)
            throw new NullPointerException("Item is null");
    }

    private void grow() {
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

    @Override
    public String toString() {
        return Arrays.toString(container);
    }
}