package group5;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Room{
	private static int totalRoom = 0;
	private static Room[] roomList = new Room[100];
	protected static HashMap<String, Integer> roomMap = new HashMap<String, Integer>();
	private String roomID;
	private RoomType roomType;
	private String area;
	//Constructor
	public Room() {
		
	}
	public Room(RoomType roomType, String area) {
		roomID = "R" + (totalRoom + 1);
		roomMap.put(roomID, totalRoom);
		this.roomType = roomType;
		this.area = area;
		totalRoom++;
	}
	//Getter
	public RoomType getRoomType() {
		return roomType;
	}
	
	public String getArea() {
		return area;
	}

	public String getRoomID() {
		return roomID;
	}

	public static int getTotalRoom() {
		return totalRoom;
	}

	public static Room[] getRoomList() {
		return roomList;
	}
	//Setter
	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}

	public static void setTotalRoom(int totalRoom) {
		Room.totalRoom = totalRoom;
	}

	public static void setRoomList(Room[] roomList) {
		Room.roomList = roomList;
	}
	//Convert room details into string
	public String toString() {
		return String.format("[%s]\n" + "Room Area: %s\n" + "%s", roomID, area, roomType.toString());
	}

	public boolean equals(Object o) {
		if (!(o instanceof Room )) {
			return false;
		}
		Room o2 = (Room) o;
		if ((this.roomID != null && o2.roomID!=null)&&this.roomID.equals(o2.roomID)) {
			return true;
		}
		return false;
	}
	
	//Create the room
	public static void addRoom() {
		RoomType roomType;
		int roomTypeIndex = 0;
		String area = "", result = "", roomTypeID = "";
		RoomType.listRoomType();
		JLabel label = new JLabel("<html>Enter the <b>Room Type ID</b> that want to select (-1 to cancel)</html>");
		label.setFont(new Font("Arial", Font.PLAIN, 18));
		do {
			do {
				result = JOptionPane.showInputDialog(null, label, "Create room", JOptionPane.INFORMATION_MESSAGE);
				if (result == null) {
					result = "-1";
				}
			} while (result.isEmpty());
			result = result.trim();
			if (result.equals("-1")) {
				return;
			}
			roomTypeID = result;
			if (RoomType.typeID.containsKey(roomTypeID)) {
				roomTypeIndex = RoomType.typeID.get(roomTypeID);

			} else {
				JLabel label2 = new JLabel("<html><p style='color:red'>Room Type ID not found!</p></html>");
				label2.setFont(new Font("Arial", Font.BOLD, 16));
				JOptionPane.showMessageDialog(null, label2, "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} while (!RoomType.typeID.containsKey(roomTypeID));
		roomType = RoomType.getRoomTypeList()[roomTypeIndex];
		label = new JLabel("<html>Enter the <b>Room Area</b> (-1 to cancel)</html>");
		label.setFont(new Font("Arial", Font.PLAIN, 18));
		do {
			result = JOptionPane.showInputDialog(null, label, "Create room", JOptionPane.INFORMATION_MESSAGE);
			if (result == null) {
				result = "-1";
			}
		} while (result.isEmpty());
		result = result.trim();
		if (result.equals("-1")) {
			return;
		}
		area = result;
		roomList[totalRoom] = new Room(roomType, area);
		JLabel label2 = new JLabel("<html><p style='color:green'>SUCCESFUL CREATED A NEW ROOM!</p></html>");
		label2.setFont(new Font("Arial", Font.BOLD, 16));
		JOptionPane.showMessageDialog(null, label2, "Succesful Created", JOptionPane.INFORMATION_MESSAGE);
	}
	
	//Display the room
	public static void displayRoom() {
		String roomID = "";
		listRoom();
		JLabel label = new JLabel("<html>Enter the <b>Room ID</b> that want to display (-1 to cancel)</html>");
		label.setFont(new Font("Arial", Font.PLAIN, 18));
		do {
			do {
				roomID = JOptionPane.showInputDialog(null, label, "Display room", JOptionPane.INFORMATION_MESSAGE);
				if (roomID == null) {
					roomID = "-1";
				}
			} while (roomID.isEmpty());
			roomID = roomID.trim();
			if (roomID.equals("-1")) {
				return;
			}
			if (roomMap.containsKey(roomID)) {
				int roomTypeIndex = roomMap.get(roomID);
				System.out.print(roomList[roomTypeIndex].toString());
			} else {
				JLabel label2 = new JLabel("<html><p style='color:red'>Room ID not found!</p></html>");
				label2.setFont(new Font("Arial", Font.BOLD, 16));
				JOptionPane.showMessageDialog(null, label2, "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} while (!roomMap.containsKey(roomID));
	}
	
	//List the room
	public static void listRoom() {
		System.out.println("\nRoom");
		System.out.println("---------");
		for (int i = 0; i < totalRoom; i++) {
			System.out.printf("%s) %s | %s |RM%.2f/person\n", roomList[i].roomID, roomList[i].area,
					roomList[i].roomType.getRoomName(), roomList[i].roomType.getRoomPricePerPax());
		}
	}
	
	//Display room menu
	public static void roomMenu() {
		int choice = 0;
		boolean valid = true;
		String input = "";
		do {
			checkError();
			valid = true;
			JLabel label = new JLabel(
					"<html><b>Room Menu</b><br>1. Create the room <br>2. Display the room <br>3. Remove the room <br>"
							+ "4. List the room <br>5. Edit the room<br>6. Search the room<br>-1. Return to main menu</html>");
			label.setFont(new Font("Arial", Font.PLAIN, 18));
			input = JOptionPane.showInputDialog(null, label);
			if (input != null && !input.isEmpty()) {
				input = input.trim();
				if (input.equals("-1")) {
					return;
				}
				for (int i = 0; i < input.length(); i++) {
					Character ch = input.charAt(i);
					if (!Character.isDigit(ch)) {
						valid = false;

					}
				}
				if (valid == true) {
					choice = Integer.parseInt(input);
				}
			} else {
				choice = -1;
				valid = true;
			}

			if (valid) {
				switch (choice) {
				case 1:
					addRoom();
					break;
				case 2:
					displayRoom();
					break;
				case 3:
					removeRoom();
					break;
				case 4:
					listRoom();
					break;
				case 5:
					editRoom();
					break;
				case 6:
					searchRoom();
					break;
				case -1:
					return;
				default:
					JLabel label2 = new JLabel("<html><p style='color:red'>Incorrect choice number!</p></html>");
					label2.setFont(new Font("Arial", Font.BOLD, 16));
					JOptionPane.showMessageDialog(null, label2, "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JLabel label2 = new JLabel("<html><p style='color:red'>Incorrect choice number!</p></html>");
				label2.setFont(new Font("Arial", Font.BOLD, 16));
				JOptionPane.showMessageDialog(null, label2, "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} while (valid == false || choice != -1);
	}
	
	//Remove the room
	public static void removeRoom() {
		String roomID = "";
		JLabel label = new JLabel("<html>Enter the <b>Room ID</b> that want to remove (-1 to cancel)</html>");
		label.setFont(new Font("Arial", Font.PLAIN, 18));
		listRoom();
		do {
			do {
				roomID = JOptionPane.showInputDialog(null, label, "Remove room", JOptionPane.INFORMATION_MESSAGE);
				if (roomID == null) {
					roomID = "-1";
				}
			} while (roomID.isEmpty());
			roomID = roomID.trim();
			if (roomID.equals("-1")) {
				return;
			}
			if (!roomMap.containsKey(roomID)) {
				JLabel label2 = new JLabel("<html><p style='color:red'>Room ID not found!</p></html>");
				label2.setFont(new Font("Arial", Font.BOLD, 16));
				JOptionPane.showMessageDialog(null, label2, "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} while (!roomMap.containsKey(roomID));
		int roomIndex = roomMap.get(roomID);
		roomMap.clear();
		for (int i = 0; i < totalRoom; i++) {
			if (i > roomIndex) {
				roomList[i - 1] = roomList[i];
				String newRoomID = "R" + i;
				roomList[i - 1].setRoomID(newRoomID);

			}
			if (i == (totalRoom - 1)) {
				roomList[i] = null;
			}
			if (i==roomIndex) {
				roomList[i].setRoomID("[OLD]"+roomList[i].getRoomID());
			}
			roomMap.put("R" + (i + 1), i);

		}
		totalRoom--;
		JLabel label2 = new JLabel("<html><p style='color:green'>Succesful removed the room!</p></html>");
		label2.setFont(new Font("Arial", Font.BOLD, 16));
		JOptionPane.showMessageDialog(null, label2, "Succesful Removed", JOptionPane.INFORMATION_MESSAGE);
	}
	//Edit the room
	public static void editRoom() {
		int choice = 0;
		boolean valid = false;
	
		String input = "",roomID = "";
		listRoom();
		do {
			JLabel label = new JLabel("<html>Enter the <b>Room ID</b> that want to edit (-1 to cancel)</html>");
	    	label.setFont(new Font("Arial", Font.PLAIN, 18));
	    	do {
		    	roomID = JOptionPane.showInputDialog(null, label,"Edit room",JOptionPane.INFORMATION_MESSAGE);
		    	if (roomID==null) {
		    		roomID = "-1";
		    	}
	    	}while(roomID.isEmpty());
			roomID = roomID.trim();
			if (roomID.equals("-1")) {
				return;
			}
			if (roomMap.containsKey(roomID)) {
				int roomIndex = roomMap.get(roomID);
				do {
					valid = true;
					label = new JLabel("<html><b>Room Edit Menu</b><br>1. Edit room type<br>2. Edit room area<br>-1. Return to room menu</html>");
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
			        	valid = editRoomChoice(choice,roomIndex);
			        }else {
			        	JLabel label2 = new JLabel("<html><p style='color:red'>Incorrect choice number!</p></html>");
		    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
		    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
			        }
				}while(valid==false||choice!=-1);
				
			}else {
				JLabel label2 = new JLabel("<html><p style='color:red'>Room ID not found!</p></html>");
    	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
    	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
			}
		}while(!roomMap.containsKey(roomID));
	}
	
	//Edit the room according to the choice
	public static boolean editRoomChoice(int choice,int roomIndex) {
		String result = "", roomTypeID = "";
		int roomTypeIndex = 0;
		switch (choice) {
			case 1:
				JLabel label = new JLabel("<html>Current room type: <b style='color:blue'>"+Room.roomList[roomIndex].roomType.getRoomTypeID()+
						" | "+Room.roomList[roomIndex].roomType.getRoomName()+"</b><br>Enter the <b>New Room Type ID</b> (-1 to cancel)</html>");
		    	label.setFont(new Font("Arial", Font.PLAIN, 18));
		    	RoomType.listRoomType();
		    	do {
			    	do {
				    	result = JOptionPane.showInputDialog(null, label,"Edit room",JOptionPane.INFORMATION_MESSAGE);
				    	if (result==null) {
				    		result = "-1";
				    	}
			    	}while(result.isEmpty());
			    		result = result.trim();
					if (result.equals("-1")) {
						return true;
					}
					roomTypeID = result;
					if (RoomType.typeID.containsKey(roomTypeID)) {
						roomTypeIndex = RoomType.typeID.get(roomTypeID);
	
					} else {
						JLabel label2 = new JLabel("<html><p style='color:red'>Room Type ID not found!</p></html>");
						label2.setFont(new Font("Arial", Font.BOLD, 16));
						JOptionPane.showMessageDialog(null, label2, "ERROR", JOptionPane.ERROR_MESSAGE);
					}
		    	}while(!RoomType.typeID.containsKey(roomTypeID));
				roomList[roomIndex].roomType = RoomType.getRoomTypeList()[roomTypeIndex];
				JLabel label2 = new JLabel("<html><p style='color:green'>Succesful edited the room type!</p></html>");
		    	label2.setFont(new Font("Arial", Font.BOLD, 16));
		        JOptionPane.showMessageDialog(null, label2, "Succesful Edited",JOptionPane.INFORMATION_MESSAGE);
				break;
			case 2:
				label = new JLabel("<html>Current room area: <b style='color:blue'>"+roomList[roomIndex].getArea()+"</b><br>Enter the <b>New Room Area</b> (-1 to cancel)</html>");
		    	label.setFont(new Font("Arial", Font.PLAIN, 18));
		    	do {
			    	result = JOptionPane.showInputDialog(null, label,"Edit room",JOptionPane.INFORMATION_MESSAGE);
			    	if (result==null) {
			    		result = "-1";
			    	}
		    	}while(result.isEmpty());
		    	result = result.trim();
				if (result.equals("-1")) {
					return true;
				}
				roomList[roomIndex].setArea(result);
				label2 = new JLabel("<html><p style='color:green'>Succesful edited the room area!</p></html>");
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
	
	//Set default room data
	public static void defaultRoom() {
		roomList[0] = new Room(RoomType.getRoomTypeList()[1], "Area 1");
		roomList[1] = new Room(RoomType.getRoomTypeList()[0], "Area 1");
		roomList[2] = new Room(RoomType.getRoomTypeList()[2], "Area 2");
		roomList[3] = new Room(RoomType.getRoomTypeList()[1], "Area 3");
		roomList[4] = new Room(RoomType.getRoomTypeList()[1], "Area 2");
		roomList[5] = new Room(RoomType.getRoomTypeList()[3], "Area 3");
	}
	
	//Search the room
	public static void searchRoom() {
		int choice = 0;
		
		String result = "", input = "", roomID = "";
		boolean valid = false;
		do {
			boolean resultFound = false;
			ArrayList<Room> reserved = new ArrayList<Room>(); 
			valid = true;
			JLabel label = new JLabel("<html><b>Search Menu</b><br>1. Search by Room ID<br>2. Search by Room Type ID<br>-1. Return to reservation menu</html>");
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
	        		label = new JLabel("<html>Enter the <b>Room ID</b> that want to search (-1 to cancel)</html>");
	            	label.setFont(new Font("Arial", Font.PLAIN, 18));
	            	do {
	            		do {
	            	    	roomID = JOptionPane.showInputDialog(null, label,"Search",JOptionPane.INFORMATION_MESSAGE);
	            	    	if (roomID==null) {
	            	    		roomID = "-1";
	            	    	}
	                	}while(roomID.isEmpty());
	            		roomID = roomID.trim();
	            		if (roomID.equals("-1")) {
	            			return;
	            		}
	        			if (!roomMap.containsKey(roomID)) {
	        				JLabel label2 = new JLabel("<html><p style='color:red'>Room ID not found!</p></html>");
	            	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
	            	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
	        			}
	            	}while(!roomMap.containsKey(roomID));
	            	int reserveIndex = roomMap.get(roomID);
	            	label = new JLabel("<html>"+roomList[reserveIndex].toString().replace("\n", "<br>")+"</html>");
	    	    	label.setFont(new Font("Arial", Font.BOLD, 16));
	    	        JOptionPane.showMessageDialog(null, label, "RESULT",JOptionPane.INFORMATION_MESSAGE);
	        	}else if (choice == 2) {
	        		
	        		label = new JLabel("<html>Enter the <b>Room Type ID</b> that want to search (-1 to cancel)</html>");
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
	        			for (int i = 0;i < totalRoom;i++) {
	        				String id = roomList[i].getRoomType().getRoomTypeID();
	        				if (id.toLowerCase().equals(result.toLowerCase())) {
	        					resultFound = true;
	        					reserved.add(roomList[i]);
	        				}
	        			}
	        			
	        			if (!resultFound) {
	        				JLabel label2 = new JLabel("<html><p style='color:red'>Room Type ID not found!</p></html>");
	            	    	label2.setFont(new Font("Arial", Font.BOLD, 16));
	            	        JOptionPane.showMessageDialog(null, label2, "ERROR",JOptionPane.ERROR_MESSAGE);
	        			}
	            	}while(!resultFound);
	            	
	        	}
	        	if (choice == 2&&reserved.size()>0) {
	        		System.out.println("\nRoom");
	        		System.out.println("---------");
	        		for (int i = 0; i < reserved.size(); i++) {
	        			System.out.printf("%s) %s | %s |RM%.2f/person\n", reserved.get(i).roomID, reserved.get(i).area,
	        					reserved.get(i).roomType.getRoomName(), reserved.get(i).roomType.getRoomPricePerPax());
	        		}
        			
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
	
	//Check error for the room type whether is valid or not valid
	public static void checkError() {
		String errorRoom = "";
		for (int i = 0;i < totalRoom;i++) {
			if (roomList[i].getRoomType().getRoomTypeID().contains("OLD")) {
				errorRoom = "<li>"+errorRoom+roomList[i].getRoomID()+"</li>";
			}
		}
		if (!errorRoom.isEmpty()) {
			JLabel label = new JLabel("<html><p>Room that contains <b>removed</b> Room Type ID</p><ul>"+errorRoom+"</ul></html>");
	    	label.setFont(new Font("Arial", Font.BOLD, 16));
	        JOptionPane.showMessageDialog(null, label, "ERROR",JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
