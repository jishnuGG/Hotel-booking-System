package assignment;
import java.sql.*;

public class HotelBookingSystem {
    private Connection connection;

    public HotelBookingSystem() {
        try {
            // Connect to the database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_booking", "root", "asdfghjkl;1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Display all hotel bookings
    public void showHotelBookings() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM hotelbooks");
        while (resultSet.next()) {
            System.out.println("Booking ID: " + resultSet.getInt("booking_id"));
            System.out.println("Guest Name: " + resultSet.getString("guest_name"));
            System.out.println("Guest Email: " + resultSet.getString("guest_email"));
            System.out.println("Guest Phone: " + resultSet.getString("guest_phone"));
            System.out.println("Room Number: " + resultSet.getInt("room_number"));
            System.out.println("Room Type: " + resultSet.getString("room_type"));
            System.out.println("Price: " + resultSet.getDouble("price"));
            System.out.println("Check-in Date: " + resultSet.getDate("check_in_date"));
            System.out.println("Check-out Date: " + resultSet.getDate("check_out_date"));
            System.out.println("Payment Amount: " + resultSet.getDouble("payment_amount"));
            System.out.println("Payment Date: " + resultSet.getDate("payment_date"));
            System.out.println("Payment Status: " + resultSet.getString("payment_status"));
            System.out.println("Availability: " + resultSet.getBoolean("availability"));
            System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        }
    }

    // Insert a new hotel booking
    public void insertHotelBooking(String guestName, String guestEmail, String guestPhone, int roomNumber, String roomType, double price, Date checkInDate, Date checkOutDate, double paymentAmount, Date paymentDate, String paymentStatus, boolean availability) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO hotelbooks (guest_name, guest_email, guest_phone, room_number, room_type, price, check_in_date, check_out_date, payment_amount, payment_date, payment_status, availability) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        statement.setString(1, guestName);
        statement.setString(2, guestEmail);
        statement.setString(3, guestPhone);
        statement.setInt(4, roomNumber);
        statement.setString(5, roomType);
        statement.setDouble(6, price);
        statement.setDate(7, checkInDate);
        statement.setDate(8, checkOutDate);
        statement.setDouble(9, paymentAmount);
        statement.setDate(10, paymentDate);
        statement.setString(11, paymentStatus);
        statement.setBoolean(12, availability);
        statement.executeUpdate();
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
    }

    // Update an existing hotel booking
    public void updateHotelBooking(int bookingId, double paymentAmount, Date paymentDate, String paymentStatus) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE hotelbooks SET payment_amount = ?, payment_date = ?, payment_status = ? WHERE booking_id = ?");
        statement.setDouble(1, paymentAmount);
        statement.setDate(2, paymentDate);
        statement.setString(3, paymentStatus);
        statement.setInt(4, bookingId);
        statement.executeUpdate();
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
    }

    // Delete a hotel booking
    public void deleteHotelBooking(int bookingId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM hotelbooks WHERE booking_id = ?");
        statement.setInt(1, bookingId);
        statement.executeUpdate();
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
    }

    // Main method to test the functionality
    public static void main(String[] args) {
        HotelBookingSystem hotelBookingSystem = new HotelBookingSystem();

        try {
            // Example usage
            System.out.println("Hotel Bookings:");
            hotelBookingSystem.showHotelBookings();

            // Insert a new booking
            hotelBookingSystem.insertHotelBooking("John Doe", "john@example.com", "1234567890", 101, "Single", 100.0, Date.valueOf("2024-04-15"), Date.valueOf("2024-04-20"), 500.0, Date.valueOf("2024-04-15"), "Paid", true);

            // Update an existing booking
            hotelBookingSystem.updateHotelBooking(1, 600.0, Date.valueOf("2024-04-16"), "Paid");

            // Delete a booking
            hotelBookingSystem.deleteHotelBooking(1);

            System.out.println("Updated Hotel Bookings:");
            hotelBookingSystem.showHotelBookings();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

