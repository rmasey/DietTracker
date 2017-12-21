
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class StageTwo {

    TextField txtFieldFName;
    TextField txtFieldLName;
    TextField txtFieldStartWeight;
    TextField txtFieldTargetWeight;
    TextField txtFieldKCalPerDay;
    ComboBox comboBox;

    static Pane parent;

    public StageTwo(Pane theParent) {

        Stage stage = new Stage();
        parent = theParent;
        parent.setDisable(true);
        start(stage);
    }

    public void start(Stage stage) {

        Pane root = new Pane();
        Scene scene = new Scene(root, 1024, 768);
        stage.setTitle("Add New User");
        stage.setScene(scene);
        stage.setOnCloseRequest((WindowEvent we) -> closeStage(stage));
        stage.show();

        Button btn = new Button();
        btn.setText("Add new item");
        btn.setLayoutX(350);
        btn.setLayoutY(50);
        btn.setOnAction((ActionEvent ae) -> addNewItem(stage));
        root.getChildren().add(btn);

        Label label = new Label("Tell Us About Yourself");
        label.setLayoutX(350);
        label.setLayoutY(150);
        root.getChildren().add(label);

        txtFieldFName = new TextField();
        txtFieldFName.setLayoutX(400);
        txtFieldFName.setLayoutY(200);
        txtFieldFName.setPromptText("Enter First Name");
        root.getChildren().add(txtFieldFName);

        txtFieldLName = new TextField();
        txtFieldLName.setLayoutX(400);
        txtFieldLName.setLayoutY(250);
        txtFieldLName.setPromptText("Enter last name");
        root.getChildren().add(txtFieldLName);

        txtFieldStartWeight = new TextField();
        txtFieldStartWeight.setLayoutX(400);
        txtFieldStartWeight.setLayoutY(300);
        txtFieldStartWeight.setPromptText("Enter start weight");
        root.getChildren().add(txtFieldStartWeight);


        txtFieldTargetWeight = new TextField();
        txtFieldTargetWeight.setLayoutX(400);
        txtFieldTargetWeight.setLayoutY(350);
        txtFieldTargetWeight.setPromptText("Enter target weight");
        root.getChildren().add(txtFieldTargetWeight);


        txtFieldKCalPerDay = new TextField();
        txtFieldKCalPerDay.setLayoutX(400);
        txtFieldKCalPerDay.setLayoutY(400);
        txtFieldKCalPerDay.setPromptText("Enter calories per day");
        root.getChildren().add(txtFieldKCalPerDay);

        ObservableList options = FXCollections.observableArrayList("Male", "Female");
        comboBox = new ComboBox(options);
        comboBox.getSelectionModel().select(0);
        comboBox.setLayoutX(400);
        comboBox.setLayoutY(450);
        root.getChildren().add(comboBox);

    }

    private void addNewItem(Stage stage) {

        String firstName = txtFieldFName.getText();
        String lastName = txtFieldLName.getText();
        int startWeight = Integer.parseInt(txtFieldStartWeight.getText());
        int targetWeight = Integer.parseInt(txtFieldTargetWeight.getText());
        int KCalPerDay = Integer.parseInt(txtFieldKCalPerDay.getText());
        String gender = (String) comboBox.getValue();

        UserService.save(new User(1, firstName, lastName, startWeight,targetWeight, KCalPerDay, gender));

      closeStage(stage);
    }


    private void closeStage(Stage stage) {
        parent.setDisable(false);
        stage.close();

        StageOne.getListofUsers();
    }


}