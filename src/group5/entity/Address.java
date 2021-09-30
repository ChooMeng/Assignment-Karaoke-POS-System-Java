package group5.entity;
import java.awt.Font;
import java.util.regex.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Address {
	//Data member
	private String homeAddress;
	private String zipcode;
	private String city;
	private String state;

    public Address() {} //No arguement constructor
    
    //Constructor
    public Address(String homeAddress, String zipcode, String city, String state)
    {
    	this.homeAddress = homeAddress;
    	this.zipcode = zipcode;
    	this.city = city;
    	this.state = state;
    }
    
    //Getter
    public String getHomeAddress(){ return homeAddress; }
    public String getZipcode() { return zipcode; }
    public String getCity() { return city; }
    public String getState() { return state; }
    
    //Setter
    public void setHomeAddress(String homeAddress) { this.homeAddress = homeAddress; }
    public void setZipcode(String zipcode) { this.zipcode = zipcode; }
    public void setCity(String city) { this.city = city;}
    public void setState(String state) { this.state = state; }
    
    public static boolean checkHomeAddress(String homeAddress) //Validate address
    {
    	String addressPattern = "^[A-Za-z0-9\\s,\\.\\/]+$";
    	if(homeAddress.length() >= 10 && homeAddress.length() <= 50 && homeAddress.matches(addressPattern))
    	{
    		return true;
    	}
    	else if(homeAddress.length() < 10 || homeAddress.length() > 50 || !homeAddress.matches(addressPattern))
    	{
    		JLabel label2 = new JLabel("<html><p style='color:red'>The home address you have enter should be more than 10 to 50 characters!</p></html>");
	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
    	}
    	
    	return false;
    }
    
    public static boolean checkZipcode(String zipcode) //Validate zipcode
    {
    	if(Pattern.matches("^[0-9]{5}$", zipcode) && zipcode.length() == 5) 
    	{
    		return true; 
    	}
    	else if(!Pattern.matches("^[0-9]{5}$", zipcode) || zipcode.length() != 5) 
    	{
    		JLabel label2 = new JLabel("<html><p style='color:red'>The zipcode you have enter should contain only 5 digits characters!</p></html>");
	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
    	}
    	
    	return false;
    }
    
    public static boolean checkCity(String city) //Validate City
    {
    	if(Pattern.matches("^[A-Za-z\\s]+$", city) && city.length() >= 5 && city.length() <= 30) 
    	{
    		return true;
    	}
    	else if(!Pattern.matches("^[A-Za-z\\s]+$", city) || city.length() < 5 || city.length() > 30) 
    	{
    		JLabel label2 = new JLabel("<html><p style='color:red'>The city you enter should have more than 5 to 30 letter character!</p></html>");
	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
    	}
    	
    	return false;
    }
    
    public static boolean checkState(String state) //Validate State
    {
    	if(state.matches("^[A-Za-z\\s]+$") && state.length() >= 5 && state.length() <= 20) 
    	{
    		return true; 
    	}
    	else if(!state.matches("^[A-Za-z\\s]+$") || state.length() < 5 || state.length() > 20) 
    	{
    		JLabel label2 = new JLabel("<html><p style='color:red'>The state you enter should have more than 5 to 20 letter character!</p></html>");
	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
    	}
    	
    	return false;
    }
    
    //To String Method
    public String toString()
    {
    	return String.format("%s\n%s, %s\n%s\n", homeAddress, zipcode, city, state);
    }
}