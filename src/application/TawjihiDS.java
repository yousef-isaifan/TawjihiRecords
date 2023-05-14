package application;

public class TawjihiDS<T extends Comparable<T>> {
	private DLinkList<TawjihiRecords> list;
	private LinkedAVL<TawjihiRecords> gradesAVL;
	private AVL_Tree<TawjihiRecords> seatNoAVL;
	private int count = 0;

	
	public TawjihiDS() {
		this.list = new DLinkList<>();
		this.gradesAVL = new LinkedAVL<>();
		this.seatNoAVL = new AVL_Tree<>();
	}

	
	public String insertTRecord(TawjihiRecords data) {
		if (seatNoAVL.find(data) == null) {
			DNode<TawjihiRecords> newNode = new DNode<TawjihiRecords>(data);
			list.insertAtFirst(newNode);
			seatNoAVL.insert(data, newNode);
			DNode<TawjihiRecords> reference = new DNode<>(newNode);
			gradesAVL.insert(data, reference);
			count++;
			return "Added successfully";
		}
		return "Seat number already exists";
	}

	
	public String insertRecord(int seatNo, String branch, double avg) {
		if (seatNoAVL.find(new TawjihiRecords(seatNo)) == null) {
			TawjihiRecords data = new TawjihiRecords(seatNo, branch, avg);
			DNode<TawjihiRecords> newNode = new DNode<TawjihiRecords>(data);
			list.insertAtFirst(newNode);
			seatNoAVL.insert(data, newNode);
			DNode<TawjihiRecords> reference = new DNode<>(newNode);
			gradesAVL.insert(data, reference);
			count++;
			return "Added successfully";
		}
		return "Seat number already exists";
	}

	
	public String update(int seatNo, double newAvg) {
		TawjihiRecords tempRec = new TawjihiRecords(seatNo);
		TNode<TawjihiRecords> find = seatNoAVL.find(tempRec);
		String str = "Seat Number dosen't exists in data";
		if (find != null) {
			if (find.dList.data.getAvg() == newAvg) {
				str = "New Avg must be different than old avg!";
			} else {
				TawjihiRecords newRec = new TawjihiRecords(seatNo, find.data.getBranch(), newAvg);
				find.dList.data.setAvg(newAvg);
				find.data.setAvg(newAvg);
				DNode<TawjihiRecords> newNode = gradesAVL.delete(tempRec);
				newNode.data.setAvg(newAvg);// Update to new AVG.
				gradesAVL.insert(newRec, newNode);
				str = "Updated successfully.";
			}
		}

		return str;
	}

	
	
	public String delete(int seatNo) {
		TawjihiRecords tempRec = new TawjihiRecords(seatNo);
		String str = "Seat Number dosen't exists in data";
		if (seatNoAVL.find(tempRec) != null) {
			gradesAVL.delete(tempRec);
			seatNoAVL.delete(tempRec);
			count--;
			str = "Deleted successfully.";
		}
		return str;
	}

	
	
	public DNode<TawjihiRecords> findBySeat(int seatNo) {
		TawjihiRecords temp = new TawjihiRecords(seatNo);
		TNode<TawjihiRecords> result = seatNoAVL.find(temp);
		if (result != null) {
			DNode<TawjihiRecords> node = result.dList;
			return node;
		}
		return null;
	}

	
	
	public String getAllByGrade(double avg) {
		LinkedList<TawjihiRecords> result = gradesAVL.findGrade(avg);
		String str = "No such garde!!";
		if (result != null) {
			str = result.print();
		}
		return str;
	}

	
	
	public String gradesAVLTrav() {
		if (gradesAVL.getRoot() != null) {
			return gradesAVL.levelTraversal();
		}else {
			return "No data in tree";
		}
	}

	
	
	public String seatNoAVLTrav() {
		if (seatNoAVL.getRoot() != null) {
			return seatNoAVL.levelTraversal();
		}else {
			return "No data in tree";
		}
	}

	
	
	public int seatNoAvlHeight() {
		return seatNoAVL.height();
	}

	
	
	public int gradesAvlHeight() {
		return gradesAVL.height();
	}

	
	
	public void setCount(int count) {
		this.count = count;
	}

	
	
	public void clear() {
		if (count > 0) {
			this.list.clear();
			this.gradesAVL.clear();
			this.seatNoAVL.clear();
		}
	}

// -----------------------------------------------------------------------------

	public DLinkList<TawjihiRecords> getList() {
		return list;
	}

	
	
	public void setList(DLinkList<TawjihiRecords> list) {
		this.list = list;
	}

	
	public LinkedAVL<TawjihiRecords> getGradesAVL() {
		return gradesAVL;
	}

	
	
	public void setGradesAVL(LinkedAVL<TawjihiRecords> gradesAVL) {
		this.gradesAVL = gradesAVL;
	}

	
	
	public AVL_Tree<TawjihiRecords> getSeatNoAVL() {
		return seatNoAVL;
	}

	
	
	public void setSeatNoAVL(AVL_Tree<TawjihiRecords> seatNoAVL) {
		this.seatNoAVL = seatNoAVL;
	}

	
	
	public int getCount() {
		return count;
	}
	
	

}
