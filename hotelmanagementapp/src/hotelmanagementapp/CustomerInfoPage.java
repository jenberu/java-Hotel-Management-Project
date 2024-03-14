package hotelmanagementapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class CustomerInfoPage {
    private final List<Customer> customers;

    public CustomerInfoPage(List<Customer> customers) {

        this.customers = customers;
    }

    public VBox getUI(Stage primaryStage,String identity) {
        VBox root = new VBox();

       
        TableColumn<Customer, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

        TableColumn<Customer, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());

        TableColumn<Customer, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        TableColumn<Customer, String> ageColumn = new TableColumn<>("phoneNumber");
        ageColumn.setCellValueFactory(cellData -> cellData.getValue().ageProperty());

        TableColumn<Customer, String> sexColumn = new TableColumn<>("email");
        sexColumn.setCellValueFactory(cellData -> cellData.getValue().sexProperty());

        TableColumn<Customer, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());

       
        TableView<Customer> tableView = new TableView<>();
        tableView.setStyle("-fx-background-color: yellow;");
        tableView.getColumns().addAll(idColumn, firstNameColumn, lastNameColumn, ageColumn, sexColumn, addressColumn);

       
        ObservableList<Customer> customerList = FXCollections.observableArrayList(customers);
        tableView.setItems(customerList);

        root.getChildren().add(tableView);
     



        return root;
    }
}