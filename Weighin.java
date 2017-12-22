import java.sql.Date;

public class Weighin {
  private int weighinID;
  private Date date;
  private int weight;
  private int userID;

  public Weighin(int weighinID, Date date, int weight, int userID) {
    this.weighinID = weighinID;
    this.date = date;
    this.weight = weight;
    this.userID = userID;
  }

  @Override
  public String toString() {
    return "Weighin{" +
            "weighinID=" + weighinID +
            ", date=" + date +
            ", weight=" + weight +
            ", userID=" + userID +
            '}';
  }

  public int getWeighinID() {
    return weighinID;
  }

  public void setWeighinID(int weighinID) {
    this.weighinID = weighinID;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  public int getUserID() {
    return userID;
  }

  public void setUserID(int userID) {
    this.userID = userID;
  }
}
