package backtrackSudoku;

public class Puzzle {

	private int size, x, y;
	private int sdk[][];
	private Cell cells[];
	private int cellCount;
	
	public Puzzle(int x, int y, int[][] sdk, Cell[] cells, int cellCount) {
		this.x = x;
		this.y = y;
		this.size = x * y;
		this.sdk = sdk;
		this.cells = cells;
		this.cellCount = cellCount; 
		for (int i = 0; i < cells.length; i++) {
			// reduce available cell options
			if(cells[i] != null){
				Cell c = cells[i];
				c.reduceOptions(this);
				cells[i] = c;
			}
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

	public int getCellCount(){
		return cellCount;
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

	public Cell[] getCells() {
		return cells;
	}

	public int[] getBlock(int c, int r) {
		int[] block = new int[size];

		int x_offset = 0, y_offset = 0;
		for(int row = r; row > 0; row--){
			if((row % y) == 0){
				y_offset = row;
			}
		}
		
		for(int col = c; col > 0; col--){
			if((col % x) == 0){
				x_offset = col;
			}
		}
		
//		int count = 0;
//		for (int j = x_offset; j < (y + x_offset); j++) {
//			for (int k = y_offset; k < x + y_offset; k++) {
//				block[count] = sdk[j][k];
//				count++;
//			}
//		}
		int xcount = 0, ycount = 0;
		for(int j = 0; j < size; j++){
			block[j] = sdk[ycount + y_offset][xcount + x_offset];
			xcount++;
			if(xcount % x == 0 && ycount % y == 0){
				ycount++;
				xcount = 0;
			}
		}
		return block;
	}

	public void sortCells() {

	} // end sortCells
}
