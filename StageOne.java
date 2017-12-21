import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;


public class StageOne extends Application {

    public static DatabaseConnection database;
    public static Pane root;

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stageOne) {

        database = new DatabaseConnection("DietTracker.db");

        //UserService.deleteById(1, database);

        root = new Pane();
        Scene scene = new Scene(root, 600, 500);
        scene.getStylesheets().add("style.css");
        stageOne.setTitle("Diet Tracker");
        stageOne.setScene(scene);
        stageOne.show();

        getListofUsers();  //adds comboxbox with users to screen

        Image image = new Image("/Images/logo.png");
        ImageView iv1 = new ImageView();
        iv1.setImage(image);
        iv1.setLayoutX(120);
        iv1.setLayoutY(40);
        iv1.setFitHeight(100);
        iv1.setPreserveRatio(true);
        root.getChildren().add(iv1);

        Image image2 = new Image("/Images/person.png");
        ImageView iv2 = new ImageView();
        iv2.setImage(image2);
        iv2.setLayoutX(120);
        iv2.setLayoutY(150);
       iv2.setFitHeight(50);
        iv2.setPreserveRatio(true);
        root.getChildren().add(iv2);

        Button myButton = new Button("Add new user!");
        myButton.setPrefSize(250, 35);
        myButton.setLayoutX(275);
        myButton.setLayoutY(400);
        myButton.setOnAction((ActionEvent ae) -> openNewStage(root));
        root.getChildren().add(myButton);
    }

    public static void openNewStage(Pane parent) {
        StageTwo newStage = new StageTwo(parent);
    }

    public static void getListofUsers() {
        List<User> allTheUsers = UserService.selectAll();
        ObservableList options = FXCollections.observableArrayList(allTheUsers);
        ComboBox comboBox = new ComboBox(options);
        comboBox.getSelectionModel().select(0);
        comboBox.setLayoutX(160);
        comboBox.setLayoutY(150);
        comboBox.setPrefWidth(250);
        root.getChildren().add(comboBox);
    }



}
