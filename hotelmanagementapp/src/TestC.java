//
//import javafx.application.Application;
//import javafx.geometry.Insets;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.ColumnConstraints;
//import javafx.scene.layout.FlowPane;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.RowConstraints;
//import javafx.scene.shape.MeshView;
//import javafx.scene.text.Font;
//import javafx.stage.Stage;
//
//public class WorkManagePage  {
//
//
//    public void start(Stage primaryStage) {
//        GridPane gridPane = new GridPane();
//        gridPane.setId("gripane");
//        gridPane.setHgap(10.0);
//        gridPane.setMaxHeight(Double.NEGATIVE_INFINITY);
//        gridPane.setMaxWidth(Double.NEGATIVE_INFINITY);
//        gridPane.setMinHeight(Double.NEGATIVE_INFINITY);
//        gridPane.setMinWidth(Double.NEGATIVE_INFINITY);
//        gridPane.setPrefHeight(393.0);
//        gridPane.setPrefWidth(551.0);
//        gridPane.setStyle("-fx-background-color: pink; -fx-border-style: ;");
//        gridPane.setVgap(10.0);
//
//        ColumnConstraints column1 = new ColumnConstraints();
//        column1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
//        column1.setMaxWidth(290.0);
//        column1.setMinWidth(10.0);
//        column1.setPrefWidth(194.0);
//
//        ColumnConstraints column2 = new ColumnConstraints();
//        column2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
//        column2.setMaxWidth(449.0);
//        column2.setMinWidth(10.0);
//        column2.setPrefWidth(396.0);
//
//        gridPane.getColumnConstraints().addAll(column1, column2);
//
//        RowConstraints row1 = new RowConstraints();
//        row1.setMaxHeight(100.0);
//        row1.setMinHeight(10.0);
//        row1.setPrefHeight(20.0);
//        row1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
//
//        RowConstraints row2 = new RowConstraints();
//        row2.setMaxHeight(100.0);
//        row2.setMinHeight(10.0);
//        row2.setPrefHeight(20.0);
//        row2.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
//
//        RowConstraints row3 = new RowConstraints();
//        row3.setMaxHeight(100.0);
//        row3.setMinHeight(10.0);
//        row3.setPrefHeight(20.0);
//        row3.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
//
//        RowConstraints row4 = new RowConstraints();
//        row4.setMaxHeight(100.0);
//        row4.setMinHeight(10.0);
//        row4.setPrefHeight(20.0);
//        row4.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
//
//        RowConstraints row5 = new RowConstraints();
//        row5.setMaxHeight(100.0);
//        row5.setMinHeight(10.0);
//        row5.setPrefHeight(20.0);
//        row5.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
//
//        gridPane.getRowConstraints().addAll(row1, row2, row3, row4, row5);
//
//        MeshView meshView = new MeshView();
//        gridPane.add(meshView, 0, 0);
//
//        Label label1 = new Label("Employeeid:");
//        label1.setAlignment(javafx.geometry.Pos.CENTER);
//        label1.setContentDisplay(javafx.scene.control.ContentDisplay.RIGHT);
//        label1.setTextFill(javafx.scene.paint.Color.valueOf("#1bae48"));
//        label1.setFont(new Font(24.0));
//        label1.setPadding(new Insets(10.0, 20.0, 10.0, 10.0));
//        gridPane.add(label1, 0, 1);
//
//        Label label2 = new Label("Date:");
//        label2.setTextFill(javafx.scene.paint.Color.valueOf("#0ca826"));
//        label2.setAlignment(javafx.geometry.Pos.CENTER);
//        label2.setPadding(new Insets(10.0, 20.0, 10.0, 10.0));
//        label2.setFont(new Font(24.0));
//        gridPane.add(label2, 0, 2);
//
//        Label label3 = new Label("Start Time:");
//        label3.setTextFill(javafx.scene.paint.Color.valueOf("#26dd57"));
//        label3.setAlignment(javafx.geometry.Pos.CENTER);
//        label3.setPadding(new Insets(10.0, 20.0, 10.0, 10.0));
//        label3.setFont(new Font(24.0));
//        gridPane.add(label3, 0, 3);
//
//        Label label4 = new Label("End Time");
//        label4.setTextFill(javafx.scene.paint.Color.valueOf("#17ae56"));
//        label4.setPadding(new Insets(10.0, 20.0, 10.0, 10.0));
//        label4.setFont(new Font(24.0));
//        gridPane.add(label4, 0, 4);
//
//        TextField employeeID = new TextField();
//        employeeID.setMaxHeight(40.0);
//        employeeID.setMaxWidth(250.0);
//        employeeID.setPrefHeight(30.0);
//        employeeID.setPrefWidth(500.0);
//        employeeID.setStyle("-fx-border-color: blue;");
//        gridPane.add(employeeID, 1, 1);
//
//        TextField date = new TextField();
//        date.setMaxHeight(40.0);
//        date.setMaxWidth(250.0);
//        date.setPrefHeight(30.0);
//        date.setStyle("-fx-border-color: blue;");
//        gridPane.add(date, 1, 2);
//
//        TextField startTime = new TextField();
//        startTime.setMaxHeight(40.0);
//        startTime.setMaxWidth(250.0);
//        startTime.setPrefHeight(30.0);
//        startTime.setStyle("-fx-border-color: blue;");
//        gridPane.add(startTime, 1, 3);
//
//        TextField endTime = new TextField();
//        endTime.setMaxHeight(40.0);
//        endTime.setMaxWidth(250.0);
//        endTime.setPrefHeight(30.0);
//        endTime.setStyle("-fx-border-color: blue;");
//        gridPane.add(endTime, 1, 4);
//
//        Button submitButton = new Button("Submit");
//        submitButton.setMnemonicParsing(false);
//        submitButton.setPrefHeight(50.0);
//        submitButton.setPrefWidth(100.0);
//        submitButton.setStyle("-fx-background-color: green;");
//        submitButton.setFont(new Font("System Bold", 18.0));
//        submitButton.setPadding(new Insets(10.0, 20.0, 10.0, 10.0));
//
//
//        Button clearButton = new Button("Clear");
//        clearButton.setId("clear");
//        clearButton.setMnemonicParsing(false);
//        clearButton.setPrefHeight(50.0);
//        clearButton.setPrefWidth(100.0);
//        clearButton.setStyle("-fx-background-color: green;");
//        clearButton.setTextFill(javafx.scene.paint.Color.valueOf("#3e3f46"));
//        clearButton.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
//        clearButton.setFont(new Font("System Bold", 24.0));
//
//        FlowPane floepane=new FlowPane();
//
//floepane.getChildren().addAll(submitButton,clearButton);
// gridPane.add(floepane, 1, 5);
// floepane.setHgap(20);
// gridPane.setPadding(new Insets(10,10,20,10));
//        Scene scene = new Scene(gridPane);
//
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
