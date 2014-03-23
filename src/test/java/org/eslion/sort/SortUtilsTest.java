package org.eslion.sort;

import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

/**
 * Test my search operations
 */
public class SortUtilsTest {

    public static final int NUM = 1500;

    @Test
    public void testMergeSort() {
        List<Integer> integers = Arrays.asList(8, 7, 6, 5, 4, 3, 2, 1);
        SortUtils.mergeSort(integers);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8), integers);
    }

    @Test
    public void testQuickSort() {
        List<Integer> integers = Arrays.asList(8, 7, 6, 5, 4, 3, 2, 1);
        SortUtils.quickSort(integers);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8), integers);
    }

    @Test
    public void testParallelMergeSort() throws ExecutionException, InterruptedException {
        List<Integer> integers = Arrays.asList(8, 7, 6, 5, 4, 3, 2, 1, 8, 7, 6, 5, 4, 3, 2, 1, 8, 7, 6, 5, 4, 3, 2, 1);
        SortUtils.parallelMergeSort(integers);
        assertEquals(Arrays.asList(1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 8, 8, 8), integers);
    }

    @Test
    public void testPerformance() throws ExecutionException, InterruptedException {
        final Random rnd = new Random();
        List<Long> list1 = new ArrayList<Long>(NUM);
        List<Long> list2 = new ArrayList<Long>(NUM);
        for (int i = 0; i < NUM; i++) {
            long val = rnd.nextLong();
            list1.add(val);
            list2.add(val);
        }

        long mergeSortTime = System.currentTimeMillis();
        SortUtils.mergeSort(list1);
        mergeSortTime = System.currentTimeMillis() - mergeSortTime;

        long parallelMergeSortTime = System.currentTimeMillis();
        SortUtils.parallelMergeSort(list2);
        parallelMergeSortTime = System.currentTimeMillis() - parallelMergeSortTime;

        System.out.println("One thread : " + mergeSortTime + ", Multithread : " + parallelMergeSortTime);
    }
}
