package group5.transaction;

import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import group5.Customer;
import group5.Room;
import group5.RoomType;
import group5.SalesPerson;

public class Report {
	private Date reportGenerateDate;
	public Report() {
		reportGenerateDate = new Date();
	}
	//Getter & Setter
	public Date getReportGenerateDate() {
		return reportGenerateDate;
	}
	public void setReportGenerateDate(Date reportGenerateDate) {
		this.reportGenerateDate = reportGenerateDate;
	}
	//Display report menu
	public static void reportMenu() {
		int choice = 0;
		boolean valid = false;
		String input = "";
		do {
			valid = true;
	    	JLabel label = new JLabel("<html><b>Report Menu</b><br>1. Daily Sales Report<br>2. Customer Sales Report<br>3. Sales Person Transaction Report<br>"
	    			+ "4. Point Collection Report<br>5. Room Sales Report<br>-1. Return to main menu</html>");
	    	label.setFont(new Font("Arial", Font.PLAIN, 18));
	    	do {
	    		input = JOptionPane.showInputDialog(null, label);
	    		if (input==null) {
    	    		input = "-1";
    	    	}
        	}while(input.isEmpty());
	        
	        if (input!=null&&!input.isEmpty()) {
	        	input = input.trim();
	        	if (input.equals("-1")) {
	        		return;
	        	}
	        	for(int i = 0;i < input.length();i++) {
		        	Character ch = input.charAt(i);
		        	if (!Character.isDigit(ch)) {
		        		valid = false;
		        		
		        	}
		        }
	        	if (valid==true) {
	        		choice = Integer.parseInt(input);
	        	}
	        }else {
	        	choice = -1;
	        	valid=true;
	        }
	        
	        if (valid) {
	        	switch (choice) {
	        		case 1:
	        			dailySalesReport();
	        			break;
	        		case 2:
	        			customerTransactionReport();
	        			break;
	        		case 3:
	        			salesPersonTransactionReport();
	        			break;
	        		case 4:
	        			pointCollectionReport();
	        			break;
	        		case 5:
	        			roomSalesReport();
	        			break;
	        		case -1:
	        			return;
	        		default:
	        			JLabel label2 = new JLabel("<html><p style='color:red'>Incorrect choice number!</p></html>");
	        	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
	        	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
	        	}
	        }else {
	        	JLabel label2 = new JLabel("<html><p style='color:red'>Incorrect choice number!</p></html>");
    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
	        }
		}while(valid==false||choice!=-1);
	}
	@SuppressWarnings("deprecation")
	//Generate daily Sales report
	public static void dailySalesReport() {
		//Enter the date that want to display
		JLabel label = new JLabel("<html>Enter the <b>date</b> [dd-MM-yyyy] (-1 to cancel)<br>[Transaction Date that going to view the report]</html>");
		label.setFont(new Font("Arial", Font.PLAIN, 18));
		boolean validOutput = true;
		String result = "";
		DateFormat dateFormat = new SimpleDateFormat( "dd-MM-YYYY HH:mm" );
		DateFormat dateFormat2 = new SimpleDateFormat( "dd-MM-YYYY" );
		Date date = new Date();
		do {
			validOutput = false;
			do {
		    	result = JOptionPane.showInputDialog(null, label,"View Report",JOptionPane.INFORMATION_MESSAGE);
		    	if (result==null) {
		    		result = "-1";
		    	}
	    	}while(result.isEmpty());
			result = result.trim();
			if (result.equals("-1")) {
				return;
			}
			try {
				date = new SimpleDateFormat("dd-MM-yyyy").parse(result);
				validOutput = true;
			}catch (Exception e) {
				JLabel label2 = new JLabel("<html><p style='color:red'>Invalid date!</p></html>");
    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
				validOutput=false;
			}
		}while(validOutput==false);
		System.out.println("\n\nDaily Sales Report\n["+dateFormat2.format(date)+"]\n===============================\n\nTransaction ID\tCustomer Name\t\tRoom Type\tDate\t\t\tAmount" +
				"\n------------------------------------------------------------------------------------------");
		double totalAmount = 0.0;
		int record = 0;
		
		for (int i = 0;i<Transaction.getTotalTransaction();i++) {
			if (Transaction.getTransactionList()[i] instanceof ReservationTransaction) {
				ReservationTransaction transaction = (ReservationTransaction)Transaction.getTransactionList()[i];
				if (transaction.getTransactionDate().getMonth()==date.getMonth()&&transaction.getTransactionDate().getYear()==date.getYear()&&transaction.getTransactionDate().getDate()==date.getDate()) {
					System.out.printf("%-14s\t%-20s\t%-15s\t%-10s\tRM%.2f\n", 
							transaction.getTransactionNum(),transaction.getReservation().getCustomer().getName(), transaction.getReservation().getDetails().getRoom().getRoomType().getRoomName(), dateFormat.format(transaction.getTransactionDate()), transaction.getPayment().getTotalPrice());
				
					record++;
					totalAmount += transaction.getPayment().getTotalPrice();
				}
				
			}
		}
		if (record>0) {
			System.out.printf("Total : RM%.2f\n", totalAmount);
			System.out.printf("\n%d records found.\n", record);
		}else {
			System.out.println("NO RESULT FOUND");
		}
		
	}
	//Generate Customer Transaction Report
	public static void customerTransactionReport() {
		//Enter the date that want to display
		Customer.listCustomer();
		JLabel label = new JLabel("<html>Enter the <b>Customer ID</b> [Eg: M#](-1 to cancel)<br>[Customer that going to view the report]</html>");
		label.setFont(new Font("Arial", Font.PLAIN, 18));
		boolean validOutput = true;
		String result = "";
		String customerID = "";
		do {
			validOutput = false;
			do {
		    	result = JOptionPane.showInputDialog(null, label,"View Report",JOptionPane.INFORMATION_MESSAGE);
		    	if (result==null) {
		    		result = "-1";
		    	}
	    	}while(result.isEmpty());
			result = result.trim();
			if (result.equals("-1")) {
				return;
			}
			customerID = result;
			for (int i = 0;i < Customer.getTotalCustomer();i++) {
				if (Customer.getCustomerList()[i].getCustomerID().equals(customerID)) {
					validOutput = true;
					break;
				}
			}
			if (!validOutput) {
				JLabel label2 = new JLabel("<html><p style='color:red'>Customer ID not found!</p></html>");
				label2.setFont(new Font("Arial", Font.BOLD, 16));
				JOptionPane.showMessageDialog(null, label2, "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}while(validOutput==false);
		System.out.println("\n\nCustomer Transaction Report\n["+customerID+"]\n===============================\n\nTransaction ID\tCustomer Name\t\tRoom Type\tDate\t\t\tAmount" +
				"\n------------------------------------------------------------------------------------------");
		double totalAmount = 0.0;
		int record = 0;
		DateFormat dateFormat = new SimpleDateFormat( "dd-MM-YYYY HH:mm" );
		for (int i = 0;i<Transaction.getTotalTransaction();i++) {
			if (Transaction.getTransactionList()[i] instanceof ReservationTransaction) {
				ReservationTransaction transaction = (ReservationTransaction)Transaction.getTransactionList()[i];
				if (transaction.getReservation().getCustomer().getCustomerID().equals(customerID)) {
					System.out.printf("%-14s\t%-20s\t%-15s\t%-10s\tRM%.2f\n", 
							transaction.getTransactionNum(),transaction.getReservation().getCustomer().getName(), transaction.getReservation().getDetails().getRoom().getRoomType().getRoomName(), dateFormat.format(transaction.getTransactionDate()), transaction.getPayment().getTotalPrice());
				
					record++;
					totalAmount += transaction.getPayment().getTotalPrice();
				}
				
			}
		}
		if (record>0) {
			System.out.printf("Total : RM%.2f\n", totalAmount);
			System.out.printf("\n%d records found.\n", record);
		}else {
			System.out.println("NO RESULT FOUND");
		}
	}
	//Generate Sales Person Transaction Report
	public static void salesPersonTransactionReport() {
		//Enter the date that want to display
		SalesPerson.listSalesPerson();
		JLabel label = new JLabel("<html>Enter the <b>SalesPerson UserName</b> [Eg: S#](-1 to cancel)<br>[SalesPerson that going to view the report]</html>");
		label.setFont(new Font("Arial", Font.PLAIN, 18));
		boolean validOutput = true;
		String result = "";
		String salesPersonID = "";
		do {
			validOutput = false;
			do {
		    	result = JOptionPane.showInputDialog(null, label,"View Report",JOptionPane.INFORMATION_MESSAGE);
		    	if (result==null) {
		    		result = "-1";
		    	}
	    	}while(result.isEmpty());
			result = result.trim();
			if (result.equals("-1")) {
				return;
			}
			salesPersonID = result;
			for (int i = 0;i < SalesPerson.getTotalSalesPerson();i++) {
				if (SalesPerson.getSalesPersonList()[i].getSalesUserName().equals(salesPersonID)) {
					validOutput = true;
					break;
				}
			}
			if (!validOutput) {
				JLabel label2 = new JLabel("<html><p style='color:red'>Sales Person Username not found!</p></html>");
				label2.setFont(new Font("Arial", Font.BOLD, 16));
				JOptionPane.showMessageDialog(null, label2, "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}while(validOutput==false);
		System.out.println("\n\nSales Person Transaction Report\n["+salesPersonID+"]\n===============================\n\nTransaction ID\tCustomer Name\t\tRoom Type\tDate\t\t\tAmount" +
				"\n------------------------------------------------------------------------------------------");
		double totalAmount = 0.0;
		int record = 0;
		DateFormat dateFormat = new SimpleDateFormat( "dd-MM-YYYY HH:mm" );
		for (int i = 0;i<Transaction.getTotalTransaction();i++) {
			if (Transaction.getTransactionList()[i] instanceof ReservationTransaction) {
				ReservationTransaction transaction = (ReservationTransaction)Transaction.getTransactionList()[i];
				if (transaction.getReservation().getSalesPerson().getSalesUserName().equals(salesPersonID)) {
					System.out.printf("%-14s\t%-20s\t%-15s\t%-10s\tRM%.2f\n", 
							transaction.getTransactionNum(),transaction.getReservation().getCustomer().getName(), transaction.getReservation().getDetails().getRoom().getRoomType().getRoomName(), dateFormat.format(transaction.getTransactionDate()), transaction.getPayment().getTotalPrice());
				
					record++;
					totalAmount += transaction.getPayment().getTotalPrice();
				}
				
			}
		}
		if (record>0) {
			System.out.printf("Total : RM%.2f\n", totalAmount);
			System.out.printf("\n%d records found.\n", record);
		}else {
			System.out.println("NO RESULT FOUND");
		}
	}
	//Generate Point Collection Report
	public static void pointCollectionReport() {
		//Enter the customer id that want to display
		Customer.listCustomer();
		JLabel label = new JLabel("<html>Enter the <b>Customer ID</b> [Eg: M#](-1 to cancel)<br>[Customer that going to view the report]</html>");
		label.setFont(new Font("Arial", Font.PLAIN, 18));
		boolean validOutput = true;
		String result = "";
		String customerID = "";
		do {
			validOutput = false;
			do {
		    	result = JOptionPane.showInputDialog(null, label,"View Report",JOptionPane.INFORMATION_MESSAGE);
		    	if (result==null) {
		    		result = "-1";
		    	}
	    	}while(result.isEmpty());
			result = result.trim();
			if (result.equals("-1")) {
				return;
			}
			customerID = result;
			for (int i = 0;i < Customer.getTotalCustomer();i++) {
				if (Customer.getCustomerList()[i].getCustomerID().equals(customerID)) {
					validOutput = true;
					break;
				}
			}
			if (!validOutput) {
				JLabel label2 = new JLabel("<html><p style='color:red'>Customer ID not found!</p></html>");
				label2.setFont(new Font("Arial", Font.BOLD, 16));
				JOptionPane.showMessageDialog(null, label2, "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}while(validOutput==false);
		System.out.println("\n\nPoint Collection Report\n["+customerID+"]\n===============================\n" + 
				"\nTransaction ID\tCustomer\t\tDate\t\t\tPoint Collect/Use" +
				"\n--------------------------------------------------------------------------------");
		int totalPointUse = 0,totalPointCollect = 0;
		int record = 0;
		DateFormat dateFormat = new SimpleDateFormat( "dd-MM-YYYY HH:mm" );
		for (int i = 0;i<Transaction.getTotalTransaction();i++) {
			if (Transaction.getTransactionList()[i] instanceof PointTransaction) {
				PointTransaction transaction = (PointTransaction)Transaction.getTransactionList()[i];
				if (transaction.getCustomer().getCustomerID().equals(customerID)) {
					System.out.printf("%-14s\t%-20s\t%-12s\t%-5d\n", transaction.getTransactionNum(), 
					transaction.getCustomer().getName(), dateFormat.format(transaction.getTransactionDate()), transaction.getPointCollect());
					record++;
					if (transaction.getPointCollect()>=0) {
						totalPointCollect += transaction.getPointCollect();
					}else {
						totalPointUse += transaction.getPointCollect();
					}
				}
				
			}
		}
		if (record>0) {
			System.out.printf("Total Point Collect: %d\n", totalPointCollect);
			System.out.printf("Total Point Use: %d\n", -totalPointUse);
			System.out.printf("\n%d records found.\n", record);
		}else {
			System.out.println("NO RESULT FOUND");
		}
	}
	public static void roomSalesReport() {
		System.out.println("\n\nRoom Sales Report\n===============================\n" + 
				"\nRoom Type\t\tNumber of Rentals\tTotal Earn\tRoom Quantity" +
				"\n----------------------------------------------------------------------------------");
		int total = 0, totalRoom = 0;
		double totalEarn = 0;
		for (int i = 0; i < RoomType.getTotalRoomType();i++) {
			total = 0;
			totalRoom = 0;
			totalEarn = 0;
			for (int k = 0; k < Transaction.getTotalTransaction();k++) {
				if (Transaction.getTransactionList()[k] instanceof ReservationTransaction) {
					ReservationTransaction transaction = (ReservationTransaction)Transaction.getTransactionList()[k];
					if (transaction.getReservation().getDetails().getRoom().getRoomType().getRoomName().equals(RoomType.getRoomTypeList()[i].getRoomName())) {
						total++;
						totalEarn += transaction.getPayment().getTotalPrice();
					}
				}
			}
			//Get room amount
			for (int k = 0; k < Room.getTotalRoom();k++) {
				if (Room.getRoomList()[k].getRoomType().getRoomName().equals(RoomType.getRoomTypeList()[i].getRoomName())) {
					totalRoom++;
				}
			}
			System.out.printf("%-20s\t%-15d\t\tRM%-6.2f\t%d\n", RoomType.getRoomTypeList()[i].getRoomName(), total,totalEarn,totalRoom);
		}
	}
}
