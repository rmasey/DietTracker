
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
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


    public vTrackWeight(Pane theParent, User selectedUser) {

        Stage stage = new Stage();
        parent = theParent;
        parent.setDisable(true);
        start(stage, selectedUser);
    }

    public void start(Stage stage, User selectedUser) {

        Pane root = new Pane();
        Scene scene = new Scene(root, 450, 600);
        scene.getStylesheets().add("style.css");
        stage.setTitle("Track Weight");
        stage.setScene(scene);
        stage.setOnCloseRequest((WindowEvent we) -> closeStage(stage));
        stage.show();

        Image image = new Image("/Images/logo.png");
        ImageView iv1 = new ImageView();
        iv1.setImage(image);
        iv1.setFitHeight(100);
        iv1.setPreserveRatio(true);

        Label label1 = new Label("Current Weight " +  "kg         Target Weight " );
        Label label2 = new Label("Weight change "  + "kg         Remaining " );




        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        //creating the chart
        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        XYChart.Series series = new XYChart.Series();
        //populating the series with data

        int i = 1;
//        for (Weighin w : allItems){
//            series.getData().add(new XYChart.Data(i ,  w.getWeight()));
//            i++;
//        }

        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));

        lineChart.getData().add(series);
        lineChart.setMaxHeight(300);
        lineChart.setLayoutX(100);
        lineChart.setLayoutY(200);
        root.getChildren().add(lineChart);


        dp = new DatePicker(LocalDate.now());





        txtFieldCurrentWeight = new TextField();
        txtFieldCurrentWeight.setPromptText("Current weight in KG");

        Button createWeightBtn = new Button();
        createWeightBtn.setText("TRACK WEIGHT");
        createWeightBtn.setOnAction((ActionEvent ae) -> addWeighin(selectedUser, tblView));


        TableView<Weight> tblView = new TableView<Weight>();
        tblView.setEditable(true);


        TableColumn<Weight, Date> firstColumn = new TableColumn<>("Date");
        firstColumn.setCellValueFactory(new PropertyValueFactory<Weight, Date>("Date"));

        TableColumn<Weight, Integer> secondColumn = new TableColumn<>("currentWeight");
           secondColumn.setCellValueFactory(new PropertyValueFactory<Weight, Integer>("currentWeight"));


        tblView.getColumns().addAll(firstColumn, secondColumn);

        tblView.setLayoutX(50);
        tblView.setLayoutY(350);

        VBox vb = new VBox(iv1, label1, label2, dp, txtFieldCurrentWeight, createWeightBtn);
        vb.setPadding(new Insets(10, 50, 50, 50));
        vb.setSpacing(10);
        vb.setAlignment(BASELINE_CENTER);


        root.getChildren().addAll(vb, tblView);


        loadData(selectedUser, tblView);

    }

    public void addWeighin(User selectedUser, TableView tblView) {



        java.sql.Date datePicked = java.sql.Date.valueOf(dp.getValue());


        int currentWeight = Integer.parseInt(txtFieldCurrentWeight.getText());
        int userID = selectedUser.getUserID();

        WeightDAO.save(new Weight(1, datePicked, currentWeight, userID));

        loadData(selectedUser, tblView);
    }

    public static void loadData(User selectedUser, TableView tblView) {

        weightData.clear();
        WeightDAO.readAll(weightData, selectedUser.getUserID());

        tblView.setItems(weightData);
        tblView.refresh();

        int lastItem = weightData.size() - 1;
        int startWeight = weightData.get(lastItem).getCurrentweight();
        int latestWeight = weightData.get(0).getCurrentweight();
        System.out.println(weightData.get(0).getUserid());
        int selectedUserID = weightData.get(0).getUserid();

        Label label1 = new Label("Current Weight " + latestWeight + "kg         Target Weight " + selectedUserID);
        Label label2 = new Label("Weight change " + (startWeight - latestWeight) + "kg         Remaining " + (latestWeight - selectedUserID));



    }


    public void closeStage(Stage stage) {
        parent.setDisable(false);
        stage.close();

    }


}
