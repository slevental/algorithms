package org.eslion.trees;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class RedBlackTreeTest {

    private RedBlackTree<String,String> tree;

    @Before
    public void setUp() throws Exception {
        tree = new RedBlackTree<String, String>();
    }

    @Test
    public void testPut_putFewElementsAndGetThem() throws Exception {
        tree.put("2", "two");
        tree.put("5", "five");
        tree.put("1", "one");
        tree.put("4", "four");
        tree.put("3", "three");
        assertEquals("one", tree.get("1"));
        assertEquals("two", tree.get("2"));
        assertEquals("three", tree.get("3"));
        assertEquals("four", tree.get("4"));
        assertEquals("five", tree.get("5"));
    }

    @Test
    public void test() throws Exception {

    }
}
