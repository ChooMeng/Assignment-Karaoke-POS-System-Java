package group5.transaction;

import java.util.Date;

import group5.Customer;

public class PointTransaction extends Transaction{
	private Customer customer;
	private int pointCollect;
	public PointTransaction(Customer customer, int pointCollect) {
		super();
		this.customer = customer;
		this.pointCollect = pointCollect;
	}
	public PointTransaction(Customer customer, int pointCollect, Date transactionDate) {
		super(transactionDate);
		this.customer = customer;
		this.pointCollect = pointCollect;
	}
	//getter
	public Customer getCustomer() {
		return customer;
	}
	public int getPointCollect() {
		return pointCollect;
	}
	public String toString() {
		
		return "[Point Transaction]\n"
				+ super.toString()+String.format("Customer ID: %s\n"
						+ "Customer Name: %s\n"
						+ "Point Collect: %d\n"
						+ "Current Point: %d\n",customer.getCustomerID(),customer.getName(),
						pointCollect,customer.getPoints());
	}

}
