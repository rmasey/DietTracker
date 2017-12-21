public class Mealfood {
  private int mealFoodID;
  private int foodID;
  private int mealID;
  private int servingQuantity;

    public Mealfood(int mealFoodID, int foodID, int mealID, int servingQuantity) {
        this.mealFoodID = mealFoodID;
        this.foodID = foodID;
        this.mealID = mealID;
        this.servingQuantity = servingQuantity;
    }

    @Override
    public String toString() {
        return "Mealfood{" +
                "mealFoodID=" + mealFoodID +
                ", foodID=" + foodID +
                ", mealID=" + mealID +
                ", servingQuantity=" + servingQuantity +
                '}';
    }

    public int getMealFoodID() {
        return mealFoodID;
    }

    public void setMealFoodID(int mealFoodID) {
        this.mealFoodID = mealFoodID;
    }

    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    public int getMealID() {
        return mealID;
    }

    public void setMealID(int mealID) {
        this.mealID = mealID;
    }

    public int getServingQuantity() {
        return servingQuantity;
    }

    public void setServingQuantity(int servingQuantity) {
        this.servingQuantity = servingQuantity;
    }
}
