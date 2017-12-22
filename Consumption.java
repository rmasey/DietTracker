import java.util.Date;

public class Consumption
{
  private int userID;
  private String mealName;
  private String foodName;
  private int calories;
    private Date dateEaten;

    public Consumption(int userID, String mealName, String foodName, int calories, Date dateEaten) {
        this.userID = userID;
        this.mealName = mealName;
        this.foodName = foodName;
        this.calories = calories;
        this.dateEaten = dateEaten;
    }

    @Override
    public String toString() {
        return "Consumption{" +
                "userID=" + userID +
                ", mealName='" + mealName + '\'' +
                ", foodName='" + foodName + '\'' +
                ", calories=" + calories +
                ", dateEaten=" + dateEaten +
                '}';
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public Date getDateEaten() {
        return dateEaten;
    }

    public void setDateEaten(Date date) {
        this.dateEaten = dateEaten;
    }
}