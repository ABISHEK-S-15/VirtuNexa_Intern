import java.util.*;

public class Hotel {
    private Map<String, Integer> totalRooms;
    private Map<String, Map<String, Integer>> bookedRooms;
    private List<Reservation> reservations;

    public Hotel() {
        totalRooms = new HashMap<>();
        bookedRooms = new HashMap<>();
        reservations = new ArrayList<>();

        totalRooms.put("Single", 10);
        totalRooms.put("Double", 10);
        totalRooms.put("Suite", 5);
    }

    public boolean checkAvailability(String date, String roomType) {
        bookedRooms.putIfAbsent(date, new HashMap<>());
        Map<String, Integer> dateBooking = bookedRooms.get(date);
        int booked = dateBooking.getOrDefault(roomType, 0);
        return booked < totalRooms.get(roomType);
    }

    public Reservation bookRoom(String name, String date, String type) {
        if (!checkAvailability(date, type)) return null;
        bookedRooms.get(date).put(type, bookedRooms.get(date).getOrDefault(type, 0) + 1);
        Reservation r = new Reservation(name, date, type);
        reservations.add(r);
        return r;
    }

    public boolean cancelReservation(int id) {
        Iterator<Reservation> it = reservations.iterator();
        while (it.hasNext()) {
            Reservation r = it.next();
            if (r.getReservationId() == id) {
                Map<String, Integer> dateBooking = bookedRooms.get(r.getDate());
                dateBooking.put(r.getRoomType(), dateBooking.get(r.getRoomType()) - 1);
                it.remove();
                return true;
            }
        }
        return false;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}
