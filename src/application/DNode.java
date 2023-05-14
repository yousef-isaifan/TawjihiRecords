package application;

public class DNode<T extends Comparable<T>> {
	T data;
	DNode<T> next;
	DNode<T> prev;
	DNode<T> reference;
	
	public DNode() {
	}
	
	public DNode(DNode<T> reference) {
		this.data = reference.data;
		this.reference = reference;
	}
	
	public DNode(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public DNode<T> getNext() {
		return next;
	}

	public void setNext(DNode<T> next) {
		this.next = next;
	}

	public DNode<T> getPrev() {
		return prev;
	}

	public void setPrev(DNode<T> prev) {
		this.prev = prev;
	}

	@Override
	public String toString() {
		return "[" + data + "]";
	}

}
