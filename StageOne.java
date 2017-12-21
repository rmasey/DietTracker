import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

import static javafx.geometry.Pos.*;


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
        Scene scene = new Scene(root, 420, 400);
        scene.getStylesheets().add("style.css");
        stageOne.setTitle("Diet Tracker");
        stageOne.setScene(scene);
        stageOne.show();

        Image image = new Image("/Images/logo.png");
        ImageView iv1 = new ImageView();
        iv1.setImage(image);
        iv1.setFitHeight(100);
        iv1.setPreserveRatio(true);

        // Can't get this to appear in front of combobox - leave out
//        Image image2 = new Image("/Images/person.png");
//        ImageView iv2 = new ImageView();
//        iv2.setImage(image2);
//        iv2.setFitHeight(50);
//        iv2.setPreserveRatio(true);
//        iv2.setLayoutX(50);
//        iv2.setLayoutY(150);
//        root.getChildren().add(iv2);


        Button loginBtn = new Button("LOGIN");
        loginBtn.setPrefSize(250, 35);

        Button newUserBtn = new Button("NEW USER");
        newUserBtn.setPrefSize(250, 35);


        List<User> allTheUsers = UserService.selectAll();
        ObservableList options = FXCollections.observableArrayList(allTheUsers);
        ComboBox comboBox = new ComboBox(options);
        comboBox.setPrefWidth(250);

        newUserBtn.setOnAction((ActionEvent ae) -> openNewStage(root));

        VBox vb = new VBox(iv1, comboBox, loginBtn, newUserBtn);
        vb.setPadding(new Insets(10, 50, 50, 50));
        vb.setSpacing(10);
        vb.setAlignment(BASELINE_CENTER);

        root.getChildren().add(vb);

    }

    public static void openNewStage(Pane parent) {
        StageTwo newStage = new StageTwo(parent);
    }


}
