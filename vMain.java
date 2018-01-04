
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static javafx.geometry.Pos.BASELINE_CENTER;

public class vMain {

    private static Pane parent;
    private static int userID;
    private static Consumption selectedItem = null;
    private static TextField txtFieldMealName;
    private static TextField txtFieldFoodName;
    private static TextField txtFieldCalories;
    private static TextField txtFieldDateEaten;




    public vMain(Pane theParent, User selectedUser) {

        Stage stage = new Stage();
        parent = theParent;
        parent.setDisable(true);
        start(stage, selectedUser);

    }

    public void start(Stage stage, User selectedUser) {

        Pane root = new Pane();
        Scene scene = new Scene(root, 900, 600);
        scene.getStylesheets().add("style.css");
        stage.setTitle("Main");
        stage.setScene(scene);
        stage.setOnCloseRequest((WindowEvent we) -> terminate());
        stage.show();

        Image image = new Image("/Images/logo.png");
        ImageView iv1 = new ImageView();
        iv1.setImage(image);
        iv1.setFitHeight(100);
        iv1.setPreserveRatio(true);

        Label label = new Label("Welcome, " + selectedUser.getFirstName() + "!");

        Button trackWeightBtn = new Button("TRACK WEIGHT");
        trackWeightBtn.setPrefSize(250, 35);
        trackWeightBtn.setOnAction((ActionEvent ae) -> openStageFour(root, selectedUser));

        DatePicker dp = new DatePicker();
        dp.setValue( LocalDate.now() );




        TableView<Consumption> tvTable = new TableView<Consumption>();
        tvTable.setEditable(true);
        tvTable.setMaxHeight(200);
        tvTable.setOnMouseClicked((MouseEvent me) -> {
            selectedItem = tvTable.getSelectionModel().getSelectedItem();
        });

        //get all data for the table
        loadItemsinTable(selectedUser, tvTable);

        TableColumn <Consumption, String> mealNameCol = new TableColumn<>("Meal");
        mealNameCol.setCellValueFactory(new PropertyValueFactory<Consumption, String>("MealName"));
        mealNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        // for this to be called user must hit return after changing the data!
       mealNameCol.setOnEditCommit((e) -> mealNameCol_OnEditCommit(e, selectedUser, tvTable));
        tvTable.getColumns().add(mealNameCol);


        TableColumn foodNameCol = new TableColumn<>("Food Name");
        foodNameCol.setCellValueFactory(new PropertyValueFactory<>("foodName"));
        foodNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        // for this to be called user must hit return after changing the data!
        foodNameCol.setOnEditCommit((e) -> foodNameCol_OnEditCommit(e, selectedUser, tvTable));
        tvTable.getColumns().add(foodNameCol);

        TableColumn caloriesCol = new TableColumn<>("Calories");
        caloriesCol.setCellValueFactory(new PropertyValueFactory<>("Calories"));
        //caloriesCol.setCellFactory(TextFieldTableCell.forTableColumn());
        // for this to be called user must hit return after changing the data!
        caloriesCol.setOnEditCommit((e) -> caloriesCol_OnEditCommit(e, selectedUser, tvTable));
        tvTable.getColumns().add(caloriesCol);

        TableColumn dateEaten = new TableColumn<>("Date Eaten");
        dateEaten.setCellValueFactory(new PropertyValueFactory<>("dateEaten"));
        tvTable.getColumns().add(dateEaten);

        txtFieldMealName = new TextField();
        txtFieldMealName.setLayoutX(200);
        txtFieldMealName.setLayoutY(600);
        txtFieldMealName.setPrefWidth(300);
        txtFieldMealName.setPromptText("Enter meal eg lunch, snack");

        txtFieldFoodName = new TextField();
        txtFieldFoodName.setLayoutX(550);
        txtFieldFoodName.setLayoutY(600);
        txtFieldFoodName.setPromptText("Enter food");

        txtFieldCalories = new TextField();
        txtFieldCalories.setLayoutX(550);
        txtFieldCalories.setLayoutY(600);
        txtFieldCalories.setPromptText("Enter calories");

        txtFieldDateEaten = new TextField();
        txtFieldDateEaten.setLayoutX(550);
        txtFieldDateEaten.setLayoutY(600);
        txtFieldDateEaten.setPromptText("Enter date");


        Button btnAdd = new Button("Add");
        btnAdd.setLayoutX(50);
        btnAdd.setLayoutY(600);
        btnAdd.setOnAction((ActionEvent ae) -> addItem(selectedUser, tvTable));

        Button btnDelete = new Button("Delete Selected");
        btnDelete.setLayoutX(50);
        btnDelete.setLayoutY(500);
        btnDelete.setOnAction((ActionEvent ae) -> deleteItem(selectedUser, tvTable));

        VBox vb1 = new VBox(txtFieldMealName, txtFieldFoodName, txtFieldCalories, txtFieldDateEaten, btnAdd, btnDelete);
        vb1.setPadding(new Insets(10, 50, 50, 50));

        HBox hb = new HBox(tvTable, vb1);
        hb.setPadding(new Insets(10, 50, 50, 50));


        VBox vb = new VBox(iv1, label, trackWeightBtn, dp, hb);
        vb.setPadding(new Insets(10, 50, 50, 50));
        vb.setSpacing(10);
        vb.setAlignment(BASELINE_CENTER);

        root.getChildren().add(vb);

    }

    public void terminate()
    {
        System.exit(0);
    }

    public void openStageFour(Pane parent, User selectedUser)
    {
        vTrackWeight newStage = new vTrackWeight(parent, selectedUser);
    }

    public void mealNameCol_OnEditCommit(Event e, User selectedUser, TableView tvTable)
    {
        System.out.println("Updating database...");

        TableColumn.CellEditEvent<Consumption, String>ce;
        ce = (TableColumn.CellEditEvent<Consumption, String>) e;

        try
        {
            PreparedStatement statement = vLogin.database.newStatement("UPDATE Consumption set MealName = ? WHERE consumptionID =" + selectedItem.getConsumptionID() +" ");
            statement.setString(1,ce.getNewValue());

            if (statement != null) {
                vLogin.database.executeUpdate(statement);
            }

        }
        catch (SQLException resultsexception) {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }

        loadItemsinTable(selectedUser, tvTable);


    }

    public void foodNameCol_OnEditCommit(Event e, User selectedUser, TableView tvTable)
    {
        System.out.println("Updating database...");

        TableColumn.CellEditEvent<Consumption, String>ce;
        ce = (TableColumn.CellEditEvent<Consumption, String>) e;

        try
        {
            PreparedStatement statement = vLogin.database.newStatement("UPDATE Consumption set FoodName = ? WHERE consumptionID =" + selectedItem.getConsumptionID() +" ");
            statement.setString(1,ce.getNewValue());

            if (statement != null) {
                vLogin.database.executeUpdate(statement);
            }

        }
        catch (SQLException resultsexception) {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }

        loadItemsinTable(selectedUser, tvTable);

    }

    public void caloriesCol_OnEditCommit(Event e, User selectedUser, TableView tvTable)
    {
        System.out.println("Updating database...");

        TableColumn.CellEditEvent<Consumption, String>ce;
        ce = (TableColumn.CellEditEvent<Consumption, String>) e;

        try
        {
            PreparedStatement statement = vLogin.database.newStatement("UPDATE Consumption set Calories = ? WHERE consumptionID =" + selectedItem.getConsumptionID() +" ");
            statement.setString(1,ce.getNewValue());

            if (statement != null) {
                vLogin.database.executeUpdate(statement);
            }

        }
        catch (SQLException resultsexception) {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }

        loadItemsinTable(selectedUser, tvTable);

    }


    public void addItem(User selectedUser, TableView tvTable) {
        //get the UserID
        int userID = selectedUser.getUserID();
        //get the mealName
        String mealName = txtFieldMealName.getText();
        //get the FoodName
        String foodName = txtFieldFoodName.getText();
        //get the calories
        int calories = Integer.parseInt(txtFieldCalories.getText());
        //get the date
        //java.sql.Date dateEaten = txtFieldDateEaten.getText();
        java.sql.Date dateEaten = java.sql.Date.valueOf(LocalDate.now());

        //something wrong with the


        //call save method from ConsumptionDAO using data above
        //since the ConsumptionID is autoincremented in the DB, it can be set to 1
        ConsumptionDAO.save(new Consumption(1, userID, mealName, foodName, calories, dateEaten));

        loadItemsinTable(selectedUser, tvTable);

    }

    public void deleteItem(User selectedUser, TableView tvTable) {
        // if there is not a selected weight return
        if (selectedItem == null) {
            return;
        }

        // call the method to delete the selected weight
        ConsumptionDAO.deleteById(selectedItem.getConsumptionID());

        //re-load all the food data
        loadItemsinTable(selectedUser, tvTable);
    }


    public void loadItemsinTable(User selectedUser, TableView tvTable) {

        List<Consumption> alltheFood = ConsumptionDAO.selectAll(selectedUser.getUserID());
        ObservableList options = FXCollections.observableArrayList(alltheFood);
        tvTable.setItems(options);
    }

}