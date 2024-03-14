package hotelmanagementapp;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.time.LocalTime;
public class EmployeeWorkSchedule  {

    TextField employeeIdTextField;
    private TableView<Object[]> tableView;
    private ObservableList<Object[]> data;
    BorderPane root = new BorderPane();

    public void getEmployeeWorkSchedule(Stage primaryStage,String role) {
        primaryStage.setTitle("Employee Work Schedule");

        // Create the root BorderPane

        root.setStyle("-fx-border-color: blue; -fx-background-color: lightblue;");

        // Create the left GridPane
        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(400, 700);

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setStyle("-fx-background-color: pink;");
        gridPane.setPadding(new Insets(10));

        // Add column constraints
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col1.setMaxWidth(120);
        col1.setMinWidth(10);
        col1.setPrefWidth(54);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col2.setMaxWidth(166);
        col2.setMinWidth(10);
        col2.setPrefWidth(166);

        gridPane.getColumnConstraints().addAll(col1, col2);

        // Add row constraints
        RowConstraints row1 = new RowConstraints();
        row1.setMaxHeight(235);
        row1.setMinHeight(10);
        row1.setPrefHeight(126);

        RowConstraints row2 = new RowConstraints();
        row2.setMaxHeight(302);
        row2.setMinHeight(10);
        row2.setPrefHeight(104);

        RowConstraints row3 = new RowConstraints();
        row3.setMaxHeight(375);
        row3.setMinHeight(10);
        row3.setPrefHeight(312);

        gridPane.getRowConstraints().addAll(row1, row2, row3);

        // Create labels, text fields, and button
        Label enterIdLabel = new Label("Enter ID:");
        enterIdLabel.setFont(new Font(18));
        enterIdLabel.setTextFill(javafx.scene.paint.Color.web("#26b558"));

         employeeIdTextField = new TextField();
        employeeIdTextField.setPrefHeight(39);
        employeeIdTextField.setPrefWidth(148);
        employeeIdTextField.setStyle("-fx-border-color: blue;");

        Label workScheduleLabel = new Label("Work Schedule");
        workScheduleLabel.setFont(new Font(24));
        workScheduleLabel.setTextFill(javafx.scene.paint.Color.web("#08d026"));
        GridPane.setColumnIndex(workScheduleLabel, 1);

        Label seeYourLabel = new Label("See your");
        seeYourLabel.setFont(new Font(24));
        seeYourLabel.setTextFill(javafx.scene.paint.Color.web("#16d766"));

        Button submitButton = new Button("Submit");
        submitButton.setFont(new Font("System Bold", 24));
        submitButton.setStyle("-fx-background-color: green;");
        submitButton.setTextFill(javafx.scene.paint.Color.web("#2126b5"));
        GridPane.setColumnIndex(submitButton, 1);
        GridPane.setRowIndex(submitButton, 2);
        GridPane.setValignment(submitButton, javafx.geometry.VPos.TOP);
        submitButton.setOnAction(
                event -> submitButtonHandler(role));

        // Add nodes to the grid pane
        gridPane.add(enterIdLabel, 0, 1);
        gridPane.add(employeeIdTextField, 1, 1);
        gridPane.add(workScheduleLabel, 1, 0);
        gridPane.add(seeYourLabel, 0, 0);
        gridPane.add(submitButton, 1, 2);

        root.setLeft(gridPane);
        BorderPane.setMargin(gridPane, new Insets(10));
        

        // Create the top HBox
        HBox topHBox = new HBox();
        topHBox.setStyle("-fx-background-color: brown;");
        topHBox.setPadding(new Insets(10));

        // Create the label for the top HBox
        Label titleLabel = new Label("Employee page to see their Work Schedule");
        titleLabel.setFont(new Font("System Bold", 36));
        titleLabel.setTextFill(javafx.scene.paint.Color.web("#214dd1"));
        topHBox.getChildren().add(titleLabel);

        // Set the top HBox as the root's top component
        root.setTop(topHBox);

        // Create the scene
        Scene scene = new Scene(root, 1000, 600);

        // Set the scene on the primary stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void submitButtonHandler(String role) {

        TableView<Object[]> tableView = new TableView<>();

        // Define table columns
        TableColumn<Object[], String>FirstNameColumn = new TableColumn<>("FirstName");
        FirstNameColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>((String) cellData.getValue()[0]));

        TableColumn<Object[], String>LastNamecoiomn = new TableColumn<>("LastName");
        LastNamecoiomn.setCellValueFactory(cellData -> new SimpleObjectProperty<>((String) cellData.getValue()[1]));

        TableColumn<Object[], String> workDateColumn = new TableColumn<>("work_date(LT)");
        workDateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>((String) cellData.getValue()[2]));

        TableColumn<Object[], Time> starttimeColumn = new TableColumn<>("start_time");
        starttimeColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>((Time) cellData.getValue()[3]));
        TableColumn<Object[], Time> endTime = new TableColumn<>("end_time(LT)");
        endTime.setCellValueFactory(cellData -> new SimpleObjectProperty<>((Time) cellData.getValue()[3]));


        // Add columns to the table view
        tableView.getColumns().addAll(FirstNameColumn, LastNamecoiomn, workDateColumn,starttimeColumn,endTime);

        retrieveDataFromDatabase( tableView, role);

        VBox root1 = new VBox(tableView);
        root.setCenter(root1);

    }
    private void retrieveDataFromDatabase(TableView<Object[]> tableView,String role) {


        try {

            int epId= Integer.parseInt(employeeIdTextField.getText());
            DataBaseManegement dataBaseManegement=new DataBaseManegement();
            Connection connection =dataBaseManegement.createConnection();
            // Create a statement
            Statement statement = connection.createStatement();


            String sqlQuery = "SELECT FirstName, LastName, work_date, start_time, end_time " +
                    "FROM employee E, workassignment W " +
                    "WHERE W.EmployeeID = " + epId + " AND E.EmployeeID = W.EmployeeID ";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
               if(resultSet.isBeforeFirst()){
            while (resultSet.next()) {
                Object[] row = new Object[5];
                row[0]  = resultSet.getString("FirstName");
                row[1]  = resultSet.getString("LastName");
                row[2]  = resultSet.getString("work_date");
                row[3]  = resultSet.getTime("start_time");
                row[4]  = resultSet.getTime("end_time");

                tableView.getItems().add(row);
            }
               }
               else{
                   showAlert("Not found","please enter your ID correctly");
               }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
}
      private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    }