package backtrackSudoku;

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
	}

	public ABQueue(int length) {
		items = (T[]) new Object[length];
		back = items.length - 1;
		front = 0;
		numItems = 0;
	}

	public boolean isEmpty() {
		return numItems == 0;
	}

	public void enqueue(T newItem) throws QueueException {
		if (numItems == items.length) {
			resize();
		}

		if (numItems == 0) {
			items[front] = newItem;
			back++;
			numItems++;

		} else {
			back = (back + 1) % (items.length);
			items[back] = newItem;
			numItems++;
		}
	}

	public T dequeue() throws QueueException {
		T item = null;
		if (numItems != 0) {
			item = items[front];
			items[front] = null;
			front = (++front) % items.length;
			numItems--;

		} else {
			throw new QueueException("Queue is empty");
		}
		return item;
	}

	public void dequeueAll() {
		int l = items.length;
		items = (T[]) new Object[l];
		front = 0;
		back = items.length - 1;
		numItems = 0;
	}

	public T peek() throws QueueException {
		T item = null;
		if (numItems != 0) {
			item = items[front];
		} else if (numItems == 0) {
			item = null;
		} else {
			throw new QueueException("Queue is empty");
		}
		return item;
	}

	public int length() {
		return numItems;
	}

	protected void resize() {
		T[] temp = (T[]) new Object[(items.length * 2) + 1];
		for (int i = 0; i < numItems; i++) {
			temp[i] = items[(front + i) % items.length];
		} // end for
		front = 0;
		back = items.length - 1;
		items = (T[]) temp;
	}

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
			}
		} // end for

		if (numItems == 0) {
			s = "Queue is empty.\n";
		}

		return s;

	}// end toString
}// end ABQueue
