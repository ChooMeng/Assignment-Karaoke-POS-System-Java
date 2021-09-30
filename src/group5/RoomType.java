package group5;
import java.awt.Font;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class RoomType {
	private static int totalRoomType = 0;
	private static RoomType[] roomTypeList = new RoomType[100];
	protected static HashMap<String,Integer> typeID = new HashMap<String,Integer>();
	private String roomTypeID;
	private String roomName;
	private double roomPricePerPax;
	private String roomSize;
	private int roomMaxCapacity;
	private String roomDescription;
	
	//Constructor
	public RoomType(String roomName, double roomPricePerPax, String roomSize, int roomMaxCapacity, String roomDescription){
		roomTypeID = "RT"+(totalRoomType+1);
		typeID.put(roomTypeID, totalRoomType);
		this.roomName = roomName;
		this.roomPricePerPax = roomPricePerPax;
		this.roomSize = roomSize;
		this.roomMaxCapacity = roomMaxCapacity;
		this.roomDescription = roomDescription;
		totalRoomType++;
	}
	//setter
	public static void setTotalRoomType(int totalRoomType) {
		RoomType.totalRoomType = totalRoomType;
	}
	public void setRoomTypeID(String roomTypeID) {
		this.roomTypeID = roomTypeID;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public void setRoomPricePerPax (double roomPricePerPax) {
		this.roomPricePerPax = roomPricePerPax;
	}
	public void setRoomSize(String roomSize) {
		this.roomSize = roomSize;
	}
	public void setRoomMaxCapacity (int roomMaxCapacity){
		this.roomMaxCapacity = roomMaxCapacity;
	}
	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}
	public static void setRoomTypeList(RoomType[] roomTypeList) {
		RoomType.roomTypeList = roomTypeList;
	}
	
	//getter
	public static int getTotalRoomType() {
		return totalRoomType;
	}
	public String getRoomTypeID() {
		return roomTypeID;
	}
	public String getRoomName() {
		return roomName;
	}
	public double getRoomPricePerPax() {
		return roomPricePerPax;
	}
	public String getRoomSize() {
		return roomSize;
	}
	public int getRoomMaxCapcity() {
		return roomMaxCapacity;
	}
	public String getRoomDescription() {
		return roomDescription;
	}
	public static RoomType[] getRoomTypeList() {
		return roomTypeList;
	}
	
	//Convert room type details into string
	public String toString() {
		return String.format(
				"Room Type ID: %s\n"
				+ "Room Name: %s\n"
				+ "Room Price Per Pax Per Hour: %.2f\n"
				+ "Room Size: %s\n"
				+ "Room Max Capacity: %d\n"
				+ "Room Description: %s\n", roomTypeID,roomName,roomPricePerPax,roomSize,roomMaxCapacity,roomDescription);
	}
	public boolean equals(Object o) {
		if (!(o instanceof RoomType )) {
			return false;
		}
		RoomType o2 = (RoomType) o;
		if ((this.roomTypeID != null && o2.roomTypeID!=null)&&this.roomTypeID.equals(o2.roomTypeID)) {
			return true;
		}
		return false;
	}
	
	//Set default room type value
	public static void defaultRoomType() {
		roomTypeList[0] = new RoomType("Small Room", 15, "Small",3,"Size - 20m x 10m, 2 Mic");
		roomTypeList[1] = new RoomType("Medium Room", 20, "Medium",6,"Size - 25m x 15m, 5 Mic");
		roomTypeList[2] = new RoomType("Large Room", 30, "Large",14,"Size - 30m x 25m, 8 Mic");
		roomTypeList[3] = new RoomType("Huge Room", 45, "Huge",20,"Size - 45m x 40m, 14 Mic");
	}
	
	//Create new room type
	public static void addRoomType() {
		String roomName = "", roomSize = "", roomDescription = "", result="";
		int roomMaxCapacity = 0, choice = 0;
		double roomPricePerPax = 0;
		
		JLabel label = new JLabel("<html>Enter the <b>Room Name</b> (-1 to cancel)</html>");
    	label.setFont(new Font("Arial", Font.PLAIN, 18));
    	do {
	    	roomName = JOptionPane.showInputDialog(null, label,"Create room type",JOptionPane.INFORMATION_MESSAGE);
	    	if (roomName==null) {
	    		roomName = "-1";
	    	}
    	}while(roomName.isEmpty());
		roomName = roomName.trim();
		if (roomName.equals("-1")) {
			return;
		}
		label = new JLabel("<html>Enter the <b>Room Price Per Pax Per Hour</b> (-1 to cancel)</html>");
		label.setFont(new Font("Arial", Font.PLAIN, 18));
		boolean validOutput = true;
		do {
			validOutput = true;
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
			try {
				roomPricePerPax = Double.parseDouble(result);
			}catch (Exception e) {
				JLabel label2 = new JLabel("<html><p style='color:red'>Invalid Room Price Per Pax Per Hour!</p></html>");
    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
				validOutput=false;
			}
		}while(validOutput==false);
		label = new JLabel("<html>=====================<br/><b>[ROOM SIZE]</b><br/>1) Small<br/>2) Medium<br/>3) Large<br/>"
				+ "4) Huge<br/>-1) Cancel<br/>=====================<br/>Enter a <b>number</b> for choose the <b>Room Size</b> (-1 to cancel)</html>");
		label.setFont(new Font("Arial", Font.PLAIN, 18));
		validOutput = true;
		do {
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
			try {
				choice = Integer.parseInt(result);
				validOutput = true;
			}catch (Exception e) {
				JLabel label2 = new JLabel("<html><p style='color:red'>Incorrect choice number!</p></html>");
    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
				validOutput=false;
			}
			if (validOutput) {
				switch (choice) {
					case 1:
						roomSize = "Small";
						break;
					case 2:
						roomSize = "Medium";
						break;
					case 3:
						roomSize = "Large";
						break;
					case 4:
						roomSize = "Huge";
						break;
					case -1:
						return;
					default:
						JLabel label2 = new JLabel("<html><p style='color:red'>Incorrect choice number!</p></html>");
		    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
		    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
						validOutput=false;
				}
			}
		}while(validOutput==false);
		label = new JLabel("<html>Enter the <b>Room Max Capacity</b> (-1 to cancel)</html>");
		label.setFont(new Font("Arial", Font.PLAIN, 18));
		validOutput = true;
		do {
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
			try {
				roomMaxCapacity = Integer.parseInt(result);
				validOutput = true;
			}catch (Exception e) {
				JLabel label2 = new JLabel("<html><p style='color:red'>Invalid number!</p></html>");
    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
				validOutput=false;
			}
		}while(validOutput==false);
		
		label = new JLabel("<html>Enter the <b>Room Description</b> (-1 to cancel)</html>");
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
		roomDescription = result;
		roomTypeList[totalRoomType] = new RoomType(roomName, roomPricePerPax,roomSize,roomMaxCapacity,roomDescription);
		JLabel label2 = new JLabel("<html><p style='color:green'>SUCCESFUL CREATED A NEW ROOM TYPE!</p></html>");
    	label2.setFont(new Font("Arial", Font.BOLD, 16));
        JOptionPane.showMessageDialog(null, label2, "Succesful Created",JOptionPane.INFORMATION_MESSAGE);
	}
	
	//List the room type
	public static void listRoomType() {
		System.out.println("\nRoom Type");
		System.out.println("---------");
		for (int i = 0; i < totalRoomType;i++) {
			System.out.printf("%s) %s | RM%.2f/person\n",roomTypeList[i].roomTypeID,roomTypeList[i].roomName,roomTypeList[i].roomPricePerPax);
		}
	}
	
	//Display the room type
	public static void displayRoomType() {
		String roomTypeID = "";
		JLabel label = new JLabel("<html>Enter the <b>Room Type ID</b> that want to display (-1 to cancel)</html>");
    	label.setFont(new Font("Arial", Font.PLAIN, 18));
    	listRoomType();
    	do {
    		do {
    	    	roomTypeID = JOptionPane.showInputDialog(null, label,"Display room type",JOptionPane.INFORMATION_MESSAGE);
    	    	if (roomTypeID==null) {
    	    		roomTypeID = "-1";
    	    	}
        	}while(roomTypeID.isEmpty());
    		roomTypeID = roomTypeID.trim();
    		if (roomTypeID.equals("-1")) {
    			return;
    		}
    		if (typeID.containsKey(roomTypeID)) {
    			int roomTypeIndex = typeID.get(roomTypeID);
    			System.out.println("=====[Room Details]=====");
    			System.out.println(roomTypeList[roomTypeIndex].toString());
    		}else {
    			JLabel label2 = new JLabel("<html><p style='color:red'>Room Type ID not found!</p></html>");
    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
    		}
    	}while(!typeID.containsKey(roomTypeID));
    	
	}
	
	//Remove the room type
	public static void removeRoomType() {
		String roomTypeID = "";
		JLabel label = new JLabel("<html>Enter the <b>Room Type ID</b> that want to remove (-1 to cancel)</html>");
    	label.setFont(new Font("Arial", Font.PLAIN, 18));
    	listRoomType();
    	do {
    		do {
    	    	roomTypeID = JOptionPane.showInputDialog(null, label,"Remove room type",JOptionPane.INFORMATION_MESSAGE);
    	    	if (roomTypeID==null) {
    	    		roomTypeID = "-1";
    	    	}
        	}while(roomTypeID.isEmpty());
    		roomTypeID = roomTypeID.trim();
    		if (roomTypeID.equals("-1")) {
    			return;
    		}
			if (!typeID.containsKey(roomTypeID)) {
				JLabel label2 = new JLabel("<html><p style='color:red'>Room Type ID not found!</p></html>");
    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
			}
    	}while(!typeID.containsKey(roomTypeID));
    	int roomTypeIndex = typeID.get(roomTypeID);
		typeID.clear();
		for (int i = 0; i < totalRoomType;i++) {
			if (i==roomTypeIndex) {
				roomTypeList[i].setRoomTypeID("[OLD] "+roomTypeList[i].getRoomTypeID());
			}
			if (i>roomTypeIndex) {
				roomTypeList[i-1]=roomTypeList[i];
				String newRoomTypeID = "RT"+i;
				roomTypeList[i-1].setRoomTypeID(newRoomTypeID);
				
			}
			if (i==(totalRoomType-1)) {
				
				roomTypeList[i]=null;
			}
			
			typeID.put("RT"+(i+1), i);
			
		}
		totalRoomType--;
		JLabel label2 = new JLabel("<html><p style='color:green'>Succesful removed the room type!</p></html>");
    	label2.setFont(new Font("Arial", Font.BOLD, 16));
        JOptionPane.showMessageDialog(null, label2, "Succesful Removed",JOptionPane.INFORMATION_MESSAGE);
	}
	
	//Edit the room type details
	public static void editRoomType() {
		int choice = 0;
		boolean valid = false;
	
		String input = "",roomTypeID = "";
		do {
			JLabel label = new JLabel("<html>Enter the <b>Room Type ID</b> that want to edit (-1 to cancel)</html>");
	    	label.setFont(new Font("Arial", Font.PLAIN, 18));
	    	do {
		    	roomTypeID = JOptionPane.showInputDialog(null, label,"Edit room type",JOptionPane.INFORMATION_MESSAGE);
		    	if (roomTypeID==null) {
		    		roomTypeID = "-1";
		    	}
	    	}while(roomTypeID.isEmpty());
			roomTypeID = roomTypeID.trim();
			if (roomTypeID.equals("-1")) {
				return;
			}
			if (typeID.containsKey(roomTypeID)) {
				int roomTypeIndex = typeID.get(roomTypeID);
				do {
					valid = true;
					label = new JLabel("<html><b>Room Type Edit Menu</b><br>1. Edit room name<br>2. Edit room price per pax per hour<br>3. Edit room size<br>"
			    			+ "4. Edit room max capacity<br>5. Edit room descrption<br>-1. Return to room type menu</html>");
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
			        	valid = editRoomTypeChoice(choice,roomTypeIndex);
			        }else {
			        	JLabel label2 = new JLabel("<html><p style='color:red'>Incorrect choice number!</p></html>");
		    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
		    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
			        }
				}while(valid==false||choice!=-1);
				
			}else {
				JLabel label2 = new JLabel("<html><p style='color:red'>Room Type ID not found!</p></html>");
    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
			}
		}while(!typeID.containsKey(roomTypeID));
		
	}
	
	//Edit the room type details according to the choice
	public static boolean editRoomTypeChoice(int choice,int roomTypeIndex) {
		String result = "";
		switch (choice) {
			case 1:
				JLabel label = new JLabel("<html>Current room name: <b style='color:blue'>"+roomTypeList[roomTypeIndex].getRoomName()+"</b><br>Enter the <b>New Room Name</b> (-1 to cancel)</html>");
		    	label.setFont(new Font("Arial", Font.PLAIN, 18));
		    	do {
			    	result = JOptionPane.showInputDialog(null, label,"Edit room type",JOptionPane.INFORMATION_MESSAGE);
			    	if (result==null) {
			    		result = "-1";
			    	}
		    	}while(result.isEmpty());
		    		result = result.trim();
				if (result.equals("-1")) {
					return true;
				}
				roomTypeList[roomTypeIndex].setRoomName(result);
				JLabel label2 = new JLabel("<html><p style='color:green'>Succesful edited the room name!</p></html>");
		    	label2.setFont(new Font("Arial", Font.BOLD, 16));
		        JOptionPane.showMessageDialog(null, label2, "Succesful Edited",JOptionPane.INFORMATION_MESSAGE);
				break;
			case 2:
				label = new JLabel("<html>Current room price per pax per hour: <b style='color:blue'>RM"+String.format("%.2f", roomTypeList[roomTypeIndex].getRoomPricePerPax())+"</b><br>Enter the <b>Room Price Per Pax Per Hour</b> (-1 to cancel)</html>");
				label.setFont(new Font("Arial", Font.PLAIN, 18));
				boolean validOutput = true;
				do {
					validOutput = true;
					do {
				    	result = JOptionPane.showInputDialog(null, label,"Edit room type",JOptionPane.INFORMATION_MESSAGE);
				    	if (result==null) {
				    		result = "-1";
				    	}
			    	}while(result.isEmpty());
					result = result.trim();
					if (result.equals("-1")) {
						return true;
					}
					try {
						roomTypeList[roomTypeIndex].setRoomPricePerPax(Double.parseDouble(result));
					}catch (Exception e) {
						label2 = new JLabel("<html><p style='color:red'>Invalid Room Price Per Pax Per Hour!</p></html>");
		    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
		    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
						validOutput=false;
					}
				}while(validOutput==false);
				label2 = new JLabel("<html><p style='color:green'>Succesful edited the room price per pax per hour!</p></html>");
		    	label2.setFont(new Font("Arial", Font.BOLD, 16));
		        JOptionPane.showMessageDialog(null, label2, "Succesful Edited",JOptionPane.INFORMATION_MESSAGE);
				break;
			case 3:
				label = new JLabel("<html>=====================<br/><b>[ROOM SIZE]</b><br/>1) Small<br/>2) Medium<br/>3) Large<br/>"
						+ "4) Huge<br/>-1) Cancel<br/>====================="
						+ "<br>Current room size: <b style='color:blue'>"+roomTypeList[roomTypeIndex].getRoomSize()+"</b><br/>Enter a <b>number</b> for choose the <b>Room Size</b> (-1 to cancel)</html>");
				label.setFont(new Font("Arial", Font.PLAIN, 18));
				validOutput = true;
				do {
					do {
				    	result = JOptionPane.showInputDialog(null, label,"Edit room type",JOptionPane.INFORMATION_MESSAGE);
				    	if (result==null) {
				    		result = "-1";
				    	}
			    	}while(result.isEmpty());
					result = result.trim();
					if (result.equals("-1")) {
						return true;
					}
					try {
						choice = Integer.parseInt(result);
						validOutput = true;
					}catch (Exception e) {
						label2 = new JLabel("<html><p style='color:red'>Incorrect choice number!</p></html>");
		    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
		    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
						validOutput=false;
					}
					if (validOutput) {
						switch (choice) {
							case 1:
								roomTypeList[roomTypeIndex].setRoomSize("Small");
								break;
							case 2:
								roomTypeList[roomTypeIndex].setRoomSize("Medium");
								break;
							case 3:
								roomTypeList[roomTypeIndex].setRoomSize("Large");
								break;
							case 4:
								roomTypeList[roomTypeIndex].setRoomSize("Huge");
								break;
							case -1:
								return true;
							default:
								label2 = new JLabel("<html><p style='color:red'>Incorrect choice number!</p></html>");
				    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
				    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
								validOutput=false;
						}
					}
				}while(validOutput==false);
				label2 = new JLabel("<html><p style='color:green'>Succesful edited the room size!</p></html>");
		    	label2.setFont(new Font("Arial", Font.BOLD, 16));
		        JOptionPane.showMessageDialog(null, label2, "Succesful Edited",JOptionPane.INFORMATION_MESSAGE);
				break;
			case 4:
				label = new JLabel("<html>Current room max capacity: <b style='color:blue'>"+roomTypeList[roomTypeIndex].getRoomMaxCapcity()+"</b><br/>Enter the <b>Room Max Capacity</b> (-1 to cancel)</html>");
				label.setFont(new Font("Arial", Font.PLAIN, 18));
				validOutput = true;
				do {
					do {
				    	result = JOptionPane.showInputDialog(null, label,"Edit room type",JOptionPane.INFORMATION_MESSAGE);
				    	if (result==null) {
				    		result = "-1";
				    	}
			    	}while(result.isEmpty());
					result = result.trim();
					if (result.equals("-1")) {
						return true;
					}
					try {
						roomTypeList[roomTypeIndex].setRoomMaxCapacity(Integer.parseInt(result));
						validOutput = true;
					}catch (Exception e) {
						label2 = new JLabel("<html><p style='color:red'>Invalid number!</p></html>");
		    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
		    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
						validOutput=false;
					}
				}while(validOutput==false);
				label2 = new JLabel("<html><p style='color:green'>Succesful edited the room max capacity!</p></html>");
		    	label2.setFont(new Font("Arial", Font.BOLD, 16));
		        JOptionPane.showMessageDialog(null, label2, "Succesful Edited",JOptionPane.INFORMATION_MESSAGE);
				break;
			case 5:
				label = new JLabel("<html>Current room description: <b style='color:blue'>"+roomTypeList[roomTypeIndex].getRoomDescription()+"</b><br/>Enter the <b>Room Description</b> (-1 to cancel)</html>");
		    	label.setFont(new Font("Arial", Font.PLAIN, 18));
		    	do {
			    	result = JOptionPane.showInputDialog(null, label,"Edit room type",JOptionPane.INFORMATION_MESSAGE);
			    	if (result==null) {
			    		result = "-1";
			    	}
		    	}while(result.isEmpty());
		    	result = result.trim();
				if (result.equals("-1")) {
					return true;
				}
				roomTypeList[roomTypeIndex].setRoomDescription(result);
				label2 = new JLabel("<html><p style='color:green'>Succesful edited the room description!</p></html>");
		    	label2.setFont(new Font("Arial", Font.BOLD, 16));
		        JOptionPane.showMessageDialog(null, label2, "Succesful Edited",JOptionPane.INFORMATION_MESSAGE);
				break;
			case -1:
				return true;
			default:
				label2 = new JLabel("<html><p style='color:red'>Incorrect choice number!</p></html>");
    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
				return false;
		}
		return true;
	}
	
	//Room Type Menu
	public static void roomTypeMenu() {
		int choice = 0;
		boolean valid = false;
		String input = "";
		do {
			valid = true;
	    	JLabel label = new JLabel("<html><b>Room Type Menu</b><br>1. Create the room type<br>2. Display the room type<br>3. Remove the room type<br>"
	    			+ "4. List the room type<br>5. Edit the room type<br>-1. Return to main menu</html>");
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
	        		case 1:
	        			addRoomType();
	        			break;
	        		case 2:
	        			displayRoomType();
	        			break;
	        		case 3:
	        			removeRoomType();
	        			break;
	        		case 4:
	        			listRoomType();
	        			break;
	        		case 5:
	        			editRoomType();
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
	
}
