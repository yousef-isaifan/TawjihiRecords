package application;

public class LinkedList<T extends Comparable<T>> {
	private DNode<T> head;

	public void insert(T data) {
		DNode<T> newNode = new DNode<>(data);
		DNode<T> curr = head;
		DNode<T> prev = null;

		while (curr != null) {
			if (curr.getData().compareTo(data) < 0) {
				prev = curr;
				curr = curr.getNext();
			} else {
				break;
			}
		}
		if (prev == null) {
			if (curr == null) {
				head = newNode;
			} else {
				newNode.setNext(curr);
				head = newNode;
			}
		} else {
			newNode.setNext(curr);
			prev.setNext(newNode);
		}
	}

	
	public void insertAtHead(T data) {
		DNode<T> newNode = new DNode<>(data);
		if (head != null)
			newNode.setNext(head);
		head = newNode;
	}

	public void insertNode(DNode<T> node) {
		if (head != null)
			node.setNext(head);
		head = node;
	}

	
	
	public void insertAtLast(T data) {
		DNode<T> newNode = new DNode<>(data);
		if (head != null) {
			DNode<T> curr = head;
			while (curr.getNext() != null)
				curr = curr.getNext();
			curr.setNext(newNode);
		} else
			head = newNode;
	}

	
	
	public DNode<T> find(T data) {
		DNode<T> curr = head;
		while (curr != null) {
			if (curr.data.equals(data)) {
				return curr;
			}
			curr = curr.next;
		}
		return null;
	}
	
	

	public DNode<T> delete(T data) {
		if (head != null) {
			DNode<T> curr = head;
			DNode<T> prev = null;
			while (curr != null && curr.getData().compareTo(data) != 0) {
				prev = curr;
				curr = curr.getNext();
			}
			if (curr != null) {
				if (curr.getData().equals(data)) {
					if (prev == null) {
						head = head.getNext();
					} else {
						prev.setNext(curr.getNext());
					}
					return curr;
				}
			}
		}
		return null;
	}

	
	
	public DNode<T> getHead() {
		return head;
	}

	
	
	public String print() {
		DNode<T> curr = head;
		String str = "";
		while (curr != null && curr.next != head) {
			if (curr.next != null)
				str += curr + "---> ";
			else
				str += curr;
			curr = curr.getNext();
		}
		return str;
	}

	
	
	public void traverse() {
		DNode<T> curr = head;
		System.out.print("Head---> ");
		while (curr != null) {
			System.out.print(curr + "---> ");
			curr = curr.getNext();
		}
		System.out.print("Null ");
	}
	
	

	public int size() {
		if (head != null) {
			int count = 1;
			DNode<T> curr = head;
			while (curr.getNext() != null) {
				curr = curr.getNext();
				count++;
			}
			return count;
		}
		return 0;
	}
	
	

	public int sizeRec() {
		return sizeRec(head);
	}

	
	
	private int sizeRec(DNode<T> curr) {
		if (curr == null) {
			return 0;
		}
		return 1 + sizeRec(curr.getNext());
	}
	
	

}
