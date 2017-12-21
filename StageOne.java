import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;


public class StageOne extends Application {

    public static DatabaseConnection database;

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stageOne) {

        database = new DatabaseConnection("DietTracker.db");

        //UserService.deleteById(1, database);

        Pane root = new Pane();
        Scene scene = new Scene(root, 1024, 768);
        stageOne.setTitle("Diet Tracker");
        stageOne.setScene(scene);
        stageOne.show();

        List<User> allTheUsers = UserService.selectAll();
        ObservableList options = FXCollections.observableArrayList(allTheUsers);
        ComboBox comboBox = new ComboBox(options);
        comboBox.getSelectionModel().select(0);

        root.getChildren().add(comboBox);

        Button myButton = new Button("Add new user!");
        myButton.setPrefSize(100, 35);
        myButton.setOnAction((ActionEvent ae) -> openNewStage(root));
        root.getChildren().add(myButton);
    }

    public static void openNewStage(Pane parent) {
        StageTwo newStage = new StageTwo(parent);
    }


}
