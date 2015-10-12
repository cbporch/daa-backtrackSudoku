package backtrackSudoku;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Driver {

	public static void main(String[] args) {

		if(args.length == 0){
			System.out.print("Error: need file name in command line");
		}
		
		String filename = "";
		
		try{
			filename = args[0];
		}catch(Exception e){
			System.out.print("Error reading from file.");
		}
		
		Puzzle puzz = readFile(filename);
	}
	
	private static Puzzle readFile(String filename){
		int w = 0, h = 0;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line = skipComments(br);
			w = Integer.parseInt(line);
			line = skipComments(br);
			h = Integer.parseInt(line);
		} catch (Exception e) {
			System.out.println("Error reading from file");
		} // end try catch
		Puzzle puzz = new Puzzle(w, h);
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
			System.out.println("Error reading from file");
		}// end try-catch
		return line;
	}// end skipComments

}
