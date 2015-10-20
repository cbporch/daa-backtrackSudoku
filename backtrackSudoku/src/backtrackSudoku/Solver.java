package backtrackSudoku;

/*
 *  Project: Backtracking Sudoku Solver
 *  Class: Design and Analysis of Algorithms
 *  Professor Lobo
 *  Authors:	Christopher Porch <porchc0@students.rowan.edu>
 *  			Dan Boehmke <boehmked2@students.rowan.edu>
 *  			Brian Grillo <grillo88@students.rowan.edu>
 *  version: 2015.10.20
 */
public class Solver {
	public Puzzle currentPuzzle;
	public int w = 0, h = 0, size = 0;

	Solver(Puzzle puzz) {
		currentPuzzle = puzz;
		w = puzz.getX();
		h = puzz.getY();
		size = puzz.getSize();
	}

	/*
	 * Public call-able solve method, also adds some Print statements for
	 * formatting output to be more readable.
	 */
	public void Solve() {
		System.out.println("\nEntered Puzzle:");
		listPuzzle(currentPuzzle.getGrid());
		if (solvePuzzle(currentPuzzle, currentPuzzle.getCells(), 0)) {
			System.out.println("Solved.");
		} else {
			System.out.println("Unsolvable");
		}
	}

	/*
	 * Recursive method to check a given puzzle for correctness. Base case 1:
	 * Reached end of cellList, all previous cells correct Base case 2: current
	 * cell has no remaining options, return false to check parents other
	 * subtrees
	 * 
	 * If both base cases are skipped, enter a for loop that will increment
	 * through a cells options, setting the cells first option in the puzzle,
	 * and then calling this method for its children (testing that there are
	 * still no duplicates in the puzzle) and incrementing to the next option if
	 * any of its children fail (or the next option didn't work).
	 * 
	 * If all a cells options fail, it resets its position to a zero, so as not
	 * to disrupt another cells reduceCell call.
	 * 
	 * Input: The current Puzzle, its list of cells, and a counter to iterate
	 * through that list.
	 * 
	 * Output: a boolean value, True for a valid Puzzle, false otherwise
	 */
	protected boolean solvePuzzle(Puzzle puzz, Cell[] cellList, int curr) {
		int sdk[][] = puzz.getGrid();
		if (curr == puzz.getCellCount()) {
			// no more cells, check solution for duplicates
			System.out.println();
			listPuzzle(puzz.getGrid());
			return checkPuzzle(puzz);
		} // end if
			// reduce current option
		Cell c = cellList[curr];
		c.reduceOptions(puzz);
		if (c.getOptions().isEmpty()) {
			// cell has no viable option
			sdk[c.getRow()][c.getCol()] = 0;
			puzz.setGrid(sdk);
			return false;
		} else {
			int L = c.getOptions().length();
			for (int i = 0; i < L; i++) {
				try {
					sdk[c.getRow()][c.getCol()] = c.getOptions().peek();
					puzz.setGrid(sdk);
				} catch (Exception e) {
					// peek failed, cell has no more options
					sdk[c.getRow()][c.getCol()] = 0;
					puzz.setGrid(sdk);
					return false;
				} // end try catch
					// check option
				if (checkPuzzle(puzz) && solvePuzzle(puzz, cellList, curr + 1) && !puzz.hasZeros()) {
					// option worked
					return true;
				} else {
					try {
						c.getOptions().dequeue();
					} catch (Exception e) {
						// no other options
						sdk[c.getRow()][c.getCol()] = 0;
						puzz.setGrid(sdk);
						return false;
					} // end try catch
				} // end if else
			} // end for
		} // end if else
		sdk[c.getRow()][c.getCol()] = 0;
		puzz.setGrid(sdk);
		return false;
	}// end solvePuzzle

	/*
	 * clonePuzzle returns a deep copy of a given 2D int array
	 */
	public int[][] clonePuzzle(int[][] puzz) {
		int size = puzz.length;
		int tempSDK[][] = new int[size][size];
		for (int x = 0; x < (size); x++) {
			for (int y = 0; y < (size); y++) {
				tempSDK[x][y] = puzz[x][y];
			} // end for
		} // end for
		return tempSDK;
	}// end clonePuzzle

	/*
	 * Checks a given array of integers for duplicates
	 */
	public boolean checkRowColumn(int[] nums) {
		int[] test = new int[nums.length + 1];
		for (int i = 0; i < nums.length; i++) {
			if (test[nums[i]] == 0) {
				if (nums[i] != 0) { // allows for zeros
					test[nums[i]]++;
				} // end if
			} else {
				return false;
			} // end if else
		} // end for
		return true;
	}// end CheckRowColumn

	/*
	 * Splits a puzzle's blocks into 1D arrays, then checks each using
	 * checkRowColumn method
	 */
	public boolean checkBlocks(Puzzle puzz) {
		int size = puzz.getSize(), w = puzz.getX(), h = puzz.getY();
		int arr[][] = puzz.getGrid();
		int x_offset = 0, y_offset = 0;
		boolean pass = false;
		int temparray[] = new int[size];
		// for each block in the puzzle
		for (int i = 0; i < size; i++) {
			int xcount = 0, ycount = 0;
			// go across each row, then increment ycount
			// to move to the next row
			for (int j = 0; j < size; j++) {
				// array[column y][row x]
				temparray[j] = arr[ycount + y_offset][xcount + x_offset];
				xcount++;
				if (xcount % puzz.getX() == 0) {
					ycount++;
					xcount = 0;
				} // end if
			} // end for

			// check for duplicates
			pass = checkRowColumn(temparray);

			// adjust offsets to start at the top of the next block
			// row by column
			if (x_offset < size) {
				x_offset += w;
				if (x_offset == size) {
					x_offset = 0;
					y_offset += h;
					if (y_offset == size) {
						// checked all blocks
						return pass;
					} // end if
				} // end if
			} // end if
		} // end for
		return false;
	}// end checkBlocks

	/*
	 * Checks the given puzzle for correctness, using the checkRowColumn and
	 * checkBlocks methods. returns true if the puzzle is a valid solution,
	 * false if not.
	 */
	public boolean checkPuzzle(Puzzle puzz) {
		boolean pass = false;
		int temp[][] = puzz.getGrid();
		int tempRow[] = new int[size];
		int tempCol[] = new int[size];
		for (int i = 0; i < (size); i++) {
			for (int j = 0; j < (size); j++) {
				tempCol[j] = temp[j][i]; // pulls numbers from each column
			} // end for

			tempRow = temp[i];

			if ((checkRowColumn(tempRow)) && (checkRowColumn(tempCol))) {
				pass = true;
			} else {
				// fail, exit loop
				return false;
			} // end if else
		} // end for

		if (pass) {// if all rows/columns pass
			pass = checkBlocks(puzz);
			return pass;
		} // end if
		return false;

	}// end checkPuzzle

	public void listPuzzle(int[][] puzz) {
		for (int x = 0; x < (size); x++) {
			for (int y = 0; y < (size); y++) {
				System.out.print(puzz[x][y] + " ");
			} // end for
			System.out.print("\n");
		} // end for
	}// end listPuzzle
}// end Solver
