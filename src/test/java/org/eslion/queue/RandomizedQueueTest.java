package org.eslion.queue;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static junit.framework.Assert.*;

public class RandomizedQueueTest {

    private RandomizedQueue<String> q;

    @Before
    public void setUp() throws Exception {
        q = new RandomizedQueue<String>();
    }

    @Test
    public void testSize_forEmptyQueue() throws Exception {
        assertEquals(0, q.size());
        assertTrue(q.isEmpty());
    }

    @Test
    public void testSample() throws Exception {
        q.enqueue("1");
        q.enqueue("1");
        assertEquals("1", q.sample());
        assertEquals("1", q.dequeue());
        assertEquals("1", q.dequeue());
        assertTrue(q.isEmpty());
    }

    @Test
    public void testIterator() throws Exception {
        Set<String> container = new HashSet<String>();
        Random random = new Random();
        for (int i = 0; i < 1000; i++){
            String el = String.valueOf(random.nextLong());
            container.add(el);
            q.enqueue(el);
        }
        for (String each : q) {
            container.remove(each);
        }
        assertTrue(container.isEmpty());
    }

    @Test
    public void testSize_forNonEmptyQueue() throws Exception {
        q.enqueue("1");
        assertEquals(1, q.size());
        assertFalse(q.isEmpty());
        q.enqueue("2");
        q.enqueue("3");
        assertEquals(3, q.size());
    }

    @Test
    public void testEnqueue_enqueueAndDequeue() throws Exception {
        q.enqueue("1");
        assertEquals("1", q.dequeue());
        assertTrue(q.isEmpty());
    }

    @Test
    public void testEnqueue_enqueueFewElementsAndDequeueThem() throws Exception {
        for (int i = 0; i < 10; i++)
            q.enqueue("1");
        for (int i = 0; i < 10; i++)
            assertEquals("1", q.dequeue());
        assertTrue(q.isEmpty());
    }

    @Test
    public void testEnqueue_enqueueRandomElements() throws Exception {
        Random random = new Random();
        Set<String> container = new HashSet<String>();
        for (int i = 0; i < 100; i++){
            String el = String.valueOf(random.nextLong());
            q.enqueue(el);
            container.add(el);
        }
        while (!q.isEmpty()){
            String d = q.dequeue();
            assertTrue("Element \'" + d + "\' not found", container.remove(d));
        }
        assertTrue(q.isEmpty());
        assertTrue(container.isEmpty());
    }
}
