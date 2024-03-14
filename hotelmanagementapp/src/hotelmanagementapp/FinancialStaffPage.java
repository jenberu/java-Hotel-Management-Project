package hotelmanagementapp;

import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.sql.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
public class FinancialStaffPage {

    private BorderPane mainPane;

DataBaseManegement dataBaseManegement;
    public void getFananicialStaffPage(Stage primaryStage) {

        mainPane = new BorderPane();
        HBox hbforTxt=new HBox();
        hbforTxt.setAlignment(Pos.CENTER);
        Text txt=new Text("financial management ");
        txt.setFont(Font.font("Arial", FontWeight.BOLD, 26));

       
        hbforTxt.getChildren().add(txt);
        hbforTxt.setStyle("-fx-background-color: lightgreen;");
        mainPane.setTop(hbforTxt);
        BorderPane.setMargin(hbforTxt, new Insets(10));
        
        
        mainPane.setStyle("-fx-background-color: lightblue;");

    



        primaryStage.setTitle("Reservation Management");



        VBox buttonsVBox = createButtonsVBox();
        mainPane.setLeft(buttonsVBox);

        Scene scene = new Scene(mainPane, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createButtonsVBox() {
        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(10));
        vBox.setStyle("-fx-background-color: pink;");
        Button seeReservationButton = new Button("Check Reservation");
        seeReservationButton.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");

        seeReservationButton.setOnAction(e ->
        { mainPane.setCenter(null);
            showSeeReservationPage();
        });




        Button seeBillingButton = new Button("See Billing Info");
        seeBillingButton.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");

        seeBillingButton.setOnAction(e ->{
            mainPane.setCenter(null);
         showSeeBillingPage();});

        Button addBillingButton = new Button("Add Billing Info");
        addBillingButton.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");

        addBillingButton.setOnAction(e -> showAddBillingPage());

        vBox.getChildren().addAll(
                seeReservationButton,
                
                
                seeBillingButton,
                addBillingButton
        );

        return vBox;
    }

    private void showSeeReservationPage() {
        mainPane.setCenter(null); // Clear previous content

        VBox pageContent = new VBox(10);
        pageContent.setPadding(new Insets(10));
        pageContent.setStyle("-fx-background-color: lightblue;");
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
        seeAllButton.setOnAction(event -> {
            checkReservation(0);
        });

        okButton.setOnAction(event -> {
            if(guestIdField.getText().isEmpty()){
                showAlert("Error","please enter guest id");
            }
            else {
            int id=Integer.parseInt(guestIdField.getText());
            if(id==0){
                showAlert("Error not Found","the id shuld not be Zero");
            }
            else {
                checkReservation(id);
            }
            }
        });
    }


    

    private void showSeeBillingPage() {
        mainPane.setCenter(null); // Clear previous content

        VBox pageContent = new VBox(10);
        pageContent.setStyle("-fx-background-color: lightblue;");
        pageContent.setPadding(new Insets(10));

        Label guestIdLabel = new Label("Guest ID:");
        guestIdLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill:black;");

        TextField guestIdField = new TextField();
        guestIdField.setMaxWidth(300);
        guestIdField.setPrefHeight(30);
        Button seeAllButton = new Button("See All");
        seeAllButton.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");

        Button seeBillButton = new Button("See");
        seeBillButton.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");

        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");

        pageContent.getChildren().addAll(
                seeAllButton,
                guestIdLabel,
                guestIdField,
                seeBillButton,
                cancelButton
        );
        cancelButton.setOnAction(e -> {
            mainPane.setCenter(null);
        });
        mainPane.setCenter(pageContent);
        seeBillButton.setOnAction(event -> {
            if(guestIdField.getText().isEmpty()){
                showAlert("Error empty field","please enter guest ID");
            }
            else {
                int guestid = Integer.parseInt(guestIdField.getText());
                seeAllORSingleBilling(guestid);
            }
        });

        seeAllButton.setOnAction(event -> {
            seeAllORSingleBilling(0);
        });

        
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

        Button okButton = new Button("Submit");
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
        okButton.setOnAction(event -> {
            if(guestIdField.getText().isEmpty()){
                showAlert("Error empty field","please enter guest ID");
            }
            else {
                int guestid = Integer.parseInt(guestIdField.getText());
                dataBaseManegement = new DataBaseManegement();
                dataBaseManegement.addBillingInfo(guestid);
            }
        });

    }
  private void   seeAllORSingleBilling(int value){
      TableView<Object[]> tableView = new TableView<>();

TableColumn<Object[], Integer> billingIdColumn = new TableColumn<>("Billing ID");
        billingIdColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>((Integer) cellData.getValue()[0]));

        TableColumn<Object[], String> guesFirstNameColumn = new TableColumn<>("First name");
        guesFirstNameColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>((String) cellData.getValue()[1]));

        TableColumn<Object[], String> guesLastNameColumn = new TableColumn<>("Last Name");
        guesLastNameColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>((String) cellData.getValue()[2]));

        TableColumn<Object[], Double> totalAmountColumn = new TableColumn<>("Total Amount");
        totalAmountColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>((Double) cellData.getValue()[3]));

        // Add columns to the table view
        tableView.getColumns().addAll(billingIdColumn, guesFirstNameColumn, guesLastNameColumn, totalAmountColumn);


      // Fetch data from the database and populate the table
      dataBaseManegement=new DataBaseManegement();
      dataBaseManegement.viwAllBilingInfo(tableView,value);

      VBox root = new VBox(tableView);
      mainPane.setCenter(root);


  }
  private void  checkReservation(int id){
      TableView<Object[]> tableView = new TableView<>();

      // Define table columns
      TableColumn<Object[], Integer> reservationIdColumn = new TableColumn<>("Reservation ID");
      reservationIdColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>((Integer) cellData.getValue()[0]));

      TableColumn<Object[], Integer> guestIdColumn = new TableColumn<>("Guest ID");
      guestIdColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>((Integer) cellData.getValue()[1]));

      TableColumn<Object[], Integer> roomIdColumn = new TableColumn<>("Room ID");
      roomIdColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>((Integer) cellData.getValue()[2]));

      TableColumn<Object[], String> checkInDateColumn = new TableColumn<>("Check-In Date");
      checkInDateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty((String) cellData.getValue()[3]));

      TableColumn<Object[], String> checkOutDateColumn = new TableColumn<>("Check-Out Date");
      checkOutDateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty((String) cellData.getValue()[4]));

      TableColumn<Object[], String> specialRequestsColumn = new TableColumn<>("Special Requests");
      specialRequestsColumn.setCellValueFactory(cellData -> new SimpleObjectProperty((String) cellData.getValue()[5]));

      // Add columns to the table view
      tableView.getColumns().addAll(reservationIdColumn, guestIdColumn, roomIdColumn,
              checkInDateColumn, checkOutDateColumn, specialRequestsColumn);

      // Fetch data from the database and populate the table
      fetchDataFromDatabase(tableView,id);

      VBox root = new VBox(tableView);
      mainPane.setCenter(root);
  }
    private void fetchDataFromDatabase(TableView<Object[]> tableView,int id) {
        dataBaseManegement=new DataBaseManegement();
        try {
            // Establish the database connection
            ResultSet resultSet=null;
            Connection connection = dataBaseManegement.createConnection();

            // Create a statement
            Statement statement = connection.createStatement();
         if(id==0) {
             // Execute the SQL query
             resultSet = statement.executeQuery("SELECT * FROM reservations ");
         }
         else {
             resultSet = statement.executeQuery("SELECT * FROM reservations where GuestID="+id);
         }

         if(resultSet.equals(0)){
             showAlert("NOT Found","There is no such recored");
         }

             // Populate the table view with data from the result set
             while (resultSet.next()) {
                 Object[] row = new Object[6];
                 row[0] = resultSet.getInt("ReservationID");
                 row[1] = resultSet.getInt("GuestID");
                 row[2] = resultSet.getInt("RoomID");
                 row[3] = resultSet.getString("CheckInDate");
                 row[4] = resultSet.getString("CheckOutDate");
                 row[5] = resultSet.getString("SpecialRequests");

                 tableView.getItems().add(row);


         }
            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
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
