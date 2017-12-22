
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.time.LocalDate;
import java.util.List;

import static javafx.geometry.Pos.BASELINE_CENTER;

public class vMain {

    private static Pane parent;
    private static int userID;


    public vMain(Pane theParent, User selectedUser) {

        Stage stage = new Stage();
        parent = theParent;
        parent.setDisable(true);
        start(stage, selectedUser);

    }

    public void start(Stage stage, User selectedUser) {

        Pane root = new Pane();
        Scene scene = new Scene(root, 450, 600);
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

        Button trackCaloriesBtn = new Button("TRACK CALORIES");
        trackCaloriesBtn.setPrefSize(250, 35);
        trackCaloriesBtn.setOnAction((ActionEvent ae) -> openStageFour(root, selectedUser));


        DatePicker dp = new DatePicker();
        dp.setValue( LocalDate.now() );


        List<Consumption> alltheFood = ConsumptionDAO.selectAll(selectedUser.getUserID());
        ObservableList options = FXCollections.observableArrayList(alltheFood);

        TableView table = new TableView<>();

        table.setItems(options);


        TableColumn userID = new TableColumn<>("userID");
        userID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        table.getColumns().add(userID);

        TableColumn mealName = new TableColumn<>("mealName");
        mealName.setCellValueFactory(new PropertyValueFactory<>("mealName"));
        table.getColumns().add(mealName);

        TableColumn foodName = new TableColumn<>("foodName");
        foodName.setCellValueFactory(new PropertyValueFactory<>("foodName"));
        table.getColumns().add(foodName);

        TableColumn calories = new TableColumn<>("calories");
        calories.setCellValueFactory(new PropertyValueFactory<>("calories"));
        table.getColumns().add(calories);

        TableColumn dateEaten = new TableColumn<>("dateEaten");
        dateEaten.setCellValueFactory(new PropertyValueFactory<>("dateEaten"));
        table.getColumns().add(dateEaten);

        table.setLayoutX(50);
        table.setLayoutY(350);




        VBox vb = new VBox(iv1, label, trackWeightBtn, trackCaloriesBtn, dp);
        vb.setPadding(new Insets(10, 50, 50, 50));
        vb.setSpacing(10);
        vb.setAlignment(BASELINE_CENTER);

        root.getChildren().add(vb);
        root.getChildren().add(table);
    }

    public static void terminate()
    {
        System.exit(0);
    }

    public static void openStageFour(Pane parent, User selectedUser) {
        vTrackWeight newStage = new vTrackWeight(parent, selectedUser);

    }

}