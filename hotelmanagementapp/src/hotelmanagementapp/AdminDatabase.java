package hotelmanagementapp;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdminDatabase {

   DataBaseManegement dataBaseManegement;
    public VBox  checkReservation(){
        TableView<Object[]> tableView = new TableView<>();

        // Define table columns
        TableColumn<Object[], Integer> reservationIdColumn = new TableColumn<>("RoomID");
        reservationIdColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>((Integer) cellData.getValue()[0]));

        TableColumn<Object[], String> checkInDateColumn = new TableColumn<>("RoomNumber");
        checkInDateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty((String) cellData.getValue()[1]));

        TableColumn<Object[], Integer> guestIdColumn = new TableColumn<>("FloorNumber");
        guestIdColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>((Integer) cellData.getValue()[2]));

        TableColumn<Object[], String> roomIdColumn = new TableColumn<>("RoomTypeName");
        roomIdColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>((String) cellData.getValue()[3]));


        TableColumn<Object[], String> checkOutDateColumn = new TableColumn<>("AvailabilityStatus");
        checkOutDateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty((String) cellData.getValue()[4]));

        tableView.getColumns().addAll(reservationIdColumn, guestIdColumn, roomIdColumn,
                checkInDateColumn, checkOutDateColumn);

        // Fetch data from the database and populate the table
        fetchDataFromDatabase(tableView);

        VBox root = new VBox(tableView);
        return root;
    }
    private void fetchDataFromDatabase(TableView<Object[]> tableView) {
        dataBaseManegement=new DataBaseManegement();
        try {
            // Establish the database connection
            ResultSet resultSet=null;
            Connection connection = dataBaseManegement.createConnection();

            // Create a statement
            Statement statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT RoomID,RoomNumber,FloorNumber,RoomTypeName,AvailabilityStatus FROM rooms,roomtypes WHERE roomtypes.RoomTypeID=rooms.RoomTypeID ");
            if(resultSet.equals(0)){
                showAlert("NOT Found","There is no such recored");
            }

            // Populate the table view with data from the result set
            while (resultSet.next()) {
                Object[] row = new Object[5];
                row[0] = resultSet.getInt("RoomID");
                row[1] = resultSet.getString("RoomNumber");
                row[2] = resultSet.getInt("FloorNumber");
                row[3] = resultSet.getString("RoomTypeName");
                row[4] = resultSet.getString("AvailabilityStatus");


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
