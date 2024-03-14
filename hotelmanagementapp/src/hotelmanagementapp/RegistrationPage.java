package hotelmanagementapp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;
import javax.swing.JOptionPane;

public class RegistrationPage {

    private TextField firstnametf;
    private TextField lastnametf;
    private TextField addresstf;
    private TextField phonetf;
    private TextField idtf;
    private TextField emailtf;
    Button assignbtn,refresh,rigbtn,clear ,deletguest,updateinfo;
  BorderPane mainBorder = new BorderPane();
         BorderPane outBorder = new BorderPane();
         DataBaseManegement dataBaseManegement;
    public void getRegistrationPage(Stage primaryStage) {
        deletguest=new Button("Delete");
        updateinfo=new Button("Update");
        deletguest.setStyle("-fx-background-color:green;-fx-font-weight: bold;-fx-text-fill:red;-fx-font-size: 24;");
         assignbtn = new Button("Assign Room");
        outBorder.setStyle("-fx-background-color: brown");
        outBorder.setPadding( new Insets(10));
        outBorder.setPadding( new Insets(20));
         
        FlowPane hbox = new FlowPane();
        hbox.setHgap(10);
          hbox.setVgap(10);
         VBox vbox= new VBox();
        
     
         refresh=new Button("view diatail");
        refresh.setStyle("-fx-background-color:green;-fx-font-weight: bold;-fx-font-size: 24;");


        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        outBorder.setLeft(vbox);
        BorderPane.setMargin(vbox, new Insets(10));
         
        ImageView backbtnimg = new ImageView("hotelmanagementapp/download.png");
        backbtnimg.setFitWidth(50);
        backbtnimg.setFitHeight(50);
        BorderPane.setAlignment(backbtnimg, Pos.TOP_LEFT);
        backbtnimg.setOnMouseClicked(event -> {
            ReceptionicitPage getrecepionPage = new ReceptionicitPage();
            getrecepionPage.getReceptionicitPage(primaryStage);
        });
        Label gtxt = new Label("Manage Guests");
        gtxt.setStyle("-fx-text-fill: red;");
       BorderPane.setMargin(gtxt,new Insets(10));

        HBox hb=new HBox();
        hb.getChildren().addAll(backbtnimg,gtxt);
hb.setStyle("-fx-background-color: purple;");
        hb.setSpacing(300);
        outBorder.setTop(hb);

      //  outBorder.setTop(gtxt);
        gtxt.setFont(Font.font(24));
        VBox.setMargin(gtxt, new Insets(10, 10, 10, 50));

         AnchorPane textFp = new AnchorPane();
           outBorder.setCenter(textFp);

        textFp.setStyle("-fx-background-color: lightblue;");

        
        

        Label firstnamelbl = new Label("First name:");
        firstnamelbl.setFont(Font.font(14));
        firstnamelbl.setTextFill(javafx.scene.paint.Color.web("black"));

        Label lastnamelbl = new Label("Last Name:");
        lastnamelbl.setFont(Font.font(14));
        lastnamelbl.setTextFill(javafx.scene.paint.Color.web("black"));

        Label addresslbl = new Label("Address:");
        addresslbl.setFont(Font.font(14));
        addresslbl.setTextFill(javafx.scene.paint.Color.web("black"));

        Label phonelabl = new Label("Phone Number:");
        phonelabl.setFont(Font.font(14));
        phonelabl.setTextFill(javafx.scene.paint.Color.web("black"));

        Label idlbl = new Label("ID:");
        idlbl.setFont(Font.font(14));
        idlbl.setTextFill(javafx.scene.paint.Color.web("black"));
         Label emaillbl = new Label("Email:");
        emaillbl.setFont(Font.font(14));
        emaillbl.setTextFill(javafx.scene.paint.Color.web("black"));

         firstnametf = new TextField();
        firstnametf.setPromptText("It should be a character");
        firstnametf.setStyle("-fx-border-color: blue;");

         lastnametf = new TextField();
        lastnametf.setPromptText("It should be a character");
        lastnametf.setStyle("-fx-border-color: blue;");

         addresstf = new TextField();
        addresstf.setPromptText("It should be a string");
        addresstf.setStyle("-fx-border-color: blue;");

         phonetf = new TextField();
        phonetf.setPromptText("Should be a number");
        phonetf.setStyle("-fx-border-color: blue;");

         idtf = new TextField();
        idtf.setPromptText("Should be a number");
        idtf.setStyle("-fx-border-color: blue;");
           emailtf = new TextField();
        emailtf.setPromptText("Should email format");
        emailtf.setStyle("-fx-border-color: blue;");

         rigbtn = new Button("Register");
        rigbtn.setStyle("-fx-background-color: blue; -fx-text-fill: #b06946; -fx-font-weight: bold;-fx-font-size: 20px;");

         clear = new Button("Clear ");
        clear.setStyle("-fx-background-color: green; -fx-text-fill: #b06946; -fx-font-weight: bold;-fx-font-size: 20px;");

        rigbtn.setOnAction(e->{
            if(fieldsAreValid()){

                String firstName = firstnametf.getText();
                String lastName = lastnametf.getText();
                String address = addresstf.getText();
                String phoneNumber = phonetf.getText();
                String email = emailtf.getText();
                int id = Integer.parseInt(idtf.getText());
                // Create an instance of your database helper class and call the registerGuest method
                DataBaseManegement databaseHelper = new DataBaseManegement();
                databaseHelper.registerGuest(firstName, lastName, address, phoneNumber, email,id);
            }

            });


        refresh.setOnAction(e -> {
               outBorder.setCenter(null);

            refresh.setText("refresh");
            DataBaseManegement dataBaseManegement =new DataBaseManegement();
            List<Customer> customers = dataBaseManegement.getAllCustomers();
            String identity="recip";
            CustomerInfoPage customerInfoPage = new CustomerInfoPage(customers);

            VBox infopagevbox=customerInfoPage.getUI(primaryStage,identity);
            textFp.getChildren().add(infopagevbox);
               outBorder.setCenter(textFp);


        });


        clear.setOnAction(e -> {
            firstnametf.clear();
             lastnametf.clear();
              addresstf.clear();
               phonetf.clear();
                idtf.clear();
               emailtf.clear();
        });
        deletguest.setOnA
                ction(event -> {
            getDeletGeusts();
        });
        assignbtn.setOnAction(e->{
            AssignRoomPage getIt=new AssignRoomPage();
            getIt.getRoomAssinPage(primaryStage);
        });

        assignbtn.setStyle("-fx-border-color: green; -fx-text-fill: blue; -fx-font-weight: bold;-fx-font-size: 20px;");

        gridPane.add(firstnamelbl, 0, 0);
        gridPane.add(lastnamelbl, 0, 1);
        gridPane.add(addresslbl, 0, 2);
        gridPane.add(phonelabl, 0, 3);
        gridPane.add(idlbl, 0, 4);
         gridPane.add(emaillbl, 0, 5);
        gridPane.add(firstnametf, 1, 0);
        gridPane.add(lastnametf, 1, 1);
        gridPane.add(addresstf, 1, 2);
        gridPane.add(phonetf, 1, 3);
        gridPane.add(idtf, 1, 4);
          gridPane.add(emailtf, 1, 5);
      
      hbox.getChildren().addAll(rigbtn, clear, refresh,assignbtn,deletguest);
  
       VBox.setMargin(vbox, new Insets(10));
       vbox.getChildren().addAll(gridPane,hbox);
      // outBorder.setBottom(vbox);

        Scene scene = new Scene(outBorder, 1000, 600);
        primaryStage.setTitle("Welcome to Guest Registration Page");
        primaryStage.setScene(scene);
        primaryStage.show();



    }

    private void getDeletGeusts(){
        GridPane gpane = new GridPane();
        gpane.setStyle("-fx-background-color: lightgreen;");
        Label rnlbl = new Label("Enter Geust Id");
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
        outBorder.setCenter(gpane);
        gpane.setHgap(10);
        gpane.setVgap(10);
        dletrbtn.setOnAction(event -> {
            if(rtxtf.getText().isEmpty()){
                showAlert("Error empty ","field is empty");
            }
            else {
                int id = Integer.parseInt(rtxtf.getText());
                dataBaseManegement = new DataBaseManegement();
                int affectedRow=   dataBaseManegement.deletGuest(id);
                if(affectedRow>0) {
                    showAlert("Successs ", "Deleted successfully");
                }
                else
                    showAlert("Not Found ", "there is no Such Guest");
            }
        });
        cancelButton.setOnAction(e -> {
            
            outBorder.setCenter(null);
            outBorder.setBottom(null);

        });
    }


    private boolean fieldsAreValid() {
        // You can implement validation rules here for each field

            String firstName = firstnametf.getText();
            String lastName = lastnametf.getText();
            String address = addresstf.getText();
            String phoneNumber = phonetf.getText();
            String email = emailtf.getText();
            String idText = idtf.getText();

            // Validate firstName
            if (firstName.isEmpty()||!firstName.matches("[a-zA-Z]+")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid first name.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Validate lastName
            if (lastName.isEmpty()||!lastName.matches("[a-zA-Z]+")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid last name.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Validate address
            if (address.isEmpty()||!address.matches("[a-zA-Z]+")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid address.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Validate phoneNumber
            if (phoneNumber.isEmpty() || !phoneNumber.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid phone number (10 digits).", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Validate email
            if (email.isEmpty() || !email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid email address.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Validate id
            try {
                int id = Integer.parseInt(idText);
                if (id <= 0) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid ID (positive integer).", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid ID (numeric value).", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            return true;
        }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
   
}