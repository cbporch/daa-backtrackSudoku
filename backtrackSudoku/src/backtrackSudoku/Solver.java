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
}
