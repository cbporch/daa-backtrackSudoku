package backtrackSudoku;

public class Cell {
	
	int x;
	int y;
	
	public Cell(int x, int y, int size) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
