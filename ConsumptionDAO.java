import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


public class ConsumptionDAO {

    public static List<Consumption> selectAll(int userID) {

        List<Consumption> targetList = new ArrayList<>();
        PreparedStatement statement = vLogin.database.newStatement("SELECT userID, mealName, foodName, calories, dateEaten FROM Consumption where userID = " + userID);

        try {
            if (statement != null) {
                ResultSet results = vLogin.database.executeQuery(statement);
                if (results != null) {
                    while (results.next()) {
                        targetList.add(new Consumption(results.getInt("userID"), results.getString("mealName"), results.getString("foodName"), results.getInt("calories"), results.getDate("dateEaten")));
                    }
                }
            }
        } catch (SQLException resultsException) {
            System.out.println("Database select all error: " + resultsException.getMessage());
        }
        return targetList;
    }


    public static void save(Consumption itemToSave) {

        PreparedStatement statement;

        try{
            statement = vLogin.database.newStatement("INSERT INTO Consumption (userID, mealName, foodName, calories) VALUES (?, ?, ?, ?)");
            statement.setInt(1, itemToSave.getUserID());
            statement.setString(2, itemToSave.getMealName());
            statement.setString(3, itemToSave.getFoodName());
            statement.setInt(4, itemToSave.getCalories());

            if (statement != null) {
                vLogin.database.executeUpdate(statement);
            }
        }
        catch (SQLException resultsexception) {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }
    }


}
