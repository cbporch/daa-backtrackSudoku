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

public class Puzzle {
	private int size, x, y;
	private int sdk[][];
	private Cell cells[];
	private int cellCount;

	public Puzzle(int x, int y, int[][] sdk, Cell[] cells, int cellCount) {
		this.x = x;
		this.y = y;
		this.size = x * y;
		this.sdk = sdk;
		this.cells = cells;
		this.cellCount = cellCount;
		for (int i = 0; i < cells.length; i++) {
			// reduce available cell options
			if (cells[i] != null) {
				Cell c = cells[i];
				c.reduceOptions(this);
				cells[i] = c;
			} // end if
		} // end for
	}// end Puzzle constructor

	public Puzzle(int x, int y, int[][] sdk) {
		this.x = x;
		this.y = y;
		this.size = x * y;
		this.sdk = sdk;
		this.cellCount = 0;
	} // end Puzzle constructor

	/*
	 * Accessor method for a given row
	 */
	public int[] getRow(int r) {
		return sdk[r];
	}// end getRow

	/*
	 * Accessor method for a given column
	 */
	public int[] getColumn(int c) {
		int col[] = new int[size];
		for (int i = 0; i < size; i++) {
			col[i] = sdk[i][c];
		} // end for
		return col;
	}// end getColumn

	/*
	 * Accessor method for celCount
	 */
	public int getCellCount() {
		return cellCount;
	} // end getCellCount

	/*
	 * Accessor method for size
	 */
	public int getSize() {
		return size;
	}// end getSize

	/*
	 * Accessor method for x
	 */
	public int getX() {
		return x;
	}// end getX

	/*
	 * Accessor method for y
	 */
	public int getY() {
		return y;
	}// end getY

	/*
	 * Accessor method for the 2D array
	 */
	public int[][] getGrid() {
		return sdk;
	}// end getGrid

	/*
	 * Mutator method for grid
	 */
	public void setGrid(int grid[][]) {
		sdk = grid;
	}// end setGrid

	/*
	 * Accessor method for the array of Cells
	 */
	public Cell[] getCells() {
		return cells;
	}// end getCells

	/*
	 * Accessor method for a specific block, given a coordinate inside that
	 * block
	 */
	public int[] getBlock(int c, int r) {
		int[] block = new int[size];

		int x_offset = 0, y_offset = 0;
		for (int row = r; row > 0; row--) {
			if ((row % y) == 0) {
				y_offset = row;
				// break loop
				row = 0;
			} // end if
		} // end for

		for (int col = c; col > 0; col--) {
			if ((col % x) == 0) {
				x_offset = col;
				// break loop
				col = 0;
			} // end if
		} // end for

		int xcount = 0, ycount = 0;
		for (int j = 0; j < size; j++) {
			block[j] = sdk[ycount + y_offset][xcount + x_offset];
			xcount++;
			if (xcount % x == 0) {
				ycount++;
				xcount = 0;
			} // end if
		} // end for
		return block;
	} // end getBlock

	/*
	 * Checks if the Puzzle's 2D int array has any zeros in it
	 */
	public boolean hasZeros() {
		int j = 0;
		for (int i = 0; i < size; i++) {
			if (sdk[i][j] == 0) {
				return true;
			} // end if

			if ((i + 1) == size) {
				j++;
				i = 0;
			} // end if

			if (j == size) {
				i = size;
			} // end if
		} // end for
		return false;
	}// end hasZeros

	/*
	 * Creates cell objects for a given 2D Array for each zero in the array, a
	 * Cell is created and added to the Cell array
	 */
	public Cell[] makeCells(int[][] sdk) {
		int cellCount = 0;
		Cell[] tempCells = new Cell[size * size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				// System.out.println(sdk[i][j] + " ");
				if (sdk[i][j] == 0) {
					// adds cells to cell queue
					Cell cell = new Cell(i, j, size);
					tempCells[cellCount] = cell;
					cellCount++;
				} // end if
			} // end for
		} // end for
		this.cellCount = cellCount;
		Cell[] returnCells = new Cell[cellCount];
		for (int p = 0; p < cellCount; p++) {
			returnCells[p] = tempCells[p];
		} // end for
		return returnCells;
	}// end makeCells

	/*
	 * Sorts the list of Cells by the number of options they contain, in
	 * increasing order
	 */
	public void sortCells() {
		int High = cellCount - 1;
		quickSort(0, High, cells);
	} // end sortCells

	/*
	 * Quicksort method, called by sortCells
	 */
	private static void quickSort(int Low, int High, Cell[] num) {
		int low = Low;
		int high = High;
		int pivot = num[Low + (High - Low) / 2].getOptions().length();
		while (low <= high) {
			while (num[low].getOptions().length() < pivot) {
				low++;
			} // end while

			while (num[high].getOptions().length() > pivot) {
				high--;
			} // end while

			if (low <= high) {
				sort(low, high, num);
				low++;
				high--;
			} // end if
		} // end while

		if (Low < high) {
			quickSort(Low, high, num);
		} // end if

		if (low < High) {
			quickSort(low, High, num);
		} // end if
	}// end quickSort

	/*
	 * Swap method, used by quickSort
	 */
	private static void sort(int low, int high, Cell[] num) {
		Cell temp = num[low];
		num[low] = num[high];
		num[high] = temp;
	}// end sort
}// end Puzzle