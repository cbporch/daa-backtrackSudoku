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
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Driver {
	public static Timer tim;

	/*
	 * Method main initializes all file reading and contains the program timer.
	 * Boolean test used to ignore this sectionn while running JUnit
	 */
	public static void main(String[] args) {
		// start Timer
		tim = new Timer();
		tim.start();
		boolean test = false;
		if (!test) {
			if (args.length < 1) {
				System.out.print("Error: need file name in command line\n");
			} // end if
			String filename = "";
			try {
				filename = args[0];
			} catch (Exception e) {
				System.out.print("Error reading from file.\n" + e.toString());
				throw e;
			} // end try catch
				// initialize puzzle and call Solver class solve method
			Solver solve = new Solver(readFile(filename));
			solve.Solve();
		} // end if
		tim.stop();
		System.out.println("Total Runtime: " + tim.getDuration() + " milliseconds");
	}// end main

	/*
	 * Using a given filename, reads from a file using BufferedReader, and
	 * parses that input into a Puzzle object.
	 * 
	 * Parameters: String filename Returns: Puzzle
	 */
	private static Puzzle readFile(String filename) {
		int w = 0, h = 0, size = 0;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filename));
			String line = skipComments(br);
			w = Integer.parseInt(line);
			line = skipComments(br);
			h = Integer.parseInt(line);
		} catch (Exception e) {
			System.out.println("Error reading from file\n");
		} // end try catch
		size = w * h;
		Cell[] cells = new Cell[size * size]; // use size simply for
												// initialization
		int sdk[][] = new int[size][size];
		int cellCount = 0;
		// Use regex to pull numbers out of the string read in by
		// BufferedReader br.readLine() and populate 2D array sdk[][]
		// counts number of zeros in the base file
		Pattern p = Pattern.compile("\\d+");
		for (int x = 0; x < (size); x++) {
			int y = 0;
			String line = null;
			try {
				line = skipComments(br);
			} catch (IOException e) {
			}
			Matcher m = p.matcher(line);

			while (m.find() && y < (size)) {
				sdk[x][y] = Integer.parseInt(m.group());
				// System.out.print(sdk[x][y] + " ");
				if (sdk[x][y] == 0) {
					// adds cells to cell queue
					Cell cell = new Cell(x, y, size);
					cells[cellCount] = cell;
					cellCount++;
				} // end if
				y++;
			} // end while
		} // end for

		Puzzle puzz = new Puzzle(w, h, sdk, cells, cellCount);
		return puzz;
	}// end readFile

	/*
	 * Method to skip comments in the given input file. If the given line begins
	 * with a 'c' the line is skipped until a line that does not start with a
	 * 'c' is reached, then returns that line.
	 */
	private static String skipComments(BufferedReader br) throws IOException {
		String line = "";
		try {
			line = br.readLine();
			while (line.trim().substring(0, 1).equals("c")) {
				// line is a comment or empty, skip
				line = br.readLine();
			} // end while
		} catch (Exception e) {
			System.out.println("Error reading from file\n");
		} // end try-catch
		return line;
	}// end skipComments
}// end Driver
