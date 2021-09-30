package group5.entity;
import javax.swing.*;

import java.util.*;
import java.text.*;
import java.awt.Font;
import java.lang.Character;

public class Person {
	//Private data
	private String name;
	private String icNumber;
	private String contactNumber;
	private String email;
	private char gender;
	private Date birthDate;
	private Address address;
	
	//No arguement constructor
    public Person() {}
    
    //Constructor
    public Person(String name, String icNumber, String contactNumber, String email, char gender, Date birthDate, Address address)
    {
    	this.name = name;
    	this.icNumber = icNumber;
    	this.contactNumber = contactNumber;
    	this.email = email;
    	this.gender = gender;
    	this.birthDate = birthDate;
    	this.address = address;
    }
    
    //Getter
    public String getName() { return name; }
    public String getIcNumber() { return icNumber; }
    public String getContactNumber() { return contactNumber; }
    public String getEmail() { return email; }
    public char getGender() { return gender; }
    public Date getBirthDate() { return birthDate; }
    public Address getAddress() { return address; }
    
    //Setter
    public void setName(String name) { this.name = name; }
    public void setIcNumber(String icNumber) { this.icNumber = icNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public void setEmail(String email) { this.email = email; }
    public void setGender(char gender) { this.gender = gender; }
    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }
    public void setAddress(Address address) { this.address = address; }
    
    //Validation method
    public static boolean checkName(String name) //Validate name : Logic Done
    {
    	//The name should contain only letters and its length >= 5 && <= 30
		if(name.length() >= 3 && name.length() <= 50 && name.matches("^[a-zA-Z\\s]+$"))
		{
			return true;
		}
		else
		{
			JLabel label2 = new JLabel("<html><p style='color:red'>The name format should enter more than 3 and less than 50 of the letter characters!</p></html>");
	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
	        return false;
		}
    	
    }
    
    public static boolean checkPassword(String password) {
    	if (password.length()>=6 && password.length()<=32) {
    		return true;
    	}else {
    		JLabel label2 = new JLabel("<html><p style='color:red'>The password format should enter more than 6 and less than 32 of the characters!</p></html>");
	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
    		return false;
    	}
    }
    
    public static boolean checkIcNumber(String icNumber) //Validate icNumber : Logic Done
    {
    	//The icNumber should only contain 12 digit character
    	if(icNumber.length() == 12 && icNumber.matches("^[0-9]+$"))
    	{
    		return true;
    	} else{
    		JLabel label2 = new JLabel("<html><p style='color:red'>The IC number should have contain only 12 digit character without a \'-\'!</p></html>");
	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
    		return false;
    	}
    	
    	
    }
    
    public static boolean checkContactNumber(String contactNumber) //Validate contactNumber : Logic Done
    {
    	//The contact number should contain only 11 digit character
    	if((contactNumber.length() == 10 || contactNumber.length() == 11) && contactNumber.matches("[0-9]+$"))
    	{
			return true;
    	} else
		{
    		JLabel label2 = new JLabel("<html><p style='color:red'>The contact number should have contain only 10 to 11 digit character without a\'-\'!</p></html>");
	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
			return false;
		}
    	
    	
    }
    
    public static boolean checkEmail(String email) //Validate email : Logic Done
    {
    	String emailValidation = "^[A-Za-z0-9+_\\.-]+@[A-Za-z0-9\\.-]+$";
    	if(email.length() >= 10 && email.length() <= 50 && email.matches(emailValidation))
    	{
    		return true;
    	} else
    	{
    		JLabel label2 = new JLabel("<html><p style='color:red'>The email you enter should follow with the format of the standard email format!\n Eg. (dailykaraoke@gmail.com)</p></html>");
	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
			return false;
    	}
    }
    
    public static boolean checkGender(char gender) //Validate gender : Logic Done
    {
    	char c1 = Character.toUpperCase(gender);
    	if(c1 == 'M' || c1 == 'F')
    	{
    		return true;
    	} else 
    	{
    		JLabel label2 = new JLabel("<html><p style='color:red'>The gender you enter is invalid!</p></html>");
	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
	        return false;
    	}
    	
    	
    }
    
    //To String Method
    public String toString()
    {
    	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String birthDay = dateFormat.format(birthDate);
        
        String genderSentence = "";
        if(gender == 'M')
        {
        	genderSentence  = "Male";
        } else
        {
        	genderSentence = "Female";
        }
    	
    	return String.format("%s, %s \n\nIC Number: %-15s \nContact Number: %-12s \nEmail Address: %-50s \nBirth date: %-10s\n\n%s\n",
    		name, genderSentence, icNumber, contactNumber, email, birthDay, address.toString());
    }
    public boolean equals(Object o) {
		if (!(o instanceof Person )) {
			return false;
		}
		Person o2 = (Person) o;
		if ((this.name != null && o2.name!=null)&&this.name.equals(o2.name)) {
			return true;
		}
		return false;
	}
}