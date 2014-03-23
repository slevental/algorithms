package org.eslion.percolation;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class PercolationTest {
    private Percolation p;

    @Before
    public void setUp() throws Exception {
        p = new Percolation(3);
    }

    @Test
    public void testOpen() throws Exception {
        assertFalse(p.isOpen(0, 0));
        p.open(0, 0);
        assertTrue(p.isOpen(0, 0));
    }

    @Test
    public void testFull() throws Exception {
        assertFalse(p.isFull(0, 0));
        p.open(0, 0);
        assertTrue(p.isFull(0, 0));
    }

    @Test
    public void testFromCenter() throws Exception {
        p.open(1, 1);
        assertTrue(p.isOpen(1, 1));
        assertFalse(p.isFull(1, 1));
        p.open(0, 0);
        assertFalse(p.isFull(1, 1));
        p.open(0, 1);
        assertTrue(p.isFull(1, 1));
        assertTrue(p.isFull(0, 0));
        assertTrue(p.isFull(0, 1));
    }

    @Test
    public void testIsFull_backwash() throws Exception {
        p.open(0, 0);
        p.open(0, 1);
        p.open(0, 2);
        assertTrue(p.percolates());
        p.open(2, 2);
        assertFalse(p.isFull(2, 2));
    }

    @Test
    public void testPercolation() throws Exception {
        p.open(1, 1);
        assertFalse(p.percolates());
        p.open(0, 0);
        assertFalse(p.percolates());
        p.open(2, 2);
        assertFalse(p.percolates());
        p.open(2, 1);
        assertFalse(p.percolates());
        p.open(0, 1);
        assertTrue(p.percolates());
    }
}
