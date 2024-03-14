package hotelmanagementapp;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.stage.Stage;
public class UserAuthentication {


static Stage stage;
   public  void getUserAuthentication(Stage stage,String username, String password) {
this.stage=stage;
        User user = authenticateUser(username, password);
        if (user != null) {

            switch (user.getRole()) {
                case "admin":
                    AdimiPage adimiPage=new AdimiPage();
                    adimiPage.getAdimiPage(stage);
                    break;
                case "recip":
                    ReceptionicitPage recip=new ReceptionicitPage();
                    recip.getReceptionicitPage(stage);
                    break;
                case "finance":
                    FinancialStaffPage financialStaffPage= new FinancialStaffPage();
                    financialStaffPage.getFananicialStaffPage(stage);
                    break;
                case "user":
                    EmployeeWorkSchedule employeeWorkSchedule=new EmployeeWorkSchedule();
                    employeeWorkSchedule.getEmployeeWorkSchedule(stage,user.getRole());
                    break;
                     default:
                         showAlert("denied"," role not assigned");
                         break;
            }
        } else {
            showAlert("Error ","Invalid username or password. Authentication failed.");

        }
    }

    private static User authenticateUser(String username, String password) {
       DataBaseManegement dataBaseManegement=new DataBaseManegement();
        try {

            Connection conn = dataBaseManegement.createConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user_ WHERE user_name = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery() ;
                if (rs.next()) {
                    String storedPassword = rs.getString("pass");

                    if (storedPassword.equals(password))
                    {
                        String role = rs.getString("role");
                        return new User(username, role);
                    }

                }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error ",e.getMessage());
        }
        return null;
    }



    private static class User {
        private String username;
        private String role;

        public User(String username, String role) {
            this.username = username;
            this.role = role;
        }

        public String getUsername() {
            return username;
        }

        public String getRole() {
            return role;
        }
    }
    private static  void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}