package backtrackSudoku;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Puzzle {

	int size, x, y;
	int sdk[][];
	
	public Puzzle(int x, int y){
		this.x = x;
		this.y = y;
		this.size = x * y;
		sdk = new int[size][size];
	}
}
