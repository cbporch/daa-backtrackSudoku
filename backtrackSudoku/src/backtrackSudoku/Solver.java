package backtrackSudoku;

/*
 * Contains methods for solving a given Puzzle.
 */
public class Solver {
	public static Puzzle currentPuzzle;
	public static int w = 0, h = 0, size = 0;
	

	Solver(Puzzle puzz) {
		currentPuzzle = puzz;
		w = puzz.getX();
		h = puzz.getY();
		size = puzz.getSize();
	}

	public static void Solve() {
		
		int[][] tempPuzz = clonePuzzle(currentPuzzle.getGrid());
		int tempRow[] = new int[size];
		int tempCol[] = new int[size];
		// test block
		for (int i = 0; i < size; i++) {
			for(int j = 0;j < size; j++)
			{
			tempRow = tempPuzz[i];
			tempCol[j] = tempPuzz[j][i];
			}
			System.out.println("Row " + (i+1) + " = " + checkRowColumn(tempRow));
			System.out.println("Column " + (i+1) + " = " + checkRowColumn(tempCol));
		}

		listPuzzle(tempPuzz);

		System.out.println(solvePuzzle(currentPuzzle, currentPuzzle.getCells()));
		
	}

	private static boolean solvePuzzle(Puzzle puzz, ABQueue<Cell> cellList){
		if(cellList.isEmpty()){
			// no more cells, check solution
			return checkPuzzle(puzz);
		}else if(cellList.peek().getOptions().isEmpty()){
			//cell has no viable option
			return false;
		}else{
			Cell c = cellList.dequeue();
			c.reduceOptions(puzz);
			if(c.getOptions().isEmpty()){
				System.out.println();
				listPuzzle(puzz.getGrid());
				return false;
			}
			int sdk[][] = puzz.getGrid();
			sdk[c.getX()][c.getY()] = c.getOptions().dequeue();
			puzz.setGrid(sdk);
			ABQueue<Cell> temp = cellList;
			return solvePuzzle(puzz, temp);
		}
	}
	
 	public static int[][] clonePuzzle(int[][] puzz) {
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
				test[nums[i]]++;
			} else {
				return false;
			}
		}
		return true;
	}// end CheckRowColumn

	public static boolean checkBlocks(Puzzle puzz) {
		int size = puzz.getSize(), w = puzz.getX(), h = puzz.getY();
		int arr[][] = puzz.getGrid();
		int x_offset = 0, y_offset = 0, point = 0;
		boolean pass = false;
		int temparray[] = new int[size];
		for (int i = 0; i < size; i++) {
			point = 0;
			for (int j = x_offset; j < (w + x_offset); j++) {
				for (int k = y_offset; k < h + y_offset; k++) {
					temparray[point] = arr[j][k];
					point++;
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
	 * checkBlocks methods. returns true if the puzzle is a valid
	 * solution, false if not.
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
			}// end if
			return false;
		
	}// end checkPuzzle
	
	public static void listPuzzle(int[][] puzz) {
		for (int x = 0; x < (size); x++) {
			for (int y = 0; y < (size); y++) {
				System.out.print(puzz[x][y] + " ");
			} // end for
			System.out.print("\n");
		} // end for
	}// end listPuzzle
}
