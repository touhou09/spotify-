import java.sql.*;
import java.util.Scanner;

public class UserManager {
	
	private static Scanner scanner = new Scanner(System.in);
	
    public void displaySubscriptionInfo(User user) {
        String subscriptionInfoSQL = "SELECT SubType, SubStartDate, SYSDATE - SubStartDate AS SubscriptionDays " +
                                     "FROM Subscription WHERE UserID = ?";
        int discount = 10;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(subscriptionInfoSQL)) {
            
            pstmt.setString(1, user.getUserID());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int daysSubscribed = rs.getInt("SubscriptionDays");
                int discountAmount = discount * daysSubscribed;
                System.out.println("Subscription Type: " + rs.getString("SubType") + 
                                   ", Discounted Amount: " + discountAmount);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserInfo(User user) {
    	System.out.print(user);
    	
        System.out.print("Enter new Email: ");
        String newEmail = scanner.nextLine();
        System.out.print("Enter new Real Name: ");
        String newRealName = scanner.nextLine();
    	
        String updateUserInfoSQL = "UPDATE Users SET Email = ?, Real_Name = ? WHERE UserID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateUserInfoSQL)) {
            
            pstmt.setString(1, newEmail);
            pstmt.setString(2, newRealName);
            pstmt.setString(3, user.getUserID());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("User information updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
