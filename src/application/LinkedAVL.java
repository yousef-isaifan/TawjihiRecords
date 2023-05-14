package application;

@SuppressWarnings({ "unchecked", "rawtypes" })




public class LinkedAVL<T extends Comparable<T>> {
	private TNode<T> root;

	public void insert(T data, DNode<TawjihiRecords> node) {
		if (isEmpty())
			root = new TNode(data, node, 0);
		else {
			TNode rootNode = root;
			addEntry(data, node, rootNode);
			root = rebalance(rootNode);
		}
	}

	
	
	public void addEntry(T data, DNode<TawjihiRecords> node, TNode<TawjihiRecords> rootNode) {
		assert rootNode != null;
		if ((node.getData()).getAvg() < (rootNode.getData()).getAvg()) {
			if (rootNode.hasLeft()) {
				TNode leftChild = rootNode.left;
				addEntry(data, node, leftChild);
				rootNode.left = rebalance(leftChild);
			} else
				rootNode.left = new TNode(data, node, 0);
		} else if ((node.getData()).getAvg() > (rootNode.getData()).getAvg()) { // addition into right subtree
			if (rootNode.hasRight()) {
				TNode rightChild = rootNode.right;
				addEntry(data, node, rightChild);
				rootNode.right = rebalance(rightChild);
			} else
				rootNode.right = new TNode(data, node, 0);
		} else {
			rootNode.list.insertNode(node);
		}
	}

	
	
	private TNode<T> rebalance(TNode<T> nodeN) {
		int diff = getHeightDifference(nodeN);
		if (diff > 1) { // addition was in node's left subtree
			if (getHeightDifference(nodeN.left) > 0)
				nodeN = rotateRight(nodeN);
			else
				nodeN = rotateLeftRight(nodeN);
		} else if (diff < -1) { // addition was in node's right subtree
			if (getHeightDifference(nodeN.right) < 0)
				nodeN = rotateLeft(nodeN);
			else
				nodeN = rotateRightLeft(nodeN);
		}
		return nodeN;
	}

	
	
	private TNode<T> rotateRight(TNode<T> nodeN) {
		TNode<T> nodeC = nodeN.left;
		nodeN.left = nodeC.right;
		nodeC.right = nodeN;

		return nodeC;
	}

	
	
	private TNode<T> rotateLeft(TNode<T> nodeN) {
		TNode<T> nodeC = nodeN.right;
		nodeN.right = nodeC.left;
		nodeC.left = nodeN;

		return nodeC;
	}

	
	
	private TNode<T> rotateRightLeft(TNode<T> nodeN) {
		TNode<T> nodeC = nodeN.right;
		nodeN.right = rotateRight(nodeC);

		return rotateLeft(nodeN);
	}

	
	
	
	private TNode<T> rotateLeftRight(TNode<T> nodeN) {
		TNode<T> nodeC = nodeN.left;
		nodeN.left = rotateLeft(nodeC);

		return rotateRight(nodeN);
	}
	
	

	private int getHeightDifference(TNode<T> nodeC) {
		if (nodeC != null)
			return height(nodeC.left) - height(nodeC.right);
		return 0;
	}

	
	
	public boolean isEmpty() {
		return root == null;
	}

	
	
	public int size() {
		return size(root);
	}

	
	
	private int size(TNode<T> node) {
		if (node == null)
			return 0;
		else {
			return 1 + size(node.getLeft()) + size(node.getRight());
		}
	}

	
	
	public DNode<T> delete(T data) {
		DNode<T> result = delete(data, root);
		return result;
	}

	
	
	private DNode<T> delete(T data, TNode<T> node) {//Deletes from list.
		if (node != null) {
			DNode<T> n = node.list.find(data);
			if (n != null) {
				int comp = data.compareTo(n.data);
				if (comp == 0) {
					DNode<T> result = node.list.delete(data);
					if (node.list.getHead() == null)
						deleteFromTree(node.data);

					return result;
				}
			}
			DNode<T> x = delete(data, node.left);
			if (x == null)
				x = delete(data, node.right);

			return x;
		}
		return null;
	}

	
	
	public TNode<T> findNode(T data) {
		return findNode(data, root);
	}

	
	
	private TNode<T> findNode(T data, TNode<T> node) {
		if (node != null) {
			int comp = data.compareTo((T) node.data);
			if (comp == 0) {
				return node;
			}
			TNode<T> x = findNode(data, node.left);
			if (x == null)
				x = findNode(data, node.right);

			return x;
		}
		return null;
	}

	
	
	public TNode<T> deleteFromTree(T data) {
		TNode<T> temp = delete1(data);
		if (temp != null) {
			TNode<T> rootNode = root;
			root = rebalance(rootNode);
		}
		return temp;
	}

	
	
	private TNode<T> delete1(T data) {
		TNode<T> current = root;
		TNode<T> parent = root;
		boolean isLeftChild = false;
		if (isEmpty())
			return null; // tree is empty
		while (current != null && !current.data.equals(data)) {
			parent = current;
			TNode<TawjihiRecords> holder1 = new TNode<TawjihiRecords>((TawjihiRecords) data);
			TNode<TawjihiRecords> holder2 = new TNode<TawjihiRecords>((TawjihiRecords) current.data);
			if (holder1.data.getAvg() < holder2.data.getAvg()) {
				current = current.left;
				isLeftChild = true;
			} else {
				current = current.right;
				isLeftChild = false;
			}
		}
		if (current == null)
			return null; // node to be deleted not found
		// Case 1: node is a leaf
		if (!current.hasLeft() && !current.hasRight()) {
			if (current == root) // tree has one node
				root = null;
			else {
				if (isLeftChild)
					parent.left = null;
				else
					parent.right = null;
			}
		}
		// Case 2 broken down further into 2 separate cases
		else if (current.hasLeft() && !current.hasRight()) { // current has left
																// child only
			if (current == root) {
				root = current.left;
			} else if (isLeftChild) {
				parent.left = current.left;
			} else {
				parent.right = current.left;
			}// current has right child only
		} else if (current.hasRight() && !current.hasLeft()) { 
			if (current == root) {
				root = current.right;
			} else if (isLeftChild) {
				parent.left = current.right;
			} else {
				parent.right = current.right;
			}
		}
		// Case 3: node to be deleted has 2 children
		else {
			TNode<T> successor = getSuccessor(current);
			if (current == root)
				root = successor;
			else if (isLeftChild) {
				parent.left = successor;
			} else {
				parent.right = successor;
			}
			successor.left = current.left;
		}

		return current;
	}
	
	

	private TNode<T> getSuccessor(TNode<T> node) {
		TNode<T> parentOfSuccessor = node;
		TNode<T> successor = node;
		TNode<T> current = node.right;
		while (current != null) {
			parentOfSuccessor = successor;
			successor = current;
			current = current.left;
		}
		if (successor != node.right) { // fix successor connections
			parentOfSuccessor.left = successor.right;
			successor.right = node.right;
		}
		return successor;
	}
	
	

	public int height() {
		return height(root);
	}

	
	
	public LinkedList<T> findGrade(double key) {
		return findGrade(key, root);
	}

	
	
	private LinkedList<T> findGrade(double key, TNode<T> node) {
		if (node != null) {
			TNode<TawjihiRecords> holder = new TNode<TawjihiRecords>((TawjihiRecords) node.data);
			double temp = holder.data.getAvg();
			if (holder != null) {
				if (temp == key) {
					return node.list;
				}
			}
			LinkedList<T> x = findGrade(key, node.left);
			if (x == null)
				x = findGrade(key, node.right);

			return x;
		}
		return null;
	}
	
	
	public int height(TNode<T> node) {
		if (node == null)
			return 0;
		return 1 + Math.max(height(node.left), height(node.right));
	}

	
	
	public TNode<T> find(T data) {
		return find(data, root);
	}
	
	

	private TNode<T> find(T data, TNode<T> node) {
		if (node != null) {
			DNode<T> n = node.list.find(data);
			if (n != null) {
				int comp = data.compareTo(n.data);
				if (comp == 0)
					return new TNode<T>(n.data);
			}
			TNode<T> x = find(data, node.left);
			if (x == null)
				x = find(data, node.right);

			return x;
		}
		return null;
	}

	
	
	public void traverseInOrder() {
		traverseInOrder(root);
		System.out.println();
	}

	
	
	private void traverseInOrder(TNode<T> node) {
		if (node != null) {
			if (node.left != null)
				traverseInOrder(node.left);
			System.out.print(node + " ");
			if (node.right != null)
				traverseInOrder(node.right);
		}
	}

	
	
	public String levelTraversal() {
		if (root == null)
			return "Head---> Null";
		ArrayQueue<TNode<T>> q = new ArrayQueue<>(100000);
		String str = "";
		q.enqueue(root);
		int currLevel = 1;
		int nextLevel = 0;
		while (!q.isEmpty()) {
			TNode<T> curr = q.dequeue();
			str += "Head---> " + curr.list.print() + "---> NULL     ";
			if (curr.left != null) {
				q.enqueue(curr.left);
				nextLevel++;// increase number of nodes for next level.
			}
			if (curr.right != null) {
				q.enqueue(curr.right);
				nextLevel++;// increase number of nodes for next level.
			}
			currLevel--;
			if (currLevel == 0) {// Means we print all of nodes for this level.
				str += "\n";// New line for next level.
				currLevel = nextLevel;// Define the number of children nodes.
				nextLevel = 0;
			}
		}
		return str;
	}

//	public String levelTraversal() {
//		ArrayQueue<TNode> q = new ArrayQueue<>(30);
//		String str = "";
//		if (root != null) {
//			q.enqueue(root);
//			while (!q.isEmpty()) {
//				TNode temp = q.dequeue();
//				str += "Head---> " +temp.list.print() + "---> NULL      ";
//				if (temp.left != null)
//					q.enqueue(temp.left);
//				if (temp.right != null)
//					q.enqueue(temp.right);
//			}
//		}
//		return str;
//	}

	public TNode<T> getRoot() {
		return root;
	}
	
	public void clear() {
		this.root = null;
	}

}