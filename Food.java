public class Food {
  private int foodID;
  private String description;
  private String servingSize;
  private int fibrePerServing;
  private int fatPerServing;
  private int sugarsPerServing;
  private int KCalPerServing;
  private int carbsPerServing;
  private int proteinPerServing;
  private int saturatedFatPerServing;
  private int sodiumPerServing;

  public Food(int foodID, String description, String servingSize, int fibrePerServing, int fatPerServing, int sugarsPerServing, int KCalPerServing, int carbsPerServing, int proteinPerServing, int saturatedFatPerServing, int sodiumPerServing) {
    this.foodID = foodID;
    this.description = description;
    this.servingSize = servingSize;
    this.fibrePerServing = fibrePerServing;
    this.fatPerServing = fatPerServing;
    this.sugarsPerServing = sugarsPerServing;
    this.KCalPerServing = KCalPerServing;
    this.carbsPerServing = carbsPerServing;
    this.proteinPerServing = proteinPerServing;
    this.saturatedFatPerServing = saturatedFatPerServing;
    this.sodiumPerServing = sodiumPerServing;
  }

  @Override
  public String toString() {
    return "Food{" +
            "foodID=" + foodID +
            ", description='" + description + '\'' +
            ", servingSize='" + servingSize + '\'' +
            ", fibrePerServing=" + fibrePerServing +
            ", fatPerServing=" + fatPerServing +
            ", sugarsPerServing=" + sugarsPerServing +
            ", KCalPerServing=" + KCalPerServing +
            ", carbsPerServing=" + carbsPerServing +
            ", proteinPerServing=" + proteinPerServing +
            ", saturatedFatPerServing=" + saturatedFatPerServing +
            ", sodiumPerServing=" + sodiumPerServing +
            '}';
  }

  public int getFoodID() {
    return foodID;
  }

  public void setFoodID(int foodID) {
    this.foodID = foodID;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getServingSize() {
    return servingSize;
  }

  public void setServingSize(String servingSize) {
    this.servingSize = servingSize;
  }

  public int getFibrePerServing() {
    return fibrePerServing;
  }

  public void setFibrePerServing(int fibrePerServing) {
    this.fibrePerServing = fibrePerServing;
  }

  public int getFatPerServing() {
    return fatPerServing;
  }

  public void setFatPerServing(int fatPerServing) {
    this.fatPerServing = fatPerServing;
  }

  public int getSugarsPerServing() {
    return sugarsPerServing;
  }

  public void setSugarsPerServing(int sugarsPerServing) {
    this.sugarsPerServing = sugarsPerServing;
  }

  public int getKCalPerServing() {
    return KCalPerServing;
  }

  public void setKCalPerServing(int KCalPerServing) {
    this.KCalPerServing = KCalPerServing;
  }

  public int getCarbsPerServing() {
    return carbsPerServing;
  }

  public void setCarbsPerServing(int carbsPerServing) {
    this.carbsPerServing = carbsPerServing;
  }

  public int getProteinPerServing() {
    return proteinPerServing;
  }

  public void setProteinPerServing(int proteinPerServing) {
    this.proteinPerServing = proteinPerServing;
  }

  public int getSaturatedFatPerServing() {
    return saturatedFatPerServing;
  }

  public void setSaturatedFatPerServing(int saturatedFatPerServing) {
    this.saturatedFatPerServing = saturatedFatPerServing;
  }

  public int getSodiumPerServing() {
    return sodiumPerServing;
  }

  public void setSodiumPerServing(int sodiumPerServing) {
    this.sodiumPerServing = sodiumPerServing;
  }
}