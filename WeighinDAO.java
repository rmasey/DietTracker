import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WeighinDAO {

    public static List<Weighin>  selectWeighinsByUserId(int userID) {

        List<Weighin> result = new ArrayList<>();

        try {
            PreparedStatement ps = vLogin.database.newStatement("SELECT WeighInID, Date, Weight, UserID FROM WeighIn where userID = ? ORDER by Date DESC");
            if (ps != null) {
                ps.setInt(1, userID);
                ResultSet results = vLogin.database.executeQuery(ps);
                if (results != null) {
                    while (results.next()) {
                        result.add(new Weighin(results.getInt("WeighInID"), results.getDate("Date"), results.getInt("Weight"), results.getInt("UserID")));
                    }
                }
            }
        } catch (SQLException resultsException) {
            System.out.println("Database select by id error: " + resultsException.getMessage());
        }

        return result;
    }


    public static List<Weighin> getAllWeighins() {

        List<Weighin> targetList = new ArrayList<>();

        try {
            PreparedStatement ps = vLogin.database.newStatement("SELECT WeighInID, Date, Weight, UserID FROM WeighIn ORDER BY Date");
            if (ps != null) {
                ResultSet results = vLogin.database.executeQuery(ps);
                if (results != null) {
                    while (results.next()) {
                        targetList.add(new Weighin(results.getInt("WeighInID"), results.getDate("Date"), results.getInt("Weight"), results.getInt("UserID")));
                    }
                }
            }
        } catch (SQLException resultsException) {
            System.out.println("Database select all error: " + resultsException.getMessage());
        }
        return targetList;
    }


    public static void addWeighin(Weighin weighin) {

        try{
            PreparedStatement ps = vLogin.database.newStatement("INSERT INTO WeighIn (Date, Weight, UserID) VALUES (?, ?, ?)");
            ps.setDate(1, weighin.getDate());
            ps.setInt(2, weighin.getWeight());
            ps.setInt(3, weighin.getUserID());

            if (ps != null) {
                vLogin.database.executeUpdate(ps);
            }
        }
        catch (SQLException resultsexception) {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }
    }


}
