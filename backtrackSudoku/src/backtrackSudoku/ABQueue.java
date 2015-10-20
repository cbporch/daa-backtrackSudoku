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

public class ABQueue<T> {
	private T data;
	private T[] items;
	private int front, back, numItems;

	public ABQueue(T data, int length) {
		this.data = data;
		items = (T[]) new Object[length];
		back = items.length - 1;
		front = 0;
		numItems = 0;
	} // end constructor

	public ABQueue(int length) {
		items = (T[]) new Object[length];
		back = items.length - 1;
		front = 0;
		numItems = 0;
	} // end constructor

	public boolean isEmpty() {
		return numItems == 0;
	}// end isEmpty

	public void enqueue(T newItem) throws QueueException {
		if (numItems == items.length) {
			resize();
		} // end if

		if (numItems == 0) {
			items[front] = newItem;
			back++;
			numItems++;

		} else {
			back = (back + 1) % (items.length);
			items[back] = newItem;
			numItems++;
		}// end if else
	} // end enqueue

	public T dequeue() throws QueueException {
		T item = null;
		if (numItems != 0) {
			item = items[front];
			items[front] = null;
			front = (++front) % items.length;
			numItems--;

		} else {
			throw new QueueException("Queue is empty");
		}// end if else
		return item;
	} // end dequeue

	public void dequeueAll() {
		int l = items.length;
		items = (T[]) new Object[l];
		front = 0;
		back = items.length - 1;
		numItems = 0;
	} // end dequeue all

	public T peek() throws QueueException {
		T item = null;
		if (numItems != 0) {
			item = items[front];
		} else if (numItems == 0) {
			item = null;
		} else {
			throw new QueueException("Queue is empty");
		}// end if else
		return item;
	} // end peek

	public int length() {
		return numItems;
	} // end length

	protected void resize() {
		T[] temp = (T[]) new Object[(items.length * 2) + 1];
		for (int i = 0; i < numItems; i++) {
			temp[i] = items[(front + i) % items.length];
		} // end for
		front = 0;
		back = items.length - 1;
		items = (T[]) temp;
	} // end resize

	public String toString() {
		String s = "";
		Object o = null;
		int l = items.length, i = front;
		for (int j = 0; j < numItems; j++) {
			if (items[i] != null) {
				o = items[i];
				s += "\t";
				s += o;
				s += "\n";
				i = (i + 1) % l;
			}// end if
		} // end for

		if (numItems == 0) {
			s = "Queue is empty.\n";
		}// end if

		return s;

	}// end toString
}// end ABQueue
