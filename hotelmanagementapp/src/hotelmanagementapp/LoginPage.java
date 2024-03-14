
package hotelmanagementapp;

import static hotelmanagementapp.Hotelmanagementapp.primaryStage;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author bit
 */
public class LoginPage {
    
       private HBox txthb;
       private    Label lblLoginUsername,lblLoginPassword;
       private TextField txtLoginUsername;
    public  void getLoginpage(Stage loginStage) {
        
           Text txt = new Text("USER lOGIN");
      txthb=new HBox();
        txthb.setAlignment(Pos.CENTER);
        txthb.getChildren().add(txt);
        txthb.setStyle("-fx-background-color:blue");

        txt.setStyle(" -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 50px;");
        txt.setFont(Font.font(50));
         VBox.setMargin(txt, new Insets(10, 0, 0, 50));

         lblLoginUsername = new Label("Username:");
        lblLoginUsername.setFont(Font.font(20));
         txtLoginUsername = new TextField();

        txtLoginUsername.setMaxWidth(400);
        txtLoginUsername.setStyle("-fx-border-color: blue; -fx-border-width: 2px;-fx-background-color:pink");;

         lblLoginPassword = new Label("Password:");
        lblLoginPassword.setFont(Font.font(20));
        PasswordField txtLoginPassword = new PasswordField();
        txtLoginPassword.setMaxWidth(400);
        txtLoginPassword.setStyle("-fx-border-color: blue; -fx-border-width: 2px;-fx-background-color:pink");;

        Button btnLogin = new Button("Login");
        btnLogin.setStyle(" -fx-text-fill: white; -fx-font-weight: bold;-fx-font-size: 20px;-fx-background-color:green;");
        btnLogin.setPrefWidth(400);



        VBox lvb = new VBox();
        VBox forleft = new VBox();
        Text text=new Text();
        forleft.getChildren().add(text);
        forleft.setStyle("-fx-background-color:pink;");
        BorderPane bpane=new BorderPane();
        bpane.setCenter(lvb);
        bpane.setTop(txthb);
        BorderPane.setMargin(lvb,new Insets(20));
        BorderPane.setMargin(txthb,new Insets(20));
        bpane.setStyle("-fx-background-color:lightblue;");

        BorderPane.setAlignment(txt,Pos.CENTER);
        bpane.setLeft(forleft);
        lvb.setStyle("-fx-background-color:brown;");
        lvb.setAlignment(Pos.CENTER);
        lvb.setSpacing(10);
        lvb.setPadding(new Insets(20));

        HBox hb1=new HBox();
        hb1.setAlignment(Pos.CENTER);
        HBox hb2=new HBox();
        hb2.setAlignment(Pos.CENTER);
        hb1.setSpacing(10);
        hb1.getChildren().addAll(lblLoginUsername,txtLoginUsername);
        hb2.getChildren().addAll(lblLoginPassword,txtLoginPassword);
        lvb.getChildren().addAll(hb1, hb2, btnLogin);


        Scene scene = new Scene(bpane, 1000, 600);

        loginStage.setScene(scene);
        loginStage.setTitle("well come to login page");
        loginStage.show();
        btnLogin.setOnAction(e -> {
            String username = txtLoginUsername.getText();
            String password = (txtLoginPassword.getText());

            if (username.equals("") || password.equals("")) {
                showAlert("Login Failed", "please fiil all filled");
          }
          else
          {
                UserAuthentication userAuthentication=new UserAuthentication();
                userAuthentication.getUserAuthentication(loginStage,username,password);
            }

        });
    }
    


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    
    
    
}
