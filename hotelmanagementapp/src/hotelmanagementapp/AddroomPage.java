
package hotelmanagementapp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.*;

public class AddroomPage {



    Button cancelBtn,addroom,deleteroom,viewRservedRoom;
FlowPane foeBotton=new FlowPane();
    TextField rmtf = new TextField();
    TextField rttf = new TextField();
    TextField fmtf = new TextField();
    TextField cfn = new TextField();
    GridPane gridPane;
    public VBox getAddRoomPage() {



         gridPane = new GridPane();
        gridPane.setId("gr");
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(5));

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setFillWidth(false);
        column1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        column1.setMinWidth(10);
        column1.setPrefWidth(100);

        ColumnConstraints column2 = new ColumnConstraints();
        column2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        column2.setMinWidth(10);
        column2.setPrefWidth(100);

        RowConstraints row1 = new RowConstraints();
        row1.setMinHeight(0);
        row1.setPrefHeight(10);
        row1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        RowConstraints row2 = new RowConstraints();
        row2.setMinHeight(0);
        row2.setPrefHeight(10);
        row2.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        RowConstraints row3 = new RowConstraints();
        row3.setMinHeight(10);
        row3.setPrefHeight(10);
        row3.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        RowConstraints row4 = new RowConstraints();
        row4.setMinHeight(10);
        row4.setPrefHeight(10);
        row4.setVgrow(javafx.scene.layout.Priority.SOMETIMES);



        Label rmlbl = new Label("Room Number:");
        rmlbl.setFont(new Font(20));
        rmlbl.setTextFill(javafx.scene.paint.Color.web("#131886"));
        GridPane.setColumnIndex(rmlbl, 0);
        GridPane.setRowIndex(rmlbl, 0);
        Label rtlbl = new Label("Room Type:");
        rtlbl.setFont(new Font(20));
        rtlbl.setTextFill(javafx.scene.paint.Color.web("#191790"));

       GridPane.setColumnIndex(rtlbl, 0);
      GridPane.setRowIndex(rtlbl, 1);
        GridPane.setMargin(rtlbl, new Insets(5));

        Label fnlbl = new Label("Floor Number:");
        fnlbl.setFont(new Font(20));
        fnlbl.setTextFill(javafx.scene.paint.Color.web("#2a24b5"));
        GridPane.setColumnIndex(rmlbl, 0);
        GridPane.setRowIndex(fnlbl, 2);
        GridPane.setMargin(fnlbl, new Insets(5));

        Label lblCapacity = new Label("Capacity:");
        lblCapacity.setFont(new Font(20));
        lblCapacity.setTextFill(javafx.scene.paint.Color.web("#0f288a"));
        GridPane.setColumnIndex(rmlbl, 0);
        GridPane.setRowIndex(lblCapacity, 3);
        GridPane.setMargin(lblCapacity, new Insets(5));


        rmtf.setMaxHeight(50);
        rmtf.setMaxWidth(200);
        rmtf.setStyle("-fx-border-color: blue;");
        GridPane.setColumnIndex(rmtf, 1);
        GridPane.setMargin(rmtf, new Insets(5));


        rttf.setMaxHeight(50);
        rttf.setMaxWidth(200);
        rttf.setStyle("-fx-border-color: blue;");
        GridPane.setColumnIndex(rttf, 1);
        GridPane.setRowIndex(rttf, 1);
        GridPane.setMargin(rttf, new Insets(5));
        fmtf.setMaxHeight(50);
        fmtf.setMaxWidth(200);
        fmtf.setStyle("-fx-border-color: blue;");
        GridPane.setColumnIndex(fmtf, 1);
        GridPane.setRowIndex(fmtf, 2);
        GridPane.setMargin(fmtf, new Insets(5));


        cfn.setMaxHeight(50);
        cfn.setMaxWidth(200);
        cfn.setStyle("-fx-border-color: blue;");
        GridPane.setColumnIndex(cfn, 1);
        GridPane.setRowIndex(cfn, 3);
        GridPane.setMargin(cfn, new Insets(5));
        GridPane.setColumnSpan(foeBotton,2);
        GridPane.setColumnIndex(foeBotton, 0);
        GridPane.setRowIndex(foeBotton, 5);
        GridPane.setMargin(foeBotton, new Insets(5));

        Button cancelBtn = new Button("Cancel");
             cancelBtn.setStyle("-fx-font-size: 18px;");

        cancelBtn.setStyle("-fx-border-color: blue;");
        cancelBtn.setOnAction(event -> handleCancelButton());


        Button submitBtn = new Button("Submit");
        submitBtn.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");

       
        submitBtn.setOnAction(event -> handleSubmitButton());



        gridPane.getChildren().addAll(rmlbl, rtlbl, fnlbl, lblCapacity, rmtf, rttf, fmtf, cfn);
        gridPane.setHgap(10);
VBox vb=new VBox();
vb.getChildren().add(gridPane);


        return vb;



    }

    public void handleCancelButton() {
        // Handle cancel button action
    }

    public void handleSubmitButton() {
        if(fieldsAreValid())
        {

            String roomNumber = rmtf.getText();
            String roomtype = rttf.getText();
            int florNumber = Integer.parseInt(fmtf.getText());
            String capacity = cfn.getText();
            DataBaseManegement databaseHelper = new DataBaseManegement();
            databaseHelper.addRoom(roomNumber, florNumber, roomtype, capacity);
        }
     
    }
    private boolean fieldsAreValid() {
        // You can implement validation rules here for each field

        String roomNumber = rmtf.getText();
        String roontype = rttf.getText();
        String floornumber = fmtf.getText();
        String capacity = cfn.getText();



        if (roomNumber.isEmpty()||! roomNumber.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Please enter a valid room Number.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }


        if (roontype.isEmpty()||!roontype.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(null, "Please enter a valid room type.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }


        if (floornumber.isEmpty()||!floornumber.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Please enter a valid floor number.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }


        if (capacity.isEmpty() || !capacity.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Please enter a valid capacity  .", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }



        return true;
    }



    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
