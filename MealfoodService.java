import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;


public class MealfoodService {

    public static List<Mealfood> selectByID(int mealfoodID) {

        List<Mealfood> targetList = new ArrayList<>();
        PreparedStatement statement = StageOne.database.newStatement("SELECT MealFoodID, FoodID, MealID, ServingQuantity FROM MealFood WHERE mealFoodID = ?");

        try {
            if (statement != null) {

                statement.setInt(1, mealfoodID);
                ResultSet results = StageOne.database.executeQuery(statement);

                if (results != null) {
                    while (results.next()) {
                        targetList.add(new Mealfood(results.getInt("foodID"), results.getInt("MealID"), results.getInt("mealID"), results.getInt("ServingQuantity")));
                    }
                }
            }
        } catch (SQLException resultsException) {
            System.out.println("Database select by id error: " + resultsException.getMessage());
        }

        return targetList;
    }


}

