package backtrackSudoku;

public class Cell {
	
	private int x;
	private int y;
	private ABQueue<Integer> options;
	
	public Cell(int x, int y, int size) {
		this.x = x;
		this.y = y;
		options = new ABQueue<Integer>(size);
		for(int i = 1; i <= size; i++){
			options.enqueue(i);
		}
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public ABQueue<Integer> getOptions(){
		return options;
	}
	
	/*
	 * For a given puzzle, reduce available options based on location
	 */
	public void reduceOptions(Puzzle p){
		if(!options.isEmpty()){	
			int temp[] = p.getRow(x);
			int L1 = temp.length;
			
			int OptLen = options.length();
			for(int i = 0; i < OptLen; i++){
				int option = options.peek();
				for(int j = 0; j < L1; j++){
					if(option == temp[j]){
						options.dequeue();
						j = j * L1;
					}
				}
			}
			
			temp = p.getColumn(x);
			L1 = temp.length;
			
			OptLen = options.length();
			for(int i = 0; i < OptLen; i++){
				int option = options.peek();
				for(int j = 0; j < L1; j++){
					if(option == temp[j]){
						options.dequeue();
						j = j * L1;
					}
				}
			}
			
			temp = p.getBlock(x,y);
			L1 = temp.length;
			
			OptLen = options.length();
			for(int i = 0; i < OptLen; i++){
				int option = options.peek();
				for(int j = 0; j < L1; j++){
					if(option == temp[j]){
						options.dequeue();
						j = j * L1;
					}
				}
			}
		}
	}
}
