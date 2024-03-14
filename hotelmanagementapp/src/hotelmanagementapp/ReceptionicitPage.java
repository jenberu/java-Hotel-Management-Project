
package hotelmanagementapp;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import javafx.beans.binding.Bindings;


public class ReceptionicitPage {
    private DataBaseManegement dataBaseManegement;
    private ImageView image;
    BorderPane bpane;
    Button viewAvalableRoom,  manageguest, viewReservedRoom;

    public void getReceptionicitPage(Stage stage) {
        bpane = new BorderPane();
        FlowPane flowPane = new FlowPane();
        // BorderPane mainBorder = new BorderPane();
        BorderPane.setMargin(bpane, new Insets(10));
        flowPane.setStyle("-fx-background-color: blue;");
        flowPane.setPrefHeight(50);
        Text text = new Text("WELL COME");
        text.setStyle("-fx-font-weight: bold;-fx-font-size: 20px;");
        flowPane.getChildren().add(text);
        flowPane.setAlignment(Pos.CENTER);
        bpane.setTop(flowPane);

        viewAvalableRoom = new Button("View Avaliable Room");
        viewAvalableRoom.setStyle("-fx-background-color: lightgreen; -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");
       
        manageguest = new Button("Manage Guest");
        manageguest.setStyle("-fx-background-color: lightgreen; -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");


        viewR
                eservedRoom = new Button("view Reserved Room");
        viewReservedRoom.setStyle(" -fx-background-color: lightgreen;-fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");


        VBox hb = new VBox();
        
        hb.setSpacing(10);
        //hb.setAlignment(Pos.CENTER);

        Pane bottomPane = new Pane();
        bottomPane.setStyle("-fx-background-color: pink;");
        StackPane stack = new StackPane();
        stack.getChildren().addAll(bottomPane, hb);
        bpane.setLeft(stack);
        //bpane.setTop(bottomPane);
        hb.setPadding(new Insets(10));
        hb.getChildren().addAll(viewAvalableRoom, viewReservedRoom, manageguest);
        image = new ImageView("hotelmanagementapp/reception.jpg");

        image.fitWidthProperty().bind(Bindings.selectDouble(bpane.sceneProperty(), "width"));
        image.fitHeightProperty().bind(Bindings.selectDouble(bpane.sceneProperty(), "height"));

        bpane.setCenter(image);
        BorderPane.setMargin(image, new Insets(0, 30, 10, 0));
        // mainBorder.setCenter(bpane);
        Scene scene = new Scene(bpane, 1000, 600);
        stage.setScene(scene);
        stage.setTitle("Well Come dear ");
        stage.show();

        stage.iconifiedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                adjustLayoutForMinimized();

            } else {
                adjustLayoutForRestored();

            }
        });


     
        viewAvalableRoom.setOnAction(event -> {
            dataBaseManegement = new DataBaseManegement();
            VBox vBox = dataBaseManegement.getReservedRoom(stage, "not reserved", "recip");
            bpane.setCenter(vBox);

        });

        viewReservedRoom.setOnAction(event -> {
            dataBaseManegement = new DataBaseManegement();
            VBox vBox = dataBaseManegement.getReservedRoom(stage, "reserved", "recip");
            bpane.setCenter(vBox);
        });


        manageguest.setOnAction(e -> {
            RegistrationPage getRegitrationPage = new RegistrationPage();
            getRegitrationPage.getRegistrationPage(stage);

        });


    }


    private void adjustLayoutForMinimized() {
    image.fitWidthProperty().bind(Bindings.selectDouble(bpane.sceneProperty(), "width"));
image.fitHeightProperty().bind(Bindings.selectDouble(bpane.sceneProperty(), "height"));

 image.fitWidthProperty().unbind();
    image.fitHeightProperty().unbind();

    image.setPreserveRatio(true);
    image.setFitWidth(600);
    image.setFitHeight(400);
    }

    private void adjustLayoutForRestored() {

        image.setPreserveRatio(false);
        image.setFitWidth(image.getParent().getLayoutBounds().getWidth());
        image.setFitHeight(image.getParent().getLayoutBounds().getHeight());
    }
}
