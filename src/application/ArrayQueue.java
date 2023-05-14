package application;

public class ArrayQueue<T> {
	private T[] q;
	private int first = -1;
	private int last = -1;
	private int count = 0;

	@SuppressWarnings("unchecked")
	public ArrayQueue(int capacity) {
		q = (T[]) new Object[capacity];
	}

	public void enqueue(T data) {
		if (count < q.length) {
			last = ++last % q.length;
			q[last] = data;
			count++;
			if (count == 1)
				first = last;
		} else {
			System.out.println("Queue is full!");
		}
	}

	public T dequeue() {
		if (!isEmpty()) {
			T temp = q[first];
			first = ++first % q.length;
			count--;
			return temp;
		} else {
			System.out.println("Queue is Empty!!");
			return null;
		}
	}

	public boolean isEmpty() {
		return count == 0;
	}

	public void clear() {
		first = -1;
		last = -1;
		count = 0;
	}

	public String toString() {
		String res = "";
		for (int i = 0; i < q.length; i++)
			res += i + "[" + q[i] + "]\n";
		return res;
	}
}
