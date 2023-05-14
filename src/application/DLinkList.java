package application;

public class DLinkList<T extends Comparable<T>> {
	private DNode<T> head;

	public DNode<T> insert(T data) {
		DNode<T> newNode = new DNode<>(data);
		if (head == null) {
			head = newNode;
		} else {
			DNode<T> curr = head;
			while (curr != null && curr.getData().compareTo(data) < 0) {
				if (curr.getNext() != null) {
					curr = curr.getNext();
				} else {
					break;
				}
			}
			if (curr.getData().compareTo(data) < 0) { // Add after curr
				if (curr.getNext() == null) {
					newNode.setPrev(curr);
					curr.setNext(newNode);
				}
			} else {// Add before curr
				if (curr.getNext() != null) {
					if (curr.getPrev() == null) {
						newNode.setNext(curr);
						curr.setPrev(newNode);
						head = newNode;
					} else {
						newNode.setNext(curr);
						newNode.setPrev(curr.getPrev());
						curr.getPrev().setNext(newNode);
						curr.setPrev(newNode);
					}
				} else { // Insert first
					newNode.setNext(curr);
					curr.setPrev(newNode);
					head = newNode;
				}
			}
		}
		return newNode;
	}

	public void insertAtFirst(DNode<T> newNode) {
		if (head != null && head.getNext() != null) {// Case more than 2 items.
			newNode.next = head;
			head.prev.next = newNode;
			newNode.prev = head.prev;
			head.prev = newNode;
		} else if (head != null && head.getNext() == null) {// Case one item.
			newNode.next = head;
			head.prev = newNode;
			newNode.prev = head;
			head.next = newNode;
		}
		head = newNode; // In empty list or other 2 cases.
	}
	public void clear() {
		head = null;
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

	public DNode<T> delete(T data) {
		if (head != null) {
			DNode<T> curr = head;
			DNode<T> deleted = null;
			while (curr != null && !(curr.getData().equals(data))) {
				curr = curr.getNext();
				if (curr == head)
					break;
			}
			if (curr != null) {
				if (curr.getData().equals(data)) {
					deleted = curr;
					if (head.next == head) {
						head = null;
					} else if (curr == head) {// delete head node
						head.prev.next = head.next;
						head.next.prev = head.prev;
						head = head.next;
					} else if (curr == head.prev) {// delete last node.
						curr = head.prev.prev;
						curr.next = head;
						head.prev = curr;
					} else {
						curr.prev.next = curr.next;
						curr.next.prev = curr.prev;
					}
				} else
					System.out.println("Not found");
			} else
				System.out.println("Not found");
			return deleted;
		} else
			System.out.println("List empty");
		return null;
	}

	public DNode<T> getHead() {
		return head;
	}

	public void traverse() {
		DNode<T> curr = head;
		System.out.print("Head-->");
		while (curr != null) {
			System.out.print(curr + "-->");
			if (curr.getNext() != head)// if next is head break.
				curr = curr.getNext();
			else
				break;
		}
		System.out.println("Null");
	}

	public int size() {
		if (head != null) {
			int count = 1;
			DNode<T> curr = head;
			while (curr.getNext() != null && curr.getNext() != head) {
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
		if (curr.next == head) {
			return 1;
		}
		return 1 + sizeRec(curr.getNext());
	}

}
