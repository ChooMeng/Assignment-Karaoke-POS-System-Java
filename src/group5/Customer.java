package group5;
import javax.swing.*;

import group5.entity.Address;
import group5.entity.Person;

import java.util.*;
import java.text.*;
import java.awt.*;

public class Customer extends Person{
	private static int totalCustomer = 0;
	private static Customer[] customerList = new Customer[100];
	private String customerID;
	private Date regDate;
	private int points;
	
	public Customer() {} //No constructor argument
	
	//Constructor include the Person(Superclass) 
    public Customer(String name, String icNumber, String contactNumber, String email, char gender, Date birthDate, Address address, Date regDate, int points) 
    {
    	super(name, icNumber, contactNumber, email, gender, birthDate, address);
    	customerID = "M" + (totalCustomer + 1);
    	this.regDate = regDate;
    	this.points = points;
    	totalCustomer++;
    }
    
    //Constructor without regDate and points (Without regDate and points)
    public Customer(String name, String icNumber, String contactNumber, String email, char gender, Date birthDate, Address address)
    {
    	super(name, icNumber, contactNumber, email, gender, birthDate, address);
    	customerID = "M" + (totalCustomer + 1);
    	Date date = new Date();
    	regDate = date;
    	points = 0;
    	totalCustomer++;
    }
    
    //Getter
    public String getCustomerID() { return customerID; }
    public Date getRegDate() { return regDate; }
    public int getPoints() { return points; }
    public static int getTotalCustomer() { return totalCustomer; }
    public static Customer[] getCustomerList() { return customerList; }
    
    //Setter
    public void setCustomerID(String customerID) { this.customerID = customerID; }
    public void setRegDate(Date regDate) { this.regDate = regDate; }
    public void setPoints(int points) { this.points = points; }
    public static void setTotalCustomer(int totalCustomer) { Customer.totalCustomer = totalCustomer; }
    public static void setCustomerList(Customer[] customerList) { Customer.customerList = customerList; }
    public String toString()
    {
    	
    	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
	    String dateRegistered= formatter.format(regDate);
	    
    	return String.format("\nCustomer ID: %s\nDate registered: %s\nCustomer points: %d\n",
    	customerID, dateRegistered, getPoints()) + super.toString();
    }
    public boolean equals(Object o) {
		if (!(o instanceof Customer )) {
			return false;
		}
		Customer o2 = (Customer) o;
		if ((this.customerID != null && o2.customerID!=null)&&this.customerID.equals(o2.customerID)) {
			return true;
		}
		return false;
	}
    public static void defaultCustomer() //This is the sample of all customer list
    {
    	Address address[] = {
    		new Address("59 Lorong 4 Bandar Tasek Mutiara", "14420", "Simpang Ampat", "Pulau Pinang"),
    		new Address("60 Lorong 5 Bandar Tasek Mutiara", "14120", "Bayan Baru", "Pulau Pinang"),
    		new Address("38 Lorong 6 Taman Sukun", "14100", "Bukit Mertajam", "Pulau Pinang"),
    		new Address("96 Persiaran Bayan Baru", "14325", "Bayan Baru", "Pulau Pinang"),
    		new Address("58 Taman Tanjung Bungah", "17354", "Tanjong Bungah", "Pulau Pinang")
    	};
    	customerList[0] = new Customer("William Choong", "011001076489", "016494796", "williamc001@gmail.com", 'M', StringToDate("01-10-2001"), address[0], StringToDate("09-06-2020"), 73);
    	customerList[1] = new Customer("Chew Wei Chung", "010113042563", "01137145015", "polarix0113@gmail.com", 'M', StringToDate("13-01-2001"), address[1], StringToDate("15-07-2020"), 52);
    	customerList[2] = new Customer("Choo Zhi Yan", "001227071578", "0125271589", "cookiech00@gmail.com", 'F', StringToDate("27-12-2000"), address[2], StringToDate("25-07-2020"), 120);
    	customerList[3] = new Customer("Poh Choo Meng", "010417070893", "0182875567", "peacehero1234@gmail.com", 'M', StringToDate("17-04-2001"), address[3], StringToDate("09-08-2020"), 100);
    	customerList[4] = new Customer("Oon Kheng Huang", "001206072595", "0164810091", "khenghuang@gmail.com", 'M', StringToDate("06-12-2000"), address[4], StringToDate("11-08-2020"),65);
    }
  //Create a new customer : Done
    @SuppressWarnings("deprecation")
	public static void addCustomer()
    {
    	Date birthdate = new Date();
    	String result = "";
    	
    	//Name
		String name = "";
		do{
			JLabel label = new JLabel("<html>Enter <b>Name</b>(-1 to cancel)</html>");
			label.setFont(new Font("Arial", Font.PLAIN, 18));
			do {
	    		result = JOptionPane.showInputDialog(null, label, "Create customer", JOptionPane.INFORMATION_MESSAGE);
	    		if (result == null) {
					result = "-1";
				}
			}while(result.isEmpty());
			result = result.trim();
			if (result.equals("-1")) {
				return;
			}
			name = result;
		}while(Person.checkName(name) == false);
    	
    	//IC number
    	String icNumber = ""; 
    	do{
			JLabel label = new JLabel("<html>Enter <b>IC Number</b>(-1 to cancel)</html>");
			label.setFont(new Font("Arial", Font.PLAIN, 18));
			do {
	    		result = JOptionPane.showInputDialog(null, label, "Create customer", JOptionPane.INFORMATION_MESSAGE);
	    		if (result == null) {
					result = "-1";
				}
			}while(result.isEmpty());
			result = result.trim();
			if (result.equals("-1")) {
				return;
			}
			icNumber = result;
    	}while(Person.checkIcNumber(icNumber) == false);
    	
    	//Contact Number
    	String contactNumber = "";
    	do{
			JLabel label = new JLabel("<html>Enter <b>Contact Number</b>(-1 to cancel)</html>");
			label.setFont(new Font("Arial", Font.PLAIN, 18));
			do {
	    		result = JOptionPane.showInputDialog(null, label, "Create customer", JOptionPane.INFORMATION_MESSAGE);
	    		if (result == null) {
					result = "-1";
				}
			}while(result.isEmpty());
			result = result.trim();
			if (result.equals("-1")) {
				return;
			}
			contactNumber = result;
    	}while(Person.checkContactNumber(contactNumber) == false);
    		
    	//Email
    	String email = "";
    	do{
			JLabel label = new JLabel("<html>Enter <b>Email</b>(-1 to cancel)</html>");
			label.setFont(new Font("Arial", Font.PLAIN, 18));
			do {
	    		result = JOptionPane.showInputDialog(null, label, "Create customer", JOptionPane.INFORMATION_MESSAGE);
	    		if (result == null) {
					result = "-1";
				}
			}while(result.isEmpty());
			result = result.trim();
			if (result.equals("-1")) {
				return;
			}
			email = result;
    	}while(Person.checkEmail(email) == false);
    	
    	//Gender
    	char gender = ' ';
    	do{
			JLabel label = new JLabel("<html>Enter your <b>Gender</b> (M/F) (-1 to cancel)</html>");
			label.setFont(new Font("Arial", Font.PLAIN, 18));
			do {
	    		result = JOptionPane.showInputDialog(null, label, "Create customer", JOptionPane.INFORMATION_MESSAGE);
	    		if (result == null) {
					result = "-1";
				}
			}while(result.isEmpty());
			result = result.trim();
			if (result.equals("-1")) {
				return;
			}
			gender = result.charAt(0);
    	}while(Person.checkGender(gender) == false);
    	
    	//Birth date
    	boolean validInput = true;
		do {
			JLabel label = new JLabel("<html>Enter your <b>Birth Date</b> (DD-mm-yyyy) (-1 to cancel)</html>");
			label.setFont(new Font("Arial", Font.PLAIN, 18));
			do {
	    		result = JOptionPane.showInputDialog(null, label, "Create customer", JOptionPane.INFORMATION_MESSAGE);
	    		if (result == null) {
					result = "-1";
				}
			}while(result.isEmpty());
			result = result.trim();
			if (result.equals("-1")) {
				return;
			}
			try {
				birthdate = new SimpleDateFormat("dd-MM-yyyy").parse(result);
				validInput = true;
			}catch(Exception ex){
				JLabel label2 = new JLabel("<html><p style='color:red'>Invalid date!</p></html>");
    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
				validInput = false;
			}
			Date now = new Date();
			if (birthdate.getYear()>now.getYear()) {
				JLabel label2 = new JLabel("<html><p style='color:red'>Invalid date!</p></html>");
    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
				validInput = false;
			}
		}while(validInput == false);
    	
    	//Home address
    	String homeAddress = "";
    	do{
			JLabel label = new JLabel("<html>Home Address<br>Enter your <b>address</b> (-1 to cancel)</html>");
			label.setFont(new Font("Arial", Font.PLAIN, 18));
			do {
	    		result = JOptionPane.showInputDialog(null, label, "Create customer", JOptionPane.INFORMATION_MESSAGE);
	    		if (result == null) {
					result = "-1";
				}
			}while(result.isEmpty());
			result = result.trim();
			if (result.equals("-1")) {
				return;
			}
    		homeAddress = result;
    	}while(Address.checkHomeAddress(homeAddress) == false);
	    
    	//Zipcode
    	String zipCode = "";
    	do{
			JLabel label = new JLabel("<html>Home Address<br>Enter your <b>ZipCode</b> (-1 to cancel)</html>");
			label.setFont(new Font("Arial", Font.PLAIN, 18));
			do {
	    		result = JOptionPane.showInputDialog(null, label, "Create customer", JOptionPane.INFORMATION_MESSAGE);
	    		if (result == null) {
					result = "-1";
				}
			}while(result.isEmpty());
			result = result.trim();
			if (result.equals("-1")) {
				return;
			}
    		zipCode = result;
    	}while(Address.checkZipcode(zipCode) == false);
    	
    	//City
    	String city = "";
    	do{
			JLabel label = new JLabel("<html>Home Address<br>Enter your <b>City</b> (-1 to cancel)</html>");
			label.setFont(new Font("Arial", Font.PLAIN, 18));
			do {
	    		result = JOptionPane.showInputDialog(null, label, "Create customer", JOptionPane.INFORMATION_MESSAGE);
	    		if (result == null) {
					result = "-1";
				}
			}while(result.isEmpty());
			result = result.trim();
			if (result.equals("-1")) {
				return;
			}
    		city = result;
    	}while(Address.checkCity(city) == false);
    	
    	//State
    	String state = "";
    	do{
			JLabel label = new JLabel("<html>Home Address<br>Enter your <b>State</b> (-1 to cancel)</html>");
			label.setFont(new Font("Arial", Font.PLAIN, 18));
			do {
	    		result = JOptionPane.showInputDialog(null, label, "Create customer", JOptionPane.INFORMATION_MESSAGE);
	    		if (result == null) {
					result = "-1";
				}
			}while(result.isEmpty());
			result = result.trim();
			if (result.equals("-1")) {
				return;
			}
    		state = result;
    	}while(Address.checkState(state) == false);
    	
    	Address address = new Address(homeAddress, zipCode, city, state);
    	customerList[totalCustomer] = new Customer(name, icNumber, contactNumber, email, gender, birthdate, address);
    	System.out.println("You have created a new customer");
    	System.out.println(customerList[totalCustomer - 1].toString());
    }
    //Show all the list of the customers : Done
    public static void listCustomer() 
    {
    	System.out.printf("%-3s %-32s %-13s %14s %7s\n", "ID", "Name", "IC NUMBER", "CONTACT NUMBER", "POINTS");
    	for(int i = 0; i < totalCustomer; i++)
    	{
    		System.out.printf("%s) %-30s | %12s | %-12s | %d\n", 
    		customerList[i].getCustomerID(), customerList[i].getName(), customerList[i].getIcNumber(), customerList[i].getContactNumber(), customerList[i] .getPoints());
    	}
    	System.out.println("\n\n");
    }
  //Search the customer ID then display the customer details : Done
    public static int searchCustomer()
    {
    	//This module not just can search customer, when it use in deleteCustomer() and editCustomer()
    	//It will return i (from the for loop inside the if statement) then use customerList[i]
    	
    	listCustomer();
    	
    	String result = "";
    	boolean resultFound = false;
    	JLabel label = new JLabel("<html>Enter the <b>Customer ID</b> to search (-1 to cancel)</html>");
		label.setFont(new Font("Arial", Font.PLAIN, 18));
		do {
			resultFound = false;
			do {
	    		result = JOptionPane.showInputDialog(null, label, "Search", JOptionPane.INFORMATION_MESSAGE);
	    		if (result == null) {
					result = "-1";
				}
	    		
			}while(result.isEmpty());
			result = result.trim();
			if (result.equals("-1")) {
				return -1;
			}
			for(int i = 0; i < totalCustomer; i++)
	    	{
	    		if(result.equals(customerList[i].getCustomerID()))
	    		{	//Success to found the customer
	    			System.out.println(customerList[i].toString());
	    			resultFound = true;
	    			return i;
	    		}
	    	}
		}while(resultFound==false);
		
		return -1;
    }
  //Search customer ID then ask for confirmation of delete the customer
    public static void deleteCustomer() 
    {
    	int searchID = searchCustomer(); //Use the searchCustomer methods to get the customerID
    	if (searchID==-1) {
    		return;
    	}
    	String answer = "";
    	do{
    		JLabel label = new JLabel("<html>Are you sure you want to <b>delete<b> this Customer?(Y/N)</html>");
        	label.setFont(new Font("Arial", Font.PLAIN, 18));
        	do {
        		answer = JOptionPane.showInputDialog(null, label,"Confirmation",JOptionPane.INFORMATION_MESSAGE);
        		if (answer==null) {
        			answer = "-1";
        		}
        	}while(answer.isEmpty());
        	answer = answer.trim();
        	if (answer.equals("-1")) {
        		return;
        	}
    	}while(checkAnswer(answer.charAt(0)) == false);
    	char c = answer.charAt(0);
    	
    	if(c == 'Y' || c == 'y')
    	{
    		for(int i = 0; i < totalCustomer; i++)
    		{
    			if(i == searchID)
    			{
    				customerList[i].setCustomerID("[OLD]" + customerList[i].getCustomerID());
    			}
    			if(i > searchID)
    			{
    				customerList[i - 1] = customerList[i];
    				String newCustomerID = "M" + i; 
    				customerList[i - 1].setCustomerID(newCustomerID);
    			}
    			if(i == (totalCustomer - 1))
    			{
    				customerList[i] = null;
    			}
    		}
    		totalCustomer--;
    		JLabel label2 = new JLabel("<html><p style='color:green'>Succesful removed the customer!</p></html>");
        	label2.setFont(new Font("Arial", Font.BOLD, 16));
            JOptionPane.showMessageDialog(null, label2, "Succesful Removed",JOptionPane.INFORMATION_MESSAGE);
    	}
    }
    
  //Search customer ID then show edit menu : Done (bring the value to editData(choice, searchID))
    public static void editCustomer() 
    {
    	int searchID = searchCustomer(); //Use the searchCustomer methods to get the customerID
    	if (searchID==-1) {
    		return;
    	}
    	//Edit customer menu
    	int choice = 0;
    	boolean valid = false;
    	String input = "";
    	
    	do {
			valid = true;
	    	JLabel label = new JLabel("<html>Which please select the data that you would like to edit<br>1. Name<br>" + 
	    			"2. IC number<br>3. Contact number<br>"
	    			+ "4. Email<br>5. Gender<br>6. Birth Date<br>7. Address" + 
	    			"<br>-1. Return to main menu</html>");
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
	        	switch (choice) {
	        		case 1: //Change name
	        			editData(choice, searchID);
	        			break;
	        		case 2: //Change IC number
	        			editData(choice, searchID);
	        			break;
	        		case 3: //Change contact number
	        			editData(choice, searchID);
	        			break;
	        		case 4: //Change email address
	        			editData(choice, searchID);
	        			break;
	        		case 5: //Change gender
	        			editData(choice, searchID);
	        			break;
	        		case 6: //Change birth date (DD/MM/YYYY)
	        			editData(choice, searchID);
	        			break;
	        		case 7: //Change address (homeAddress, zipcode, city, state)
	        			editData(choice, searchID);
	        			break;
	        		case 8: //Change registration date
	        			editData(choice,searchID);
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
  //Retrive choices and customer ID from editCustomer() then execute this function : 
    //Done (All the function works well with Address and Person (super) class)
    @SuppressWarnings("deprecation")
	public static void editData(int choice, int customerID) 
    {
    	String result="";
    	switch(choice)
    	{
    		case 1: //Change name
    			String newName = "";
    			do{
    				JLabel label = new JLabel("<html>Enter your new <b>name</b> (-1 to cancel)</html>");
    				label.setFont(new Font("Arial", Font.PLAIN, 18));
    				do {
    		    		result = JOptionPane.showInputDialog(null, label, "Edit Customer", JOptionPane.INFORMATION_MESSAGE);
    		    		if (result == null) {
    						result = "-1";
    					}
    				}while(result.isEmpty());
    				result = result.trim();
    				if (result.equals("-1")) {
    					return;
    				}
    	    		newName = result;
				}while(Person.checkName(newName) == false);
				customerList[customerID].setName(newName);
				JLabel label2 = new JLabel("<html><p style='color:green'>You have successfully set your name into " + newName + "!</p></html>");
	        	label2.setFont(new Font("Arial", Font.BOLD, 16));
	        	JOptionPane.showMessageDialog(null, label2, "Succesful Edited",JOptionPane.INFORMATION_MESSAGE);
				break;
	    	
    		
    		case 2: //Change IC number	
    			String newIcNumber = ""; 
    			do{
    				JLabel label = new JLabel("<html>Enter your new <b>IC Number</b> (-1 to cancel)</html>");
    				label.setFont(new Font("Arial", Font.PLAIN, 18));
    				do {
    		    		result = JOptionPane.showInputDialog(null, label, "Edit Customer", JOptionPane.INFORMATION_MESSAGE);
    		    		if (result == null) {
    						result = "-1";
    					}
    				}while(result.isEmpty());
    				result = result.trim();
    				if (result.equals("-1")) {
    					return;
    				}
    	    		newIcNumber = result;
		    	}while(Person.checkIcNumber(newIcNumber) == false);
		    	customerList[customerID].setIcNumber(newIcNumber);
		    	label2 = new JLabel("<html><p style='color:green'>You have successfully set your IC number into " + newIcNumber + "!</p></html>");
	        	label2.setFont(new Font("Arial", Font.BOLD, 16));
	            JOptionPane.showMessageDialog(null, label2, "Succesful Edited",JOptionPane.INFORMATION_MESSAGE);
		    	break;
	    	
    		
    		case 3: //Change contact number
    			String newContactNumber = "";
    			do{
    				JLabel label = new JLabel("<html>Enter your new <b>Contact Number</b> (-1 to cancel)</html>");
    				label.setFont(new Font("Arial", Font.PLAIN, 18));
    				do {
    		    		result = JOptionPane.showInputDialog(null, label, "Edit Customer", JOptionPane.INFORMATION_MESSAGE);
    		    		if (result == null) {
    						result = "-1";
    					}
    				}while(result.isEmpty());
    				result = result.trim();
    				if (result.equals("-1")) {
    					return;
    				}
    	    		newContactNumber = result;
		    	}while(Person.checkContactNumber(newContactNumber) == false);
		    	customerList[customerID].setContactNumber(newContactNumber);
		    	label2 = new JLabel("<html><p style='color:green'>You have successfully set your Contact Number into " + newContactNumber + "!</p></html>");
	        	label2.setFont(new Font("Arial", Font.BOLD, 16));
	            JOptionPane.showMessageDialog(null, label2, "Succesful Edited",JOptionPane.INFORMATION_MESSAGE);
		    	break;
	    	
    		
    		case 4: //Change email address
    			String newEmail = "";
    			do{
    				JLabel label = new JLabel("<html>Enter your new <b>Email</b> (-1 to cancel)</html>");
    				label.setFont(new Font("Arial", Font.PLAIN, 18));
    				do {
    		    		result = JOptionPane.showInputDialog(null, label, "Edit Customer", JOptionPane.INFORMATION_MESSAGE);
    		    		if (result == null) {
    						result = "-1";
    					}
    				}while(result.isEmpty());
    				result = result.trim();
    				if (result.equals("-1")) {
    					return;
    				}
    	    		newEmail = result;
	    		}while(Person.checkEmail(newEmail) == false);
	    		customerList[customerID].setEmail(newEmail);
	    		label2 = new JLabel("<html><p style='color:green'>You have successfully set your Email into " + newEmail + "!</p></html>");
	        	label2.setFont(new Font("Arial", Font.BOLD, 16));
	            JOptionPane.showMessageDialog(null, label2, "Succesful Edited",JOptionPane.INFORMATION_MESSAGE);
		    	break;
    		
    		
    		case 5: //Change gender
    			String answer = "";
    			do{
    				JLabel label = new JLabel("<html>Are you sure you want to change your <b>gender</b>??(Y/N) (-1 to cancel)</html>");
    				label.setFont(new Font("Arial", Font.PLAIN, 18));
    				do {
    		    		result = JOptionPane.showInputDialog(null, label, "Edit Customer", JOptionPane.INFORMATION_MESSAGE);
    		    		if (result == null) {
    						result = "-1";
    					}
    				}while(result.isEmpty());
    				result = result.trim();
    				if (result.equals("-1")) {
    					return;
    				}
    				answer = result;
    			}while(checkAnswer(answer.charAt(0)) == false);
    			if (answer.toUpperCase().charAt(0)=='Y') {
	    			if(customerList[customerID].getGender() == 'M')
	    			{
	    				customerList[customerID].setGender('F');
	    				label2 = new JLabel("<html><p style='color:green'>You have successfully change your gender into " + "Female" + "!</p></html>");
	    	        	label2.setFont(new Font("Arial", Font.BOLD, 16));
	    	            JOptionPane.showMessageDialog(null, label2, "Succesful Edited",JOptionPane.INFORMATION_MESSAGE);
	    			} else
	    			{
	    				customerList[customerID].setGender('M');
	    				label2 = new JLabel("<html><p style='color:green'>You have successfully change your gender into " + "Male" + "!</p></html>");
	    	        	label2.setFont(new Font("Arial", Font.BOLD, 16));
	    	            JOptionPane.showMessageDialog(null, label2, "Succesful Edited",JOptionPane.INFORMATION_MESSAGE);
	    			}
    			}	
		    	break;
    			
    		
    		case 6:
    			//Birth date
    			Date birthdate = new Date();
    			boolean validInput = true;
    			do {
    				JLabel label = new JLabel("<html>Enter your new <b>Birth Date</b> (DD-mm-yyyy) (-1 to cancel)</html>");
    				label.setFont(new Font("Arial", Font.PLAIN, 18));
    				do {
    		    		result = JOptionPane.showInputDialog(null, label, "Edit Customer", JOptionPane.INFORMATION_MESSAGE);
    		    		if (result == null) {
    						result = "-1";
    					}
    				}while(result.isEmpty());
    				result = result.trim();
    				if (result.equals("-1")) {
    					return;
    				}
    				try {
    					birthdate = new SimpleDateFormat("dd-MM-yyyy").parse(result);
    					validInput = true;
    				}catch(Exception ex){
    					label2 = new JLabel("<html><p style='color:red'>Invalid date!</p></html>");
    	    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
    	    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
    					validInput = false;
    				}
    				Date now = new Date();
    				if (birthdate.getYear()>now.getYear()) {
    					label2 = new JLabel("<html><p style='color:red'>Invalid date!</p></html>");
    	    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
    	    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
    					validInput = false;
    				}
    			}while(validInput == false);
		    	
		    	customerList[customerID].setBirthDate(birthdate);
		    	label2 = new JLabel("<html><p style='color:green'>You have successfully set your birth date into " + birthdate + "!</p></html>");
	        	label2.setFont(new Font("Arial", Font.BOLD, 16));
	            JOptionPane.showMessageDialog(null, label2, "Succesful Edited",JOptionPane.INFORMATION_MESSAGE);
		    	break;
    		
    		case 7:
    			String newHomeAddress = "";
    			do{
    				JLabel label = new JLabel("<html>Enter your <b>new home address</b> (-1 to cancel)</html>");
    				label.setFont(new Font("Arial", Font.PLAIN, 18));
    				do {
    		    		result = JOptionPane.showInputDialog(null, label, "Edit Customer", JOptionPane.INFORMATION_MESSAGE);
    		    		if (result == null) {
    						result = "-1";
    					}
    				}while(result.isEmpty());
    				result = result.trim();
    				if (result.equals("-1")) {
    					return;
    				}
    				newHomeAddress = result;
		    	}while(Address.checkHomeAddress(newHomeAddress) == false);
			    
		    	String newZipcode = "";
		    	do{
    				JLabel label = new JLabel("<html>Enter your <b>new zip code</b> (-1 to cancel)</html>");
    				label.setFont(new Font("Arial", Font.PLAIN, 18));
    				do {
    		    		result = JOptionPane.showInputDialog(null, label, "Edit Customer", JOptionPane.INFORMATION_MESSAGE);
    		    		if (result == null) {
    						result = "-1";
    					}
    				}while(result.isEmpty());
    				result = result.trim();
    				if (result.equals("-1")) {
    					return;
    				}
    				newZipcode = result;
		    	}while(Address.checkZipcode(newZipcode) == false);
		    	
		    	String newCity = "";
		    	do{
    				JLabel label = new JLabel("<html>Enter your <b>new city</b> (-1 to cancel)</html>");
    				label.setFont(new Font("Arial", Font.PLAIN, 18));
    				do {
    		    		result = JOptionPane.showInputDialog(null, label, "Edit Customer", JOptionPane.INFORMATION_MESSAGE);
    		    		if (result == null) {
    						result = "-1";
    					}
    				}while(result.isEmpty());
    				result = result.trim();
    				if (result.equals("-1")) {
    					return;
    				}
    				newCity = result;
		    	}while(Address.checkCity(newCity) == false);
		    	
		    	String newState = "";
		    	do{
    				JLabel label = new JLabel("<html>Enter your <b>new state</b> (M/F) (-1 to cancel)</html>");
    				label.setFont(new Font("Arial", Font.PLAIN, 18));
    				do {
    		    		result = JOptionPane.showInputDialog(null, label, "Edit Customer", JOptionPane.INFORMATION_MESSAGE);
    		    		if (result == null) {
    						result = "-1";
    					}
    				}while(result.isEmpty());
    				result = result.trim();
    				if (result.equals("-1")) {
    					return;
    				}
    				newState = result;
		    	}while(Address.checkState(newState) == false);
		    	
		    	customerList[customerID].getAddress().setHomeAddress(newHomeAddress);
		    	customerList[customerID].getAddress().setZipcode(newZipcode);
		    	customerList[customerID].getAddress().setCity(newCity);
		    	customerList[customerID].getAddress().setState(newState);
		    	
		    	label2 = new JLabel("<html><p style='color:green'>You have successfully set your address into " + customerList[customerID].getAddress().toString() + "!</p></html>");
	        	label2.setFont(new Font("Arial", Font.BOLD, 16));
	            JOptionPane.showMessageDialog(null, label2, "Succesful Edited",JOptionPane.INFORMATION_MESSAGE);
		    	break;
    		case 8:
    			//Registration Date
    			Date regdate = new Date();
    			validInput = true;
    			do {
    				JLabel label = new JLabel("<html>Enter the new <b>Registration Date</b> (DD-mm-yyyy) (-1 to cancel)</html>");
    				label.setFont(new Font("Arial", Font.PLAIN, 18));
    				do {
    		    		result = JOptionPane.showInputDialog(null, label, "Edit Customer", JOptionPane.INFORMATION_MESSAGE);
    		    		if (result == null) {
    						result = "-1";
    					}
    				}while(result.isEmpty());
    				result = result.trim();
    				if (result.equals("-1")) {
    					return;
    				}
    				try {
    					birthdate = new SimpleDateFormat("dd-MM-yyyy").parse(result);
    					validInput = true;
    				}catch(Exception ex){
    					label2 = new JLabel("<html><p style='color:red'>Invalid date!</p></html>");
    	    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
    	    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
    					validInput = false;
    				}
    			}while(validInput == false);
		    	
		    	customerList[customerID].setRegDate(regdate);
		    	label2 = new JLabel("<html><p style='color:green'>You have successfully set the registration date into " + regdate + "!</p></html>");
	        	label2.setFont(new Font("Arial", Font.BOLD, 16));
	            JOptionPane.showMessageDialog(null, label2, "Succesful Edited",JOptionPane.INFORMATION_MESSAGE);
		    	break;
    		case 9:
    			//Points
    			int points = 0;
    			do {
    				JLabel label = new JLabel("<html>Enter the new <b>Points</b> (-1 to cancel)</html>");
    				label.setFont(new Font("Arial", Font.PLAIN, 18));
    				do {
    		    		result = JOptionPane.showInputDialog(null, label, "Edit Customer", JOptionPane.INFORMATION_MESSAGE);
    		    		if (result == null) {
    						result = "-1";
    					}
    				}while(result.isEmpty());
    				result = result.trim();
    				if (result.equals("-1")) {
    					return;
    				}
    				try {
    					points = Integer.parseInt(result);
    					validInput = true;
    				}catch(Exception ex){
    					label2 = new JLabel("<html><p style='color:red'>Invalid integer!</p></html>");
    	    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
    	    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
    					validInput = false;
    				}
    			}while(validInput == false);
    			customerList[customerID].setPoints(points);;
		    	label2 = new JLabel("<html><p style='color:green'>You have successfully set the points into " + points + "!</p></html>");
	        	label2.setFont(new Font("Arial", Font.BOLD, 16));
	            JOptionPane.showMessageDialog(null, label2, "Succesful Edited",JOptionPane.INFORMATION_MESSAGE);
	            break;
    	}
    }
    public static void displayCustomer() {
    	int searchID = searchCustomer(); //Use the searchCustomer methods to get the salesUsername
    	if (searchID != -1) {
    		System.out.printf("=========Customer Details=========\n"
        			+ "%s", customerList[searchID].toString());
    	}
    	
    }
    public static void customerMenu() {
		int choice = 0;
		boolean valid = false;
		String input = "";
		do {
			valid = true;
	    	JLabel label = new JLabel("<html><b>Customer Menu</b><br>1. Create customer<br>2. Display the customer<br>3. Remove the customer<br>"
	    			+ "4. List the customer<br>5. Edit the customer<br>-1. Return to main menu</html>");
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
	        			addCustomer();
	        			break;
	        		case 2:
	        			displayCustomer();
	        			break;
	        		case 3:
	        			deleteCustomer();
	        			break;
	        		case 4:
	        			listCustomer();
	        			break;
	        		case 5:
	        			editCustomer();
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
    //Validate yes or no
    public static boolean checkAnswer(char answer)
    {
    	char c1 = Character.toUpperCase(answer);
    	if(c1 == 'Y' || c1 == 'N')
    	{
    		return true;
    	}
    	System.out.println("The answer you enter is invalid");
    	return false;
    }
    
    //Convert String to Date ("dd-MM-yyyy")
    public static Date StringToDate(String str) {
        Date date = new Date();
        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse(str);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return date;
    }
}
