package application;

public class TNode<T extends Comparable<T>> {
	T data;
	TNode<T> left;
	TNode<T> right;
	LinkedList<T> list = new LinkedList<>();
	DNode<T> dList = new DNode<>();

	public TNode(T data) {
		this.data = data;
	}

	public TNode(T data, DNode<T> node, int index) {
		this.data = data;
		if (index == 0)
			list.insertNode(node);
		else {
			dList = node;
		}
	}

	public LinkedList<T> getList() {
		return list;
	}

	public void setList(LinkedList<T> list) {
		this.list = list;
	}

	public DNode<T> getdList() {
		return dList;
	}

	public void setdList(DNode<T> dList) {
		this.dList = dList;
	}

	public void setData(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public TNode<T> getLeft() {
		return left;
	}

	public void setLeft(TNode<T> left) {
		this.left = left;
	}

	public TNode<T> getRight() {
		return right;
	}

	public void setRight(TNode<T> right) {
		this.right = right;
	}

	public boolean isLeaf() {
		return (left == null && right == null);
	}

	public boolean hasLeft() {
		return left != null;
	}

	public boolean hasRight() {
		return right != null;
	}

	public String toString() {
		return "" + data + "";
	}
}