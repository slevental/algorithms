package org.eslion.puzzle;

import org.eslion.puzzle.Board;
import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class BoardTest {

    @Test
    public void testBoardIsGoal_isAGoal() throws Exception {
        Board board = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});
        assertTrue(board.isGoal());
    }

    @Test
    public void testBoardIsGoal_isNotAGoal() throws Exception {
        Board board = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 0, 8}});
        assertFalse(board.isGoal());
    }

    @Test
    public void testEqualsTest() throws Exception {
        Board board1 = new Board(new int[][]{{1, 2}, {1, 2}});
        Board board2 = new Board(new int[][]{{1, 2}, {1, 2}});
        assertTrue(board1.equals(board2));
        assertFalse(board1.equals(board2.twin()));
    }

    @Test
    public void testHamming() throws Exception {
        Board board = new Board(new int[][]{{3, 2, 1}, {4, 5, 6}, {7, 0, 8}});
        assertEquals(3, board.hamming());
    }

    @Test
    public void testTwin() throws Exception {
        Board board = new Board(new int[][]{{0, 0, 0}, {0, 0, 0}, {1, 2, 0}});
        assertEquals(new Board(new int[][]{{0, 0, 0}, {0, 0, 0}, {2, 1, 0}}), board.twin());
    }

    @Test
    public void testManhattan_zero() throws Exception {
        Board board = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});
        assertEquals(0, board.manhattan());
    }

    @Test
    public void testManhattan_4x4() throws Exception {
        Board board = new Board(new int[][]{{3, 2}, {1, 0}});
        assertEquals(2, board.manhattan());
    }

    @Test
    public void testManhattan_diagonal() throws Exception {
        Board board = new Board(new int[][]{{0, 2, 3}, {4, 5, 6}, {7, 8, 1}});
        assertEquals(4, board.manhattan());
    }

    @Test
    public void testManhattan_vertical() throws Exception {
        Board board = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 0, 8}});
        assertEquals(1, board.manhattan());
    }

    @Test
    public void testManhattan_horizontal() throws Exception {
        Board board = new Board(new int[][]{{1, 2, 6}, {4, 5, 0}, {7, 8, 3}});
        assertEquals(3, board.manhattan());
    }

    @Test
    public void testToString() throws Exception {
        Board board = new Board(new int[][]{{1, 2, 6, 9}, {4, 5, 0, 10}, {7, 8, 3, 11}, {12, 13, 14, 15}});
        System.out.println(board.toString());
    }

    @Test
    public void testNeighbors_center() throws Exception {
        Iterable<Board> neighbors = new Board(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}}).neighbors();
        assertNeighbors(neighbors,
                new Board(new int[][]{{1, 0, 1}, {1, 1, 1}, {1, 1, 1}}),
                new Board(new int[][]{{1, 1, 1}, {0, 1, 1}, {1, 1, 1}}),
                new Board(new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 1, 1}}),
                new Board(new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 0, 1}})
        );
    }

    @Test
    public void testNeighbors_left() throws Exception {
        Iterable<Board> neighbors = new Board(new int[][]{{1, 1, 1}, {0, 1, 1}, {1, 1, 1}}).neighbors();
        assertNeighbors(neighbors,
                new Board(new int[][]{{0, 1, 1}, {1, 1, 1}, {1, 1, 1}}),
                new Board(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}}),
                new Board(new int[][]{{1, 1, 1}, {1, 1, 1}, {0, 1, 1}})
        );
    }

    @Test
    public void testNeighbors_right() throws Exception {
        Iterable<Board> neighbors = new Board(new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 1, 1}}).neighbors();
        assertNeighbors(neighbors,
                new Board(new int[][]{{1, 1, 0}, {1, 1, 1}, {1, 1, 1}}),
                new Board(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}}),
                new Board(new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 0}})
        );
    }

    @Test
    public void corner() throws Exception {
        Iterable<Board> neighbors = new Board(new int[][]{{0, 1, 1}, {1, 1, 1}, {1, 1, 1}}).neighbors();
        assertNeighbors(neighbors,
                new Board(new int[][]{{1, 0, 1}, {1, 1, 1}, {1, 1, 1}}),
                new Board(new int[][]{{1, 1, 1}, {0, 1, 1}, {1, 1, 1}})
        );
    }

    private void assertNeighbors(Iterable<Board> actual, Board... expected) {
        List<Board> actualBoards = new ArrayList<Board>();
        for (Board eachActual : actual) {
            actualBoards.add(eachActual);
        }
        for (Board eachExpected : expected) {
            assertTrue("Not found: \n" + eachExpected + "\nActual: " + actualBoards, actualBoards.remove(eachExpected));
        }
        assertTrue(actualBoards.isEmpty());
    }
}
