package backtrackSudoku;

import static org.junit.Assert.*;

import org.junit.Test;

public class PuzzleTest {
	Puzzle puzzle;

	@Test
	public void testClonePuzzle() {
		int[][] ints = new int[][]{
			{1,2,3,4},
			{1,2,3,4},
			{1,2,3,4},
			{1,2,3,4}};
		puzzle.getBlock(2, 2);	
	}
}
