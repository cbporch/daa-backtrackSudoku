package backtrackSudoku;

public class Cell {
	
	int x;
	int y;
	Queue<Integer> options[];
	
	public Cell(int x, int y, int size) {
		this.x = x;
		this.y = y;
		options = new Queue[size];
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Queue<Integer>[] getOptions(){
		return options;
	}
	
	/*
	 * For a given puzzle, reduce available options based on location
	 */
	public void reduceOptions(Puzzle p){
		
	}
}
