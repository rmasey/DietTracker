import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;

public class WeightDAO
{


    public static void readAll(List<Weight> list, int userID)
    {
        PreparedStatement statement = vLogin.database.newStatement("SELECT WeightID, Date, CurrentWeight, UserID FROM Weight WHERE UserID = " + userID + " ORDER BY Date DESC");

        if (statement != null)      // Assuming the statement correctly initated...
        {
            ResultSet results =  vLogin.database.executeQuery(statement);      // ...run the query!

            if (results != null)        // If some results are returned from the query...
            {
                try {                               // ...add each one to the list.
                    while (results.next()) {
                        list.add(new Weight (results.getInt("WeightID"), results.getDate("Date"), results.getInt("CurrentWeight"), results.getInt("UserID")));
                    }
                }
                catch (SQLException resultsexception)       // Catch any error processing the results.
                {
                    System.out.println("Database result processing error: " + resultsexception.getMessage());
                }
            }
        }

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
