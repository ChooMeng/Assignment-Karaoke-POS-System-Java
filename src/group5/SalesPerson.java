package group5;
import javax.swing.*;

import group5.entity.Address;
import group5.entity.Person;

import java.util.*;
import java.text.*;
import java.awt.*;

public class SalesPerson extends Person {
	//data members
	 private static int totalSalesPerson=0;
	 private static SalesPerson[]salesPersonList = new SalesPerson[100];
     private String salesUsername;
     private String salesPassword;
     private Date salesEnterDate;
     
     //No constructor arguement
     public SalesPerson(){}
     
    //Constructor include the Person which is Superclass
    public SalesPerson(String name,String icNumber,String contactNumber,String email,char gender,Date birthDate,Address address,Date salesEnterDate,String salesPassword)
   	{
       super(name, icNumber, contactNumber, email, gender, birthDate, address);
       salesUsername = "S" + (totalSalesPerson + 1);
       this.salesEnterDate=salesEnterDate;
       this.salesPassword = salesPassword;
       totalSalesPerson++;
    }
    
    //getter
    public String getSalesUserName() {return salesUsername;}
    public String getSalesPassword() {
		return salesPassword;
    }
    public Date getSalesEnterDate() { return salesEnterDate; }
    public static int getTotalSalesPerson() { return totalSalesPerson; }
    public static SalesPerson[] getSalesPersonList() { return salesPersonList; }
	
	
	//setter
	public void setSalesUsername(String salesUsername) {this.salesUsername = salesUsername;}
	public void setSalesEnterDate(Date salesEnterDate) {this.salesEnterDate = salesEnterDate;}
	public void setSalesPassword(String salesPassword) {
		this.salesPassword = salesPassword;
	}
	public static void setTotalSalesPerson(int totalSalesPerson) { SalesPerson.totalSalesPerson = totalSalesPerson; }
    public static void setSalesPersonList(SalesPerson[] salesPersonList) { SalesPerson.salesPersonList = salesPersonList; }
	
	public String toString()
	{
   		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
		String dateRegistered= formatter.format(salesEnterDate);
		return String.format("\nSales Person Username: %s\nSales Person Enter Date: %s\n",
   		salesUsername, dateRegistered) + super.toString();
    }
	
	public static void defaultSalesPerson()
    {
    	Address address[] = {
    		new Address("10 Lorong 4 Bandar Tasek Mutiara", "14420", "Simpang Ampat", "Pulau Pinang"),
    		new Address("20 Lorong 5 Bandar Tasek Mutiara", "14120", "Bayan Baru", "Pulau Pinang"),
    		new Address("35 Lorong 6 Taman Sukun", "14100", "Bukit Mertajam", "Pulau Pinang"),
    		new Address("45 Persiaran Bayan Baru", "14325", "Bayan Baru", "Pulau Pinang"),
    		new Address("50 Taman Tanjung Bungah", "17354", "Tanjong Bungah", "Pulau Pinang")
    	};
    	salesPersonList[0] = new SalesPerson("WeiChung", "011001076489", "016494796", "billy0421@gmail.com", 'M', StringToDate("01-10-2001"), address[0], StringToDate("09-06-2020"),"123456");
    	salesPersonList[1] = new SalesPerson("ChooMeng", "010113042563", "01137145015", "jammes0113@gmail.com", 'M', StringToDate("13-01-2001"), address[1], StringToDate("15-07-2020"),"123456");
    	salesPersonList[2] = new SalesPerson("ZhiYan", "001227071578", "0125271589", "chris0527@gmail.com", 'F', StringToDate("27-12-2000"), address[2], StringToDate("25-07-2020"),"123456");
    	salesPersonList[3] = new SalesPerson("William", "010417070893", "0182875567", "adam1234@gmail.com", 'M', StringToDate("17-04-2001"), address[3], StringToDate("09-08-2020"),"123456");
    	salesPersonList[4] = new SalesPerson("Timmy", "001206072595", "0164810091", "tim4561.gmail.com", 'M', StringToDate("06-12-2000"), address[4], StringToDate("11-08-2020"),"123456");
    }
	
	//Add new SalesPerson
	@SuppressWarnings("deprecation")
	public static void addSalesPerson()
    {
		String result = "", name = "", icNumber = "", contactNumber = "", email = "", homeAddress = "", zipCode = "", city = "", state="", password="";
		char gender = ' ';
    	Date birthdate = new Date();
		//Sales Person Name
		do{
			JLabel label = new JLabel("<html>Enter <b>Name</b>(-1 to cancel)</html>");
			label.setFont(new Font("Arial", Font.PLAIN, 18));
			do {
	    		result = JOptionPane.showInputDialog(null, label, "Create sales person", JOptionPane.INFORMATION_MESSAGE);
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
    	//Sales Person IC number
		do{
			JLabel label = new JLabel("<html>Enter <b>IC Number</b>(-1 to cancel)</html>");
			label.setFont(new Font("Arial", Font.PLAIN, 18));
			do {
	    		result = JOptionPane.showInputDialog(null, label, "Create sales person", JOptionPane.INFORMATION_MESSAGE);
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
    	
    	//Sales Person Contact Number
		do{
			JLabel label = new JLabel("<html>Enter <b>Contact Number</b>(-1 to cancel)</html>");
			label.setFont(new Font("Arial", Font.PLAIN, 18));
			do {
	    		result = JOptionPane.showInputDialog(null, label, "Create sales person", JOptionPane.INFORMATION_MESSAGE);
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
    		
    	//Sales Person Email
		do{
			JLabel label = new JLabel("<html>Enter <b>Email</b>(-1 to cancel)</html>");
			label.setFont(new Font("Arial", Font.PLAIN, 18));
			do {
	    		result = JOptionPane.showInputDialog(null, label, "Create sales person", JOptionPane.INFORMATION_MESSAGE);
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
    	
    	//Sales Person Gender
    	do{
			JLabel label = new JLabel("<html>Enter your <b>Gender</b> (M/F) (-1 to cancel)</html>");
			label.setFont(new Font("Arial", Font.PLAIN, 18));
			do {
	    		result = JOptionPane.showInputDialog(null, label, "Create sales person", JOptionPane.INFORMATION_MESSAGE);
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
	    		result = JOptionPane.showInputDialog(null, label, "Create sales person", JOptionPane.INFORMATION_MESSAGE);
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
    	do{
			JLabel label = new JLabel("<html>Home Address<br>Enter your <b>address</b> (-1 to cancel)</html>");
			label.setFont(new Font("Arial", Font.PLAIN, 18));
			do {
	    		result = JOptionPane.showInputDialog(null, label, "Create sales person", JOptionPane.INFORMATION_MESSAGE);
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
    	do{
			JLabel label = new JLabel("<html>Home Address<br>Enter your <b>ZipCode</b> (-1 to cancel)</html>");
			label.setFont(new Font("Arial", Font.PLAIN, 18));
			do {
	    		result = JOptionPane.showInputDialog(null, label, "Create sales person", JOptionPane.INFORMATION_MESSAGE);
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
    	do{
			JLabel label = new JLabel("<html>Home Address<br>Enter your <b>City</b> (-1 to cancel)</html>");
			label.setFont(new Font("Arial", Font.PLAIN, 18));
			do {
	    		result = JOptionPane.showInputDialog(null, label, "Create sales person", JOptionPane.INFORMATION_MESSAGE);
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
    	do{
			JLabel label = new JLabel("<html>Home Address<br>Enter your <b>State</b> (-1 to cancel)</html>");
			label.setFont(new Font("Arial", Font.PLAIN, 18));
			do {
	    		result = JOptionPane.showInputDialog(null, label, "Create sales person", JOptionPane.INFORMATION_MESSAGE);
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
    	
    	//Password
    	do{
			JLabel label = new JLabel("<html>Authenication<br>Enter your <b>Password</b> for login (-1 to cancel)</html>");
			label.setFont(new Font("Arial", Font.PLAIN, 18));
			do {
	    		result = JOptionPane.showInputDialog(null, label, "Create sales person", JOptionPane.INFORMATION_MESSAGE);
	    		if (result == null) {
					result = "-1";
				}
			}while(result.isEmpty());
			result = result.trim();
			if (result.equals("-1")) {
				return;
			}
    		password = result;
    	}while(Person.checkPassword(password) == false);
    	Address address = new Address(homeAddress, zipCode, city, state);
    	Date salesEnterDate = new Date();
    	salesPersonList[totalSalesPerson] = new SalesPerson(name, icNumber, contactNumber, email, gender, birthdate, address,salesEnterDate, password);
    	JLabel label2 = new JLabel("<html><p style='color:green'>Successful registered a new SalesPerson!</p></html>");
    	label2.setFont(new Font("Arial", Font.BOLD, 16));
        JOptionPane.showMessageDialog(null, label2, "Successful Removed",JOptionPane.INFORMATION_MESSAGE);
    	System.out.println(salesPersonList[totalSalesPerson - 1].toString());
    }
    
    //Show all the list of the SalesPerson
    public static void listSalesPerson() 
    {
    	System.out.printf("%-10s %-32s %-13s %s", "USERNAME", "NAME", "IC NUMBER", "CONTACT NUMBER\n");
    	for(int i = 0; i < totalSalesPerson; i++)
    	{
    		System.out.printf("%-8s | %-30s | %12s | %-12s |\n", 
    		salesPersonList[i].getSalesUserName(), salesPersonList[i].getName(), salesPersonList[i].getIcNumber(), salesPersonList[i].getContactNumber());
    	}
    	System.out.println("\n\n");
    }
    
    //Search the SalesUsername then display the SalesPerson details
    public static int searchSalesPerson() 
    {
    	listSalesPerson();
    	
    	String result = "";
    	boolean resultFound = false;
    	JLabel label = new JLabel("<html>Enter the <b>Sales Person UserName</b> to search (-1 to cancel)</html>");
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
			for(int i = 0; i < totalSalesPerson; i++)
	    	{
	    		if(result.equals(salesPersonList[i].getSalesUserName()))
	    		{	//Success to found the customer
	    			System.out.println(salesPersonList[i].toString());
	    			resultFound = true;
	    			return i;
	    		}
	    	}
		}while(resultFound==false);
		
		return -1;
    }
    
    //Search salesUsername then ask for confirmation of delete the SalesUsername
    public static void deleteSalesPerson() 
    {
    	int searchUsername = searchSalesPerson(); //Use the searchSalesPerson methods to get the salesUsername
    	
    	String answer = "";
    	do{
    		JLabel label = new JLabel("<html>Are you sure you want to <b>delete<b> this SalesPerson?(Y/N)</html>");
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
    	if(c == 'Y')
    	{
    		for(int i = 0; i < totalSalesPerson; i++)
            {
                if(i == searchUsername)
                {
                    salesPersonList[i].setSalesUsername("[OLD]" + salesPersonList[i].getSalesUserName());
                }
                if(i > searchUsername)
                {
                    salesPersonList[i - 1] = salesPersonList[i];
                    String newSalesPerson = "S" + i; 
                    salesPersonList[i - 1].setSalesUsername(newSalesPerson);
                }
                if(i == (totalSalesPerson- 1))
                {
                    salesPersonList[i] = null;
                }
            }
            totalSalesPerson--;
            JLabel label2 = new JLabel("<html><p style='color:green'>Succesful removed the sales person!</p></html>");
        	label2.setFont(new Font("Arial", Font.BOLD, 16));
            JOptionPane.showMessageDialog(null, label2, "Succesful Removed",JOptionPane.INFORMATION_MESSAGE);
    	} 
    }
    
    //Search salesUsername then show edit menu
    public static void editSalesPerson() 
    {
    	int searchUsername = searchSalesPerson(); //Use the searchSalesPerson methods to get the salesUsername
    	if (searchUsername==-1) {
    		return;
    	}
    	//Edit member menu
    	int choice = 0;
    	boolean valid = false;
    	String input = "";
    	
    	do {
			valid = true;
	    	JLabel label = new JLabel("<html>Which please select the data that you would like to edit<br>1. Name<br>" + 
	    			"2. IC number<br>3. Contact number<br>"
	    			+ "4. Email<br>5. Gender<br>6. Birth Date<br>7. Address<br>8. Password" + 
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
	        			editData(choice, searchUsername);
	        			break;
	        		case 2: //Change IC number
	        			editData(choice, searchUsername);
	        			break;
	        		case 3: //Change contact number
	        			editData(choice, searchUsername);
	        			break;
	        		case 4: //Change email address
	        			editData(choice, searchUsername);
	        			break;
	        		case 5: //Change gender
	        			editData(choice, searchUsername);
	        			break;
	        		case 6: //Change birth date (DD/MM/YYYY)
	        			editData(choice, searchUsername);
	        			break;
	        		case 7: //Change address (homeAddress, zipcode, city, state)
	        			editData(choice, searchUsername);
	        			break;
	        		case 8: //Change password
	        			editData(choice,searchUsername);
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
    
    //Retrive choices and salesUsername from editSalesPerson() then execute this function
    @SuppressWarnings("deprecation")
	public static void editData(int choice, int salesUsername) 
    {
    	String result = "";
    	switch(choice)
    	{
    		case 1: //Change name
    			String newName = "";
    			do{
    				JLabel label = new JLabel("<html>Enter your new <b>name</b> (-1 to cancel)</html>");
    				label.setFont(new Font("Arial", Font.PLAIN, 18));
    				do {
    		    		result = JOptionPane.showInputDialog(null, label, "Edit sales person", JOptionPane.INFORMATION_MESSAGE);
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
				salesPersonList[salesUsername].setName(newName);
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
    		    		result = JOptionPane.showInputDialog(null, label, "Edit sales person", JOptionPane.INFORMATION_MESSAGE);
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
		    	salesPersonList[salesUsername].setIcNumber(newIcNumber);
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
    		    		result = JOptionPane.showInputDialog(null, label, "Edit sales person", JOptionPane.INFORMATION_MESSAGE);
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
		    	salesPersonList[salesUsername].setContactNumber(newContactNumber);
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
    		    		result = JOptionPane.showInputDialog(null, label, "Edit sales person", JOptionPane.INFORMATION_MESSAGE);
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
	    		salesPersonList[salesUsername].setEmail(newEmail);
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
    		    		result = JOptionPane.showInputDialog(null, label, "Create sales person", JOptionPane.INFORMATION_MESSAGE);
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
	    			if(salesPersonList[salesUsername].getGender() == 'M')
	    			{
	    				salesPersonList[salesUsername].setGender('F');
	    				label2 = new JLabel("<html><p style='color:green'>You have successfully change your gender into " + "Female" + "!</p></html>");
	    	        	label2.setFont(new Font("Arial", Font.BOLD, 16));
	    	            JOptionPane.showMessageDialog(null, label2, "Succesful Edited",JOptionPane.INFORMATION_MESSAGE);
	    			} else
	    			{
	    				salesPersonList[salesUsername].setGender('M');
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
    		    		result = JOptionPane.showInputDialog(null, label, "Create sales person", JOptionPane.INFORMATION_MESSAGE);
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
		    	salesPersonList[salesUsername].setBirthDate(birthdate);
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
    		    		result = JOptionPane.showInputDialog(null, label, "Create sales person", JOptionPane.INFORMATION_MESSAGE);
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
    		    		result = JOptionPane.showInputDialog(null, label, "Create sales person", JOptionPane.INFORMATION_MESSAGE);
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
    		    		result = JOptionPane.showInputDialog(null, label, "Create sales person", JOptionPane.INFORMATION_MESSAGE);
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
    		    		result = JOptionPane.showInputDialog(null, label, "Create sales person", JOptionPane.INFORMATION_MESSAGE);
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
		    	
		    	salesPersonList[salesUsername].getAddress().setHomeAddress(newHomeAddress);
		    	salesPersonList[salesUsername].getAddress().setZipcode(newZipcode);
		    	salesPersonList[salesUsername].getAddress().setCity(newCity);
		    	salesPersonList[salesUsername].getAddress().setState(newState);
		    	label2 = new JLabel("<html><p style='color:green'>You have successfully set your address into " + salesPersonList[salesUsername].getAddress().toString() + "!</p></html>");
	        	label2.setFont(new Font("Arial", Font.BOLD, 16));
	            JOptionPane.showMessageDialog(null, label2, "Succesful Edited",JOptionPane.INFORMATION_MESSAGE);
		    	break;
    		case 8:
    			String newPassword = "";
    			do{
    				JLabel label = new JLabel("<html>Enter your new <b>Password</b> (-1 to cancel)</html>");
    				label.setFont(new Font("Arial", Font.PLAIN, 18));
    				do {
    		    		result = JOptionPane.showInputDialog(null, label, "Edit sales person", JOptionPane.INFORMATION_MESSAGE);
    		    		if (result == null) {
    						result = "-1";
    					}
    				}while(result.isEmpty());
    				result = result.trim();
    				if (result.equals("-1")) {
    					return;
    				}
    	    		newPassword = result;
		    	}while(Person.checkContactNumber(newPassword) == false);
    			salesPersonList[salesUsername].setSalesPassword(newPassword);
    			label2 = new JLabel("<html><p style='color:green'>You have successfully set your password into " + newPassword + "!</p></html>");
	        	label2.setFont(new Font("Arial", Font.BOLD, 16));
	            JOptionPane.showMessageDialog(null, label2, "Succesful Edited",JOptionPane.INFORMATION_MESSAGE);
    		
    	}
    }
    public static void salesPersonMenu() {
		int choice = 0;
		boolean valid = false;
		String input = "";
		do {
			valid = true;
	    	JLabel label = new JLabel("<html><b>Sales Person Menu</b><br>1. Create sales person<br>2. Display the sales person<br>3. Remove the sales person<br>"
	    			+ "4. List the sales person<br>5. Edit the sales person<br>-1. Return to main menu</html>");
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
	        			addSalesPerson();
	        			break;
	        		case 2:
	        			displaySalesPerson();
	        			break;
	        		case 3:
	        			deleteSalesPerson();
	        			break;
	        		case 4:
	        			listSalesPerson();
	        			break;
	        		case 5:
	        			editSalesPerson();
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
    //Display member
    public static void displaySalesPerson() {
    	int searchUsername = searchSalesPerson(); //Use the searchSalesPerson methods to get the salesUsername
    	if (searchUsername != -1) {
    		System.out.printf("=========Sales Person Details=========\n"
        			+ "%s", salesPersonList[searchUsername].toString());
    	}
    	
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
    
    //Convert String to Date ("dd-MM-yyyy");
    public static Date StringToDate(String str) {
        Date date = new Date();
        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}

