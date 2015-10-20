package backtrackSudoku;

/*
 *  Project: Backtracking Sudoku Solver
 *  Class: Design and Analysis of Algorithms
 *  Professor Lobo
 *  Authors:	Christopher Porch <porchc0@students.rowan.edu>
 *  			Dan Boehmke <boehmked2@students.rowan.edu>
 *  			Brian Grillo <grillo88@students.rowan.edu>
 *  version: 2015.10.18
 */

@SuppressWarnings("serial")
public class QueueException extends RuntimeException {

	public QueueException(String s) {
		super(s);
	} // end constructor
} // end QueueException
