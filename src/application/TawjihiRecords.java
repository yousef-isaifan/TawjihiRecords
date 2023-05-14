package application;


public class TawjihiRecords implements Comparable<TawjihiRecords> {
	private int seatNumber;
	private String branch;
	private double avg;

	public TawjihiRecords() {
	}
	

	public TawjihiRecords(int seatNumber, String branch, double avg) {
		if (seatNumber > 0) {
			this.seatNumber = seatNumber;
			this.branch = branch;
			this.avg = avg;
		}
	}

	// This constructor to create temporary object to find or delete object in the list.
	// Using only seat number.
	public TawjihiRecords(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	
	// This constructor to create temporary object to find object in the list.
	// Using only seat number.
	public TawjihiRecords(double avg) {
		this.avg = avg;
	}

	
	
	public int getSeatNumber() {
		return seatNumber;
	}

	
	
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	
	public String getBranch() {
		return branch;
	}

	
	public void setBranch(String branch) {
		this.branch = branch;
	}

	
	public double getAvg() {
		return avg;
	}
	

	public void setAvg(double avg) {
		this.avg = avg;
	}

	
	@Override
	public String toString() {
		return "Seat Number: " + seatNumber + " | Branch:" + branch + " | Avg: " + avg;
	}

	
	@Override
	public int compareTo(TawjihiRecords o) {
		if (this.seatNumber > o.seatNumber) {// Reversed compareTo to sort data from bigger to
			return 1; // smaller because we focused on highest grades
		} else if (this.seatNumber < o.seatNumber) {// more than smaller grades.
			return -1;
		} else {
			return 0;
		}
	}
	
	

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		else if (obj instanceof TawjihiRecords)
			return this.seatNumber == ((TawjihiRecords) obj).seatNumber;
		else
			return super.equals(obj);
	}

	
}
