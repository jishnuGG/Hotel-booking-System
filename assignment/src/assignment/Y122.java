package assignment;
import java.sql.*;
import java.util.*;
public class Y122 {
    private Connection connection;

    public Y122() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_booking", "root", "jishnu211004.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add a new room
    public void addRoom(int roomNumber, String roomType, double price,int aval) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Rooms (room_number, room_type, price) VALUES (?, ?, ?,?)");
        statement.setInt(1, roomNumber);
        statement.setString(2, roomType);
        statement.setDouble(3, price);
        statement.setInt(4, aval);
        statement.executeUpdate();
        System.out.println("\n U P D A T E D \n +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
    }

    // Update room details
    public void updateRoom(int roomNumber, String roomType, double price,int aval) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE Rooms SET room_type = ?, price = ?,availability=? WHERE room_number = ?");
        statement.setString(1, roomType);
        statement.setDouble(2, price);
        statement.setInt(3, roomNumber);
        statement.setInt(4, aval);
        statement.executeUpdate();
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
    }

    // Delete a room
    public void deleteRoom(int roomNumber) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Rooms WHERE room_number = ?");
        statement.setInt(1, roomNumber);
        statement.executeUpdate();System.out.println("deleted Successfully");
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
    }

    // Display available rooms
    public void showAvailableRooms() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Rooms WHERE availability = true");
        while (resultSet.next()) {
            System.out.println("Room Number: " + resultSet.getInt("room_number"));
            System.out.println("Room Type: " + resultSet.getString("room_type"));
            System.out.println("Price: " + resultSet.getDouble("price"));
            System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        }
    }

    // Main method to test the functionality
    public static void main(String[] args, boolean True) {
        Y122 hotelBookingSystem = new Y122();
       
        try {
        	while(True){
        		System.out.println("click 1 Available Rooms");
        		System.out.println("click 2 to add room");
        		System.out.println("click 3 to update room");
        		System.out.println("click 4 to see Available Rooms after update:");
        		System.out.println("click 5 to delete room");
        		System.out.println("click 6 to see Available Rooms after deletion:");
        		System.out.println("click 0 to exit:");
        		Scanner sc=new Scanner("system.in");
        		System.out.println("\n enter the choice :");
        		int s=sc.nextInt();
        		switch(s) {
        		case(2):
            hotelBookingSystem.addRoom(101, "Single", 100.0,1);
            break;
        		case(1):
            System.out.println("Available Rooms:\n");
            hotelBookingSystem.showAvailableRooms();
            break;
        		case(3):
            hotelBookingSystem.updateRoom(101, "Single", 120.0,1);
        		break;
        		case(4):
            System.out.println("Available Rooms after update:");
            hotelBookingSystem.showAvailableRooms();
            break;
        		case(5):
            hotelBookingSystem.deleteRoom(102);
        		break;
                  
        		case(6):
            System.out.println("Available Rooms after delete:");
            hotelBookingSystem.showAvailableRooms();
            break;
        		case(0):
                s=0;
        		break;
        		}
        		if(s==0) break;
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}