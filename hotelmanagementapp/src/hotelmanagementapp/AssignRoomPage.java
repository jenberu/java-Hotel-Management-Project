package hotelmanagementapp;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Timer;
import java.util.TimerTask;


import javafx.stage.Stage;

import java.sql.*;

public class AssignRoomPage {

    private TextField guestIdField;
    private TextField roomIdField;
    private TextField checkInField;
    private TextField checkOutField;
    private TextField specialRequestField;
    private Label messageLabel;
    BorderPane border=new BorderPane();
 ImageView roomimage;
 
    public void getRoomAssinPage(Stage primaryStage) {
       ImageView backbtnimg = new ImageView("hotelmanagementapp/download.png");
         roomimage = new ImageView("hotelmanagementapp/room.jpg");
        roomimage.setFitWidth(800);
        roomimage.setFitHeight(600);
        backbtnimg.setFitWidth(50);
        backbtnimg.setFitHeight(50);
        border.setTop(backbtnimg);
        BorderPane.setAlignment(backbtnimg, Pos.TOP_LEFT);
        
        
        
        backbtnimg.setOnMouseClicked(event -> {
            RegistrationPage getRegistrationPage = new RegistrationPage();
            getRegistrationPage.getRegistrationPage(primaryStage);

        });
        
        


        GridPane gridPane = createGridPane();
        createFormFields(gridPane);
        Button assignButton = createAssignButton();
        Button cancelButton = createClearlButton();
        VBox vbox = new VBox(10);
         HBox hbox = new HBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(gridPane, hbox);
         
     
         hbox.setPadding(new Insets(10));
         hbox.getChildren().addAll(assignButton,cancelButton);

        
         
        messageLabel = new Label();
        vbox.getChildren().add(messageLabel);

        assignButton.setOnAction(e -> assignButtonClicked());
        cancelButton.setOnAction(e -> clearButtonClicked());
        StackPane stackPane=new StackPane();
        stackPane.getChildren().addAll(roomimage,vbox);
        border.setCenter(stackPane);
        border.setPadding(new Insets(20,20,20,20));
        Scene scene = new Scene(border, 1000, 600);
        primaryStage.setTitle("Guest Assignment");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.iconifiedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                adjustLayoutForMinimized();

            } else {
                adjustLayoutForRestored();

            }
        });
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        return gridPane;
    }

    private void createFormFields(GridPane gridPane) {
        Label guestIdLabel = new Label("Guest ID:");
        guestIdLabel.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 24");
        guestIdField = new TextField();
        guestIdField.setMaxHeight(30);
        gridPane.add(guestIdLabel, 0, 0);
        gridPane.add(guestIdField, 1, 0);

        Label roomIdLabel = new Label("Room ID:");
        roomIdLabel.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 24");

        roomIdField = new TextField();
        roomIdField.setMaxHeight(30);
        gridPane.add(roomIdLabel, 0, 1);
        gridPane.add(roomIdField, 1, 1);

        Label checkInLabel = new Label("Check-in Date:");
        checkInLabel.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 24");

        checkInField = new TextField();
        checkInField.setMaxHeight(30);

        gridPane.add(checkInLabel, 0, 2);
        gridPane.add(checkInField, 1, 2);

        Label checkOutLabel = new Label("Check-out Date:");
        checkOutLabel.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 24");


        checkOutField = new TextField();
        checkOutField.setMaxHeight(30);

        gridPane.add(checkOutLabel, 0, 3);
        gridPane.add(checkOutField, 1, 3);

        Label specialRequestLabel = new Label("Special Request:");
        specialRequestLabel.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 24");

        specialRequestField = new TextField();
        specialRequestField.setMaxHeight(30);

        gridPane.add(specialRequestLabel, 0, 4);
        gridPane.add(specialRequestField, 1, 4);
    }

    private Button createAssignButton() {
        Button assignButton = new Button("Assign");
        assignButton.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 24");

        GridPane.setConstraints(assignButton, 0, 5);
        return assignButton;
    }

    private Button createClearlButton() {
        Button cancelButton = new Button("Cleare");
        GridPane.setConstraints(cancelButton, 1, 5);
        cancelButton.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 24");

        return cancelButton;
    }

    private void assignButtonClicked() {
    if (fieldsAreValid()) {
       deleteRecords();
       // int reservationId = Integer.parseInt(reservationIdField.getText());
        int guestId = Integer.parseInt(guestIdField.getText());
        int roomId = Integer.parseInt(roomIdField.getText());
        String checkInDate = checkInField.getText();
        String checkOutDate = checkOutField.getText();
        String specialRequests = specialRequestField.getText();
           DataBaseManegement dataBaseManegement=new DataBaseManegement();
            dataBaseManegement.insertReservation( guestId, roomId, checkInDate, checkOutDate, specialRequests);

    } else
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please fill all fields correctly.");
        alert.showAndWait();
    }
}

    private void clearButtonClicked() {
        guestIdField.clear();
        roomIdField.clear();
        checkInField.clear();
        checkOutField.clear();
        specialRequestField.clear();
        messageLabel.setText("");
    }

    private boolean fieldsAreValid() {
        // You can implement validation rules here for each field
        return !guestIdField.getText().isEmpty()
                && !roomIdField.getText().isEmpty()
                && !checkInField.getText().isEmpty()
                && !checkOutField.getText().isEmpty()
                && !specialRequestField.getText().isEmpty();
    }
    
  
public void deleteRecords() {
    DataBaseManegement dataBaseManegement = new DataBaseManegement();
    LocalDate currentDate = LocalDate.now();

    try {
        Connection connection = dataBaseManegement.createConnection();
        String sqlSelect = "SELECT reservations.RoomID FROM reservations WHERE reservations.CheckOutDate < ?";
        PreparedStatement selectStatement = connection.prepareStatement(sqlSelect);
        selectStatement.setDate(1, java.sql.Date.valueOf(currentDate));

        ResultSet resultSet = selectStatement.executeQuery();
        while (resultSet.next()) {
            int roomId = resultSet.getInt("RoomID");

            // Update the room availability in the "room" table
            String sqlUpdate = "UPDATE rooms SET AvailabilityStatus = ? WHERE RoomID = ?";
            PreparedStatement updateStatement = connection.prepareStatement(sqlUpdate);
            updateStatement.setString(1, "not reserved"); 
            updateStatement.setInt(2, roomId);
            updateStatement.executeUpdate();
            updateStatement.close();
        }

        // Delete the reservations
        String sqlDelete = "DELETE FROM reservations WHERE CheckOutDate < ?";
        PreparedStatement deleteStatement = connection.prepareStatement(sqlDelete);
        deleteStatement.setDate(1, java.sql.Date.valueOf(currentDate));
        int rowsDeleted = deleteStatement.executeUpdate();

        System.out.println(rowsDeleted + " record(s) deleted.");

        connection.close();
        resultSet.close();
        selectStatement.close();
        deleteStatement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

   private void adjustLayoutForMinimized() {

        roomimage.setPreserveRatio(true);
        roomimage.setFitWidth(600);
        roomimage.setFitHeight(400);
    }

    private void adjustLayoutForRestored() {

        roomimage.setPreserveRatio(false);
        roomimage.setFitWidth(roomimage.getParent().getLayoutBounds().getWidth());
        roomimage.setFitHeight(roomimage.getParent().getLayoutBounds().getHeight());
    }
   
}