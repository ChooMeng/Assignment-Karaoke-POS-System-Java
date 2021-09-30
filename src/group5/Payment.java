package group5;

import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import group5.transaction.PointTransaction;
import group5.transaction.ReservationTransaction;
import group5.transaction.Transaction;


public class Payment {
	//data members
		private static double memberPointDiscount = 10.00;
		private double balance;
		private double totalPrice;
		private double pay;
		private Reservation reservation;

		//no arg constructor
	    public Payment() {}
	    
	    //with parameter (without member)
	    public Payment(double totalPrice){
	    	this.totalPrice = totalPrice;
	    	balance = 0;
	    	pay = totalPrice;
	    }
	    public Payment(double totalPrice,double pay){
	    	this.totalPrice = totalPrice;
	    	this.pay = pay;
	    	balance = pay-totalPrice;
	    }
	     
	    //getter
	    public double getBalance(){return balance;}
	    public double getTotalPrice(){return totalPrice;}
	    public double getPay(){return pay;}
	    public Reservation getReservation() {
	    	return reservation;
	    }
	    public static double getMemberPointDiscount() {
	    	return memberPointDiscount;
	    }

	    
	    //setter
	    public void setBalance(double balance){this.balance = balance;}    
	    public void setTotalPrice(double totalPrice){this.totalPrice = totalPrice;}
	    public void setPay(double pay){this.pay = pay;}  
	    public void setReservation(Reservation reservation) {
	    	this.reservation = reservation;
	    }
	    public static void setMemberPointDiscount(int memberPointDiscount) {
	    	Payment.memberPointDiscount = memberPointDiscount;
	    }

	    
	    //methods
	    
	    
	   public double calculateBalance(){
	   	balance = pay - totalPrice;
	   	return balance;
	   }
	    
	    public double calculatePrice(double discountPrice){
	    	totalPrice = totalPrice - discountPrice;
	    	return totalPrice;
	    }
	    
	    
	    //return totalPrice string
	    public String toString(){
	    	return String.format("%.2f\n%.2f\n%.2f", balance, totalPrice,pay);
	    }
	    
	    public static boolean pay(Reservation reservation) {
	    	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	   		LocalDateTime now = LocalDateTime.now();
	   		Payment payment = new Payment();
	   		payment.setReservation(reservation);
	   		payment.setTotalPrice(reservation.getTotalCosts());
	   		boolean valid = false;
	   		double pay = 0;
	   		long diff = reservation.getDetails().getTimeReservedEnd().getTime()-reservation.getDetails().getTimeReserved().getTime();
			long duration = TimeUnit.MILLISECONDS.toMinutes(diff);
			String info = String.format("========[SalesPerson Details]=========<br/>"
   	   				+ "SalesPerson ID: %s<br/>"
   	   				+ "SalesPerson Name: %s<br/><br/>"
   	   				+ "========[Customer Details]=========<br/>"
   	   				+ "Customer ID: %s<br/>"
   	   				+ "Customer Name: %s<br/>"
   	   				+ "Contact Number: %s<br/>"
   	   				+ "Points: %d<br/>", reservation.getSalesPerson().getSalesUserName(),reservation.getSalesPerson().getName(),reservation.getCustomer().getCustomerID(),
   	   				reservation.getCustomer().getName(),reservation.getCustomer().getContactNumber(),reservation.getCustomer().getPoints());
   	   		String items = String.format("<table><tr>"
   	   				+ "<td>Reservation ID</td><td>Reservation Desc</td><td>Room Users</td><td>Duration</td><td>Sub Total</td></tr><tr>"
   	   				+ "<td>%s</td><td>%s[%s]</td><td>%d</td><td>%d</td><td>%.2f</td>"
   	   				+ "</tr></table>", reservation.getReservationID(),reservation.getDetails().getRoom().getRoomType().getRoomName(),reservation.getDetails().getRoom().getRoomID()
   	   				,reservation.getDetails().getRoomUsers(),duration,reservation.getTotalCosts());
	   		do{
	   			valid = false;
	   	   		String input = "";
	   	   		
	   	        JLabel label = new JLabel("<html><b>Payment Details	</b>" + dateFormat.format(now) + "<br>----------------------------------------------------------------"
	   	   				+ "<br>"+info+"<br>[Reservation Info]<br>"+items+"<br><br>-1 to cancel payment.<br>1 to proceed</html>");
	   	        label.setFont(new Font("Arial", Font.PLAIN, 18));
	   	        do {
	   	        	do{	
		   	        	input = JOptionPane.showInputDialog(null, label,"Payment",JOptionPane.INFORMATION_MESSAGE);
		   	         	if (input == null) {
		   	    	    	input = "-1";
		   	    	    }
		   	        }while(input.isEmpty());
		   	        JLabel labelCancel = new JLabel("<html>Are you sure want to <b>Cancel Payment</b> ?</html>");
		   	        labelCancel.setFont(new Font("Arial", Font.PLAIN, 18));
		   	        if (input.equals("-1")) {
	   					int confirm = JOptionPane.showConfirmDialog(null, labelCancel,"Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
	   					if (confirm == 0) {
	   						return false;
	   					}
		   	        }
	   	        }while(!input.equals("1"));
	   	        String paymentBill = "";
   	         	if(reservation.getCustomer().getPoints()> 100){
   	         		paymentBill=String.format("\nMember Point Discount: RM%.2f<br>Total Price: RM%.2f<br>",Payment.memberPointDiscount, payment.calculatePrice(memberPointDiscount));
   	         	}else{
   	         		paymentBill=String.format("\nMember Point Discount: RM%.2f<br>Total Price: RM%.2f<br>",0.00, payment.calculatePrice(0));
   	         	}
	   	    	JLabel showPayment = new JLabel("<html><b>Payment Bill</b><br>"+paymentBill+"<br>-1 to quit.<br>1 to confirm pay.</html>");
   				showPayment.setFont(new Font("Arial", Font.PLAIN, 18));
   				
   				JLabel labelCancel2 = new JLabel("<html>Are you sure want to <b>Cancel Payment</b>?</html>");
   				labelCancel2.setFont(new Font("Arial", Font.PLAIN, 18));
   				do {
   					input = JOptionPane.showInputDialog(null, showPayment,"Payment",JOptionPane.INFORMATION_MESSAGE);
			    	if (input==null) {
			    		input = "-1";
			    	}
		    	}while(input.isEmpty());
   				if (input.equals("-1")) {
   					int confirm2 = JOptionPane.showConfirmDialog(null, labelCancel2,"Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
   					if (confirm2 == 0) {
   						return false;
   					}
   				}else if(input.equals("1")){
   					pay = 0;
   					String result = "";
   					boolean validOutput = false;
   					do {
   						validOutput = false;
   						label = new JLabel("<html>Enter the <b>Payment Amount</b> by customer(-1 to cancel)</b></html>");
   						label.setFont(new Font("Arial", Font.PLAIN, 18));
   						do {
   					    	result = JOptionPane.showInputDialog(null, label,"Edit reservation",JOptionPane.INFORMATION_MESSAGE);
   					    	if (result==null) {
   					    		result = "-1";
   					    	}
   				    	}while(result.isEmpty());
   						result = result.trim();
   						if (result.equals("-1")) {
   							return false;
   						}
   						try {
   							pay = Integer.parseInt(result);
   							validOutput = true;
   						}catch(Exception ex) {
   							JLabel label2 = new JLabel("<html><p style='color:red'>Invalid integer!</p></html>");
   			    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
   			    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
   			    	        validOutput = false;
   						}
   						if (pay < payment.getTotalPrice()) {
   							JLabel label2 = new JLabel("<html><p style='color:red'>Invalid payment amount!</p></html>");
		   	    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
		   	    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
   						}
   					}while(pay < payment.getTotalPrice()||validOutput==false);
   					valid = true;
   				}else {
   					valid = false;
   					JLabel label2 = new JLabel("<html><p style='color:red'>Invalid choice!</p></html>");
	    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
	    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
   				}
	   		}while(valid==false);
	   		payment.setPay(pay);
	   		payment.setBalance(pay-payment.getTotalPrice());
	   		int pointEarn = (int)(payment.getTotalPrice()/10.00);
	   		int pointUse = 0;
	   		if(reservation.getCustomer().getPoints()> 100){
	   			pointUse = 100;
	   		}
	   		reservation.getCustomer().setPoints(payment.reservation.getCustomer().getPoints()-pointUse+pointEarn);
	   		String message = String.format("Balance: %.2f<br>Points Earn: %d<br>Point Left: %d!", payment.getBalance(),pointEarn,payment.reservation.getCustomer().getPoints()-pointUse+pointEarn);
	   		String paymentMessage = String.format("Member Point Discount: RM%.2f<br>Total Price: RM%.2f<br>Payment Amount: RM%.2f<br>Balance: %.2f<br>", pointUse==100?10.00:0.00,payment.totalPrice,payment.pay,payment.balance);
	   		JLabel label2 = new JLabel("<html><p style='color:green'>"+message+"</p></html>");
	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
	        JOptionPane.showMessageDialog(null, label2, "Succesful Pay",JOptionPane.INFORMATION_MESSAGE);
	        label2 = new JLabel("<html><b>Payment Details	</b>" + dateFormat.format(now) + "<br>----------------------------------------------------------------"
   	   				+ "<br>"+info+"<br>[Reservation Info]<br>"+items+"<br>"+paymentMessage+"</html>");
	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
	        JOptionPane.showMessageDialog(null, label2, "Receipt",JOptionPane.INFORMATION_MESSAGE);
	        Transaction.getTransactionList()[Transaction.getTotalTransaction()]= new ReservationTransaction(reservation, payment);
	        if (pointUse>0) {
	        	 Transaction.getTransactionList()[Transaction.getTotalTransaction()]= new PointTransaction(reservation.getCustomer(),-pointUse);
	        }
	        if (pointEarn>0) {
	        	Transaction.getTransactionList()[Transaction.getTotalTransaction()]= new PointTransaction(reservation.getCustomer(),pointEarn);
	        }
	        
	        return true;
	    }
}
