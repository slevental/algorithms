package org.eslion.unionfind;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

@RunWith(value = Parameterized.class)
public class UnionFindTest {

    AbstractUnionFind unionFind;

    public UnionFindTest(AbstractUnionFind uf) {
        unionFind = uf;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{{new QuickFind(10)}, {new QuickUnion(10)}, {new WeightedQuickUnion(10)}};
        return Arrays.asList(data);
    }

    @Before
    public void setUp() throws Exception {
        unionFind.clear();
    }

    @Test
    public void testFind_falseCase() throws Exception {
        UnionFind fastFind = new QuickUnion(10);
        assertFalse(fastFind.connected(1, 2));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testFind_illegalCase() throws Exception {
        unionFind.connected(1, 100);
    }

    @Test
    public void testFind_trueCase() throws Exception {
        assertFalse(unionFind.connected(1, 2));
        unionFind.union(1, 2);
        assertTrue(unionFind.connected(1, 2));
    }

    @Test
    public void testFind_transitiveCase() throws Exception {
        unionFind.union(1, 2);
        unionFind.union(3, 4);
        unionFind.union(2, 3);
        assertTrue(unionFind.connected(1, 4));
        assertTrue(unionFind.connected(1, 3));
        assertTrue(unionFind.connected(2, 3));
        assertTrue(unionFind.connected(1, 2));
    }

    @Test
    public void testFind_symmetricCase() throws Exception {
        unionFind.union(1, 2);
        assertTrue(unionFind.connected(1, 2));
        assertTrue(unionFind.connected(2, 1));
    }

    @Test
    public void testOut() throws Exception {
        unionFind.union(9, 4);
        unionFind.union(5, 0);
        unionFind.union(5, 2);
        unionFind.union(1, 7);
        unionFind.union(7, 3);
        unionFind.union(6, 0);
        unionFind.union(7, 9);
        unionFind.union(2, 8);
        unionFind.union(8, 3);
        System.out.println(unionFind.toString());
    }
}
