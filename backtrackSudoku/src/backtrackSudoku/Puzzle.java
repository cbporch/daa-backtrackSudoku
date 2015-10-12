package backtrackSudoku;

public class Puzzle {

	int size, x, y;
	int sdk[][];
	// Linked List of cells?
	
	public Puzzle(int x, int y, int[][] sdk, Queue<Cell> cells){
		this.x = x;
		this.y = y;
		this.size = x * y;
		this.sdk = sdk;
		for(int i = 0; i < cells.numItems; i++){
			// reduce available cell options
			Cell c = cells.dequeue();
			c.reduceOptions(this);
			cells.enqueue(c);
		}
	}
	
}
