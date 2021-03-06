import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class WeightDAO
{

    public static List<Weight> selectAll(int userID) {

        List<Weight> targetList = new ArrayList<>();
        PreparedStatement statement = vLogin.database.newStatement("SELECT WeightID, Date,  CurrentWeight, UserID FROM Weight WHERE UserID = " + userID + " ORDER BY Date DESC" );

        try {
            if (statement != null) {
                ResultSet results = vLogin.database.executeQuery(statement);
                if (results != null) {
                    while (results.next()) {
                        targetList.add(new Weight(results.getInt("WeightID"), results.getDate("Date"),results.getInt("CurrentWeight"), results.getInt("UserID")));
                    }
                }
            }
        } catch (SQLException resultsException) {
            System.out.println("Database select all error: " + resultsException.getMessage());
        }
        return targetList;
    }





//    public static Weight getById(int BookID)
//    {
//        Weight weight = null;
//
//        PreparedStatement statement = vLogin.database.newStatement("SELECT BookID, Title, AuthorID FROM Books WHERE BookID = ?");
//
//        try
//        {
//            if (statement != null)
//            {
//                statement.setInt(1, BookID);
//                ResultSet results = vLogin.database.executeQuery(statement);
//
//                if (results != null)
//                {
//                    weight = new Weight(results.getInt("BookID"), results.getString("Title"), results.getInt("AuthorID"));
//                }
//            }
//        }
//        catch (SQLException resultsexception)
//        {
//            System.out.println("Database result processing error: " + resultsexception.getMessage());
//        }
//
//        return weight;
//    }
//
//    public static void deleteById(int BookID)
//    {
//        try{
//            PreparedStatement statement = Main.database.newStatement("DELETE FROM Books WHERE BookID = ?");
//            statement.setInt(1, BookID);
//
//            if (statement != null){
//                Main.database.executeUpdate(statement);
//            }
//        } catch (SQLException resultsexception){
//            System.out.println("Database result processing error: " + resultsexception.getMessage());
//        }
//    }
//
    public static void save(Weight weight)
    {
        try
        {
            PreparedStatement statement = vLogin.database.newStatement("INSERT INTO Weight (Date, currentWeight, userID) VALUES (?, ?, ?)");
            statement.setDate(1, weight.getDate());
            statement.setInt(2, weight.getCurrentWeight());
            statement.setInt(3, weight.getUserID());

            if (statement != null) {
                vLogin.database.executeUpdate(statement);
            }

        }
        catch (SQLException resultsexception) {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }
    }

    public static void deleteById(int id) {

        PreparedStatement statement = vLogin.database.newStatement("DELETE FROM Weight WHERE WeightID = ?");

        try {
            if (statement != null) {
                statement.setInt(1, id);
                vLogin.database.executeUpdate(statement);
            }
        } catch (SQLException resultsException) {
            System.out.println("Database deletion error: " + resultsException.getMessage());
        }
    }




}
