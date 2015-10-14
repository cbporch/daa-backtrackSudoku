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

	public int[] getBlock(int r, int c) {
		int[] block = new int[size];
		int row = 0, col = 0;
		for (int i = 0; i <= c; i++) {
			if (i % x == 0) {
				col++;
			}
		}
		for (int i = 0; i <= r; i++) {
			if (i % y == 0) {
				row++;
			}
		}
		int x_offset = x * (row - 1);
		int y_offset = y * (col - 1);
		if(x_offset<0){
			x_offset = 0;
		}
		if(y_offset<0){
			y_offset = 0;
		}
		int count = 0;
		for (int j = x_offset; j < (x + x_offset); j++) {
			for (int k = y_offset; k < y + y_offset; k++) {
				block[count] = sdk[k][j];
				count++;
			}
		}
		return block;
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
		int l = cells.length();
		int tempArray[] = new int[l];
		ABQueue<Cell> tempCells= new ABQueue<Cell>(l);
		for(int i=0; i<l; i++){
			Cell temp = cells.dequeue();
			tempCells.enqueue(temp);
			cells.enqueue(temp);
		}
		for(int i=0; i<l; i++){
			tempArray[i] = cells.dequeue().getOptions().length();
		}
		int High = tempArray.length-1;
		quickSort(0,High,tempArray);
	} // end sortCells
	
	private static void quickSort(int Low, int High,int[] num)
    {
        int low = Low;
        int high = High;
        int pivot = num[Low + (High - Low) /2];
        while(low <= high)
            {
                while (num[low] < pivot)
                    {
                        low++;
                    }
                while (num[high] > pivot)
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

    private static void sort(int low, int high, int[] num)
    {
        int temp = num[low];
        num[low] = num[high];
        num[high] = temp;
    }
}
