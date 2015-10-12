package backtrackSudoku;

/**
 * This class provides a simple mechanism to time a computation
 * After creating a Timer object, call it's start method before
 * the computation being timed is started. After the computation
 * finishes, call the Timer object's stop method. 
 * The method getDuration computes the time taken by the computation
 * 
 * @author Dr. Andrea Lobo
 * @author Dr. Ganesh R. Baliga 
 * @author Kenneth Janes
 * 
 * This material is based upon work supported by 
 * the National Science Foundation under Grant No. 1140753.
 * 
 */

public class Timer {

	long start, end;

	public void start ( ) {
		start = System.currentTimeMillis();

	}

	public void stop ( ) {
		end = System.currentTimeMillis ( );

	}

	// Return duration in milliseconds
	public long getDuration ( ) {
		return end - start;
	}
}