package hotelmanagementapp;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.SimpleObjectProperty;
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
import java.util.List;
import javafx.scene.shape.Rectangle;

public class AdimiPage {

    DataBaseManegement dataBaseManegement = new DataBaseManegement();
    FlowPane pane = new FlowPane();

    VBox billingtogle = new VBox();

    BorderPane bpane = new BorderPane();

    Stage stage;
    Button manageroom = new Button("Manage Room");
    Button managework = new Button("Manage Work");
    Button managestaff = new Button("Manage Staff");
    Button managebill = new Button("Manage Billing");
    Button vieStaff, deletStaff, viwroom, viewBill, clearbtn, addbtn;

    Text txt;
    Button cancelBtn, addroom, deleteroom, viewRservedRoom;
    FlowPane fpforStaff = new FlowPane();
    // VBox vbforManageStaff=new VBox();
    FlowPane fbforRoom = new FlowPane();
    VBox center = new VBox();
    //VBox left = new VBox();
    VBox ritght = new VBox();
    VBox vBox;

    ;
    public void getAdimiPage(Stage stage) {
        ;

        center.setStyle("-fx-background-color: lightgreen;");
        ritght.setStyle("-fx-background-color: brwon;");
        bpane.setCenter(center);
        bpane.setRight(ritght);

        viwroom = new Button("Refresh");
        addroom = new Button("Add ");
        deleteroom = new Button("Delete");
        viewRservedRoom = new Button("view Reserved\n Room ");
        cancelBtn = new Button("Clear");
        deleteroom.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;");
        viewRservedRoom.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;");
        cancelBtn.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;");
        addroom.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;");
        fbforRoom.setHgap(10);
        fbforRoom.setVgap(10);
        fbforRoom.getChildren().addAll(addroom, deleteroom, viwroom, viewRservedRoom);

        fbforRoom.setHgap(5);

        addbtn = new Button("Add ");
        clearbtn = new Button("Clear");
//        UpdateStaff = new Button("Update Staff");
        deletStaff = new Button("Delet");
        vieStaff = new Button("  Staff info");
        viewBill = new Button("Billing info");
        clearbtn = new Button("Clear");

        fpforStaff.setAlignment(Pos.CENTER);
        fpforStaff.setHgap(10);
        fpforStaff.setVgap(10);
        fpforStaff.setPadding(new Insets(20));
        fpforStaff.getChildren().addAll(addbtn, clearbtn, vieStaff);
        FlowPane flowPane = new FlowPane();
        flowPane.getChildren().addAll( deletStaff);
        flowPane.setHgap(10);
        flowPane.setAlignment(Pos.CENTER);
        vBox = new VBox(fpforStaff, flowPane);
        vBox.setSpacing(10);

        setlayout();

        billingtogle.getChildren().add(viewBill);

        billingtogle.setStyle("-fx-background-color: bluelght;");

        pane.setStyle("-fx-background-color: purple;");
        BorderPane.setMargin(pane, new Insets(10));
        pane.getChildren().addAll(manageroom, managestaff, managework, managebill);
        bpane.setTop(pane);
        pane.setPadding(new Insets(10));
        // pane.setStyle("-fx-background-color: brwon;");
        pane.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        manageroom.getStyleClass().add("clicked-botton");
        managestaff.getStyleClass().add("clicked-botton");
        managework.getStyleClass().add("clicked-botton");
        managebill.getStyleClass().add("clicked-botton");

        manageroom.setOnAction(event -> {
            bpane.setCenter(null);
            getmanageRoom();

        });

        managestaff.setOnAction(event -> {
            bpane.setCenter(null);
            getmanageStaff();
        });
        managebill.setOnAction(event -> {

            bpane.setCenter(null);
            bpane.setLeft(billingtogle);
            billingtogle.setVisible(!billingtogle.isVisible()); // Toggle visibility

            viewBill.setOnAction(e -> {
                seeAllORSingleBilling();
            });
        });
        managework.setOnAction(event -> {
            bpane.setCenter(null);
            getManageWork();
        });

        StackPane stackPane = new StackPane();
        stackPane.setPadding(new Insets(10, 10, 10, 10));
        stackPane.setStyle("-fx-background-color: lightgreen;");
        stackPane.getChildren().add(bpane);
        stackPane.setPrefSize(1300, 700);
        Scene scene = new Scene(stackPane, 1000, 600);

        stage.setScene(scene);

        stage.setTitle("Admin Page");
        stage.show();

    }

    private void getmanageRoom() {

        AddroomPage room = new AddroomPage();
        VBox gridPane = room.getAddRoomPage();

        VBox left = new VBox();
        left.setStyle("-fx-background-color: lightblue;");
        left.getChildren().addAll(gridPane, fbforRoom);
        bpane.setLeft(left);
        addroom.setOnAction(event -> {
            room.handleSubmitButton();
        }
        );

        viwroom.setOnAction(e -> {
            AdminDatabase databse = new AdminDatabase();
            VBox vb2 = databse.checkReservation();
            BorderPane.setMargin(vb2, new Insets(0, 10, 0, 0));
            bpane.setCenter(vb2);
        });

        deleteroom.setOnAction(e -> {
            getDeletRoom();
        });

        viewRservedRoom.setOnAction(e -> {
            dataBaseManegement = new DataBaseManegement();
            VBox vbox = dataBaseManegement.getReservedRoom(stage, "reserved", "admin");
            bpane.setCenter(vbox);
        });
    }

    private void getmanageStaff() {

        AddStaffPage staff = new AddStaffPage();
        VBox forMagestaff = staff.getStaffPage(stage);

        VBox left = new VBox();
        left.setStyle("-fx-background-color: lightblue;");
        left.getChildren().addAll(forMagestaff, vBox);

        bpane.setLeft(left);
        addbtn.setOnAction(event -> {
            if (staff.fieldsAreValid()) {
                staff.addStaff();
                showAlert("succes", "data succefully inserted");
            }
        });
        clearbtn.setOnAction(event -> {

            staff.clearFields();
        });
        vieStaff.setOnAction(e -> {
            vieStaff.setText("refresh");

            dataBaseManegement = new DataBaseManegement();
            List<Employee> employees = dataBaseManegement.getAllEmployees();
            String identity = "admin";
            EmployeeInfoPage customerInfoPage = new EmployeeInfoPage(employees);

            VBox center = customerInfoPage.getUI(stage, identity);

            bpane.setCenter(center);

        });

        deletStaff.setOnAction(event -> {
            getDeletStaff();
        });

    }

    private void setlayout() {

        addbtn.setMinSize(20, 10);
        addbtn.setPrefWidth(100); // Set the preferred width
        addbtn.setStyle("-fx-background-color: green; -fx-text-fill: black;-fx-font-size: 20px;-fx-font-weight: bold;");

        clearbtn.setStyle("-fx-background-color: green; -fx-text-fill: black;-fx-font-size: 20px;-fx-font-weight: bold;");
        clearbtn.setPrefWidth(100); // Set the preferred width
        deletStaff.setMinSize(20, 10);
        deletStaff.setStyle("-fx-background-color: green; -fx-text-fill: black;-fx-font-size: 20px;-fx-font-weight: bold;");
        vieStaff.setMinSize(20, 10);
        vieStaff.setStyle("-fx-background-color: green; -fx-text-fill: black;-fx-font-size: 20px;-fx-font-weight: bold;");
//        UpdateStaff.setMinSize(20, 10);
//        UpdateStaff.setStyle("-fx-background-color: green; -fx-text-fill: black;-fx-font-size: 20px;-fx-font-weight: bold;");

        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTBLUE, null, null);
        Background background = new Background(backgroundFill);

        manageroom.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");
        managework.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");
        manageroom.getStyleClass().add("clicked-button");

        managestaff.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");
        managebill.setStyle(" -fx-background-color: green; -fx-font-weight: bold;-fx-font-size: 20px;");

        deleteroom.setStyle(" -fx-background-color: green;-fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");
        addroom.setStyle("-fx-background-color: green; -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");
        viwroom.setStyle("-fx-background-color: green; -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");

        viewRservedRoom.setStyle(" -fx-background-color: green;-fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");
        viewBill.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");

        billingtogle.setVisible(false);
        billingtogle.setPadding(new Insets(10));
        billingtogle.setAlignment(Pos.CENTER);
        billingtogle.setSpacing(20);

        pane.setPadding(new Insets(20));
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(20);
        pane.setVgap(20);

    }

    private void getDeletRoom() {
        GridPane gpane = new GridPane();
        gpane.setStyle("-fx-background-color: lightblue;");
        Label rnlbl = new Label("Enter Room Id");
        rnlbl.setStyle("-fx-background-color: pink; -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20");
        TextField rtxtf = new TextField();
        Button dletrbtn = new Button("Delete");
        dletrbtn.setStyle(" -fx-text-fill: red; -fx-font-weight: bold;-fx-font-size: 20px;");
        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");
        gpane.add(rnlbl, 0, 0);
        gpane.add(rtxtf, 1, 0);
        gpane.add(dletrbtn, 1, 2);
        gpane.add(cancelButton, 2, 2);
        gpane.setAlignment(Pos.CENTER);
        bpane.setCenter(gpane);
        gpane.setHgap(10);
        gpane.setVgap(10);
        dletrbtn.setOnAction(event -> {
            if (rtxtf.getText().isEmpty()) {
                showAlert("Error empty ", "field is empty");
            } else {
                int id = Integer.parseInt(rtxtf.getText());
                dataBaseManegement = new DataBaseManegement();
                int affectedRow = dataBaseManegement.deletRoom(id);
                if (affectedRow > 0) {
                    showAlert("Successs ", "Deleted successfully");
                } else {
                    showAlert("Not Found ", "there is no Such Room");
                }
            }
        });
        cancelButton.setOnAction(e -> {

            bpane.setCenter(null);
            bpane.setBottom(null);

        });
    }

    private void getRoomSearch() {
        GridPane gpane = new GridPane();

        gpane.setStyle("-fx-background-color: lightblue;");
        Label rnlbl = new Label("Enter Room Number");
        rnlbl.setStyle("-fx-background-color: pink; -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20");
        TextField rtxtf = new TextField();
        Button Search = new Button("Search");

        Search.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");

        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");

        gpane.add(rnlbl, 0, 0);
        gpane.add(rtxtf, 1, 0);
        gpane.add(Search, 1, 2);
        gpane.add(cancelButton, 2, 2);
        gpane.setAlignment(Pos.CENTER);
        bpane.setCenter(gpane);
        gpane.setHgap(10);
        gpane.setVgap(10);
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(30));
        vbox.setStyle("-fx-background-color:pink;");
        Text txt = new Text("RoomID\t\t RoomNumber\t\tFloorNumber\t\tRoomType\t\tAvailabilityStatus");
        Label label = new Label();
        vbox.getChildren().addAll(txt, label);

        Search.setOnAction(e -> {
            int roomId = 0;
            if (rtxtf.getText().isEmpty()) {
                showAlert("Search Failed", "empty field.");
            } else {
                roomId = Integer.parseInt(rtxtf.getText());

                DataBaseManegement dataBaseManegement = new DataBaseManegement();

                try {

                    ResultSet resultSet = dataBaseManegement.viewRoom(roomId);
                    if (!resultSet.next()) {
                        showAlert("Search Failed", "room id is Empty.");

                    } else {
//                  while (resultSet.next()) {
                        label.setText(resultSet.getInt(1) + "\t\t\t\t" + resultSet.getString("RoomNumber") + "\t\t\t\t" + resultSet.getString("FloorNumber")
                                + "\t\t\t\t"
                                + resultSet.getString("RoomTypeName") + "\t\t\t" + resultSet.getString("AvailabilityStatus"));
                        bpane.setCenter(vbox);
//                  }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println("Error selecting room: " + ex.getMessage());
                }

            }
        });
        cancelButton.setOnAction(e -> {

            bpane.setCenter(null);
            bpane.setBottom(null);

        });

    }

    public void getDeletStaff() {
        GridPane gpane = new GridPane();
        gpane.setStyle("-fx-background-color: lightgreen;");
        Label rnlbl = new Label("Enter Staff Id");
        rnlbl.setStyle("-fx-background-color: pink; -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20");
        TextField rtxtf = new TextField();
        Button dletrbtn = new Button("Delete");
        dletrbtn.setStyle(" -fx-text-fill: red; -fx-font-weight: bold;-fx-font-size: 20px;");
        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");
        gpane.add(rnlbl, 0, 0);
        gpane.add(rtxtf, 1, 0);
        gpane.add(dletrbtn, 1, 2);
        gpane.add(cancelButton, 2, 2);
        gpane.setAlignment(Pos.CENTER);
        bpane.setCenter(gpane);
        gpane.setHgap(10);
        gpane.setVgap(10);
        dletrbtn.setOnAction(event -> {
            if (rtxtf.getText().isEmpty()) {
                showAlert("Error empty ", "field is empty");
            } else {
                int id = Integer.parseInt(rtxtf.getText());
                dataBaseManegement = new DataBaseManegement();
                dataBaseManegement.deletStaff(id);
                showAlert("Successs ", "Deleted successfully");
                bpane.setCenter(null);
            }
        });
        cancelButton.setOnAction(e -> {

            bpane.setCenter(null);
            bpane.setBottom(null);

        });
    }

    private void seeAllORSingleBilling() {
        TableView<Object[]> tableView = new TableView<>();

        // Define table columns
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
        dataBaseManegement = new DataBaseManegement();
        dataBaseManegement.viwAllBilingInfo(tableView, 0);

        VBox root = new VBox(tableView);
        bpane.setCenter(root);

    }

    private void getManageWork() {
        WorkManagePage workManagePage = new WorkManagePage();
        GridPane gridPane = workManagePage.getWorkManagePage();
        Button deletSchedule = new Button("DropSchedule");
        deletSchedule.setMnemonicParsing(false);
        deletSchedule.setPrefHeight(50.0);
        deletSchedule.setPrefWidth(150.0);
        deletSchedule.setStyle("-fx-background-color: green;");
        deletSchedule.setFont(new Font("System Bold", 18.0));
        deletSchedule.setPadding(new Insets(10.0, 20.0, 10.0, 10.0));

        Button viewAssiment = new Button("ViewSchedule");
        viewAssiment.setMnemonicParsing(false);
        viewAssiment.setPrefHeight(50.0);
        viewAssiment.setPrefWidth(150.0);
        viewAssiment.setStyle("-fx-background-color: green;");
        viewAssiment.setFont(new Font("System Bold", 18.0));
        viewAssiment.setPadding(new Insets(10.0, 20.0, 10.0, 10.0));
        FlowPane floepane2 = new FlowPane();
        bpane.setLeft(gridPane);
        floepane2.getChildren().addAll(viewAssiment, deletSchedule);
        gridPane.add(floepane2, 1, 6);
        floepane2.setHgap(20);
        viewAssiment.setOnAction(event -> {
            bpane.setLeft(null);
            showSeeWorkassignPage();

        });
        deletSchedule.setOnAction(event -> {
            showDropSchedul();
        });
    }

    public void showSeeWorkassignPage() {

        VBox pageContent = new VBox(10);
        pageContent.setStyle("-fx-background-color: lightblue;");
        pageContent.setPadding(new Insets(10));

        Label employeeIdLabel = new Label("Employee ID:");
        employeeIdLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill:black;");

        TextField employeeIdField = new TextField();

        employeeIdField.setMaxWidth(300);
        employeeIdField.setPrefHeight(30);

        Button seeAllButton = new Button("See All");
        seeAllButton.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");

        Button seesingleButton = new Button("See");
        seesingleButton.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");

        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");

        pageContent.getChildren().addAll(
                seeAllButton,
                employeeIdLabel,
                employeeIdField,
                seesingleButton,
                cancelButton
        );

        cancelButton.setOnAction(e -> {
            bpane.setCenter(null);
            bpane.setLeft(null);
        });

        seesingleButton.setOnAction(event -> {
            if (employeeIdField.getText().isEmpty()) {
                showAlert("Error empty field", "please enter Employee ID");
            } else {
                int employeeId = Integer.parseInt(employeeIdField.getText());
                seeAllORSingleWorkScedule(employeeId);
            }
        });

        seeAllButton.setOnAction(event -> {
            seeAllORSingleWorkScedule(0);
        });

//
        bpane.setLeft(pageContent);
    }

    private void seeAllORSingleWorkScedule(int value) {
        TableView<Object[]> tableView = new TableView<>();

        TableColumn<Object[], Integer> EmployeeID = new TableColumn<>("EmployeeID");
        EmployeeID.setCellValueFactory(cellData -> new SimpleObjectProperty<>((Integer) cellData.getValue()[0]));

        TableColumn<Object[], String> FirstName = new TableColumn<>("FirstName");
        FirstName.setCellValueFactory(cellData -> new SimpleObjectProperty<>((String) cellData.getValue()[1]));

        TableColumn<Object[], String> LastName = new TableColumn<>("LastName");
        LastName.setCellValueFactory(cellData -> new SimpleObjectProperty<>((String) cellData.getValue()[2]));

        TableColumn<Object[], String> workDateColumn = new TableColumn<>("work_date");
        workDateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>((String) cellData.getValue()[3]));

        TableColumn<Object[], Time> starttimeColumn = new TableColumn<>("start_time(LT)");
        starttimeColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>((Time) cellData.getValue()[4]));
        TableColumn<Object[], Time> endTime = new TableColumn<>("end_time(LT)");
        endTime.setCellValueFactory(cellData -> new SimpleObjectProperty<>((Time) cellData.getValue()[5]));

        tableView.getColumns().addAll(EmployeeID, FirstName, LastName, workDateColumn, starttimeColumn, endTime);

        dataBaseManegement = new DataBaseManegement();
        dataBaseManegement.viwAllWorkScheduleInfo(tableView, value);

        VBox root = new VBox(tableView);
        bpane.setCenter(root);

    }

    public void showDropSchedul() {

        VBox pageContent = new VBox(10);
        BorderPane.setMargin(pageContent, new Insets(20));
        pageContent.setStyle("-fx-background-color: lightblue;");
        pageContent.setPadding(new Insets(10));

        Label employeeIdLabel = new Label("Employee ID:");
        employeeIdLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill:black;");
        Label daylabel = new Label("Assigned Day:");
        daylabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill:black;");
        TextField employeeIdField = new TextField();
        TextField assigneddaye = new TextField();
        employeeIdField.setMaxWidth(300);
        employeeIdField.setPrefHeight(30);
        assigneddaye.setMaxWidth(300);
        assigneddaye.setPrefHeight(30);
        Button seeAllButton = new Button("See All");
        seeAllButton.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");

        Button drop = new Button("Drop");
        drop.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");

        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");

        pageContent.getChildren().addAll(
                employeeIdLabel,
                employeeIdField,
                daylabel,
                assigneddaye,
                drop,
                cancelButton
        );

        cancelButton.setOnAction(e -> {
            bpane.setCenter(null);

        });

        drop.setOnAction(event -> {
            if (employeeIdField.getText().isEmpty() || assigneddaye.getText().isEmpty()) {
                showAlert("Error empty field", "please enter Fields");
            } else {
                String assignedday = assigneddaye.getText();
                int employeeId = Integer.parseInt(employeeIdField.getText());
                try {
                    DataBaseManegement dataBaseManegement = new DataBaseManegement();
                    Connection con = dataBaseManegement.createConnection();
                    String sql = "delete from workassignment where EmployeeId=" + employeeId + " and work_date ='" + assignedday + "';";
                    Statement stm = con.createStatement();
                    int count = stm.executeUpdate(sql);
                    if (count > 0) {
                        showAlert("success", "deleted succesfully");
                    } else {
                        showAlert("Failed", "not deleted");
                    }
                    stm.close();
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });

        seeAllButton.setOnAction(event -> {
            seeAllORSingleWorkScedule(0);
        });

//
        bpane.setCenter(pageContent);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
