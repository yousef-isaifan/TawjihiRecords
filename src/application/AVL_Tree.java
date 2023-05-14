package application;

public class AVL_Tree<T extends Comparable<T>> {
	private TNode<T> root;

	public void insert(T data, DNode<T> node) {
		if (isEmpty())
			root = new TNode<T>(data, node, 1);
		else {
			TNode<T> rootNode = root;
			addEntry(data, node, rootNode);
			root = rebalance(rootNode);
		}
	}

	
	
	public void addEntry(T data, DNode<T> node, TNode<T> rootNode) {
		assert rootNode != null;
		if (data.compareTo((T) rootNode.dList.data) < 0) { // right into left subtree
			if (rootNode.hasLeft()) {
				TNode<T> leftChild = rootNode.left;
				addEntry(data, node, leftChild);
				rootNode.left = rebalance(leftChild);
			} else
				rootNode.left = new TNode<T>(data, node, 1);
		} else { // right into right subtree
			if (rootNode.hasRight()) {
				TNode<T> rightChild = rootNode.right;
				addEntry(data, node, rightChild);
				rootNode.right = rebalance(rightChild);
			} else
				rootNode.right = new TNode<T>(data, node, 1);
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

	
	
	public TNode<T> delete(T data) {
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
		while (current != null && !current.dList.data.equals(data)) {
			parent = current;
			if (data.compareTo((T) current.dList.data) < 0) {
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
			}
		} else if (current.hasRight() && !current.hasLeft()) { // current has
																// right child
																// only
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

	
	
	
	public boolean isEmpty() {
		return root == null;
	}

	
	
	public int height() {
		return height(root);
	}

	
	
	public int height(TNode<T> node) {
		if (node == null)
			return 0;
		return 1 + Math.max(height(node.left), height(node.right));
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
		int lh = height(nodeC.left);
		int rh = height(nodeC.right);

		return lh - rh;
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

	
	
	public TNode<T> find(T data) {
		return find(data, root);
	}

	
	
	private TNode<T> find(T data, TNode<T> node) {
		if (node != null) {
			int comp = node.dList.data.compareTo(data);
			if (comp == 0) {
				TNode<T> result = new TNode<T>(data, node.dList, 1);
				return result;
			}
			else if (comp > 0 && node.hasLeft())
				return find(data, node.left);
			else if (comp < 0 && node.hasRight())
				return find(data, node.right);
		}
		return null;
	}

	
	
	public String levelTraversal() {
		if (root == null)
			return "";
		ArrayQueue<TNode<T>> q = new ArrayQueue<>(10000);
		String str = "";
		q.enqueue(root);
		int currLevel = 1;
		int nextLevel = 0;
		while (!q.isEmpty()) {
			TNode<T> curr = q.dequeue();
			str += curr.data + "      ";
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
//				TNode<T> temp = q.dequeue();
//				str += temp + "    ";
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