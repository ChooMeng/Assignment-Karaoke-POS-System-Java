package group5;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ReservationDetails {
	private int roomUsers;
	private Date timeReserved;
	private Date timeReservedEnd;
	private Date timeCheckin;
	private Date timeCheckout;
	private Room room;
	private String status;
	public ReservationDetails() {}
	
	public ReservationDetails(int roomUsers, Date timeReserved, Date timeReservedEnd,Date timeCheckin, Date timeCheckout, Room room) {
		this.roomUsers = roomUsers;
		this.timeReserved = timeReserved;
		this.timeReservedEnd = timeReservedEnd;
		this.timeCheckout = timeCheckout;
		this.timeCheckin = timeCheckin;
		this.room = room;
		this.status = "Pending";
	}
	public ReservationDetails(int roomUsers, Date timeReserved, Date timeReservedEnd,Date timeCheckin, Date timeCheckout, Room room, String status) {
		this.roomUsers = roomUsers;
		this.timeReserved = timeReserved;
		this.timeReservedEnd = timeReservedEnd;
		this.timeCheckout = timeCheckout;
		this.timeCheckin = timeCheckin;
		this.room = room;
		this.status = status;
	}
	public ReservationDetails(int roomUsers, Date timeReserved, Date timeReservedEnd, Room room) {
		this.roomUsers = roomUsers;
		this.timeReserved = timeReserved;
		this.timeReservedEnd = timeReservedEnd;
		this.timeCheckout = null;
		this.timeCheckin = null;
		this.room = room;
		this.status = "Pending";
	}
	public ReservationDetails(int roomUsers, Date timeReserved, Date timeReservedEnd, Room room, String status) {
		this.roomUsers = roomUsers;
		this.timeReserved = timeReserved;
		this.timeReservedEnd = timeReservedEnd;
		this.timeCheckout = null;
		this.timeCheckin = null;
		this.room = room;
		this.status = status;
	}
	public int getRoomUsers() {
		return roomUsers;
	}
	public Date getTimeReserved() {
		return timeReserved;
	}
	public Date getTimeReservedEnd() {
		return timeReservedEnd;
	}
	public Date getTimeCheckIn() {
		return timeCheckin;
	}
	public Date getTimeCheckOut() {
		return timeCheckout;
	}
	public Room getRoom() {
		return room;
	}
	public String getStatus() {
		return status;
	}
	public void setRoomUsers(int roomUsers) {
		this.roomUsers = roomUsers;
	}
	public void setTimeReserved(Date timeReserved) {
		this.timeReserved = timeReserved;
	}
	public void setTimeReservedEnd(Date timeReservedEnd) {
		this.timeReservedEnd = timeReservedEnd;
	}
	public void setTimeCheckIn(Date timeCheckin) {
		this.timeCheckin = timeCheckin;
	}
	public void setTimeCheckout(Date timeCheckout) {
		this.timeCheckout = timeCheckout;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String toString() {
		DateFormat dateFormat = new SimpleDateFormat( "dd-MM-YYYY HH:mm" );
		return String.format("\nRoom Users: %d\n"
				+ "Time Reserved: %s\n"
				+ "Time ReservedEnd: %s\n"
				+ "Time Checkin: %s\n"
				+ "Time Checkout: %s\n"
				+ "Status: %s\n"
				+ "====[ Room Details ]====\n"
				+ "%s", roomUsers,dateFormat.format(timeReserved),dateFormat.format(timeReservedEnd), timeCheckin==null?"-":dateFormat.format(timeCheckin),timeCheckout==null?"-":dateFormat.format(timeCheckout), status,room.toString());
	}
	public boolean equals(Object o) {
		if (!(o instanceof ReservationDetails )) {
			return false;
		}
		ReservationDetails o2 = (ReservationDetails) o;
		if ((this != null && o2!=null)&&this == o2) {
			return true;
		}
		return false;
	}

}
