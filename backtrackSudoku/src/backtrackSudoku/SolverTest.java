package backtrackSudoku;

import static org.junit.Assert.*;

import org.junit.Test;

public class SolverTest {

	Solver solve;

	public SolverTest(){
		
	}

	@Test
	public void testSolver() {
		fail("Not yet implemented");
	}

	@Test
	public void testSolve() {
		fail("Not yet implemented");
	}

	@Test
	public void testClonePuzzle() {
		fail("Not yet implemented");
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
		fail("Not yet implemented");
	}

	@Test
	public void testListPuzzle() {
		fail("Not yet implemented");
	}

}
