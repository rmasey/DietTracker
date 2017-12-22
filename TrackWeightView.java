
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static javafx.geometry.Pos.BASELINE_CENTER;


public class TrackWeightView {

    private static TextField txtFieldCurrentWeight;
    private static DatePicker dp;
    private static Pane parent;

    public TrackWeightView(Pane theParent, User selectedUser) {

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


        List<Weighin> allItems = WeighinDAO.selectWeighinsByUserId(selectedUser.getUserID());
        ObservableList options = FXCollections.observableArrayList(allItems);

        int latestWeight = allItems.get(0).getWeight();
        int lastItem = allItems.size()-1;
        int startWeight = allItems.get(lastItem).getWeight();


        Label label1 = new Label("Current Weight " + latestWeight +  "kg         Target Weight " + selectedUser.getTargetWeight());
        Label label2 = new Label("Weight change "  + (startWeight-latestWeight) +  "kg         Remaining " + (latestWeight - selectedUser.getTargetWeight()));

        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        //creating the chart
        final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        XYChart.Series series = new XYChart.Series();
        //populating the series with data

        int i = 1;
        for (Weighin w : allItems){
            series.getData().add(new XYChart.Data(i ,  w.getWeight()));
            i++;
        }

//        series.getData().add(new XYChart.Data(1, 23));
//        series.getData().add(new XYChart.Data(2, 14));
//        series.getData().add(new XYChart.Data(3, 15));
//        series.getData().add(new XYChart.Data(4, 24));
//        series.getData().add(new XYChart.Data(5, 34));
//        series.getData().add(new XYChart.Data(6, 36));
//        series.getData().add(new XYChart.Data(7, 22));
//        series.getData().add(new XYChart.Data(8, 45));
//        series.getData().add(new XYChart.Data(9, 43));
//        series.getData().add(new XYChart.Data(10, 17));
//        series.getData().add(new XYChart.Data(11, 29));
//        series.getData().add(new XYChart.Data(12, 25));

        lineChart.getData().add(series);
        lineChart.setMaxHeight(300);
        lineChart.setLayoutX(100);
        lineChart.setLayoutY(200);
        root.getChildren().add(lineChart);

        DatePicker dp = new DatePicker();
        LocalDate.now();

        txtFieldCurrentWeight = new TextField();
        txtFieldCurrentWeight.setPromptText("Current weight in KG");

        Button createWeightBtn = new Button();
        createWeightBtn.setText("TRACK WEIGHT");
        createWeightBtn.setOnAction((ActionEvent ae) -> addWeighin(selectedUser));


        TableView table = new TableView<>();

        table.setItems(options);

        TableColumn Date = new TableColumn<>("Date");
        Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        table.getColumns().add(Date);

        TableColumn Weight = new TableColumn<>("Weight");
        Weight.setCellValueFactory(new PropertyValueFactory<>("Weight"));
        table.getColumns().add(Weight);


        table.setLayoutX(50);
        table.setLayoutY(350);

        VBox vb = new VBox(iv1, label1, label2, dp, txtFieldCurrentWeight, createWeightBtn);
        vb.setPadding(new Insets(10, 50, 50, 50));
        vb.setSpacing(10);
        vb.setAlignment(BASELINE_CENTER);

        root.getChildren().add(vb);
        root.getChildren().add(table);
    }

    private void addWeighin(User selectedUser) {

        String str="2017-12-21";
        Date date = Date.valueOf(str);//converting string into sql date

        int currentWeight = Integer.parseInt(txtFieldCurrentWeight.getText());
        int userID = selectedUser.getUserID();

        WeighinDAO.addWeighin(new Weighin(1, date, currentWeight, userID));


    }


       private void closeStage(Stage stage) {
        parent.setDisable(false);
        stage.close();

    }

}
