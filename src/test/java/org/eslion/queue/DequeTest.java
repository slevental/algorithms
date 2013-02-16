package org.eslion.queue;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class DequeTest {

    private Deque<String> q;

    @Before
    public void setUp() throws Exception {
        q = new Deque<String>();
    }

    @Test
    public void testSize_addSomeAndCheckSize() throws Exception {
        assertEquals(0, q.size());
        assertTrue(q.isEmpty());
        q.addFirst("1");
        assertEquals(1, q.size());
    }

    @Test
    public void testSize_addSomeAndRemove() throws Exception {
        q.addFirst("1");
        q.addFirst("2");
        assertEquals(2, q.size());
        q.removeFirst();
        assertEquals(1, q.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void testSize_removeFromEmptyQueue() throws Exception {
        q.removeFirst();
    }

    @Test
    public void testSize_checkSizeAfterExceptionThrown() throws Exception {
        Exception ex = null;
        try {
            q.removeFirst();
        } catch (Exception e) {
            ex = e;
        }
        assertNotNull(ex);
        assertTrue(q.isEmpty());
        assertEquals(0, q.size());
    }

    @Test(expected = NullPointerException.class)
    public void testAddFisrt_null() throws Exception {
        q.addFirst(null);
    }

    @Test
    public void testAddFirst_addAndGet() throws Exception {
        q.addFirst("1");
        assertEquals("1", q.removeFirst());
    }

    @Test
    public void testAddFirst_addAndGet2Elements() throws Exception {
        q.addFirst("1");
        q.addFirst("2");
        assertEquals("2", q.removeFirst());
        assertEquals("1", q.removeFirst());
    }

    @Test
    public void testAddFirstRemoveLast_inLoop() throws Exception {
        for (int i = 0; i < 100; i++){
            q.addFirst("1");
            assertEquals("1", q.removeLast());
        }
    }

    @Test
    public void testAddLastRemoveFirst_inLoop() throws Exception {
        for (int i = 0; i < 100; i++){
            q.addLast("1");
            assertEquals("1", q.removeFirst());
        }
    }

    @Test
    public void testAddFirstRemoveLast_fewElements() throws Exception {
        for (int i = 0; i < 10; i++){ q.addFirst(String.valueOf(i)); }
        for (int i = 0; i < 10; i++){ assertEquals(i, Integer.parseInt(q.removeLast())); }
    }

    @Test
    public void testAddLstRemoveFirst_fewElements() throws Exception {
        for (int i = 0; i < 10; i++){ q.addLast(String.valueOf(i)); }
        for (int i = 0; i < 10; i++){ assertEquals(i, Integer.parseInt(q.removeFirst())); }
    }

    @Test
    public void testSizeGrowTest() throws Exception {
        for (int i = 0; i < 20; i++){ q.addFirst("1"); q.addLast("2"); }
        for (int i = 0; i < 20; i++){
            assertEquals("1", q.removeFirst());
            assertEquals("2", q.removeLast());
        }
    }

    @Test
    public void testAddLast_addAndGet() throws Exception {
        q.addLast("1");
        q.addLast("2");
        assertEquals("2", q.removeLast());
        assertEquals("1", q.removeLast());
    }

    @Test(expected = NullPointerException.class)
    public void testAddLast_null() throws Exception {
        q.addLast(null);
    }

}
