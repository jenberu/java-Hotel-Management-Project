package hotelmanagementapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class EmployeeInfoPage {
    private final List<Employee> employees;

    public EmployeeInfoPage(List<Employee> employees) {

        this.employees = employees;
    }

    public VBox getUI(Stage primaryStage,String identity) {
        VBox root = new VBox();

        // Create table columns
        TableColumn<Employee, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

        TableColumn<Employee, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());

        TableColumn<Employee, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        TableColumn<Employee, String> sexColumn = new TableColumn<>("sex");
        sexColumn.setCellValueFactory(cellData -> cellData.getValue().sexProperty());

        TableColumn<Employee, String> phoneNumberColumn = new TableColumn<>("phoneNumber");
        phoneNumberColumn.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());

        TableColumn<Employee, String> emailColumn = new TableColumn<>("email");
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());

        TableColumn<Employee, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setCellValueFactory(cellData -> cellData.getValue().positoinProperty());
        TableColumn<Employee, String> deptColumn = new TableColumn<>("departement");
        deptColumn.setCellValueFactory(cellData -> cellData.getValue().departementProperty());
        TableColumn<Employee, Double> salaryColumn = new TableColumn<>("salary");
        salaryColumn.setCellValueFactory(cellData -> cellData.getValue().salaryProperty().asObject());

//
        TableView<Employee> tableView = new TableView<>();
        tableView.getColumns().addAll(idColumn, firstNameColumn, lastNameColumn, sexColumn, phoneNumberColumn, emailColumn,roleColumn,deptColumn,salaryColumn);

        // Create observable list of customers
        ObservableList<Employee> employelistList = FXCollections.observableArrayList(employees);
        tableView.setItems(employelistList);

        root.getChildren().addAll(tableView);




        return root;
    }
}