package backtrackSudoku;

public class Cell {
	
	int x;
	int y;
	ABQueue<Integer> options;
	
	public Cell(int x, int y, int size) {
		this.x = x;
		this.y = y;
		options = new ABQueue<Integer>(size);
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
		int temp[] = p.getRow(x);
		for(int i = 0; i < temp.length; i++){
			int a = temp[i];
			int l = options.length();
			for(int j = 0; j < l; j++){
				if(a == options.peek()){
					options.dequeue();
				}
			}
		}
		temp = p.getColumn(y);
		for(int i = 0; i < temp.length; i++){
			int a = temp[i];
			int l = options.length();
			for(int j = 0; j < l; j++){
				if(a == options.peek()){
					options.dequeue();
				}
			}
		}
		//temp = p.getBlock(x,y);
		//for(int i = 0; i < temp.length; i++){
		//	int a = temp[i];
		//	int l = options.length();
		//	for(int j = 0; j < l; j++){
		//		if(a == options.peek()){
		//			options.dequeue();
		//		}
		//	}
		//}
	}
}
