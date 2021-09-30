package group5.transaction;

import java.util.Date;

import group5.Payment;
import group5.Reservation;

public class ReservationTransaction extends Transaction{
	private Reservation reservation;
	private Payment payment;
	public ReservationTransaction(Reservation reservation, Payment payment) {
		super();
		this.reservation = reservation;
		this.payment = payment;
	}
	public ReservationTransaction(Reservation reservation, Payment payment, Date transactionDate) {
		super(transactionDate);
		this.reservation = reservation;
		this.payment = payment;
	}
	//getter
	public Reservation getReservation() {
		return reservation;
	}
	public Payment getPayment() {
		return payment;
	}
	public String toString() {
		
		return "[Reservation Transaction]\n"
				+ super.toString()+String.format("Reservation ID: %s\n"
						+ "Room Type: %s\n"
						+ "Room ID:%s\n"
						+ "Customer Name: %s\n"
						+ "SalesPerson Name: %s\n"
						+ "Total Price: RM%.2f\n",reservation.getReservationID(),reservation.getDetails().getRoom().getRoomType().getRoomName(),
						reservation.getDetails().getRoom().getRoomID(),reservation.getCustomer().getName(),
						reservation.getSalesPerson().getName(),payment.getTotalPrice());
	}

}
