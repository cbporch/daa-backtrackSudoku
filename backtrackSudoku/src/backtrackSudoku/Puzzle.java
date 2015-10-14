package backtrackSudoku;

public class Puzzle {

	private int size, x, y;
	private int sdk[][];
	// Linked List of cells?
	
	public Puzzle(int x, int y, int[][] sdk, Queue<Cell> cells){
		this.x = x;
		this.y = y;
		this.size = x * y;
		this.sdk = sdk;
		for(int i = 0; i < cells.numItems; i++){
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
	
	public int[] getBlock(int r, int c){
		int[] block = new int[size];
		int row = 0,col = 0;
		for (int i=0; i <= c; i++){
			if(i%y == 0)
			{
				col++;
			}
		}
		for (int i=0; i <= r; i++){
			if(i%x == 0){
				row++;
			}
		}
		int x_offset = x*(row-1);
		int y_offset = y*(col-1);
		int count = 0;
		for (int j = x_offset; j < (x + x_offset); j++) {
			for (int k = y_offset; k < y + y_offset; k++) {
				block[count] = sdk[j][k];
				count++;
			}
		}
		return block;
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
}
