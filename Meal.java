import java.sql.Date;

public class Meal {
  private int mealID;
  private java.sql.Date date;
  private String mealtypeid;
  private int userID;

  public Meal(int mealID, Date date, String mealtypeid, int userID) {
    this.mealID = mealID;
    this.date = date;
    this.mealtypeid = mealtypeid;
    this.userID = userID;
  }

  @Override
  public String toString() {
    return "Meal{" +
            "mealID=" + mealID +
            ", date=" + date +
            ", mealtypeid='" + mealtypeid + '\'' +
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

  public String getMealtypeid() {
    return mealtypeid;
  }

  public void setMealtypeid(String mealtypeid) {
    this.mealtypeid = mealtypeid;
  }

  public int getUserID() {
    return userID;
  }

  public void setUserID(int userID) {
    this.userID = userID;
  }
}
