package backtrackSudoku;

import static org.junit.Assert.*;

import org.junit.Test;

public class SolverTest {

	Solver solve;

	@Test
	public void testClonePuzzle() {
		int[][] ints = new int[][]{
			{1,2,3,4},
			{1,2,3,4},
			{1,2,3,4},
			{1,2,3,4}};
		int[][] tempInts = solve.clonePuzzle(ints);
		solve.listPuzzle(ints);
		solve.listPuzzle(tempInts);
		assertArrayEquals(ints,tempInts);
	}

	@Test
	public void testCheckRowColumn() {
		int passRow[] = { 1, 2, 3, 4, 5, 6 };
		int failRow[] = { 6, 2, 3, 4, 5, 6 };

		assertTrue(solve.checkRowColumn(passRow));
		assertFalse(solve.checkRowColumn(failRow));
	}

	@Test
	public void testCheckBlocks() {
		int[][] falseInts = new int[][]{
			{1,2,3,4},
			{1,2,3,4},
			{1,2,3,4},
			{1,2,3,4}};
		int[][] trueInts = new int[][]{
			{2,3,1,4},
			{4,1,3,2},
			{3,2,4,1},
			{1,4,2,3}};
		Queue tempQueue = new Queue(0);
		Puzzle falsePuzz = new Puzzle(2,2,falseInts,tempQueue);
		Puzzle truePuzz = new Puzzle(2,2,trueInts,tempQueue);
		assertFalse(solve.checkBlocks(falsePuzz));
		assertTrue(solve.checkBlocks(truePuzz));
	}
}
