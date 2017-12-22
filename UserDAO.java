import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


public class UserDAO {

    public static List<User> getAllUsers() {

        List<User> targetList = new ArrayList<>();
        PreparedStatement statement = LoginView.database.newStatement("SELECT UserID, FirstName, LastName, TargetWeight FROM User ORDER BY LastName");

        try {
            if (statement != null) {
                ResultSet results = LoginView.database.executeQuery(statement);
                if (results != null) {
                    while (results.next()) {
                        targetList.add(new User(results.getInt("UserID"), results.getString("FirstName"), results.getString("LastName"), results.getInt("TargetWeight")));
                    }
                }
            }
        } catch (SQLException resultsException) {
            System.out.println("Database select all error: " + resultsException.getMessage());
        }
        return targetList;
    }

    public static void deleteUserById(int id) {

        PreparedStatement statement = LoginView.database.newStatement("DELETE FROM User WHERE userID = ?");

        try {
            if (statement != null) {
                statement.setInt(1, id);
                LoginView.database.executeUpdate(statement);
            }
        } catch (SQLException resultsException) {
            System.out.println("Database deletion error: " + resultsException.getMessage());
        }
    }

    public static void addUser(User itemToSave) {

        PreparedStatement statement;

        try{
            statement = LoginView.database.newStatement("INSERT INTO User (FirstName, LastName, TargetWeight, CaloriesPerDay, Gender) VALUES ( ?, ?, ?, ?, ?)");
            statement.setString(1, itemToSave.getFirstName());
            statement.setString(2, itemToSave.getLastName());
            statement.setInt(3, itemToSave.getTargetWeight());
            statement.setInt(4, itemToSave.getCaloriesPerDay());
            statement.setString(5, itemToSave.getGender());

            if (statement != null) {
                LoginView.database.executeUpdate(statement);
            }
        }
        catch (SQLException resultsexception) {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }
    }


}
