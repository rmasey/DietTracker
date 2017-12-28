
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

import static javafx.geometry.Pos.BASELINE_CENTER;
import static javafx.scene.control.cell.TextFieldTableCell.forTableColumn;


public class vTrackWeight {

    private static TextField txtFieldCurrentWeight;
    private static DatePicker dp;
    private static Pane parent;
    private static TableView<Weight> tblView;
    private static ObservableList<Weight> weightData = FXCollections.observableArrayList();
    private static int startWeight;
    private static int latestWeight;
    private static VBox vb;
    private static ImageView iv1;
    private static Button createWeightBtn;
    private static Pane root;
    private static Label label1;
    private static Label label2;
    private static Weight currentlySelectedWeight = null;
    private static Button deleteWeightBtn;
    private static XYChart.Series series;


    public vTrackWeight(Pane theParent, User selectedUser) {
        Stage stage = new Stage();
        parent = theParent;
        parent.setDisable(true);
        start(stage, selectedUser);
    }

    public void start(Stage stage, User selectedUser) {

        root = new Pane();
        Scene scene = new Scene(root, 450, 600);
        scene.getStylesheets().add("style.css");
        stage.setTitle("Track Weight");
        stage.setScene(scene);
        stage.setOnCloseRequest((WindowEvent we) -> closeStage(stage));
        stage.show();

        Image image = new Image("/Images/logo.png");
        iv1 = new ImageView();
        iv1.setImage(image);
        iv1.setFitHeight(100);
        iv1.setPreserveRatio(true);

        series = new XYChart.Series();

        //defining the axes
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        // dateAxis.setLabel("Date");

        //creating the chart
        final LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
        lineChart.getData().add(series);
        lineChart.setMaxHeight(300);

        dp = new DatePicker(LocalDate.now());

        txtFieldCurrentWeight = new TextField();
        txtFieldCurrentWeight.setPromptText("Current weight in KG");

        createWeightBtn = new Button();
        createWeightBtn.setText("TRACK WEIGHT");
        createWeightBtn.setOnAction((ActionEvent ae) -> addItem(selectedUser, tblView));
        deleteWeightBtn = new Button();
        deleteWeightBtn.setText("DELETE WEIGHT");
        deleteWeightBtn.setOnAction((ActionEvent ae) -> deleteSelectedItem(selectedUser, tblView));


        //create editable table
        tblView = new TableView<Weight>();
        tblView.setEditable(true);
        tblView.setOnMouseClicked((MouseEvent me) -> {
            currentlySelectedWeight = tblView.getSelectionModel().getSelectedItem();
        });

        TableColumn<Weight, Date> firstColumn = new TableColumn<>("Date");
        firstColumn.setCellValueFactory(new PropertyValueFactory<Weight, Date>("Date"));

        TableColumn<Weight, Integer> secondColumn = new TableColumn<>("Weight");
        secondColumn.setCellValueFactory(new PropertyValueFactory<Weight, Integer>("currentWeight"));


        tblView.getColumns().addAll(firstColumn, secondColumn);

        loadData(selectedUser, tblView);

        vb = new VBox(iv1, label1, label2, dp, txtFieldCurrentWeight, createWeightBtn, deleteWeightBtn);
        vb.setPadding(new Insets(10, 50, 50, 50));
        vb.setSpacing(10);
        vb.setAlignment(BASELINE_CENTER);

        root.getChildren().addAll(vb, tblView, lineChart);
    }


    public void addItem(User selectedUser, TableView tblView) {
        //get the date
        java.sql.Date datePicked = java.sql.Date.valueOf(dp.getValue());
        //get the weight
        int currentWeight = Integer.parseInt(txtFieldCurrentWeight.getText());
        //get the UserID
        int userID = selectedUser.getUserID();
        //call save method from WeightDAO using data above
        //since the weightID is autoincremented in the DB, weightID can be set to 1
        WeightDAO.save(new Weight(1, datePicked, currentWeight, userID));
        //re-load data into table view with new item showing
        loadData(selectedUser, tblView);
    }

    public static void loadData(User selectedUser, TableView tblView) {

        weightData.clear();
        WeightDAO.readAll(weightData, selectedUser.getUserID());

        tblView.setItems(weightData);

        int selectedUserID = selectedUser.getUserID();


        if (weightData.size() != 0) {
            int lastItem = weightData.size() - 1;
            int startWeight = weightData.get(lastItem).getCurrentWeight();
            int latestWeight = weightData.get(0).getCurrentWeight();
            label1 = new Label("Current Weight " + latestWeight + "kg         Target Weight " + selectedUserID);
            label2 = new Label("Weight change " + (startWeight - latestWeight) + "kg         Remaining " + (latestWeight - selectedUserID));
        } else {
            label1 = new Label("Current Weight " + "kg         Target Weight " + selectedUserID);
            label2 = new Label("Weight change " + "kg         Remaining ");
        }

        // series = null; need to clear this not keep adding to it as when weight deleted still in series
        for (Weight w : weightData) {
            series.getData().add(new XYChart.Data(w.getDate().toString(), w.getCurrentWeight()));
        }

    }

    private static void deleteSelectedItem(User selectedUser, TableView tblView) {
        // if there is not a selected weight return
        if (currentlySelectedWeight == null) {
            return;
        }

        // call the method to delete the selected weight
        WeightDAO.deleteById(currentlySelectedWeight.getWeightID());

        //re-load all the weight data
        loadData(selectedUser, tblView);
    }


    public void closeStage(Stage stage) {

        //enable parent again and close this stage
        parent.setDisable(false);
        stage.close();
    }


}
