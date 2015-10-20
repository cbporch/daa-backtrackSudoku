package backtrackSudoku;

import static org.junit.Assert.*;

import org.junit.Test;

public class SolverTest {

	int[][] testPuzzInts = new int[][]{
		{1,0,5,0,8,0,0,0,0},
		{3,0,0,4,1,0,0,0,8},
		{0,0,2,0,0,9,0,1,7},
		{0,0,0,0,7,0,9,0,1},
		{0,0,9,0,3,6,8,0,0},
		{0,1,8,5,0,0,0,0,3},
		{5,0,1,8,0,7,3,0,6},
		{7,0,6,9,5,0,0,0,0},
		{0,0,0,0,2,3,1,0,5}};
	Cell tempCell = new Cell(0,0,0);
	Cell cells[] = {tempCell};
	Puzzle testPuzz = new Puzzle(3,3,testPuzzInts,cells,1);
	Solver solve = new Solver(testPuzz);

	@Test
	public void testClonePuzzle() {
		int[][] ints = new int[][]{
			{1,2,3,4},
			{1,2,3,4},
			{1,2,3,4},
			{1,2,3,4}};
		int[][] tempInts = solve.clonePuzzle(ints);
		//solve.listPuzzle(ints);
		//solve.listPuzzle(tempInts);
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
			Cell tempCell = new Cell(0,0,0);
		Cell cells[] = {tempCell};
		Puzzle falsePuzz = new Puzzle(2,2,falseInts, cells,1);
		Puzzle truePuzz = new Puzzle(2,2,trueInts, cells,1);
		assertFalse(solve.checkBlocks(falsePuzz));
		assertTrue(solve.checkBlocks(truePuzz));
	}
	
	@Test
	public void testSolver(){
		int[][] testPuzzInts = new int[][]{
			{1,0,5,0,8,0,0,0,0},
			{3,0,0,4,1,0,0,0,8},
			{0,0,2,0,0,9,0,1,7},
			{0,0,0,0,7,0,9,0,1},
			{0,0,9,0,3,6,8,0,0},
			{0,1,8,5,0,0,0,0,3},
			{5,0,1,8,0,7,3,0,6},
			{7,0,6,9,5,0,0,0,0},
			{0,0,0,0,2,3,1,0,5}};
		Puzzle testPuzz = new Puzzle(3,3,testPuzzInts);
		Cell cells[] = testPuzz.makeCells(testPuzzInts);
		testPuzz = new Puzzle(3,3,testPuzzInts,cells,cells.length);
		Solver solve = new Solver(testPuzz);
		assertTrue(solve.solvePuzzle(testPuzz, testPuzz.getCells(), 0));
	}
	
	@Test
	public void testB1()
	{
		int[][] testPuzzInts = new int[][]{
		{0,0,0,1,0,6},
		{6,0,4,0,0,0},
		{1,0,2,0,0,0},
		{0,0,0,5,0,1},
		{0,0,0,6,0,3},
		{5,0,6,0,0,0}};
		Puzzle testPuzz = new Puzzle(3,2,testPuzzInts);
		Cell cells[] = testPuzz.makeCells(testPuzzInts);
		testPuzz = new Puzzle(3,2,testPuzzInts,cells,cells.length);
		Solver solve = new Solver(testPuzz);
		assertTrue(solve.solvePuzzle(testPuzz, testPuzz.getCells(), 0));
	}
	
	@Test
	public void testB3()
	{
		int[][] testPuzzInts = new int[][]{
			{8,1,0,0,0,3,0,0,9},
			{0,0,3,6,0,0,0,0,0},
			{0,7,0,0,9,0,2,0,0},
			{0,5,0,0,0,7,0,0,6},
			{0,0,0,0,4,5,7,0,0},
			{0,0,0,1,0,0,0,3,0},
			{0,0,1,0,0,0,0,6,8},
			{4,3,8,5,0,0,0,1,0},
			{0,9,0,0,0,0,4,0,0}};
		Puzzle testPuzz = new Puzzle(3,3,testPuzzInts);
		Cell cells[] = testPuzz.makeCells(testPuzzInts);
		testPuzz = new Puzzle(3,3,testPuzzInts,cells,cells.length);
		Solver solve = new Solver(testPuzz);
		assertTrue(solve.solvePuzzle(testPuzz, testPuzz.getCells(), 0));
	}
	
	@Test
	public void testB5()
	{
		int[][] testPuzzInts = new int[][]{
			{0,5,0,0,0,0,0,0,7},
			{0,7,8,0,0,4,0,0,6},
			{0,0,0,0,0,1,8,0,0},
			{0,0,0,0,1,0,7,5,4},
			{0,0,5,4,0,0,3,0,0},
			{0,0,0,9,8,0,6,0,1},
			{7,0,3,1,0,8,0,0,0},
			{4,0,0,0,0,9,0,0,0},
			{0,1,9,0,0,6,0,0,8}};
		Puzzle testPuzz = new Puzzle(3,3,testPuzzInts);
		Cell cells[] = testPuzz.makeCells(testPuzzInts);
		testPuzz = new Puzzle(3,3,testPuzzInts,cells,cells.length);
		Solver solve = new Solver(testPuzz);
		assertTrue(solve.solvePuzzle(testPuzz, testPuzz.getCells(), 0));
	}
	
	@Test
	public void testBU()
	{
		int[][] testPuzzInts = new int[][]{
			{1,2,8,0,0,3,0,0,9},
			{0,9,5,0,1,0,0,0,0}, 
			{0,0,0,1,0,6,0,0,0}, 
			{0,0,9,2,0,0,0,0,0},
			{0,3,0,0,0,0,5,8,0},
			{0,8,0,0,0,5,0,0,0},
			{0,0,0,0,3,8,0,0,0},
			{0,0,7,5,6,0,0,9,0},
			{0,0,0,9,7,2,6,5,0}};
		Puzzle testPuzz = new Puzzle(3,3,testPuzzInts);
		Cell cells[] = testPuzz.makeCells(testPuzzInts);
		testPuzz = new Puzzle(3,3,testPuzzInts,cells,cells.length);
		Solver solve = new Solver(testPuzz);
		assertFalse(solve.solvePuzzle(testPuzz, testPuzz.getCells(), 0));
	}
}
