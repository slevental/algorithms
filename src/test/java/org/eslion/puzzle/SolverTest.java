package org.eslion.puzzle;

import org.eslion.puzzle.Board;
import org.eslion.puzzle.Solver;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class SolverTest {

    @Test
    public void testSolve() throws Exception {
        Board board = new Board(new int[][]{{2, 3}, {1, 0}});
        Solver s = new Solver(board);
        for (Board each : s.solution()) {
            System.out.println(each);
        }
    }

    @Test
    public void testSolve_3x3() throws Exception {
        Board board = new Board(new int[][]{{2, 1, 3}, {4, 5, 6}, {8, 7, 0}});
        Solver s = new Solver(board);
        assertTrue(s.isSolvable());
        for (Board each : s.solution()) {
            System.out.println(each);
        }
    }

    @Test
    public void testSolve_4x4() throws Exception {
        Board board = new Board(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {0, 13, 14, 15}});
        Solver s = new Solver(board);
        assertTrue(s.isSolvable());
        for (Board each : s.solution()) {
            System.out.println(each);
        }
    }

    @Test
    public void testSolvable() throws Exception {
        Board board = new Board(new int[][]{{3, 2}, {1, 0}});
        Solver s = new Solver(board);
        assertFalse(s.isSolvable());
    }
}
