import java.sql.Date;

public class Meal {
  private int mealID;
  private java.sql.Date date;
  private String mealTypeID;
  private int userID;

    public Meal(int mealID, Date date, String mealTypeID, int userID) {
        this.mealID = mealID;
        this.date = date;
        this.mealTypeID = mealTypeID;
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "mealID=" + mealID +
                ", date=" + date +
                ", mealTypeID='" + mealTypeID + '\'' +
                ", userID=" + userID +
                '}';
    }

    public int getMealID() {
        return mealID;
    }

    public void setMealID(int mealID) {
        this.mealID = mealID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMealTypeID() {
        return mealTypeID;
    }

    public void setMealTypeID(String mealTypeID) {
        this.mealTypeID = mealTypeID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
