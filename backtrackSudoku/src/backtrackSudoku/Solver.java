package backtrackSudoku;

public class Solver {

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
		Puzzle puzz = new Puzzle(filename);
	}

}
