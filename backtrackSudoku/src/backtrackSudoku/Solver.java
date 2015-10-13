package backtrackSudoku;

/*
 * Contains methods for solving a given Puzzle.
 * Do we need this? - Chris
 */
public class Solver {
	Puzzle currentPuzzle;

	Solver(Puzzle puzz) {
		currentPuzzle = puzz;

		int[] testList = { 6, 2, 3, 4, 5, 6 };
		System.out.println(checkRowColumn(testList));
	}

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
}
