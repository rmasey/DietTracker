import java.sql.Date;

public class Weight {
    private int weightID;
    private Date date;
    private int currentWeight;
    private int userID;

    public Weight(int weightID, Date date, int currentWeight, int userID) {
        this.weightID = weightID;
        this.date = date;
        this.currentWeight = currentWeight;
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Weight{" +
                "weightID=" + weightID +
                ", date=" + date +
                ", currentWeight=" + currentWeight +
                ", userID=" + userID +
                '}';
    }

    public int getWeightID() {
        return weightID;
    }

    public void setWeightID(int weightID) {
        this.weightID = weightID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}