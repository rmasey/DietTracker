import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

import static javafx.geometry.Pos.BASELINE_CENTER;


public class StageOne extends Application {

    public static DatabaseConnection database;  //single connection used across the application
                                                //so for ease this is made public

    private static Pane root;
    private static ComboBox comboBox;
    private static ImageView iv1;
    private static Button loginBtn;
    private static Button newUserBtn;
    private static VBox vb;


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
        iv1 = new ImageView();
        iv1.setImage(image);
        iv1.setFitHeight(100);
        iv1.setPreserveRatio(true);

        loginBtn = new Button("LOGIN");
        loginBtn.setPrefSize(250, 35);

        newUserBtn = new Button("NEW USER");
        newUserBtn.setPrefSize(250, 35);
        newUserBtn.setOnAction((ActionEvent ae) -> openNewStage(root));

        ComboBox comboBox = new ComboBox();
        List<User> allTheUsers = UserService.selectAll();
        ObservableList options = FXCollections.observableArrayList(allTheUsers);
        comboBox.setItems(options);
        comboBox.setPrefWidth(250);

        vb = new VBox(iv1, comboBox, loginBtn, newUserBtn);
        vb.setPadding(new Insets(10, 50, 50, 50));
        vb.setSpacing(10);
        vb.setAlignment(BASELINE_CENTER);

        root.getChildren().add(vb);
    }




    public static void openNewStage(Pane parent) {
        StageTwo newStage = new StageTwo(parent);
    }


}
