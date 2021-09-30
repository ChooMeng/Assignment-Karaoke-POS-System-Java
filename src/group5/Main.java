package group5;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import group5.transaction.Report;
import group5.transaction.Transaction;

public class Main {
	public static void main(String[] args) {
		//Run default data
		Customer.defaultCustomer();
		SalesPerson.defaultSalesPerson();
		RoomType.defaultRoomType();
		Room.defaultRoom();
		Reservation.defaultReservation();
		Transaction.defaultTransaction();
		boolean logout = false;
		//Authentication
		Login login = new Login();
		do {
			logout=false;
			boolean validLogin = false;
			do {
				login.loginGui();
				validLogin = login.validation();
				if (validLogin != true) {
					login.displayWrong();
				}
			}while(validLogin!=true);
			login.displayWelcome();
			selectionMenu();
		}while(logout==false);
	}
	public static void selectionMenu() {
		//Action Menu
		boolean valid = false;
		int choice = 0;
		do {
			choice = 0;
			String result = "";
			JLabel label = new JLabel("<html><b>Main Menu</b><br>1. Customer<br>2. SalesPerson<br>3. Check in<br>4. Check out<br>5. Reservation Menu<br>6. Room Menu <br>7. Room Type Menu <br>8. View transaction<br>9. Report<br>-1. Logout</html>");
	    	label.setFont(new Font("Arial", Font.PLAIN, 18));
			do {
    	    	result = JOptionPane.showInputDialog(null, label,"Main Menu",JOptionPane.INFORMATION_MESSAGE);
    	    	if (result==null) {
    	    		result = "-1";
    	    	}
        	}while(result.isEmpty());
			result = result.trim();
			JLabel label3 = new JLabel("<html>Are you sure want to <b>Logout</b>?</html>");
			label3.setFont(new Font("Arial", Font.PLAIN, 18));
			if (result.equals("-1")) {
				int confirm = JOptionPane.showConfirmDialog(null, label3,"Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
				if (confirm == 0) {
					return;
				}else {
					result = "3";
				}
				
			}
			try {
				choice = Integer.parseInt(result);
			}catch (Exception e) {
				JLabel label2 = new JLabel("<html><p style='color:red'>Invalid choice number!</p></html>");
    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
				valid=false;
			}
			switch(choice) {
				case 1:
					Customer.customerMenu();
					break;
				case 2:
					SalesPerson.salesPersonMenu();
					break;
				case 3:
					Reservation.reservationCheckIn();
					break;
				case 4:
					Reservation.reservationCheckOut();
					break;
				case 5:
					Reservation.reservationMenu();
					break;
				case 6:
					Room.roomMenu();
					break;
				case 7:
					RoomType.roomTypeMenu();
					break;
				case 8:
					Transaction.viewTransaction();
					break;
				case 9:
					Report.reportMenu();
					break;
				default:
        			label3 = new JLabel("<html><p style='color:red'>Incorrect choice number!</p></html>");
	    	    	label3.setFont(new Font("Arial", Font.BOLD, 16));
	    	        JOptionPane.showMessageDialog(null, label3, "ERROR",JOptionPane.ERROR_MESSAGE);
	    	        break;
			}
		}while(valid==false||choice!=-1);
	}
	public static Date StringToDate(String str) {
		Date date = new Date();
		try {
			date = new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return date;
	}

	
	
}
