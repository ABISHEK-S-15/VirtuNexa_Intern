


public class Reservation {
    private static int idCounter = 1;
    private int reservationId;
    private String customerName;
    private String date;
    private String roomType;

    public Reservation(String customerName, String date, String roomType) {
        this.reservationId = idCounter++;
        this.customerName = customerName;
        this.date = date;
        this.roomType = roomType;
    }

    public int getReservationId() {
        return reservationId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getDate() {
        return date;
    }

    public String getRoomType() {
        return roomType;
    }

    @Override
    public String toString() {
        return "ID: " + reservationId + " | Name: " + customerName + " | Date: " + date + " | Room: " + roomType;
    }
}
