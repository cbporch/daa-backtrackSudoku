package backtrackSudoku;

public class Cell {
	
	int x;
	int y;
	int options[];
	
	public Cell(int x, int y, int size) {
		this.x = x;
		this.y = y;
		options = new int[size];
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int[] getOptions(){
		return options;
	}
	
	/*
	 * For a given puzzle, reduce available options based on location
	 */
	public void reduceOptions(Puzzle p){
		
	}
}
