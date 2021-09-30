package group5.transaction;

import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import group5.Main;
import group5.Payment;
import group5.Reservation;

public class Transaction {
	private static int totalTransaction = 0;
	private static Transaction[] transactionList = new Transaction[1000];
	private String transactionNum;
	private Date transactionDate;
	public Transaction() {
		transactionNum = "TRS-"+(totalTransaction+1);
		transactionDate = new Date();
		totalTransaction++;
	}
	public Transaction(Date transactionDate) {
		transactionNum = "TRS-"+(totalTransaction+1);
		this.transactionDate = transactionDate;
		totalTransaction++;
	}
	//getter
	public String getTransactionNum() {
		return transactionNum;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public static Transaction[] getTransactionList() {
		return transactionList;
	}
	public static int getTotalTransaction() {
		return totalTransaction;
	}
	//setter
	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}
	public void setTransactionType(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public static void setTransactionList(Transaction[] transactionList) {
		Transaction.transactionList = transactionList;
	}
	public static void setTotalTransaction(int totalTransaction) {
		Transaction.totalTransaction = totalTransaction;
	}
	//Convert the data value into string
	public String toString() {
		DateFormat dateFormat = new SimpleDateFormat( "dd-MM-YYYY HH:mm" );
		return String.format("[%s] %s\n", transactionNum,dateFormat.format(transactionDate));
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof Transaction )) {
			return false;
		}
		Transaction o2 = (Transaction) o;
		if ((this.getTransactionNum() != null && o2.getTransactionNum()!=null)&&this.getTransactionNum().equals(o2.getTransactionNum())) {
			return true;
		}
		return false;
	}
	//Set the default value of the transaction
	public static void defaultTransaction() {
		transactionList[0] = new ReservationTransaction(Reservation.getReservationList()[0], new Payment(36),Main.StringToDate("12-5-2020 15:50"));
		transactionList[1] = new ReservationTransaction(Reservation.getReservationList()[1], new Payment(36),Main.StringToDate("12-5-2020 16:50"));
		transactionList[2] = new ReservationTransaction(Reservation.getReservationList()[2], new Payment(9),Main.StringToDate("12-5-2020 17:50"));
		transactionList[3] = new ReservationTransaction(Reservation.getReservationList()[3], new Payment(24),Main.StringToDate("13-5-2020 10:50"));
		transactionList[4] = new ReservationTransaction(Reservation.getReservationList()[4], new Payment(36),Main.StringToDate("13-5-2020 11:50"));
		transactionList[5] = new ReservationTransaction(Reservation.getReservationList()[5], new Payment(24),Main.StringToDate("13-5-2020 14:50"));
		transactionList[6] = new ReservationTransaction(Reservation.getReservationList()[6], new Payment(9),Main.StringToDate("13-5-2020 15:50"));
		transactionList[7] = new ReservationTransaction(Reservation.getReservationList()[7], new Payment(12),Main.StringToDate("13-5-2020 17:50"));
		transactionList[8] = new ReservationTransaction(Reservation.getReservationList()[8], new Payment(36),Main.StringToDate("14-5-2020 9:40"));
		transactionList[9] = new ReservationTransaction(Reservation.getReservationList()[9], new Payment(18),Main.StringToDate("14-5-2020 10:50"));
		transactionList[10] = new ReservationTransaction(Reservation.getReservationList()[10], new Payment(24),Main.StringToDate("14-5-2020 15:50"));
		transactionList[11] = new ReservationTransaction(Reservation.getReservationList()[11], new Payment(24),Main.StringToDate("15-5-2020 8:50"));
		transactionList[12] = new ReservationTransaction(Reservation.getReservationList()[12], new Payment(54),Main.StringToDate("15-5-2020 11:50"));
		transactionList[13] = new ReservationTransaction(Reservation.getReservationList()[13], new Payment(27),Main.StringToDate("15-5-2020 13:50"));
		transactionList[14] = new ReservationTransaction(Reservation.getReservationList()[14], new Payment(36),Main.StringToDate("16-5-2020 10:50"));
		transactionList[15] = new ReservationTransaction(Reservation.getReservationList()[15], new Payment(24),Main.StringToDate("16-5-2020 12:50"));
		transactionList[16] = new ReservationTransaction(Reservation.getReservationList()[16], new Payment(18),Main.StringToDate("16-5-2020 14:50"));
		transactionList[17] = new ReservationTransaction(Reservation.getReservationList()[17], new Payment(12),Main.StringToDate("17-5-2020 12:50"));
		transactionList[18] = new PointTransaction(Reservation.getReservationList()[0].getCustomer(), 36,Main.StringToDate("12-5-2020 15:50"));
		transactionList[19] = new PointTransaction(Reservation.getReservationList()[1].getCustomer(), 36,Main.StringToDate("12-5-2020 16:50"));
		transactionList[20] = new PointTransaction(Reservation.getReservationList()[2].getCustomer(), 9,Main.StringToDate("12-5-2020 17:50"));
		transactionList[21] = new PointTransaction(Reservation.getReservationList()[3].getCustomer(), 24,Main.StringToDate("13-5-2020 10:50"));
		transactionList[22] = new PointTransaction(Reservation.getReservationList()[4].getCustomer(), 36,Main.StringToDate("13-5-2020 11:50"));
		transactionList[23] = new PointTransaction(Reservation.getReservationList()[5].getCustomer(), 24,Main.StringToDate("13-5-2020 14:50"));
		transactionList[24] = new PointTransaction(Reservation.getReservationList()[6].getCustomer(), 9,Main.StringToDate("13-5-2020 15:50"));
		transactionList[25] = new PointTransaction(Reservation.getReservationList()[7].getCustomer(), 12,Main.StringToDate("13-5-2020 17:50"));
		transactionList[26] = new PointTransaction(Reservation.getReservationList()[8].getCustomer(), 36,Main.StringToDate("14-5-2020 9:40"));
		transactionList[27] = new PointTransaction(Reservation.getReservationList()[9].getCustomer(), 18,Main.StringToDate("14-5-2020 10:50"));
		transactionList[28] = new PointTransaction(Reservation.getReservationList()[10].getCustomer(), 24,Main.StringToDate("14-5-2020 15:50"));
		transactionList[29] = new PointTransaction(Reservation.getReservationList()[11].getCustomer(), 24,Main.StringToDate("15-5-2020 8:50"));
		transactionList[30] = new PointTransaction(Reservation.getReservationList()[12].getCustomer(), 54,Main.StringToDate("15-5-2020 11:50"));
		transactionList[31] = new PointTransaction(Reservation.getReservationList()[13].getCustomer(), 27,Main.StringToDate("15-5-2020 13:50"));
		transactionList[32] = new PointTransaction(Reservation.getReservationList()[14].getCustomer(), 36,Main.StringToDate("16-5-2020 10:50"));
		transactionList[33] = new PointTransaction(Reservation.getReservationList()[15].getCustomer(), 24,Main.StringToDate("16-5-2020 12:50"));
		transactionList[34] = new PointTransaction(Reservation.getReservationList()[16].getCustomer(), 18,Main.StringToDate("16-5-2020 14:50"));
		transactionList[35] = new PointTransaction(Reservation.getReservationList()[17].getCustomer(), 12,Main.StringToDate("17-5-2020 12:50"));
		transactionList[36] = new PointTransaction(Reservation.getReservationList()[2].getCustomer(), -100,Main.StringToDate("12-5-2020 17:50"));
		transactionList[37] = new PointTransaction(Reservation.getReservationList()[5].getCustomer(), -100,Main.StringToDate("13-5-2020 14:50"));
		transactionList[38] = new PointTransaction(Reservation.getReservationList()[12].getCustomer(), -100,Main.StringToDate("15-5-2020 11:50"));
		transactionList[39] = new PointTransaction(Reservation.getReservationList()[13].getCustomer(), -100,Main.StringToDate("15-5-2020 13:50"));
		transactionList[40] = new PointTransaction(Reservation.getReservationList()[17].getCustomer(), -100,Main.StringToDate("17-5-2020 12:50"));
	}
	//Display the transaction details
	public static void viewTransaction() {
		boolean transactionExist = false;
		String transactionNum = "";
		String result = "";
		int transactionIndex = 0;
		JLabel label = new JLabel("<html>Enter the <b>Transaction Num</b> to view [Eg: TRS-x] (-1 to cancel)</html>");
    	label.setFont(new Font("Arial", Font.PLAIN, 18));
    	do {
    		transactionExist = false;
	    	do {
		    	result = JOptionPane.showInputDialog(null, label,"View transaction",JOptionPane.INFORMATION_MESSAGE);
		    	if (result==null) {
		    		result = "-1";
		    	}
	    	}while(result.isEmpty());
	    		result = result.trim();
			if (result.equals("-1")) {
				return;
			}
			transactionNum = result;
			for (int i = 0;i < Transaction.getTotalTransaction();i++) {
				if (Transaction.getTransactionList()[i].getTransactionNum().toLowerCase().equals(transactionNum.toLowerCase())) {
					transactionIndex = i;
					transactionExist = true;
					break;
				}
			}
			if (!transactionExist) {
				JLabel label3 = new JLabel("<html><p style='color:red'>Transaction Num not found!</p></html>");
				label3.setFont(new Font("Arial", Font.BOLD, 16));
				JOptionPane.showMessageDialog(null, label3, "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} while (!transactionExist);
    	label = new JLabel("<html>"+Transaction.getTransactionList()[transactionIndex].toString().replace("\n", "<br>")+"</html>");
    	label.setFont(new Font("Arial", Font.BOLD, 16));
        JOptionPane.showMessageDialog(null, label, "RESULT",JOptionPane.INFORMATION_MESSAGE);
	}
}
