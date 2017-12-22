import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;


public class WeighinService {


    public static void save(Weighin itemToSave) {

        PreparedStatement statement;

        try{
            statement = StageOne.database.newStatement("INSERT INTO WeighIn (Date, Weight, UserID) VALUES (?, ?, ?)");
            statement.setDate(1, itemToSave.getDate());
            statement.setInt(2, itemToSave.getWeight());
            statement.setInt(3, itemToSave.getUserID());

            if (statement != null) {
                StageOne.database.executeUpdate(statement);
            }
        }
        catch (SQLException resultsexception) {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }
    }


}
