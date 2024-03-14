
package hotelmanagementapp;

import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataBaseManegement {
    static int roomid = 1;
    static int reservationId = 2;

    static String url = "jdbc:mysql://localhost:3306/hotel_manegement";
    static Connection connection;

    public Connection createConnection() {
      
        try {
            connection = DriverManager.getConnection(url,"root","");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("connection Error : ", e.getMessage());
        }
        return connection;
    }


    public void addBillingInfo(int guestid) {
        Connection connection1 = createConnection();
        int billingID = 0;
        float totalamount = 0;
        String strsql = "select MAX(BillingID) from billing";
        String sqlstaydate = "SELECT DATEDIFF(CheckOutDate, CheckInDate) " +
                "FROM reservations WHERE GuestID=" + guestid;
        String sqlRate = " select Rate from roomtypes where RoomTypeID IN(select RoomTypeID from rooms where RoomID " +
                "IN (select RoomID from reservations where  GuestID= " + guestid + "))";
        String sqlinsert = "INSERT INTO billing (BillingID, GuestID, ReservationID, TotalAmount) VALUES (?,?,?,?)";
        String reservID = "select ReservationID from reservations where  GuestID=" + guestid;
        String updateroom = "update rooms set AvailabilityStatus='occupied' where RoomID in(select RoomID from reservations where GuestID =" + guestid + ")";
        try {

            Statement getreserID = connection1.createStatement();
            ResultSet resultreservId = getreserID.executeQuery(reservID);
            if (resultreservId.next()) {
                int reservId = resultreservId.getInt(1);
                Statement getmaxBillingID = connection1.createStatement();
                ResultSet resultSetmax = getmaxBillingID.executeQuery(strsql);
                resultSetmax.next();
                int maxvalue = resultSetmax.getInt(1);
                billingID = maxvalue + 1;
                Statement getstaydate = connection1.createStatement();
                ResultSet getstaydateresult = getstaydate.executeQuery(sqlstaydate);
                getstaydateresult.next();
                int stayDate = getstaydateresult.getInt(1);
                Statement getRate = connection1.createStatement();
                ResultSet rateResult = getRate.executeQuery(sqlRate);
                rateResult.next();
                float rate = rateResult.getFloat(1);
                totalamount = rate * stayDate;

                PreparedStatement preparedStatement = connection1.prepareStatement(sqlinsert);
                preparedStatement.setInt(1, billingID);
                preparedStatement.setInt(2, guestid);
                preparedStatement.setInt(3, reservId);
                preparedStatement.setFloat(4, totalamount);
                preparedStatement.executeUpdate();
                Statement updateroomsta = connection1.createStatement();
                updateroomsta.executeUpdate(updateroom);
                showAlert("Succes", "inserted succesfully");
            } else {
                showAlert("Error", "the room is not reserved for this guest");
            }
            getreserID.close();
            connection1.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            showAlert("Error adding billing info", ex.getMessage());
        }

    }

    public void viwAllBilingInfo(TableView<Object[]> tableView, int value) {

        ResultSet resultSet = null;

        try {
           
            Connection connection = createConnection();

           
            Statement statement = connection.createStatement();

           
 if (value > 0){
     resultSet = statement.executeQuery("SELECT BillingID,FirstName,LastName,"
                        + "TotalAmount FROM billing, guests where billing.GuestID=" +value+ " and guests.GuestID = billing.GuestID ");
            }
            
            
            else {
                resultSet = statement.executeQuery("SELECT BillingID, FirstName,LastName,TotalAmount FROM billing,guests where guests.GuestID= billing.GuestID ");
            }
           
            while (resultSet.next()) {
                Object[] row = new Object[4];
                row[0] = resultSet.getInt("BillingID");
                row[1] = resultSet.getString("FirstName");
                row[2] = resultSet.getString("LastName");
                row[3] = resultSet.getDouble("TotalAmount");

                tableView.getItems().add(row);
            }

            
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void registerGuest(String firstName, String lastName, String address, String phoneNumber, String email, int id) {
        connection = createConnection();

        try {



            String query = "INSERT INTO guests (GuestID,FirstName, LastName,  phoneNumber, Email,Address) VALUES (?, ?, ?, ?, ?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setString(6, address);
            statement.setString(4, phoneNumber);
            statement.setString(5, email);
            statement.setString(6, address);
            statement.executeUpdate();
            showAlert("success","Guest registered successfully.");
            statement.close();
            connection.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error registering guest: " , e.getMessage());
        }
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        connection = createConnection();
        try {
            String query = "SELECT * FROM guests";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
           
            while (resultSet.next()) {
                int id = resultSet.getInt("GuestID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String age = resultSet.getString("phoneNumber");
                String sex = resultSet.getString("Email");
                String address = resultSet.getString("Address");

                Customer customer = new Customer(id, firstName, lastName, age, sex, address);
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
           showAlert("Error slecting specified room: " , e.getMessage());
        }

        return customers;
    }


    public int deletGuest(int guestid) {
        connection = createConnection();
        int numberOFRowaffected = 0;
        try {
            Statement st = connection.createStatement();
            Statement bilingst = connection.createStatement();
            String slect = "select * from reservations where GuestID=" + guestid;
            String slectForBiling = "select * from billing where GuestID=" + guestid;
            ResultSet resultSet = st.executeQuery(slect);
            ResultSet resultSetForBilling = st.executeQuery(slectForBiling);
            String sqlre = "DELETE from reservations where GuestID= ?";
            String sql = "DELETE from guests WHERE GuestID=?";
            String deletForBiling = "DELETE from billing WHERE GuestID=?";
            PreparedStatement stmdeletreser = connection.prepareStatement(sqlre);
            PreparedStatement ForBiling = connection.prepareStatement(deletForBiling);
            stmdeletreser.setInt(1, guestid);
            ForBiling.setInt(1, guestid);
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, guestid);

            ForBiling.executeUpdate();
            stmdeletreser.executeUpdate();
            numberOFRowaffected = stm.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error: Canot delet Room  " + ex.getMessage());
        }
        return numberOFRowaffected;


    }

    public void insertEmployee(int employeeId, String firstName, String lastName, String position, String department, String phoneNumber, String email, String gender, double salary) {
        try {
            connection = createConnection();
            String query = "INSERT INTO `employee`(EmployeeID, `FirstName`, `LastName`, `gender`, `phoneNumber`, `Email`, `Role`, `DepartmentID`, Salary)  " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, (SELECT DepartmentID FROM departments WHERE DepartmentName = '" + department + "'), ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, employeeId);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setString(4, gender);
            statement.setString(5, phoneNumber);
            statement.setString(6, email);
            statement.setString(7, position);
            statement.setDouble(8, salary);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
           showAlert("Error inserting Staff: " , e.getMessage());
        }
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        connection = createConnection();
        try {
            String query = "SELECT EmployeeID,FirstName,LastName,gender,phoneNumber,Email,Role, DepartmentName, Salary FROM employee E ,departments D where D.DepartmentID=E.DepartmentID ";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("EmployeeID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String sex = resultSet.getString("gender");

                String phoneNumber = resultSet.getString("phoneNumber");
                String email = resultSet.getString("Email");
                String positoin = resultSet.getString("Role");
                String departement = resultSet.getString("DepartmentName");

                double Salary = resultSet.getDouble("Salary");


                Employee employee = new Employee(id, firstName, lastName, phoneNumber, email, positoin, departement, sex, Salary);
                employees.add(employee);

            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error slecting specified room: " + e.getMessage());
        }

        return employees;
    }

    public void deletStaff(int id) {
        connection = createConnection();
        try {
            String sql = "DELETE from employee WHERE EmployeeID=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error: Canot delet staff" + ex.getMessage());
        }


    }


    public void addRoom(String roomN, int floorn, String roomType, String capacity) {
        connection = createConnection();


        roomid = roomid + 1;
        // int RoomTypeID=getRoomTypeid(roomType);
        try {
            String rowcount = "SELECT MAX(RoomID) FROM rooms";
            Statement countstatement = connection.createStatement();
            ResultSet resultSet = countstatement.executeQuery(rowcount);
            resultSet.next();
            int count = resultSet.getInt(1);
            if (roomid <= count) {
                roomid = count + 1;
            }

            Statement statement = connection.createStatement();

            statement.executeUpdate("    INSERT INTO rooms (RoomID,RoomNumber, FloorNumber,  RoomTypeID," +
                    " AvailabilityStatus) VALUES (" + roomid + ", '" + roomN + "'," + floorn + ",(SELECT RoomTypeID FROM roomtypes WHERE RoomTypeName = '" + roomType + "'), 'not reserved');");
            showAlert("success", " values are inserted");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error inserting room: " + e.getMessage());
        }


    }


    public ResultSet viewRoom(int roomid) {
        connection = createConnection();
        String query = "SELECT RoomID,RoomNumber,FloorNumber,RoomTypeName,AvailabilityStatus FROM rooms,roomtypes WHERE RoomID=" + roomid + " AND roomtypes.RoomTypeID=rooms.RoomTypeID";
        ResultSet resultSet = null;
        try {

            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error slecting specified room: " + e.getMessage());
        }

        return resultSet;
    }


    public int deletRoom(int roomid) {
        connection = createConnection();
        int numberOFRowaffected = 0;
        try {
            Statement st = connection.createStatement();
            String slect = "select * from reservations where RoomID=" + roomid;
            ResultSet resultSet = st.executeQuery(slect);
            String sqlre = "DELETE from reservations where RoomID= ?";
            String sql = "DELETE from rooms WHERE RoomID=?";
            PreparedStatement stmdeletreser = connection.prepareStatement(sqlre);
            stmdeletreser.setInt(1, roomid);

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, roomid);
            if (resultSet.next()) {
                stmdeletreser.executeUpdate();
                stm.executeUpdate();
            } else {
                numberOFRowaffected = stm.executeUpdate();


            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error: Canot delet Room  " + ex.getMessage());
        }
        return numberOFRowaffected;


    }


    public void insertReservation(int guestId, int roomId, String checkInDates,
                                  String checkOutDates, String specialRequests) {
        connection = createConnection();
        reservationId = 0;
        java.util.Date checkInDateu = null;
        java.util.Date checkOutDateu = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            checkInDateu = dateFormat.parse(checkInDates);
            checkOutDateu = dateFormat.parse(checkOutDates);
        } catch (ParseException p) {
            p.printStackTrace();
        }
        try {
            java.sql.Date checkInDate = new java.sql.Date(checkInDateu.getTime());
            java.sql.Date checkOutDate = new java.sql.Date(checkOutDateu.getTime());

            String strsql = "select MAX(ReservationID) from reservations";
            String checkReserved = "select AvailabilityStatus from rooms where RoomID=" + roomId;
            Statement checkReservedst = connection.createStatement();
            ResultSet resultSet = checkReservedst.executeQuery(checkReserved);
            resultSet.next();
            String resultSetString = resultSet.getString(1);

            Statement getmaxBillingID = connection.createStatement();
            ResultSet resultSetmax = getmaxBillingID.executeQuery(strsql);
            resultSetmax.next();
            int maxvalue = resultSetmax.getInt(1);
            reservationId = maxvalue + 1;
            String query = "INSERT INTO reservations (ReservationID, GuestID, RoomID, CheckInDate, CheckOutDate, SpecialRequests)" +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            String updateQ = "update  rooms set AvailabilityStatus  = 'reserved' where RoomID= ?";
            if (resultSetString.equals("reserved")||resultSetString.equals("occupied")) {
                showAlert("Denied", "the room is already reserved");
            } else {
                PreparedStatement statement = connection.prepareStatement(query);
                PreparedStatement updatestatement = connection.prepareStatement(updateQ);
                statement.setInt(1, reservationId);
                statement.setInt(2, guestId);
                statement.setInt(3, roomId);
                statement.setDate(4, checkInDate);
                statement.setDate(5, checkOutDate);
                statement.setString(6, specialRequests);

                statement.executeUpdate();
                updatestatement.setInt(1, roomId);
                updatestatement.executeUpdate();
                resultSetmax.close();
                resultSet.close();
                checkReservedst.close();
                getmaxBillingID.close();
                
             showAlert("Success", "inserted succesfully");
                updatestatement.cancel();
                statement.close();
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error Reservation Erorr ", e.getMessage());
        }

    }

    public VBox getReservedRoom(Stage stage, String status, String identity) {

        TableView<Object[]> tableView = new TableView<>();


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
        //  fetchDataFromDatabase(tableView);
        VBox root = new VBox(tableView);


        try {
            // Establish the database connection
            ResultSet resultSet = null;
            Connection connection = createConnection();


            String sql = "select RoomID ,RoomNumber, FloorNumber, RoomTypeName,AvailabilityStatus from rooms R ,roomTypes RT where R.AvailabilityStatus ='reserved' AND RT.RoomTypeID=R.RoomTypeID";
            String sqlnotresreved = "select RoomID ,RoomNumber, FloorNumber, RoomTypeName ,AvailabilityStatus from rooms R ,roomTypes RT where R.AvailabilityStatus ='not reserved' AND RT.RoomTypeID=R.RoomTypeID";
            // dataBaseManegement=new DataBaseManegement();

            Statement st = connection.createStatement();
            if (status.equalsIgnoreCase("reserved")) {
                resultSet = st.executeQuery(sql);
            } else if (status.equalsIgnoreCase("not reserved")) {
                resultSet = st.executeQuery(sqlnotresreved);
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
            st.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return root;


    }

    public void viwAllWorkScheduleInfo(TableView<Object[]> tableView, int value) {

        ResultSet resultSet = null;

        try {
            // Establish the database connection
            Connection connection = createConnection();

            // Create a statement
            Statement statement = connection.createStatement();

            // Execute the SQL queryi
            if (value > 0) {
                resultSet = statement.executeQuery("SELECT workassignment.EmployeeID,employee.FirstName, employee.LastName ,workassignment.work_date ,workassignment.start_time,workassignment.end_time" +
                        " FROM workassignment  , employee  where workassignment.EmployeeID='" + value+"' and employee.EmployeeID= workassignment.EmployeeID");


            } else {
                resultSet = statement.executeQuery("SELECT workassignment.EmployeeID,employee.FirstName, employee.LastName ,workassignment.work_date ,workassignment.start_time,workassignment.end_time" +
                        " FROM workassignment , employee  where  employee.EmployeeID= workassignment.EmployeeID");
            }

            if (resultSet != null && resultSet.isBeforeFirst())
            {
                
            

            
                while (resultSet.next()) {
                    Object[] row = new Object[6];
                    row[0] = resultSet.getInt("EmployeeID");
                    row[1] = resultSet.getString("FirstName");
                    row[2] = resultSet.getString("LastName");
                    row[3] = resultSet.getString("work_date");
                    row[4] = resultSet.getTime("start_time");
                    row[5] = resultSet.getTime("end_time");

                    tableView.getItems().add(row);
                }
            }
            else{
            showAlert("NOT found","there is no data ");
            }
            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
          showAlert("Error N",e.getMessage());
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





