
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import static javafx.geometry.Pos.BASELINE_CENTER;


public class vNewUser {

   private static TextField txtFieldFName;
    private static TextField txtFieldLName;
    private static TextField txtFieldTargetWeight;
    private static TextField txtFieldKCalPerDay;
    private static ComboBox comboBox;

    static Pane parent;

    public vNewUser(Pane theParent) {

        Stage stage = new Stage();
        parent = theParent;
        parent.setDisable(true);
        start(stage);
    }

    public void start(Stage stage) {

        Pane root = new Pane();
        Scene scene = new Scene(root, 450, 600);
        scene.getStylesheets().add("style.css");
        stage.setTitle("New User");
        stage.setScene(scene);
        stage.setOnCloseRequest((WindowEvent we) -> closeStage(stage));
        stage.show();

        Image image = new Image("/Images/logo.png");
        ImageView iv1 = new ImageView();
        iv1.setImage(image);
        iv1.setFitHeight(100);
        iv1.setPreserveRatio(true);

        Label label = new Label("Tell Us About Yourself");
        txtFieldFName = new TextField();
        txtFieldFName.setPromptText("First Name");
        txtFieldLName = new TextField();
        txtFieldLName.setPromptText("Last name");
        txtFieldTargetWeight = new TextField();
        txtFieldTargetWeight.setPromptText("Target weight in KG");
        txtFieldKCalPerDay = new TextField();
        txtFieldKCalPerDay.setPromptText("Maximum calories per day");

        ObservableList options = FXCollections.observableArrayList("Male", "Female");
        comboBox = new ComboBox(options);
        comboBox.setPrefWidth(350);
        comboBox.setPromptText("Select your gender");

        Button createUserBtn = new Button();
        createUserBtn.setText("CREATE NEW USER");
        createUserBtn.setOnAction((ActionEvent ae) -> addUser(stage));

        VBox vb = new VBox(iv1, label, txtFieldFName, txtFieldLName, txtFieldTargetWeight, txtFieldKCalPerDay, comboBox, createUserBtn);
        vb.setPadding(new Insets(10, 50, 50, 50));
        vb.setSpacing(10);
        vb.setAlignment(BASELINE_CENTER);

        root.getChildren().add(vb);
    }

    private void addUser(Stage stage) {

        String firstName = txtFieldFName.getText();
        String lastName = txtFieldLName.getText();
        int targetWeight = Integer.parseInt(txtFieldTargetWeight.getText());
        int KCalPerDay = Integer.parseInt(txtFieldKCalPerDay.getText());
        String gender = (String) comboBox.getValue();

        UserDAO.addUser(new User(1, firstName, lastName, targetWeight, KCalPerDay, gender));

        closeStage(stage);
    }

    private void closeStage(Stage stage) {
        parent.setDisable(false);
        stage.close();


    }

}