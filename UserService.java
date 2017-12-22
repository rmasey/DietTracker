import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


public class UserService {

    public static List<User> selectAll() {

        List<User> targetList = new ArrayList<>();
        PreparedStatement statement = StageOne.database.newStatement("SELECT UserID, FirstName, LastName FROM User ORDER BY LastName");

        try {
            if (statement != null) {
                ResultSet results = StageOne.database.executeQuery(statement);
                if (results != null) {
                    while (results.next()) {
                        targetList.add(new User(results.getInt("UserID"), results.getString("FirstName"), results.getString("LastName")));
                    }
                }
            }
        } catch (SQLException resultsException) {
            System.out.println("Database select all error: " + resultsException.getMessage());
        }
        return targetList;
    }

    public static void deleteById(int id) {

        PreparedStatement statement = StageOne.database.newStatement("DELETE FROM User WHERE userID = ?");

        try {
            if (statement != null) {
                statement.setInt(1, id);
                StageOne.database.executeUpdate(statement);
            }
        } catch (SQLException resultsException) {
            System.out.println("Database deletion error: " + resultsException.getMessage());
        }
    }

    public static void save(User itemToSave) {

        PreparedStatement statement;

        try{
            statement = StageOne.database.newStatement("INSERT INTO User (FirstName, LastName, StartWeight, TargetWeight, CaloriesPerDay, Gender) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, itemToSave.getFirstName());
            statement.setString(2, itemToSave.getLastName());
            statement.setInt(3, itemToSave.getStartWeight());
            statement.setInt(4, itemToSave.getTargetWeight());
            statement.setInt(5, itemToSave.getCaloriesPerDay());
            statement.setString(6, itemToSave.getGender());

            if (statement != null) {
                StageOne.database.executeUpdate(statement);
            }
        }
        catch (SQLException resultsexception) {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }
    }


}
