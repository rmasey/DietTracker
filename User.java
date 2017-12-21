public class User {
  private int userID;
  private String firstName;
  private String lastName;
  private int startWeight;
  private int targetWeight;
  private int caloriesPerDay;
  private String gender;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(int userID, String firstName, String lastName, int startWeight, int targetWeight, int caloriesPerDay, String gender) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.startWeight = startWeight;
        this.targetWeight = targetWeight;
        this.caloriesPerDay = caloriesPerDay;
        this.gender = gender;
    }



    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", startWeight=" + startWeight +
                ", targetWeight=" + targetWeight +
                ", caloriesPerDay=" + caloriesPerDay +
                ", gender='" + gender + '\'' +
                '}';
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getStartWeight() {
        return startWeight;
    }

    public void setStartWeight(int startWeight) {
        this.startWeight = startWeight;
    }

    public int getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(int targetWeight) {
        this.targetWeight = targetWeight;
    }

    public int getCaloriesPerDay() {
        return caloriesPerDay;
    }

    public void setCaloriesPerDay(int caloriesPerDay) {
        this.caloriesPerDay = caloriesPerDay;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
