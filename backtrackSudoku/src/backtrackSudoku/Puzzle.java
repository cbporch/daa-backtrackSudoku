package backtrackSudoku;

public class Puzzle {

	private int size, x, y;
	private int sdk[][];
	ABQueue<Cell> cells;

	public Puzzle(int x, int y, int[][] sdk, ABQueue<Cell> cells) {
		this.x = x;
		this.y = y;
		this.size = x * y;
		this.sdk = sdk;
		this.cells = cells;
		for (int i = 0; i < cells.length(); i++) {
			// reduce available cell options
			Cell c = cells.dequeue();
			c.reduceOptions(this);
			cells.enqueue(c);
		}
		// sortCells();
	}

	public int[] getRow(int r) {
		return sdk[r];
	}

	public int[] getColumn(int c) {
		int col[] = new int[size];
		for (int i = 0; i < size; i++) {
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

	public void setGrid(int grid[][]) {
		sdk = grid;
	}

	public ABQueue<Cell> getCells() {
		return cells;
	}

	public int[] getBlock(int r, int c) {
		int[] block = new int[size];

		int x_offset = 0, y_offset = 0;
		for(int row = r; row > 0; row--){
			if((row % y) == 0){
				x_offset = row;
			}
		}
		
		for(int col = c; col > 0; col--){
			if((col % x) == 0){
				y_offset = col;
			}
		}
		
		int count = 0;
		for (int j = x_offset; j < (y + x_offset); j++) {
			for (int k = y_offset; k < x + y_offset; k++) {
				block[count] = sdk[j][k];
				count++;
			}
		}
		return block;
	}

	public void sortCells() {

	} // end sortCells
}
