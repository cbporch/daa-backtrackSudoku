package backtrackSudoku;

/*
 *  Project: Backtracking Sudoku Solver
 *  Class: Design and Analysis of Algorithms
 *  Professor Lobo
 *  Authors:	Christopher Porch <porchc0@students.rowan.edu>
 *  			Dan Boehmke <boehmked2@students.rowan.edu>
 *  			Brian Grillo <grillo88@students.rowan.edu>
 *  version: 2015.10.18
 */

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
		//sortCells();
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
				// break loop
				row = 0;
			}
		}
		
		for(int col = c; col > 0; col--){
			if((col % x) == 0){
				x_offset = col;
				// break loop
				col =  0;
			}
		}
		
		int xcount = 0, ycount = 0;
		for(int j = 0; j < size; j++){
			block[j] = sdk[ycount + y_offset][xcount + x_offset];
			xcount++;
			if(xcount % x == 0){
				ycount++;
				xcount = 0;
			}
		}
		return block;
	}

	public boolean hasZeros(){
		for(int i = 0; i<size; i++){
			for(int j = 0; j < size; j++){
				if(sdk[i][j] == 0){
					return true;
				}
			}
		}
		return false;
	}
	public void sortCells() {
//		int l = cells.length();
//		if (l > 1) {
//			Cell greatest = null, next = null;
//			for (int i = 0; i < l; i++) {
//				greatest = cells.dequeue();
//				next = cells.peek();
//				if (greatest.getOptions().length() <= next.getOptions().length()) {
//					cells.enqueue(greatest);
//					greatest = next;
//					next = cells.peek();
//				}
//			}
//		}
		//int l = cells.length;
		//Cell tempArray[] = new Cell[l];
		
		//for(int i = 0; i<l; i++){
			//tempArray[i] = cells[i];
		//}
		int High = cellCount-1;
		quickSort(0,High,cells);
	} // end sortCells
	
	private static void quickSort(int Low, int High,Cell[] num)
    {
        int low = Low;
        int high = High;
        int pivot = num[Low + (High - Low) /2].getOptions().length();
        while(low <= high)
            {
                while (num[low].getOptions().length() < pivot)
                    {
                        low++;
                    }
                while (num[high].getOptions().length() > pivot)
                    {
                        high--;
                    }
                if (low <= high)
                    {
                        sort(low, high, num);
                        low++;
                        high--;
                    }
            }
        if (Low < high)
            {
                quickSort(Low, high, num);
            }
        if (low < High)
            {
                quickSort(low, High, num);
            }
    }

    private static void sort(int low, int high, Cell[] num)
    {
        Cell temp = num[low];
        num[low] = num[high];
        num[high] = temp;
    }
}