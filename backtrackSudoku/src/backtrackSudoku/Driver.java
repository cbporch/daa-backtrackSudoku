package backtrackSudoku;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Driver handles all file input
 */
public class Driver {
	public static Timer tim;
	public static void main(String[] args) {
		tim = new Timer();
		tim.start();

		boolean test = false;
		if(!test){
			if(args.length < 1){
				System.out.print("Error: need file name in command line\n");
			}
			
			String filename = "";
			
			try{
				filename = args[0];
			}catch(Exception e){
				System.out.print("Error reading from file.\n" + e.toString());
				throw e;
			}
			
			Puzzle puzz = readFile(filename);
			
			int t[] = puzz.getBlock(1, 1);
			int t2[] = puzz.getBlock(0, 1);
			boolean pass = false;
			for(int i = 0; i < t.length; i++){
				
				pass = (t[i] == t2[i]);
				System.out.println(t[i] + " " + t2[i] + " " + pass);
			}
			System.out.println();
			Solver solve = new Solver(puzz);
			solve.Solve();
			
		}else{
			
		}
		
		tim.stop();
		System.out.println("Total Runtime: " + tim.getDuration() + " milliseconds");
	}
	
	/*
	 * Using a given filename, reads from a file using BufferedReader,
	 * and parses that input into a Puzzle object.
	 * 
	 * Parameters: String filename
	 * Returns: Puzzle
	 */
	private static Puzzle readFile(String filename){
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
		Cell[] cells = new Cell[size*size]; //use size simply for initialization
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
				//System.out.print(sdk[x][y] + " ");
				if (sdk[x][y] == 0) {
					//adds cells to cell queue
					Cell cell = new Cell(x, y, size);
					cells[cellCount] = cell;
					cellCount++;
				} // end if
				y++;
			} // end while
			//System.out.println("");

		} // end for
		
		Puzzle puzz = new Puzzle(w, h, sdk, cells, cellCount);
		return puzz;
	}
	
	/*
	 * Method to skip comments in the given input file. If the given line
	 * begins with a 'c' the line is skipped until a line that does not start
	 * with a 'c' is reached, then returns that line.
	 */
	private static String skipComments(BufferedReader br) throws IOException {
		String line = "";
		try {
			line = br.readLine();
			while (line.trim().substring(0, 1).equals("c")) {
				// line is a comment, skip
				line = br.readLine();
			} // end while
		} catch (Exception e) {
			System.out.println("Error reading from file\n");
		}// end try-catch
		return line;
	}// end skipComments

}
