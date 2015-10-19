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
public class Solver {
	public Puzzle currentPuzzle;
	public static int w = 0, h = 0, size = 0;

	Solver(Puzzle puzz) {
		currentPuzzle = puzz;
		w = puzz.getX();
		h = puzz.getY();
		size = puzz.getSize();
	}

	public void Solve() {
		
		int[][] tempPuzz = clonePuzzle(currentPuzzle.getGrid());
		int tempRow[] = new int[size];
		int tempCol[] = new int[size];
		// test block
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				tempRow = tempPuzz[i];
				tempCol[j] = tempPuzz[j][i];
			}
			System.out.println("Row " + (i + 1) + " = " + checkRowColumn(tempRow));
			System.out.println("Column " + (i + 1) + " = " + checkRowColumn(tempCol));
		}
		System.out.println("\nEntered Puzzle:");
		listPuzzle(tempPuzz);
		if (solvePuzzle(currentPuzzle, currentPuzzle.getCells(), 0)) {
			System.out.println("Solved");
		} else {
			System.out.println("Unsolvable");
		}
	}

	static boolean solvePuzzle(Puzzle puzz, Cell[] cellList, int curr) {
		int sdk[][] = puzz.getGrid();
		Cell c = cellList[curr];

		System.out.println();
		listPuzzle(puzz.getGrid());
		if (curr == puzz.getCellCount()) {
			// no more cells, check solution for duplicates
			System.out.println();
			listPuzzle(puzz.getGrid());
			return checkPuzzle(puzz);
		}
		// reduce current option
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
				}
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
					}
				}
			}
		}
		sdk[c.getRow()][c.getCol()] = 0;
		puzz.setGrid(sdk);
		return false;
	}

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

	public static boolean checkRowColumn(int[] nums) {
		int[] test = new int[nums.length + 1];
		for (int i = 0; i < nums.length; i++) {
			if (test[nums[i]] == 0) {
				if (nums[i] != 0) { // allows for zeros
					test[nums[i]]++;
				}
			} else {
				return false;
			}
		}
		return true;
	}// end CheckRowColumn

	public static boolean checkBlocks(Puzzle puzz) {
		int size = puzz.getSize(), w = puzz.getX(), h = puzz.getY();
		int arr[][] = puzz.getGrid();
		int x_offset = 0, y_offset = 0;
		boolean pass = false;
		int temparray[] = new int[size];
		for (int i = 0; i < size; i++) {
			int xcount = 0, ycount = 0;
			for (int j = 0; j < size; j++) {
				// arr[column y ][row x]
				temparray[j] = arr[ycount + y_offset][xcount + x_offset];
				xcount++;
				if (xcount % puzz.getX() == 0) {
					ycount++;
					xcount = 0;
				}
			}

			pass = checkRowColumn(temparray);
			if (x_offset < size) {
				x_offset += w;
				if (x_offset == size) {
					x_offset = 0;
					y_offset += h;
					if (y_offset == size) {
						// checked all blocks
						return pass;
					}
				}
			}
		}
		return false;
	}// end checkBlocks

	/*
	 * Checks the given puzzle for correctness, using the checkRowColumn and
	 * checkBlocks methods. returns true if the puzzle is a valid solution,
	 * false if not.
	 */
	public static boolean checkPuzzle(Puzzle puzz) {
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

	public static void listPuzzle(int[][] puzz) {
		size = puzz.length;
		for (int x = 0; x < (size); x++) {
			for (int y = 0; y < (size); y++) {
				System.out.print(puzz[x][y] + " ");
			} // end for
			System.out.print("\n");
		} // end for
	}// end listPuzzle
}
