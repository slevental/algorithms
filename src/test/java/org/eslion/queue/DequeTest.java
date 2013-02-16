package org.eslion.queue;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static junit.framework.Assert.*;

public class DequeTest {

    public static final int N = 20;
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
    public void testSize_checkSizeAfterGrow() throws Exception {
        for (int i = 0; i < N; i++) {
            q.addFirst("1");
        }
        assertEquals(N, q.size());
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
        for (int i = 0; i < 100; i++) {
            q.addFirst("1");
            assertEquals("1", q.removeLast());
        }
    }

    @Test
    public void testIterator_hasNextTest() throws Exception {
        assertNotNull(q.iterator());
        assertFalse(q.iterator().hasNext());
        q.addLast("1");
        assertTrue(q.iterator().hasNext());
    }

    @Test
    public void testIterator_next() throws Exception {
        q.addLast("1");
        q.addFirst("1");
        int counter = 0;
        for (String each : q) {
            counter++;
            assertEquals("1", each);
        }
        assertEquals(2, counter);
    }

    @Test
    public void testIterator_remove() throws Exception {
        q.addFirst("1");
        q.addFirst("2");
        Iterator<String> i = q.iterator();
        assertTrue(i.hasNext());
        assertEquals("1", i.next());
        assertTrue(i.hasNext());
        assertEquals("2", i.next());
        assertFalse(i.hasNext());
        i.remove();
        assertEquals(1, q.size());
    }

    @Test
    public void testAddDeleteAndThenAddAgain() throws Exception {
        q.addFirst("1");
        q.addLast("2");
        q.removeFirst();
        q.removeFirst();
        q.isEmpty();
        q.addLast("3");
        assertEquals("3", q.removeFirst());
    }

    @Test
    public void testIterator_removeTailLessThanCurrent() throws Exception {
        q.addFirst("1");
        q.addFirst("2");
        q.addFirst("3");
        for (Iterator<String> i = q.iterator(); i.hasNext(); i.next()) {
            i.remove();
        }
        assertTrue(q.isEmpty());
    }

    @Test
    public void testIterator_removeTailGraterThanCurrent() throws Exception {
        q.addLast("2");
        q.addLast("1");
        q.addFirst("3");
        Iterator<String> i = q.iterator();
        for (; i.hasNext(); i.next()) {
        }
        i.remove();
        assertEquals(2, q.size());
        assertEquals("2", q.removeFirst());
        assertEquals("1", q.removeFirst());
    }

    @Test
    public void testIterator_checkElementsAfterRemove() throws Exception {
        q.addFirst("3");
        q.addFirst("2");
        q.addFirst("1");
        Iterator<String> i = q.iterator();
        assertEquals("3", i.next());
        i.remove();
        assertEquals("2", i.next());
        assertEquals("1", i.next());
        assertFalse(i.hasNext());
        assertEquals(2, q.size());
    }

    @Test
    public void testIterator_currentPointerOnLeftArrayEdge() throws Exception {
        q.addLast("1");
        Iterator<String> i = q.iterator();
        assertEquals("1", i.next());
        i.remove();
        assertFalse(i.hasNext());
        assertTrue(q.isEmpty());
    }

    @Test
    public void testIterator_currentPointerOnRightArrayEdge() throws Exception {
        q.addLast("2");
        q.addLast("1");
        Iterator<String> i = q.iterator();
        assertEquals("1", i.next());
        assertEquals("2", i.next());
        i.remove();
        assertFalse(i.hasNext());
        assertEquals("1", q.removeFirst());
        assertTrue(q.isEmpty());
    }

    @Test
    public void testAddLastRemoveFirst_inLoop() throws Exception {
        for (int i = 0; i < 100; i++) {
            q.addLast("1");
            assertEquals("1", q.removeFirst());
        }
    }

    @Test
    public void testAddFirstRemoveLast_fewElements() throws Exception {
        for (int i = 0; i < 10; i++) {
            q.addFirst(String.valueOf(i));
        }
        for (int i = 0; i < 10; i++) {
            assertEquals(i, Integer.parseInt(q.removeLast()));
        }
    }

    @Test
    public void testAddLstRemoveFirst_fewElements() throws Exception {
        for (int i = 0; i < 10; i++) {
            q.addLast(String.valueOf(i));
        }
        for (int i = 0; i < 10; i++) {
            assertEquals(i, Integer.parseInt(q.removeFirst()));
        }
    }

    @Test
    public void testSizeGrowTest_bothDirections() throws Exception {
        for (int i = 0; i < N; i++) {
            q.addFirst("1");
            q.addLast("2");
        }
        for (int i = 0; i < N; i++) {
            assertEquals("1", q.removeFirst());
            assertEquals("2", q.removeLast());
        }
    }

    @Test
    public void testSizeGrowTest_tailDirection() throws Exception {
        for (int i = 0; i < N; i++) {
            q.addLast("2");
        }
        for (int i = 0; i < N; i++) {
            assertEquals("2", q.removeLast());
        }
    }

    @Test
    public void testSizeGrowTest_headDirection() throws Exception {
        for (int i = 0; i < N; i++) {
            q.addFirst("2");
        }
        for (int i = 0; i < N; i++) {
            assertEquals("2", q.removeFirst());
        }
    }

    @Test
    public void testSizeGrowTest_headDirectionCheckFromTail() throws Exception {
        for (int i = 0; i < N; i++) {
            q.addFirst("2");
        }
        for (int i = 0; i < N; i++) {
            assertEquals("2", q.removeLast());
        }
    }

    @Test
    public void testSizeGrowTest_tailDirectionCheckFromHead() throws Exception {
        for (int i = 0; i < N; i++) {
            q.addLast("2");
        }
        for (int i = 0; i < N; i++) {
            assertEquals("2", q.removeFirst());
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
