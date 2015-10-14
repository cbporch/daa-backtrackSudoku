package backtrackSudoku;

public class Puzzle {

	private int size, x, y;
	private int sdk[][];
	ABQueue<Cell> cells;
	
	public Puzzle(int x, int y, int[][] sdk, ABQueue<Cell> cells){
		this.x = x;
		this.y = y;
		this.size = x * y;
		this.sdk = sdk;
		this.cells = cells;
		for(int i = 0; i < cells.length(); i++){
			// reduce available cell options
			Cell c = cells.dequeue();
			
			// Possibly Not Ready
			//c.reduceOptions(this);
			//cells.enqueue(c);
		}
	}
	
	public int[] getRow(int r){
		return sdk[r];
	}
	
	public int[] getColumn(int c){
		int col[] = new int[size];
		for(int i = 0; i < size;i++){
			col[i] = sdk[i][c];
		}
		return col;
	}

	public int getSize() {
		return size;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int[][] getGrid() {
		return sdk;
	}
	
	public void  sortCells(){
		
	}
}
