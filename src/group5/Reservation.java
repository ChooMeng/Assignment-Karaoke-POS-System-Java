package group5;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Reservation {
	private static int totalReservation;
	private static Reservation[] reservationList = new Reservation[9999];
	private static HashMap<String,Integer> reserveMap = new HashMap<String,Integer>();
	private String reservationID;
	private SalesPerson salesperson;
	private Customer customer;
	private double totalCosts;
	private Date dateRequested;
	private String remark;
	private ReservationDetails details;
	private static final long ONE_MINUTE_IN_MILLIS = 60000;
	private static final int MAXDISPLAYRESULT = 15; 
	//Constructor
	public Reservation() {
		
	}
	public Reservation(double totalCosts,String remark, ReservationDetails details, Customer customer) {
		reservationID = "Res"+(totalReservation+1);
		reserveMap.put(reservationID, totalReservation);
		dateRequested = new Date();
		this.remark = remark;
		this.totalCosts = totalCosts;
		this.details = details;
		this.customer = customer;
		this.salesperson = Login.getAuthAccount();
		totalReservation++;
	}
	public Reservation(double totalCosts,String remark, ReservationDetails details, Customer customer, SalesPerson salesperson) {
		reservationID = "Res"+(totalReservation+1);
		reserveMap.put(reservationID, totalReservation);
		dateRequested = new Date();
		this.remark = remark;
		this.totalCosts = totalCosts;
		this.details = details;
		this.customer = customer;
		this.salesperson = salesperson;
		totalReservation++;
	}
	//Getter
	public String getReservationID() {
		return reservationID;
	}
	public double getTotalCosts() {
		return totalCosts;
	}
	public Date getDateRequested() {
		return dateRequested;
	}
	public String getRemark() {
		return remark;
	}
	public ReservationDetails getDetails() {
		return details;
	}
	public SalesPerson getSalesPerson() {
		return salesperson;
	}
	public Customer getCustomer() {
		return customer;
	}
	public static int getTotalReservation() {
		return totalReservation;
	}
	public static Reservation[] getReservationList() {
		return reservationList;
	}
	public void setReservationID(String reservationID) {
		this.reservationID = reservationID;

	}
	public void setTotalCosts(double totalCosts) {
		this.totalCosts = totalCosts;
	}
	public void setDateRequested(Date dateRequested) {
		this.dateRequested = dateRequested;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setDetails(ReservationDetails details) {
		this.details = details;
	}
	public void setSalesPerson(SalesPerson salesperson) {
		this.salesperson = salesperson;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public static void setTotalReservation(int totalReservation) {
		Reservation.totalReservation = totalReservation;
	}
	public static void setReservationList(Reservation[] reservationList) {
		Reservation.reservationList = reservationList;
	}
	public String toString() {
		DateFormat dateFormat = new SimpleDateFormat( "dd-MM-YYYY HH:mm" );
		return String.format("--------RESERVATION DETAILS--------\n"
							+"[%s]\n"
							+ "Date Requested: %s\n"
							+ "Customer: %s [%s]\n"
							+ "Sales Person: %s [%s]\n"
							+ "Total Costs: %.2f\n"
							+ "Remarks: %s"
							+ "%s", this.reservationID,dateFormat.format(this.dateRequested),this.customer.getName(),this.customer.getCustomerID(),this.salesperson.getName(),this.salesperson.getSalesUserName(),this.totalCosts,this.remark,this.details.toString());
	}
	// Create new object for compare two variable
	public boolean equals(Object o) {
		if (!(o instanceof Reservation )) {
			return false;
		}
		Reservation o2 = (Reservation) o;
		if ((this.reservationID != null && o2.reservationID!=null)&&this.reservationID.equals(o2.reservationID)) {
			return true;
		}
		return false;
	}
	// Create new checkin reservation record
	public void checkIn() {
		details.setStatus("CheckIn");
    	details.setTimeCheckIn(new Date());
	}
	// Create new checkout reservation record
	public void checkOut() {
		details.setStatus("CheckOut");
    	details.setTimeCheckout(new Date());
	}
	// Create new reservation record
	public static void addReservation() {
		double totalCosts = 0;
		String remark = "", customerID = "";
		int roomUsers = 0,roomIndex = 0,customerIndex = 0;
		Date timeReserved = new Date();
		Date timeReservedEnd = new Date();
		boolean validOutput = true, customerIDExist = false;
		String result = "", roomID = "";
		ReservationDetails details = new ReservationDetails();
		Room room = new Room();
		//Enter amount of room users for the reservation
		JLabel label = new JLabel("<html>Enter the <b>Customer ID</b> that going to reserve the room (-1 to cancel)</html>");
		label.setFont(new Font("Arial", Font.PLAIN, 18));
		validOutput = true;
		int timeDuration = 0;
		Customer.listCustomer();
		do {
			customerIDExist = false;
			do {
				result = JOptionPane.showInputDialog(null, label, "Create reservation", JOptionPane.INFORMATION_MESSAGE);
				if (result == null) {
					result = "-1";
				}
			} while (result.isEmpty());
			result = result.trim();
			if (result.equals("-1")) {
				return;
			}
			customerID = result;
			
			for (int i = 0;i < Customer.getTotalCustomer();i++) {
				if (Customer.getCustomerList()[i].getCustomerID().equals(customerID)) {
					customerIndex = i;
					customerIDExist = true;
					break;
				}
			}
			if (!customerIDExist) {
				JLabel label2 = new JLabel("<html><p style='color:red'>Customer ID not found!</p></html>");
				label2.setFont(new Font("Arial", Font.BOLD, 16));
				JOptionPane.showMessageDialog(null, label2, "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} while (!customerIDExist);
		//Enter amount of room users for the reservation
		label = new JLabel("<html>Enter the amount of <b>Room Users</b> that going to use the room (-1 to cancel)</html>");
		label.setFont(new Font("Arial", Font.PLAIN, 18));
		validOutput = true;
		do {
			do {
		    	result = JOptionPane.showInputDialog(null, label,"Create reservation",JOptionPane.INFORMATION_MESSAGE);
		    	if (result==null) {
		    		result = "-1";
		    	}
	    	}while(result.isEmpty());
			result = result.trim();
			if (result.equals("-1")) {
				return;
			}
			try {
				roomUsers = Integer.parseInt(result);
				validOutput = true;
			}catch (Exception e) {
				JLabel label2 = new JLabel("<html><p style='color:red'>Invalid number!</p></html>");
    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
				validOutput=false;
			}
		}while(validOutput==false);
		//Enter the time reserved that customer going to reserve the room
		label = new JLabel("<html>Enter the <b>Time Reserved</b> [dd-MM-yyyy HH:mm] (-1 to cancel)<br><Time that going to use the room></html>");
		label.setFont(new Font("Arial", Font.PLAIN, 18));
		validOutput = true;
		do {
			do {
		    	result = JOptionPane.showInputDialog(null, label,"Create reservation",JOptionPane.INFORMATION_MESSAGE);
		    	if (result==null) {
		    		result = "-1";
		    	}
	    	}while(result.isEmpty());
			result = result.trim();
			if (result.equals("-1")) {
				return;
			}
			try {
				timeReserved = new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(result);
				validOutput = true;
			}catch (Exception e) {
				JLabel label2 = new JLabel("<html><p style='color:red'>Invalid date!</p></html>");
    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
				validOutput=false;
			}
		}while(validOutput==false);
		//Enter amount of room users for the reservation
		label = new JLabel("<html>Enter the <b>Duration</b> that going to use the room (-1 to cancel)</html>");
		label.setFont(new Font("Arial", Font.PLAIN, 18));
		validOutput = true;
		do {
			do {
		    	result = JOptionPane.showInputDialog(null, label,"Create reservation",JOptionPane.INFORMATION_MESSAGE);
		    	if (result==null) {
		    		result = "-1";
		    	}
	    	}while(result.isEmpty());
			result = result.trim();
			if (result.equals("-1")) {
				return;
			}
			try {
				timeDuration = Integer.parseInt(result);
				long time = timeReserved.getTime();
				timeReservedEnd = new Date(time+(timeDuration*ONE_MINUTE_IN_MILLIS));
				validOutput = true;
			}catch (Exception e) {
				JLabel label2 = new JLabel("<html><p style='color:red'>Invalid number!</p></html>");
    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
				validOutput=false;
			}
		}while(validOutput==false);
		Room.listRoom();
		label = new JLabel("<html>Enter the <b>Room ID</b> that want to select (-1 to cancel)</html>");
		label.setFont(new Font("Arial", Font.PLAIN, 18));
		do {
			do {
				result = JOptionPane.showInputDialog(null, label, "Create reservation", JOptionPane.INFORMATION_MESSAGE);
				if (result == null) {
					result = "-1";
				}
			} while (result.isEmpty());
			result = result.trim();
			if (result.equals("-1")) {
				return;
			}
			roomID = result;
			if (Room.roomMap.containsKey(roomID)) {
				roomIndex = Room.roomMap.get(roomID);

			} else {
				JLabel label2 = new JLabel("<html><p style='color:red'>Room ID not found!</p></html>");
				label2.setFont(new Font("Arial", Font.BOLD, 16));
				JOptionPane.showMessageDialog(null, label2, "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} while (!Room.roomMap.containsKey(roomID));
		room = Room.getRoomList()[roomIndex];
		label = new JLabel("<html>Enter the <b>Remarks</b> (-1 to cancel)</html>");
    	label.setFont(new Font("Arial", Font.PLAIN, 18));
    	do {
	    	result = JOptionPane.showInputDialog(null, label,"Create room type",JOptionPane.INFORMATION_MESSAGE);
	    	if (result==null) {
	    		result = "-1";
	    	}
    	}while(result.isEmpty());
    	result = result.trim();
		if (result.equals("-1")) {
			return;
		}
		remark = result;
		int hour = (int) Math.ceil(timeDuration/60.0);
		totalCosts += (room.getRoomType().getRoomPricePerPax()*roomUsers*hour);
		details = new ReservationDetails(roomUsers, timeReserved, timeReservedEnd, room);
		Reservation reservation = new Reservation(totalCosts, remark, details,Customer.getCustomerList()[customerIndex]);
		if (Payment.pay(reservation)) {
			reservationList[totalReservation-1] = reservation;
			JLabel label2 = new JLabel("<html><p style='color:green'>SUCCESFUL CREATED A NEW RESERVATION!</p></html>");
	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
	        JOptionPane.showMessageDialog(null, label2, "Succesful Created",JOptionPane.INFORMATION_MESSAGE);
		}else {
			JLabel label2 = new JLabel("<html><p style='color:red'>Cancelled to create reservation!</p></html>");
			label2.setFont(new Font("Arial", Font.BOLD, 16));
			JOptionPane.showMessageDialog(null, label2, "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	// Default reservation record
	public static void defaultReservation() {
		reservationList[0] = new Reservation(360, "-", new ReservationDetails(6, Main.StringToDate("12-5-2020 15:50"), Main.StringToDate("12-5-2020 18:50"), Room.getRoomList()[0]),Customer.getCustomerList()[2],SalesPerson.getSalesPersonList()[1]);
		reservationList[1] = new Reservation(360, "-", new ReservationDetails(6, Main.StringToDate("12-5-2020 16:50"), Main.StringToDate("12-5-2020 18:50"), Room.getRoomList()[2]),Customer.getCustomerList()[3],SalesPerson.getSalesPersonList()[1]);
		reservationList[2] = new Reservation(90, "-", new ReservationDetails(6, Main.StringToDate("12-5-2020 17:50"), Main.StringToDate("12-5-2020 18:50"), Room.getRoomList()[1]),Customer.getCustomerList()[1],SalesPerson.getSalesPersonList()[1]);
		reservationList[3] = new Reservation(240, "-", new ReservationDetails(6, Main.StringToDate("13-5-2020 10:50"), Main.StringToDate("13-5-2020 12:50"), Room.getRoomList()[4]),Customer.getCustomerList()[2],SalesPerson.getSalesPersonList()[2]);
		reservationList[4] = new Reservation(360, "-", new ReservationDetails(6, Main.StringToDate("13-5-2020 11:50"), Main.StringToDate("13-5-2020 13:50"), Room.getRoomList()[2]),Customer.getCustomerList()[2],SalesPerson.getSalesPersonList()[2]);
		reservationList[5] = new Reservation(240, "-", new ReservationDetails(6, Main.StringToDate("13-5-2020 14:50"), Main.StringToDate("13-5-2020 15:50"), Room.getRoomList()[3]),Customer.getCustomerList()[4],SalesPerson.getSalesPersonList()[0]);
		reservationList[6] = new Reservation(90, "-", new ReservationDetails(6, Main.StringToDate("13-5-2020 15:50"), Main.StringToDate("13-5-2020 16:50"), Room.getRoomList()[1]),Customer.getCustomerList()[3],SalesPerson.getSalesPersonList()[0]);
		reservationList[7] = new Reservation(120, "-", new ReservationDetails(6, Main.StringToDate("13-5-2020 17:50"), Main.StringToDate("13-5-2020 18:50"), Room.getRoomList()[4]),Customer.getCustomerList()[1],SalesPerson.getSalesPersonList()[0]);
		reservationList[8] = new Reservation(360, "-", new ReservationDetails(6, Main.StringToDate("14-5-2020 9:40"), Main.StringToDate("14-5-2020 12:50"), Room.getRoomList()[0]),Customer.getCustomerList()[2],SalesPerson.getSalesPersonList()[3]);
		reservationList[9] = new Reservation(180, "-", new ReservationDetails(6, Main.StringToDate("14-5-2020 10:50"), Main.StringToDate("14-5-2020 12:50"), Room.getRoomList()[1]),Customer.getCustomerList()[0],SalesPerson.getSalesPersonList()[3]);
		reservationList[10] = new Reservation(240, "-", new ReservationDetails(6, Main.StringToDate("14-5-2020 15:50"), Main.StringToDate("14-5-2020 17:50"), Room.getRoomList()[3]),Customer.getCustomerList()[1],SalesPerson.getSalesPersonList()[1]);
		reservationList[11] = new Reservation(240, "-", new ReservationDetails(6, Main.StringToDate("15-5-2020 8:50"), Main.StringToDate("15-5-2020 10:50"), Room.getRoomList()[4]),Customer.getCustomerList()[3],SalesPerson.getSalesPersonList()[1]);
		reservationList[12] = new Reservation(540, "-", new ReservationDetails(6, Main.StringToDate("15-5-2020 11:50"), Main.StringToDate("15-5-2020 14:50"), Room.getRoomList()[2]),Customer.getCustomerList()[2],SalesPerson.getSalesPersonList()[1]);
		reservationList[13] = new Reservation(270, "-", new ReservationDetails(6, Main.StringToDate("15-5-2020 13:50"), Main.StringToDate("15-5-2020 14:50"), Room.getRoomList()[5]),Customer.getCustomerList()[4],SalesPerson.getSalesPersonList()[1]);
		reservationList[14] = new Reservation(360, "-", new ReservationDetails(6, Main.StringToDate("16-5-2020 10:50"), Main.StringToDate("16-5-2020 12:50"), Room.getRoomList()[2]),Customer.getCustomerList()[2],SalesPerson.getSalesPersonList()[1]);
		reservationList[15] = new Reservation(240, "-", new ReservationDetails(6, Main.StringToDate("16-5-2020 12:50"), Main.StringToDate("16-5-2020 14:50"), Room.getRoomList()[3]),Customer.getCustomerList()[1],SalesPerson.getSalesPersonList()[2]);
		reservationList[16] = new Reservation(180, "-", new ReservationDetails(6, Main.StringToDate("16-5-2020 14:50"), Main.StringToDate("16-5-2020 16:50"), Room.getRoomList()[1]),Customer.getCustomerList()[3],SalesPerson.getSalesPersonList()[2]);
		reservationList[17] = new Reservation(120, "-", new ReservationDetails(6, Main.StringToDate("17-5-2020 12:50"), Main.StringToDate("17-5-2020 13:50"), Room.getRoomList()[0]),Customer.getCustomerList()[2],SalesPerson.getSalesPersonList()[2]);
	}
	//Display all the reservation list with summarized details
	public static void listReservation() {
		int choice = -5;
		boolean valid = false;
		int page = 1;
		String input = "", dateCheckIn = "", dateCheckOut = "";
		int maxPage = (int) Math.ceil(totalReservation/MAXDISPLAYRESULT);
		do {
			int maxResult = totalReservation-((page-1)*MAXDISPLAYRESULT);
			int from = totalReservation-(MAXDISPLAYRESULT*page);
			if (from <0) {
				from = 0;
			}
			valid = true;
			String listStatement = "<html><h1 style='text-align:center'>Reservation List</h2><table><tr><th colspan='9' style='text-align:right;'>Page "+page+"</th><tr><td><b>| ReservationID </b></td><td><b>| Customer Name </td><td><b>| Time Reserved </b></td><td><b>| Room Users </b></td><td><b>| Room ID </b></td><td><b>| Room Type </b></td><td><b>| Status </b></td><td><b>| Check In </b></td><td><b>| Check Out </b></td><td><b>| Sales Person</b></td></tr>";
			for (int i = maxResult-1; i > from-1;i--) {
				
				DateFormat dateFormat = new SimpleDateFormat( "dd-MM-YYYY HH:mm" );
				String dateDisplayedStart = dateFormat.format(reservationList[i].details.getTimeReserved());
				dateFormat = new SimpleDateFormat( "HH:mm" );
				String dateDisplayedEnd = dateFormat.format(reservationList[i].details.getTimeReservedEnd());
				dateFormat = new SimpleDateFormat( "dd-MM-YYYY HH:mm" );
				if (reservationList[i].details.getTimeCheckIn()==null) {
					dateCheckIn = "-";
				}else {
					dateCheckIn = dateFormat.format(reservationList[i].details.getTimeCheckIn());
				}
				if (reservationList[i].details.getTimeCheckOut()==null) {
					dateCheckOut = "-";
				}else {
					dateCheckOut = dateFormat.format(reservationList[i].details.getTimeCheckOut());
				}
				
				
				listStatement += String.format("<tr><td>| %s </td><td>| %s</td><td>| %s - %s </td><td>| %d </td><td>| %s </td><td>| %s </td><td>| %s </td><td>| %s </td><td>| %s </td><td>| %s</td></tr>", reservationList[i].getReservationID(), reservationList[i].getCustomer().getName(), 
						dateDisplayedStart,dateDisplayedEnd,reservationList[i].details.getRoomUsers(),reservationList[i].details.getRoom().getRoomID(),reservationList[i].details.getRoom().getRoomType().getRoomName(),reservationList[i].details.getStatus()
						,dateCheckIn, dateCheckOut, reservationList[i].getSalesPerson().getName());
			}
			listStatement += "</table><b>-1 - return to reservation menu<br>0 - go to previous page<br>1 - go to next page</b><br/></html>";
			JLabel label = new JLabel(listStatement);
			label.setFont(new Font("Arial", Font.PLAIN, 18));
			do {
				input = JOptionPane.showInputDialog(null, label, "Reservation List",JOptionPane.INFORMATION_MESSAGE);
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
	        if (choice == 1) {
	        	page++;
	        	if (page>(maxPage+1)) {
	        		JLabel label2 = new JLabel("<html><p style='color:red'>Reach the max pages!</p></html>");
	    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
	    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
	        		page= maxPage+1;
	        	}
	        }else if (choice==0) {
	        	page--;
	        	if (page<1) {
	        		JLabel label2 = new JLabel("<html><p style='color:red'>Reach the min page!</p></html>");
	    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
	    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
	        		page= 1;
	        	}
	        }else if (choice!=-1){
	        	valid = false;
	        }
	        if (!valid) {
	        	JLabel label2 = new JLabel("<html><p style='color:red'>Incorrect choice number!</p></html>");
    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
	        }
		}while(valid==false||choice!=-1);
	}
	//Display all the reservation records in details
	public static void displayReservation() {
		String reservationID = "";
		JLabel label = new JLabel("<html>Enter the <b>Reservation ID</b> that want to display [Eg: Res##] (-1 to cancel)</html>");
    	label.setFont(new Font("Arial", Font.PLAIN, 18));
    	do {
    		do {
    			reservationID = JOptionPane.showInputDialog(null, label,"Display reservation",JOptionPane.INFORMATION_MESSAGE);
    	    	if (reservationID==null) {
    	    		reservationID = "-1";
    	    	}
        	}while(reservationID.isEmpty());
    		reservationID = reservationID.trim();
    		if (reservationID.equals("-1")) {
    			return;
    		}
    		if (reserveMap.containsKey(reservationID)) {
    			int reservationIndex = reserveMap.get(reservationID);
    			System.out.println(reservationList[reservationIndex].toString());
    		}else {
    			JLabel label2 = new JLabel("<html><p style='color:red'>Reservation ID not found!</p></html>");
    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
    		}
    	}while(!reserveMap.containsKey(reservationID));
	}
	//Remove the records from the reservations
	public static void removeReservation() {
		String reservationID = "";
		JLabel label = new JLabel("<html>Enter the <b>Reservation ID</b> that want to remove (-1 to cancel)</html>");
    	label.setFont(new Font("Arial", Font.PLAIN, 18));
    	do {
    		do {
    	    	reservationID = JOptionPane.showInputDialog(null, label,"Remove reservation",JOptionPane.INFORMATION_MESSAGE);
    	    	if (reservationID==null) {
    	    		reservationID = "-1";
    	    	}
        	}while(reservationID.isEmpty());
    		reservationID = reservationID.trim();
    		if (reservationID.equals("-1")) {
    			return;
    		}
			if (!reserveMap.containsKey(reservationID)) {
				JLabel label2 = new JLabel("<html><p style='color:red'>Reservation ID not found!</p></html>");
    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
			}
    	}while(!reserveMap.containsKey(reservationID));
    	int reserveIndex = reserveMap.get(reservationID);
		reserveMap.clear();
		for (int i = 0; i < totalReservation;i++) {
			if (i==reserveIndex) {
				reservationList[i].setReservationID("[OLD] "+reservationList[i].getReservationID());
			}
			if (i>reserveIndex) {
				reservationList[i-1]=reservationList[i];
				String newReservationID = "Res"+i;
				reservationList[i-1].setReservationID(newReservationID);
				
			}
			if (i==(totalReservation-1)) {
				
				reservationList[i]=null;
			}
			
			reserveMap.put("Res"+(i+1), i);
			
		}
		totalReservation--;
		JLabel label2 = new JLabel("<html><p style='color:green'>Succesful removed the reservation!</p></html>");
    	label2.setFont(new Font("Arial", Font.BOLD, 16));
        JOptionPane.showMessageDialog(null, label2, "Succesful Removed",JOptionPane.INFORMATION_MESSAGE);
	}
	//Reservation menu for chosing the action that related to reservation
	public static void reservationMenu() {
		int choice = 0;
		boolean valid = false;
		String input = "";
		do {
			valid = true;
	    	JLabel label = new JLabel("<html><b>Reservation Menu</b><br>1. Create the reservation<br>2. Display the reservation<br>3. Remove the reservation<br>"
	    			+ "4. List the reservation<br>5. Edit the reservation<br>6. Search the reservation<br>-1. Return to main menu</html>");
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
	        			addReservation();
	        			break;
	        		case 2:
	        			displayReservation();
	        			break;
	        		case 3:
	        			removeReservation();
	        			break;
	        		case 4:
	        			listReservation();
	        			break;
	        		case 5:
	        			editReservation();
	        			break;
	        		case 6:
	        			searchReservation();
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
	//Modified the reservation details
	public static void editReservation() {
		int choice = 0;
		boolean valid = false;
		String input = "", reservationID = "";
		do {
			JLabel label = new JLabel("<html>Enter the <b>Reservation ID</b> that want to edit (-1 to cancel)</html>");
	    	label.setFont(new Font("Arial", Font.PLAIN, 18));
	    	do {
		    	reservationID = JOptionPane.showInputDialog(null, label,"Edit reservation",JOptionPane.INFORMATION_MESSAGE);
		    	if (reservationID==null) {
		    		reservationID = "-1";
		    	}
	    	}while(reservationID.isEmpty());
			reservationID = reservationID.trim();
			if (reservationID.equals("-1")) {
				return;
			}
			if (reserveMap.containsKey(reservationID)) {
				int reservationIndex = reserveMap.get(reservationID);
				do {
					valid = true;
					label = new JLabel("<html><b>Reservation Edit Menu</b><br>1. Edit total costs<br>2. Edit remarks<br>3. Edit room users<br>4. Edit time reserved<br>5. Edit time reserved end<br>"
			    			+ "6. Edit time checkin<br>7. Edit time checkout<br>8. Edit status<br>9. Edit room id<br>10. Edit customer id<br>11. Edit staff id<br>-1. Return to room type menu</html>");
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
			        	valid = editReservationChoice(choice,reservationIndex);
			        }else {
			        	JLabel label2 = new JLabel("<html><p style='color:red'>Incorrect choice number!</p></html>");
		    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
		    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
			        }
				}while(valid==false||choice!=-1);
				
			}else {
				JLabel label2 = new JLabel("<html><p style='color:red'>Reservation ID not found!</p></html>");
    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
			}
		}while(!reserveMap.containsKey(reservationID));
	}
	//Modified the reservation details according to the action
	public static boolean editReservationChoice(int choice,int reservationIndex) {
		String result = "", roomID = "", customerID = "", salesUserName = "";
		Integer roomIndex = 0;
		SimpleDateFormat dateFormat = new SimpleDateFormat( "dd-MM-YYYY HH:mm" );
		switch (choice) {
			case 1:
				JLabel label = new JLabel("<html>Current total costs: <b style='color:blue'>RM"+String.format("%.2f", reservationList[reservationIndex].getTotalCosts())+"</b><br>Enter the <b>Total Costs</b> (-1 to cancel)</html>");
				label.setFont(new Font("Arial", Font.PLAIN, 18));
				boolean validOutput = true;
				do {
					validOutput = true;
					do {
				    	result = JOptionPane.showInputDialog(null, label,"Edit reservation",JOptionPane.INFORMATION_MESSAGE);
				    	if (result==null) {
				    		result = "-1";
				    	}
			    	}while(result.isEmpty());
					result = result.trim();
					if (result.equals("-1")) {
						return true;
					}
					try {
						reservationList[reservationIndex].setTotalCosts(Double.parseDouble(result));
					}catch (Exception e) {
						JLabel label2 = new JLabel("<html><p style='color:red'>Invalid total costs of reservation!</p></html>");
		    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
		    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
						validOutput=false;
					}
				}while(validOutput==false);
				JLabel label2 = new JLabel("<html><p style='color:green'>Succesful edited the total costs of reservation!</p></html>");
		    	label2.setFont(new Font("Arial", Font.BOLD, 16));
		        JOptionPane.showMessageDialog(null, label2, "Succesful Edited",JOptionPane.INFORMATION_MESSAGE);
				break;
			case 2:
				label = new JLabel("<html>Current reservation remarks: <b style='color:blue'>"+reservationList[reservationIndex].getRemark()+"</b><br/>Enter the <b>Reservation Remarks</b> (-1 to cancel)</html>");
		    	label.setFont(new Font("Arial", Font.PLAIN, 18));
		    	do {
			    	result = JOptionPane.showInputDialog(null, label,"Edit reservation",JOptionPane.INFORMATION_MESSAGE);
			    	if (result==null) {
			    		result = "-1";
			    	}
		    	}while(result.isEmpty());
		    	result = result.trim();
				if (result.equals("-1")) {
					return true;
				}
				reservationList[reservationIndex].setRemark(result);
				label2 = new JLabel("<html><p style='color:green'>Succesful edited the reservation remarks!</p></html>");
		    	label2.setFont(new Font("Arial", Font.BOLD, 16));
		        JOptionPane.showMessageDialog(null, label2, "Succesful Edited",JOptionPane.INFORMATION_MESSAGE);
				break;
			case 3:
				label = new JLabel("<html>Current reservation room users: <b style='color:blue'>"+reservationList[reservationIndex].details.getRoomUsers()+"</b><br/>Enter the <b>Room Users</b> (-1 to cancel)</html>");
				label.setFont(new Font("Arial", Font.PLAIN, 18));
				validOutput = true;
				do {
					do {
				    	result = JOptionPane.showInputDialog(null, label,"Edit reservation",JOptionPane.INFORMATION_MESSAGE);
				    	if (result==null) {
				    		result = "-1";
				    	}
			    	}while(result.isEmpty());
					result = result.trim();
					if (result.equals("-1")) {
						return true;
					}
					try {
						reservationList[reservationIndex].details.setRoomUsers(Integer.parseInt(result));
						Room room = reservationList[reservationIndex].details.getRoom();
						long diff = reservationList[reservationIndex].details.getTimeReservedEnd().getTime()-reservationList[reservationIndex].details.getTimeReserved().getTime();
						long duration = TimeUnit.MILLISECONDS.toMinutes(diff);
						int hour = (int)Math.ceil(duration/60.0);
						System.out.println(room.getRoomType().getRoomPricePerPax());
						double totalCosts = (room.getRoomType().getRoomPricePerPax()*Integer.parseInt(result)*hour);
						reservationList[reservationIndex].setTotalCosts(totalCosts);
						validOutput = true;
					}catch (Exception e) {
						label2 = new JLabel("<html><p style='color:red'>Invalid number!</p></html>");
		    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
		    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
						validOutput=false;
					}
				}while(validOutput==false);
				label2 = new JLabel("<html><p style='color:green'>Succesful edited the reservation room users!</p></html>");
		    	label2.setFont(new Font("Arial", Font.BOLD, 16));
		        JOptionPane.showMessageDialog(null, label2, "Succesful Edited",JOptionPane.INFORMATION_MESSAGE);
				break;
			case 4:
				label = new JLabel("<html>Current reservation time reserved: <b style='color:blue'>"+dateFormat.format(reservationList[reservationIndex].details.getTimeReserved())+"</b><br/>Enter the <b>Time Reserved</b> [dd-MM-yyyy HH:mm] (-1 to cancel)</html>");
				label.setFont(new Font("Arial", Font.PLAIN, 18));
				validOutput = true;
				do {
					do {
				    	result = JOptionPane.showInputDialog(null, label,"Edit reservation",JOptionPane.INFORMATION_MESSAGE);
				    	if (result==null) {
				    		result = "-1";
				    	}
			    	}while(result.isEmpty());
					result = result.trim();
					if (result.equals("-1")) {
						return true;
					}
					try {
						Date timereserved = new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(result);
						reservationList[reservationIndex].details.setTimeReserved(timereserved);
						validOutput = true;
					}catch (Exception e) {
						label2 = new JLabel("<html><p style='color:red'>Invalid date and time!</p></html>");
		    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
		    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
						validOutput=false;
					}
				}while(validOutput==false);
				label2 = new JLabel("<html><p style='color:green'>Succesful edited the reservation time reserved!</p></html>");
		    	label2.setFont(new Font("Arial", Font.BOLD, 16));
		        JOptionPane.showMessageDialog(null, label2, "Succesful Edited",JOptionPane.INFORMATION_MESSAGE);
				break;
			case 5:
				long diff = reservationList[reservationIndex].details.getTimeReservedEnd().getTime()-reservationList[reservationIndex].details.getTimeReserved().getTime();
				long duration = TimeUnit.MILLISECONDS.toMinutes(diff);
				label = new JLabel("<html>Current reservation reserved duration: <b style='color:blue'>"+duration+" minutes</b><br/>Enter the <b>Reserved Duration</b> (-1 to cancel)</html>");
				label.setFont(new Font("Arial", Font.PLAIN, 18));
				validOutput = true;
				do {
					do {
				    	result = JOptionPane.showInputDialog(null, label,"Edit reservation",JOptionPane.INFORMATION_MESSAGE);
				    	if (result==null) {
				    		result = "-1";
				    	}
			    	}while(result.isEmpty());
					result = result.trim();
					if (result.equals("-1")) {
						return true;
					}
					Date timeReserved = reservationList[reservationIndex].details.getTimeReserved();
					Date timeReservedEnd = new Date();
					try {
						int timeDuration = Integer.parseInt(result);
						long time = timeReserved.getTime();
						timeReservedEnd = new Date(time+(timeDuration*ONE_MINUTE_IN_MILLIS));
						reservationList[reservationIndex].details.setTimeReservedEnd(timeReservedEnd);
						validOutput = true;
					}catch (Exception e) {
						label2 = new JLabel("<html><p style='color:red'>Invalid number!</p></html>");
		    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
		    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
						validOutput=false;
					}
				}while(validOutput==false);
				label2 = new JLabel("<html><p style='color:green'>Succesful edited the reservation reserved dyratuib!</p></html>");
		    	label2.setFont(new Font("Arial", Font.BOLD, 16));
		        JOptionPane.showMessageDialog(null, label2, "Succesful Edited",JOptionPane.INFORMATION_MESSAGE);
				break;
			case 6:
				if (reservationList[reservationIndex].details.getTimeCheckIn()!=null) {
					label = new JLabel("<html>Current reservation time check in: <b style='color:blue'>"+dateFormat.format(reservationList[reservationIndex].details.getTimeCheckIn())+"</b><br/>Enter the <b>Time Check In</b> [dd-MM-yyyy HH:mm] (-1 to cancel)</html>");
				}else {
					label = new JLabel("<html>Current reservation time check in: <b style='color:blue'>Not yet Checkin</b><br/>Enter the <b>Time Check In</b> [dd-MM-yyyy HH:mm] (-1 to cancel)</html>");
					
				}
				label.setFont(new Font("Arial", Font.PLAIN, 18));
				validOutput = true;
				do {
					do {
				    	result = JOptionPane.showInputDialog(null, label,"Edit reservation",JOptionPane.INFORMATION_MESSAGE);
				    	if (result==null) {
				    		result = "-1";
				    	}
			    	}while(result.isEmpty());
					result = result.trim();
					if (result.equals("-1")) {
						return true;
					}
					try {
						Date timecheckin = new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(result);
						reservationList[reservationIndex].details.setTimeCheckIn(timecheckin);
						validOutput = true;
					}catch (Exception e) {
						label2 = new JLabel("<html><p style='color:red'>Invalid date and time!</p></html>");
		    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
		    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
						validOutput=false;
					}
				}while(validOutput==false);
				label2 = new JLabel("<html><p style='color:green'>Succesful edited the reservation time check in!</p></html>");
		    	label2.setFont(new Font("Arial", Font.BOLD, 16));
		        JOptionPane.showMessageDialog(null, label2, "Succesful Edited",JOptionPane.INFORMATION_MESSAGE);
				break;
			case 7:
				if (reservationList[reservationIndex].details.getTimeCheckOut()!=null) {
					label = new JLabel("<html>Current reservation time check out: <b style='color:blue'>"+dateFormat.format(reservationList[reservationIndex].details.getTimeCheckOut())+"</b><br/>Enter the <b>Time Check Out</b> [dd-MM-yyyy HH:mm] (-1 to cancel)</html>");
				}else {
					label = new JLabel("<html>Current reservation time check out: <b style='color:blue'>Not yet Checkout</b><br/>Enter the <b>Time Check Out</b> [dd-MM-yyyy HH:mm] (-1 to cancel)</html>");
				}
				label.setFont(new Font("Arial", Font.PLAIN, 18));
				validOutput = true;
				do {
					do {
				    	result = JOptionPane.showInputDialog(null, label,"Edit reservation",JOptionPane.INFORMATION_MESSAGE);
				    	if (result==null) {
				    		result = "-1";
				    	}
			    	}while(result.isEmpty());
					result = result.trim();
					if (result.equals("-1")) {
						return true;
					}
					try {
						Date timecheckout = new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(result);
						reservationList[reservationIndex].details.setTimeCheckout(timecheckout);
						validOutput = true;
					}catch (Exception e) {
						label2 = new JLabel("<html><p style='color:red'>Invalid date and time!</p></html>");
		    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
		    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
						validOutput=false;
					}
				}while(validOutput==false);
				label2 = new JLabel("<html><p style='color:green'>Succesful edited the reservation time check out!</p></html>");
		    	label2.setFont(new Font("Arial", Font.BOLD, 16));
		        JOptionPane.showMessageDialog(null, label2, "Succesful Edited",JOptionPane.INFORMATION_MESSAGE);
				break;
			case 8:
				boolean valid = true;
				String input = "";
				do {
					valid = true;
					label = new JLabel("<html><b>Status Type</b><br>1. Pending<br>2. CheckIn<br>3. CheckOut<br>4. Cancelled<br>-1. Cancel</html>");
			    	label.setFont(new Font("Arial", Font.PLAIN, 18));
			    	do {
			    		input = JOptionPane.showInputDialog(null, label,"Select Status",JOptionPane.INFORMATION_MESSAGE);
			    		if (input==null) {
			    			input = "-1";
		    	    	}
		        	}while(input.isEmpty());
			        
			        
			        if (input!=null&&!input.isEmpty()) {
			        	input = input.trim();
			        	if (input.equals("-1")) {
			        		return true;
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
			        	Date date = new Date();
			        	switch (choice) {
			        		case 1:
			        			label2 = new JLabel("<html><p style='color:green'>Succesful edited the reservation status!</p></html>");
			    		    	label2.setFont(new Font("Arial", Font.BOLD, 16));
			    		        JOptionPane.showMessageDialog(null, label2, "Succesful Edited",JOptionPane.INFORMATION_MESSAGE);
			        			reservationList[reservationIndex].details.setStatus("Pending");
			        			reservationList[reservationIndex].details.setTimeCheckIn(null);
			        			reservationList[reservationIndex].details.setTimeCheckout(null);
			        			break;
			        		case 2:
			        			label2 = new JLabel("<html><p style='color:green'>Succesful edited the reservation status!</p></html>");
			    		    	label2.setFont(new Font("Arial", Font.BOLD, 16));
			    		        JOptionPane.showMessageDialog(null, label2, "Succesful Edited",JOptionPane.INFORMATION_MESSAGE);
			    		        
			        			reservationList[reservationIndex].details.setStatus("CheckIn");
			        			reservationList[reservationIndex].details.setTimeCheckIn(date);
			        			break;
			        		case 3:
			        			label2 = new JLabel("<html><p style='color:green'>Succesful edited the reservation status!</p></html>");
			    		    	label2.setFont(new Font("Arial", Font.BOLD, 16));
			    		        JOptionPane.showMessageDialog(null, label2, "Succesful Edited",JOptionPane.INFORMATION_MESSAGE);
			        			reservationList[reservationIndex].details.setStatus("CheckOut");
			        			reservationList[reservationIndex].details.setTimeCheckout(date);
			        			break;
			        		case 4:
			        			label2 = new JLabel("<html><p style='color:green'>Succesful edited the reservation status!</p></html>");
			    		    	label2.setFont(new Font("Arial", Font.BOLD, 16));
			    		        JOptionPane.showMessageDialog(null, label2, "Succesful Edited",JOptionPane.INFORMATION_MESSAGE);
			        			reservationList[reservationIndex].details.setStatus("Cancelled");
			        			break;
			        		case -1:
			        			break;
			        		default:
			        			JLabel label3 = new JLabel("<html><p style='color:red'>Incorrect choice number!</p></html>");
				    	    	label3.setFont(new Font("Arial", Font.BOLD, 16));
				    	        JOptionPane.showMessageDialog(null, label3, "ERROR",JOptionPane.ERROR_MESSAGE);
				    	        break;
			        	}
			        }else {
			        	JLabel label3 = new JLabel("<html><p style='color:red'>Incorrect choice number!</p></html>");
		    	    	label3.setFont(new Font("Arial", Font.BOLD, 16));
		    	        JOptionPane.showMessageDialog(null, label3, "ERROR",JOptionPane.ERROR_MESSAGE);
			        }
				}while(valid==false||choice!=-1&&(choice<1||choice>4));
				break;
			case 9:
				Room.listRoom();
				label = new JLabel("<html>Current room id: <b style='color:blue'>"+reservationList[reservationIndex].details.getRoom().getRoomID()+"|"+reservationList[reservationIndex].details.getRoom().getRoomType().getRoomName()+"</b><br>Enter the <b>New Room ID</b> (-1 to cancel)</html>");
		    	label.setFont(new Font("Arial", Font.PLAIN, 18));
		    	do {
		    		
			    	do {
				    	result = JOptionPane.showInputDialog(null, label,"Edit reservation",JOptionPane.INFORMATION_MESSAGE);
				    	if (result==null) {
				    		result = "-1";
				    	}
			    	}while(result.isEmpty());
			    		result = result.trim();
					if (result.equals("-1")) {
						return true;
					}
					roomID = result;
					if (Room.roomMap.containsKey(roomID)) {
						roomIndex = Room.roomMap.get(roomID);
	
					} else {
						JLabel label3 = new JLabel("<html><p style='color:red'>Room ID not found!</p></html>");
						label3.setFont(new Font("Arial", Font.BOLD, 16));
						JOptionPane.showMessageDialog(null, label3, "ERROR", JOptionPane.ERROR_MESSAGE);
					}
		    	}while(!Room.roomMap.containsKey(roomID));
				reservationList[reservationIndex].details.setRoom(Room.getRoomList()[roomIndex]);
				label2 = new JLabel("<html><p style='color:green'>Succesful edited the room!</p></html>");
		    	label2.setFont(new Font("Arial", Font.BOLD, 16));
		        JOptionPane.showMessageDialog(null, label2, "Succesful Edited",JOptionPane.INFORMATION_MESSAGE);
				break;
			case 10:
				Customer.listCustomer();
				boolean customerIDExist = false;
				int customerIndex = 0;
				label = new JLabel("<html>Current customer id: <b style='color:blue'>"+reservationList[reservationIndex].getCustomer().getCustomerID()+"</b><br>Enter the <b>New Customer ID</b> (-1 to cancel)</html>");
		    	label.setFont(new Font("Arial", Font.PLAIN, 18));
		    	do {
		    		customerIDExist = false;
			    	do {
				    	result = JOptionPane.showInputDialog(null, label,"Edit reservation",JOptionPane.INFORMATION_MESSAGE);
				    	if (result==null) {
				    		result = "-1";
				    	}
			    	}while(result.isEmpty());
			    		result = result.trim();
					if (result.equals("-1")) {
						return true;
					}
					customerID = result;
					for (int i = 0;i < Customer.getTotalCustomer();i++) {
						if (Customer.getCustomerList()[i].getCustomerID().equals(customerID)) {
							customerIndex = i;
							customerIDExist = true;
							break;
						}
					}
					if (!customerIDExist) {
						JLabel label3 = new JLabel("<html><p style='color:red'>Customer ID not found!</p></html>");
						label3.setFont(new Font("Arial", Font.BOLD, 16));
						JOptionPane.showMessageDialog(null, label3, "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				} while (!customerIDExist);
		    	reservationList[reservationIndex].setCustomer(Customer.getCustomerList()[customerIndex]);
				label2 = new JLabel("<html><p style='color:green'>Succesful edited the customer!</p></html>");
		    	label2.setFont(new Font("Arial", Font.BOLD, 16));
		        JOptionPane.showMessageDialog(null, label2, "Succesful Edited",JOptionPane.INFORMATION_MESSAGE);
				break;
			case 11:
				SalesPerson.listSalesPerson();
				boolean salesPersonExist = false;
				int salesPersonIndex = 0;
				label = new JLabel("<html>Current salesperson username: <b style='color:blue'>"+reservationList[reservationIndex].getSalesPerson().getSalesUserName()+"</b><br>Enter the <b>New salesperson username</b> (-1 to cancel)</html>");
		    	label.setFont(new Font("Arial", Font.PLAIN, 18));
		    	do {
		    		salesPersonExist = false;
			    	do {
				    	result = JOptionPane.showInputDialog(null, label,"Edit reservation",JOptionPane.INFORMATION_MESSAGE);
				    	if (result==null) {
				    		result = "-1";
				    	}
			    	}while(result.isEmpty());
			    		result = result.trim();
					if (result.equals("-1")) {
						return true;
					}
					salesUserName = result;
					for (int i = 0;i < SalesPerson.getTotalSalesPerson();i++) {
						if (SalesPerson.getSalesPersonList()[i].getSalesUserName().equals(salesUserName)) {
							salesPersonIndex = i;
							salesPersonExist = true;
							break;
						}
					}
					if (!salesPersonExist) {
						JLabel label3 = new JLabel("<html><p style='color:red'>SalesPerson Username not found!</p></html>");
						label3.setFont(new Font("Arial", Font.BOLD, 16));
						JOptionPane.showMessageDialog(null, label3, "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				} while (!salesPersonExist);
				reservationList[reservationIndex].setSalesPerson(SalesPerson.getSalesPersonList()[salesPersonIndex]);
				label2 = new JLabel("<html><p style='color:green'>Succesful edited the salesperson!</p></html>");
		    	label2.setFont(new Font("Arial", Font.BOLD, 16));
		        JOptionPane.showMessageDialog(null, label2, "Succesful Edited",JOptionPane.INFORMATION_MESSAGE);
				break;
			default:
				label2 = new JLabel("<html><p style='color:red'>Incorrect choice number!</p></html>");
    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);	
		}
		return true;
	}
	//Check in the reservation
	public static void reservationCheckIn() {
		String reservationID = "";
		JLabel label = new JLabel("<html>Enter the <b>Reservation ID</b> that want to Check In (-1 to cancel)</html>");
    	label.setFont(new Font("Arial", Font.PLAIN, 18));
    	do {
    		do {
    	    	reservationID = JOptionPane.showInputDialog(null, label,"Check In",JOptionPane.INFORMATION_MESSAGE);
    	    	if (reservationID==null) {
    	    		reservationID = "-1";
    	    	}
        	}while(reservationID.isEmpty());
    		reservationID = reservationID.trim();
    		if (reservationID.equals("-1")) {
    			return;
    		}
			if (!reserveMap.containsKey(reservationID)) {
				JLabel label2 = new JLabel("<html><p style='color:red'>Reservation ID not found!</p></html>");
    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
			}
    	}while(!reserveMap.containsKey(reservationID));
    	int reserveIndex = reserveMap.get(reservationID);
    	if (!reservationList[reserveIndex].getDetails().getStatus().toLowerCase().equals("checkout")&&!reservationList[reserveIndex].getDetails().getStatus().toLowerCase().equals("checkin")){
    		reservationList[reserveIndex].checkIn();
        	JLabel label2 = new JLabel("<html><p style='color:green'>Succesful checkin the room!</p></html>");
        	label2.setFont(new Font("Arial", Font.BOLD, 16));
        	JOptionPane.showMessageDialog(null, label2, "Succesful Edited",JOptionPane.INFORMATION_MESSAGE);
    	}else {
    		JLabel label2 = new JLabel("<html><p style='color:red'>The room already checked out or checked in!</p></html>");
	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
    	}
    	
        
	}
	//Check out the reservation
	public static void reservationCheckOut() {
		String reservationID = "";
		JLabel label = new JLabel("<html>Enter the <b>Reservation ID</b> that want to Check Out (-1 to cancel)</html>");
    	label.setFont(new Font("Arial", Font.PLAIN, 18));
    	do {
    		do {
    	    	reservationID = JOptionPane.showInputDialog(null, label,"Check Out",JOptionPane.INFORMATION_MESSAGE);
    	    	if (reservationID==null) {
    	    		reservationID = "-1";
    	    	}
        	}while(reservationID.isEmpty());
    		reservationID = reservationID.trim();
    		if (reservationID.equals("-1")) {
    			return;
    		}
			if (!reserveMap.containsKey(reservationID)) {
				JLabel label2 = new JLabel("<html><p style='color:red'>Reservation ID not found!</p></html>");
    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
			}
    	}while(!reserveMap.containsKey(reservationID));
    	int reserveIndex = reserveMap.get(reservationID);
    	if (reservationList[reserveIndex].getDetails().getStatus().toLowerCase().equals("checkin")){
    		reservationList[reserveIndex].checkOut();
        	JLabel label2 = new JLabel("<html><p style='color:green'>Succesful checkout the room!</p></html>");
        	label2.setFont(new Font("Arial", Font.BOLD, 16));
            JOptionPane.showMessageDialog(null, label2, "Succesful Edited",JOptionPane.INFORMATION_MESSAGE);
    	}else {
    		JLabel label2 = new JLabel("<html><p style='color:red'>The room status must be check in!</p></html>");
	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
    	}
    	
	}
	public static void searchReservation() {
		int choice = 0;
		
		String result = "", input = "", reservationID = "";
		boolean valid = false;
		do {
			boolean resultFound = false;
			ArrayList<Reservation> reserved= new ArrayList<Reservation>(); 
			valid = true;
			JLabel label = new JLabel("<html><b>Search Menu</b><br>1. Search by Reservation ID<br>2. Search by Customer Name<br>3. Search by Customer ID<br>4. Search by Room Type<br>"
	    			+ "5. Search by Room ID<br>6. Search by Status<br>7. Search by SalesPerson Name<br>8. Search by SalesPerson UserName<br>-1. Return to reservation menu</html>");
	    	label.setFont(new Font("Arial", Font.PLAIN, 18));
	        input = JOptionPane.showInputDialog(null, label);
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
	        	if (choice == 1) {
	        		label = new JLabel("<html>Enter the <b>Reservation ID</b> that want to search (-1 to cancel)</html>");
	            	label.setFont(new Font("Arial", Font.PLAIN, 18));
	            	do {
	            		do {
	            	    	reservationID = JOptionPane.showInputDialog(null, label,"Search",JOptionPane.INFORMATION_MESSAGE);
	            	    	if (reservationID==null) {
	            	    		reservationID = "-1";
	            	    	}
	                	}while(reservationID.isEmpty());
	            		reservationID = reservationID.trim();
	            		if (reservationID.equals("-1")) {
	            			return;
	            		}
	        			if (!reserveMap.containsKey(reservationID)) {
	        				JLabel label2 = new JLabel("<html><p style='color:red'>Reservation ID not found!</p></html>");
	            	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
	            	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
	        			}
	            	}while(!reserveMap.containsKey(reservationID));
	            	int reserveIndex = reserveMap.get(reservationID);
	            	label = new JLabel("<html>"+reservationList[reserveIndex].toString().replace("\n", "<br>")+"</html>");
	    	    	label.setFont(new Font("Arial", Font.BOLD, 16));
	    	        JOptionPane.showMessageDialog(null, label, "RESULT",JOptionPane.INFORMATION_MESSAGE);
	        	}else if (choice == 2) {
	        		
	        		label = new JLabel("<html>Enter the <b>Customer Name</b> that want to search (-1 to cancel)</html>");
	            	label.setFont(new Font("Arial", Font.PLAIN, 18));
	            	do {
	            		do {
	            	    	result = JOptionPane.showInputDialog(null, label,"Search",JOptionPane.INFORMATION_MESSAGE);
	            	    	if (result==null) {
	            	    		result = "-1";
	            	    	}
	                	}while(result.isEmpty());
	            		result = result.trim();
	            		if (result.equals("-1")) {
	            			return;
	            		}
	        			for (int i = 0;i < totalReservation;i++) {
	        				String name = reservationList[i].customer.getName();
	        				if (name.toLowerCase().equals(result.toLowerCase())) {
	        					resultFound = true;
	        					reserved.add(reservationList[i]);
	        				}
	        			}
	        			
	        			if (!resultFound) {
	        				JLabel label2 = new JLabel("<html><p style='color:red'>Customer Name not found!</p></html>");
	            	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
	            	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
	        			}
	            	}while(!resultFound);
	            	
	        	}else if(choice==3){
	        		label = new JLabel("<html>Enter the <b>Customer ID</b> that want to search (-1 to cancel)</html>");
	            	label.setFont(new Font("Arial", Font.PLAIN, 18));
	            	do {
	            		do {
	            	    	result = JOptionPane.showInputDialog(null, label,"Search",JOptionPane.INFORMATION_MESSAGE);
	            	    	if (result==null) {
	            	    		result = "-1";
	            	    	}
	                	}while(result.isEmpty());
	            		result = result.trim();
	            		if (result.equals("-1")) {
	            			return;
	            		}
	        			for (int i = 0;i < totalReservation;i++) {
	        				String id = reservationList[i].customer.getCustomerID();
	        				if (id.toLowerCase().equals(result.toLowerCase())) {
	        					resultFound = true;
	        					reserved.add(reservationList[i]);
	        				}
	        			}
	        			
	        			if (!resultFound) {
	        				JLabel label2 = new JLabel("<html><p style='color:red'>Customer ID not found!</p></html>");
	            	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
	            	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
	        			}
	            	}while(!resultFound);
	        	} else if (choice == 4) {
	        		label = new JLabel("<html>Enter the <b>RoomType ID</b> that want to search (-1 to cancel)</html>");
	            	label.setFont(new Font("Arial", Font.PLAIN, 18));
	            	do {
	            		do {
	            	    	result = JOptionPane.showInputDialog(null, label,"Search",JOptionPane.INFORMATION_MESSAGE);
	            	    	if (result==null) {
	            	    		result = "-1";
	            	    	}
	                	}while(result.isEmpty());
	            		result = result.trim();
	            		if (result.equals("-1")) {
	            			return;
	            		}
	            		for (int i = 0;i < totalReservation;i++) {
	            			if (reservationList[i].details.getRoom().getRoomType().getRoomTypeID().toLowerCase().equals(result.toLowerCase())) {
	            				resultFound= true;
	            				reserved.add(reservationList[i]);
	            			}
	            		}
	        			if (!resultFound) {
	        				JLabel label2 = new JLabel("<html><p style='color:red'>Not found any result for this Room Type ID!</p></html>");
	            	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
	            	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
	        			}
	            	}while(!resultFound);
	        	}else if (choice == 5) {
	        		label = new JLabel("<html>Enter the <b>Room ID</b> that want to search (-1 to cancel)</html>");
	            	label.setFont(new Font("Arial", Font.PLAIN, 18));
	            	do {
	            		do {
	            	    	result = JOptionPane.showInputDialog(null, label,"Search",JOptionPane.INFORMATION_MESSAGE);
	            	    	if (result==null) {
	            	    		result = "-1";
	            	    	}
	                	}while(result.isEmpty());
	            		result = result.trim();
	            		if (result.equals("-1")) {
	            			return;
	            		}
	            		for (int i = 0;i < totalReservation;i++) {
	            			if (reservationList[i].details.getRoom().getRoomID().toLowerCase().equals(result.toLowerCase())) {
	            				resultFound= true;
	            				reserved.add(reservationList[i]);
	            			}
	            		}
	        			if (!resultFound) {
	        				JLabel label2 = new JLabel("<html><p style='color:red'>Not found any result for this Room ID!</p></html>");
	            	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
	            	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
	        			}
	            	}while(!resultFound);
	        	}else if(choice==6) {
	        		label = new JLabel("<html>Enter the <b>Status</b> that want to search (-1 to cancel)</html>");
	            	label.setFont(new Font("Arial", Font.PLAIN, 18));
	            	do {
	            		do {
	            	    	result = JOptionPane.showInputDialog(null, label,"Search",JOptionPane.INFORMATION_MESSAGE);
	            	    	if (result==null) {
	            	    		result = "-1";
	            	    	}
	                	}while(result.isEmpty());
	            		result = result.trim();
	            		if (result.equals("-1")) {
	            			return;
	            		}
	        			for (int i = 0;i < totalReservation;i++) {
	        				String name = reservationList[i].details.getStatus();
	        				if (name.toLowerCase().equals(result.toLowerCase())) {
	        					resultFound = true;
	        					reserved.add(reservationList[i]);
	        				}
	        			}
	        			
	        			if (!resultFound) {
	        				JLabel label2 = new JLabel("<html><p style='color:red'>Status not found!</p></html>");
	            	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
	            	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
	        			}
	            	}while(!resultFound);
	        	}else if(choice==7) {
	        		label = new JLabel("<html>Enter the <b>Sales Person Name</b> that want to search (-1 to cancel)</html>");
	            	label.setFont(new Font("Arial", Font.PLAIN, 18));
	            	do {
	            		do {
	            	    	result = JOptionPane.showInputDialog(null, label,"Search",JOptionPane.INFORMATION_MESSAGE);
	            	    	if (result==null) {
	            	    		result = "-1";
	            	    	}
	                	}while(result.isEmpty());
	            		result = result.trim();
	            		if (result.equals("-1")) {
	            			return;
	            		}
	        			for (int i = 0;i < totalReservation;i++) {
	        				String name = reservationList[i].salesperson.getName();
	        				if (name.toLowerCase().equals(result.toLowerCase())) {
	        					resultFound = true;
	        					reserved.add(reservationList[i]);
	        				}
	        			}
	        			
	        			if (!resultFound) {
	        				JLabel label2 = new JLabel("<html><p style='color:red'>Sales Person Name not found!</p></html>");
	            	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
	            	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
	        			}
	            	}while(!resultFound);
	        	}else if(choice==8) {
	        		label = new JLabel("<html>Enter the <b>SalesPerson Username</b> that want to search (-1 to cancel)</html>");
	            	label.setFont(new Font("Arial", Font.PLAIN, 18));
	            	do {
	            		do {
	            	    	result = JOptionPane.showInputDialog(null, label,"Search",JOptionPane.INFORMATION_MESSAGE);
	            	    	if (result==null) {
	            	    		result = "-1";
	            	    	}
	                	}while(result.isEmpty());
	            		result = result.trim();
	            		if (result.equals("-1")) {
	            			return;
	            		}
	        			for (int i = 0;i < totalReservation;i++) {
	        				String name = reservationList[i].salesperson.getSalesUserName();
	        				if (name.toLowerCase().equals(result.toLowerCase())) {
	        					resultFound = true;
	        					reserved.add(reservationList[i]);
	        				}
	        			}
	        			
	        			if (!resultFound) {
	        				JLabel label2 = new JLabel("<html><p style='color:red'>Sales Person Username not found!</p></html>");
	            	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
	            	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
	        			}
	            	}while(!resultFound);
	        	}
	        	if (choice > 1&&choice<=8&&reserved.size()>0) {
	        		String dateCheckIn = "", dateCheckOut = "";
	        		int page = 1,choice2=0;
        			int maxPage = (int) Math.ceil(reserved.size()/MAXDISPLAYRESULT);
        			boolean valid2 = false;
	            	do {
	            		choice2 = 0;
	            		int maxResult = reserved.size()-((page-1)*MAXDISPLAYRESULT);
	        			int from = reserved.size()-(MAXDISPLAYRESULT*page);
	        			if (from <0) {
	        				from = 0;
	        			}
	        			valid2 = true;
	        			String listStatement = "<html><h1 style='text-align:center'>Reservation List</h2><table><tr><th colspan='9' style='text-align:right;'>Page "+page+"</th><tr><td><b>| ReservationID </b></td><td><b>| Customer Name </td><td><b>| Time Reserved </b></td><td><b>| Room Users </b></td><td><b>| Room ID </b></td><td><b>| Room Type </b></td><td><b>| Status </b></td><td><b>| Check In </b></td><td><b>| Check Out </b></td><td><b>| SalesPerson Name</b></td></tr>";
	        			for (int i = maxResult-1; i > from-1;i--) {
	        				
	        				DateFormat dateFormat = new SimpleDateFormat( "dd-MM-YYYY HH:mm" );
	        				String dateDisplayedStart = dateFormat.format(reservationList[i].details.getTimeReserved());
	        				dateFormat = new SimpleDateFormat( "HH:mm" );
	        				String dateDisplayedEnd = dateFormat.format(reservationList[i].details.getTimeReservedEnd());
	        				dateFormat = new SimpleDateFormat( "dd-MM-YYYY HH:mm" );
	        				if (reservationList[i].details.getTimeCheckIn()==null) {
	        					dateCheckIn = "-";
	        				}else {
	        					dateCheckIn = dateFormat.format(reservationList[i].details.getTimeCheckIn());
	        				}
	        				if (reservationList[i].details.getTimeCheckOut()==null) {
	        					dateCheckOut = "-";
	        				}else {
	        					dateCheckOut = dateFormat.format(reservationList[i].details.getTimeCheckOut());
	        				}
	        				
	        				
	        				listStatement += String.format("<tr><td>| %s </td><td>| %s</td><td>| %s - %s </td><td>| %d </td><td>| %s </td><td>| %s </td><td>| %s </td><td>| %s </td><td>| %s </td><td>| %s </td></tr>", reservationList[i].getReservationID(), reservationList[i].getCustomer().getName(), 
	        						dateDisplayedStart,dateDisplayedEnd,reservationList[i].details.getRoomUsers(),reservationList[i].details.getRoom().getRoomID(),reservationList[i].details.getRoom().getRoomType().getRoomName(),reservationList[i].details.getStatus()
	        						,dateCheckIn, dateCheckOut,reservationList[i].getSalesPerson().getName());
	        			}
	        			listStatement += "</table><b>-1 - return to reservation menu<br>0 - go to previous page<br>1 - go to next page</b><br/></html>";
	        			label = new JLabel(listStatement);
	        			label.setFont(new Font("Arial", Font.PLAIN, 18));
	        			do {
	        				input = JOptionPane.showInputDialog(null, label, "RESULT",JOptionPane.INFORMATION_MESSAGE);
        			    	if (input==null) {
        			    		input = "-1";
        			    	}
	        			}while(input.isEmpty());
	        	        if (input!=null&&!input.isEmpty()) {
	        	        	input = input.trim();
	        	        	for(int i = 0;i < input.length();i++) {
	        		        	Character ch = input.charAt(i);
	        		        	if (!Character.isDigit(ch)) {
	        		        		valid2 = false;
	        		        		
	        		        	}
	        		        }
	        	        	if (valid2==true) {
	        	        		choice2 = Integer.parseInt(input);
	        	        	}
	        	        }else {
	        	        	choice2 = -1;
	        	        	valid2=true;
	        	        }
	        	        if (choice2 == 1) {
	        	        	page++;
	        	        	if (page>(maxPage+1)) {
	        	        		JLabel label2 = new JLabel("<html><p style='color:red'>Reach the max pages!</p></html>");
	        	    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
	        	    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
	        	        		page= maxPage+1;
	        	        	}
	        	        }else if (choice2==0&&!input.equals("-1")) {
	        	        	page--;
	        	        	if (page<1) {
	        	        		JLabel label2 = new JLabel("<html><p style='color:red'>Reach the min page!</p></html>");
	        	    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
	        	    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
	        	        		page= 1;
	        	        	}
	        	        }else if (choice2!=-1){
	        	        	valid2 = false;
	        	        }
        	        	if (input.equals("-1")) {
        	        		valid2=true;
        	        		choice2 = -1;
        	        	}
	        	        if (!valid2) {
	        	        	JLabel label2 = new JLabel("<html><p style='color:red'>Incorrect choice number!</p></html>");
	            	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
	            	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
	        	        }
        			}while(valid2==false||choice2!=-1);
	        	}
	        }else if (choice!=-1){
	        	valid = false;
	        }
	        if (!valid) {
	        	JLabel label2 = new JLabel("<html><p style='color:red'>Incorrect choice number!</p></html>");
    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
	        }
		}while(valid==false||choice!=-1);
	}
}
