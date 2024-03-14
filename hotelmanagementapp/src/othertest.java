/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author OHlala
 */
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class othertest extends Application {

    private BorderPane mainPane;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Reservation Management");

        mainPane = new BorderPane();

        VBox buttonsVBox = createButtonsVBox();
        mainPane.setLeft(buttonsVBox);

        Scene scene = new Scene(mainPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createButtonsVBox() {
        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(10));
        vBox.setStyle("-fx-background-color: pink;");
        Button seeReservationButton = new Button("See Reservation");
        seeReservationButton.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");

        seeReservationButton.setOnAction(e -> showSeeReservationPage());

        Button seeTransactionButton = new Button("See Transaction Detail");
        seeTransactionButton.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");

        seeTransactionButton.setOnAction(e -> showSeeTransactionPage());

        Button updateTransactionButton = new Button("Update Transaction");
        updateTransactionButton.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");

        updateTransactionButton.setOnAction(e -> showUpdateTransactionPage());

        Button seeBillingButton = new Button("See Billing Info");
        seeBillingButton.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");

        seeBillingButton.setOnAction(e -> showSeeBillingPage());

        Button addBillingButton = new Button("Add Billing Info");
        addBillingButton.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");

        addBillingButton.setOnAction(e -> showAddBillingPage());

        vBox.getChildren().addAll(
                seeReservationButton,
                seeTransactionButton,
                updateTransactionButton,
                seeBillingButton,
                addBillingButton
        );

        return vBox;
    }

    private void showSeeReservationPage() {
        mainPane.setCenter(null); // Clear previous content

        VBox pageContent = new VBox(10);
        pageContent.setPadding(new Insets(10));
        pageContent.setStyle("-fx-background-color: yellow;");
        Label guestIdLabel = new Label("Guest ID:");
       guestIdLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill:black;");

        TextField guestIdField = new TextField();
guestIdField.setMaxWidth(300);
guestIdField.setPrefHeight(30);
        Button seeAllButton = new Button("See All");
        seeAllButton.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");
        Button okButton = new Button("OK");
        okButton.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");
        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");

        pageContent.getChildren().addAll(
                seeAllButton,
                guestIdLabel,
                guestIdField,
                okButton,
                cancelButton
        );

        mainPane.setCenter(pageContent);
        cancelButton.setOnAction(e -> {
            mainPane.setCenter(null);
        });
    }

    private void showSeeTransactionPage() {

        mainPane.setCenter(null); // Clear previous content

        VBox pageContent = new VBox(10);
        pageContent.setPadding(new Insets(10));
        pageContent.setStyle("-fx-background-color: blue;");
        Label guestIdLabel = new Label("Guest ID:");
           guestIdLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill:black;");

        TextField guestIdField = new TextField();
guestIdField.setMaxWidth(300);
guestIdField.setPrefHeight(30);
        Button seeAllButton = new Button("See All");
        seeAllButton.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");

        Button okButton = new Button("OK");
        okButton.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");

        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");

        pageContent.getChildren().addAll(
                seeAllButton,
                guestIdLabel,
                guestIdField,
                okButton,
                cancelButton
        );
        cancelButton.setOnAction(e -> {
            mainPane.setCenter(null);
        });
        mainPane.setCenter(pageContent);
        // Similar logic as showSeeReservationPage()
    }

    private void showUpdateTransactionPage() {
        // Similar logic as showSeeReservationPage()
    }

    private void showSeeBillingPage() {
        mainPane.setCenter(null); // Clear previous content

        VBox pageContent = new VBox(10);
        pageContent.setStyle("-fx-background-color: green;");
        pageContent.setPadding(new Insets(10));

        Label guestIdLabel = new Label("Guest ID:");
         guestIdLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill:black;");

        TextField guestIdField = new TextField();
guestIdField.setMaxWidth(300);
guestIdField.setPrefHeight(30);
        Button seeAllButton = new Button("See All");
        seeAllButton.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");

        Button okButton = new Button("OK");
        okButton.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");

        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");

        pageContent.getChildren().addAll(
                seeAllButton,
                guestIdLabel,
                guestIdField,
                okButton,
                cancelButton
        );
        cancelButton.setOnAction(e -> {
            mainPane.setCenter(null);
        });
        mainPane.setCenter(pageContent);
        // Similar logic as showSeeReservationPage()
    }

    private void showAddBillingPage() {
        mainPane.setCenter(null); // Clear previous content

        VBox pageContent = new VBox(10);
        pageContent.setPadding(new Insets(10));
        pageContent.setStyle("-fx-background-color: lightblue;");
        Label guestIdLabel = new Label("Guest ID:");
          guestIdLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill:black;");

        TextField guestIdField = new TextField();
guestIdField.setMaxWidth(300);
guestIdField.setPrefHeight(30);

        Button okButton = new Button("OK");
        okButton.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");

        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");

        pageContent.getChildren().addAll(
                guestIdLabel,
                guestIdField,
                okButton,
                cancelButton
        );
        cancelButton.setOnAction(e -> {
            mainPane.setCenter(null);
        });
        mainPane.setCenter(pageContent);
        // Similar logic as showSeeReservationPage()
    }

    public static void main(String[] args) {
        launch(args);
    }
}
