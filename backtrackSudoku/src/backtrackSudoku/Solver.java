package backtrackSudoku;

/*
 * Contains methods for solving a given Puzzle.
 * Do we need this? - Chris
 */
public class Solver {
	public static Puzzle currentPuzzle;
	public static int w = 0, h = 0, size = 0;
	public static Timer tim;

	Solver(Puzzle puzz) {
		currentPuzzle = puzz;
		w = puzz.getX();
		h = puzz.getY();
		size = puzz.getSize();
	}

	public static void Solve() {
		tim = new Timer();
		tim.start();

		int[][] tempPuzz = clonePuzzle(currentPuzzle.getGrid());
		int tempRow[] = new int[size];
		int tempCol[] = new int[size];
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

		tim.stop();
		System.out.println("Total Runtime: " + tim.getDuration() + " milliseconds");
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

	public static void listPuzzle(int[][] puzz) {
		for (int x = 0; x < (size); x++) {
			for (int y = 0; y < (size); y++) {
				System.out.print(puzz[x][y] + " ");
			} // end for
			System.out.print("\n");
		} // end for
	}// end listPuzzle
}
