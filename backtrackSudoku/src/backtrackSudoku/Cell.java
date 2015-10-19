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

public class Cell {

	private int col;
	private int row;
	private ABQueue<Integer> options;

	public Cell(int x, int y, int size) {
		this.col = y;
		this.row = x;
		options = new ABQueue<Integer>(size);
		for (int i = 1; i <= size; i++) {
			options.enqueue(i);
		}
	}

	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}

	public ABQueue<Integer> getOptions() {
		return options;
	}

	/*
	 * For a given puzzle, reduce available options based on location
	 */
	public void reduceOptions(Puzzle p) {
		int pSize = p.getSize();
		ABQueue<Integer> newOptions = new ABQueue<Integer>(pSize);
		for (int i = 1; i <= (pSize); i++) {
			newOptions.enqueue(i);
		}

		int temp[] = p.getRow(row);
		int OptLen = newOptions.length();
		boolean dup;
		for (int i = 0; i < OptLen; i++) {
			dup = false;
			int option = newOptions.dequeue();
			for (int j = 0; j < pSize; j++) {
				if (option == temp[j]) {
					dup = true;
					j = (j + 2) * pSize;
				}
			}
			if (!dup) {
				newOptions.enqueue(option);
			}
		}

		temp = p.getColumn(col);
		OptLen = newOptions.length();
		for (int i = 0; i < OptLen; i++) {
			dup = false;
			int option = newOptions.dequeue();
			for (int j = 0; j < pSize; j++) {
				if (option == temp[j]) {
					dup = true;
					j = (j + 2) * pSize;
				}
			}
			if (!dup) {
				newOptions.enqueue(option);
			}
		}

		temp = p.getBlock(col, row);
		OptLen = newOptions.length();
		for (int i = 0; i < OptLen; i++) {
			dup = false;
			int option = newOptions.dequeue();
			for (int j = 0; j < pSize; j++) {
				if (option == temp[j]) {
					dup = true;
					j = (j + 2) * pSize;
				}
			}
			if (!dup) {
				newOptions.enqueue(option);
			}
		}
		options = newOptions;

	}
}
