package org.eslion.percolation;

import org.eslion.unionfind.UnionFind;
import org.eslion.unionfind.WeightedQuickUnion;

import java.util.Arrays;
import java.util.BitSet;

/**
 * <a href="http://coursera.cs.princeton.edu/algs4/assignments/percolation.html">details</a>
 */
public class Percolation {
    public static final int ADDITIONAL_SITES_COUNT = 2;

    private final int size;
    private final int upperVirtualSite;
    private final int downVirtualSite;
    private final UnionFind container;
    private final BitSet states;

    /**
     * create N-by-N grid, with all sites blocked
     *
     * @param size grid size
     */
    public Percolation(int size) {
        int sqSize = size * size;
        this.size = size;
        this.container = new WeightedQuickUnion(sqSize + ADDITIONAL_SITES_COUNT);
        this.states = new BitSet(sqSize);
        this.upperVirtualSite = sqSize;
        this.downVirtualSite = sqSize + 1;
    }

    /**
     * open site (row i, column j) if it is not already
     */
    public void open(int i, int j) {
        int pos = i + j * size;
        if (!states.get(pos)) {
            states.set(pos);
            join(pos, i - 1, j); //left
            join(pos, i + 1, j); //right
            join(pos, i, j - 1); //up
            join(pos, i, j + 1); //down
        }
    }

    private void join(int original, int i, int j) {
        if (i < 0 || i >= size)
            return;
        if (j < 0) {
            container.union(original, upperVirtualSite);
        } else if (j >= size) {
            container.union(original, downVirtualSite);
        } else if (isOpen(i, j)) {
            container.union(original, i + j * size);
        }
    }

    /**
     * is site (row i, column j) open?
     */
    public boolean isOpen(int i, int j) {
        return states.get(i + j * size);
    }

    /**
     * is site (row i, column j) full?
     */
    public boolean isFull(int i, int j) {
        return states.get(i + j * size) && container.connected(upperVirtualSite, i + j * size);
    }

    /**
     * does the system percolate?
     */
    public boolean percolates() {
        return container.connected(upperVirtualSite, downVirtualSite);
    }

    public static void main(String[] args) {
        System.out.println("test");
    }
}
