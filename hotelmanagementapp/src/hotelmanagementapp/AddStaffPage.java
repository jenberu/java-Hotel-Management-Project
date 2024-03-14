
package hotelmanagementapp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.*;

public class AddStaffPage {
    Button addbtn;
    Button clearbtn;
    TextField employeeIdField;
    TextField firstNameField;
    TextField lastNameField;
    TextField positionField;
    TextField departmentField;
    TextField phoneNumberField;
    TextField emailField;
    TextField salaryField;
    RadioButton maleRadioButton,femaleRadioButton;
    ToggleGroup genderToggleGroup;
//    Button  UpdateStaff;
//    Button deletStaff;
//    Button vieStaff ;
    public VBox getStaffPage(Stage primaryStage) {


        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
      //  root.setStyle("-fx-background-color: yellow;");
        root.setPadding(new Insets(10));

        // Employee ID
        Label employeeIdLabel = new Label("Employee ID:");
        employeeIdLabel.setTextFill(Paint.valueOf("#1ab96e"));
        employeeIdLabel.setFont(new Font(18));
        GridPane.setConstraints(employeeIdLabel, 0, 0);
        root.getChildren().add(employeeIdLabel);

        employeeIdField = new TextField();
        employeeIdField.setMaxWidth(200);
        employeeIdField.setPrefHeight(40);
        employeeIdField.setStyle("-fx-border-color: blue; -fx-border-width: 2;");
        GridPane.setConstraints(employeeIdField, 1, 0);
        root.getChildren().add(employeeIdField);

        // First Name
        Label firstNameLabel = new Label("First Name:");
        firstNameLabel.setTextFill(Paint.valueOf("#1cb54c"));
        firstNameLabel.setFont(new Font(18));
        GridPane.setConstraints(firstNameLabel, 0, 1);
        root.getChildren().add(firstNameLabel);

        firstNameField = new TextField();
        firstNameField.setStyle("-fx-border-color: blue; -fx-border-width: 2;");
        GridPane.setConstraints(firstNameField, 1, 1);
        root.getChildren().add(firstNameField);
        firstNameField.setMaxWidth(200);
        firstNameField.setPrefHeight(40);

        // Last Name
        Label lastNameLabel = new Label("Last Name:");
        lastNameLabel.setTextFill(Paint.valueOf("#2bae99"));
        lastNameLabel.setFont(new Font(18));
        GridPane.setConstraints(lastNameLabel, 0, 2);
        root.getChildren().add(lastNameLabel);

        lastNameField = new TextField();
        lastNameField.setStyle("-fx-border-color: blue; -fx-border-width: 2;");
        GridPane.setConstraints(lastNameField, 1, 2);
        root.getChildren().add(lastNameField);
        lastNameField.setMaxWidth(200);
        lastNameField.setPrefHeight(40);
        // Position
        Label positionLabel = new Label("Position:");
        positionLabel.setTextFill(Paint.valueOf("#27b562"));
        positionLabel.setFont(new Font(18));
        GridPane.setConstraints(positionLabel, 0, 3);
        root.getChildren().add(positionLabel);

        positionField = new TextField();
        positionField.setStyle("-fx-border-color: blue; -fx-border-width: 2;");
        GridPane.setConstraints(positionField, 1, 3);
        root.getChildren().add(positionField);
        positionField.setMaxWidth(200);
        positionField.setPrefHeight(40);
        // Department
        Label departmentLabel = new Label("Department:");
        departmentLabel.setTextFill(Paint.valueOf("#2c9a30"));
        departmentLabel.setFont(new Font(18));
        GridPane.setConstraints(departmentLabel, 0, 4);
        root.getChildren().add(departmentLabel);

        departmentField = new TextField();
        departmentField.setStyle("-fx-border-color: blue; -fx-border-width: 2;");
        GridPane.setConstraints(departmentField, 1, 4);
        root.getChildren().add(departmentField);
        departmentField.setMaxWidth(200);
        departmentField.setPrefHeight(40);
        // Phone Number
        Label phoneNumberLabel = new Label("Phone Number:");
        phoneNumberLabel.setTextFill(Paint.valueOf("#1fb545"));
        phoneNumberLabel.setFont(new Font(18));
        GridPane.setConstraints(phoneNumberLabel, 0, 5);
        root.getChildren().add(phoneNumberLabel);

        phoneNumberField = new TextField();
        phoneNumberField.setStyle("-fx-border-color: blue; -fx-border-width: 2;");
        GridPane.setConstraints(phoneNumberField, 1, 5);
        root.getChildren().add(phoneNumberField);
        phoneNumberField.setMaxWidth(200);
        phoneNumberField.setPrefHeight(40);
        // Email
        Label emailLabel = new Label("Email:");
        emailLabel.setTextFill(Paint.valueOf("#1fe488"));
        emailLabel.setFont(new Font(18));
        GridPane.setConstraints(emailLabel, 0, 6);
        root.getChildren().add(emailLabel);

        emailField = new TextField();
        emailField.setStyle("-fx-border-color: blue; -fx-border-width: 2;");
        GridPane.setConstraints(emailField, 1, 6);
        root.getChildren().add(emailField);
        emailField.setMaxWidth(200);
        emailField.setPrefHeight(40);
        // Gender
        Label genderLabel = new Label("Gender:");
        genderLabel.setTextFill(Paint.valueOf("#32a850"));
        genderLabel.setFont(new Font(18));
        GridPane.setConstraints(genderLabel, 0, 7);
        root.getChildren().add(genderLabel);

        FlowPane genderPane = new FlowPane();
        genderPane.setHgap(10);
        genderPane.setVgap(10);
        GridPane.setConstraints(genderPane, 1, 7);
        root.getChildren().add(genderPane);

        genderToggleGroup = new ToggleGroup();

        maleRadioButton = new RadioButton("Male");
        maleRadioButton.setFont(new Font(18));
        maleRadioButton.setToggleGroup(genderToggleGroup);
        genderPane.getChildren().add(maleRadioButton);

        femaleRadioButton = new RadioButton("Female");
        femaleRadioButton.setFont(new Font(18));
        femaleRadioButton.setToggleGroup(genderToggleGroup);
        genderPane.getChildren().add(femaleRadioButton);

        // Salary
        Label salaryLabel = new Label("Salary:");
        salaryLabel.setTextFill(Paint.valueOf("#1b8646"));
        salaryLabel.setFont(new Font(18));
        GridPane.setConstraints(salaryLabel, 0, 8);
        root.getChildren().add(salaryLabel);

        salaryField = new TextField();
        salaryField.setStyle("-fx-border-color: blue; -fx-border-width: 2;");
        GridPane.setConstraints(salaryField, 1, 8);
        root.getChildren().add(salaryField);
        salaryField.setMaxWidth(200);
        salaryField.setPrefHeight(40);
        addbtn = new Button("Add");

        addbtn.setMinSize(20, 10);
        addbtn.setPrefWidth(100); // Set the preferred width
        addbtn.setStyle("-fx-background-color: green; -fx-text-fill: black;-fx-font-size: 20px;-fx-font-weight: bold;");
        clearbtn = new Button("Clear");
        clearbtn.setStyle("-fx-background-color: green; -fx-text-fill: black;-fx-font-size: 20px;-fx-font-weight: bold;");
        clearbtn.setPrefWidth(100); // Set the preferred width



        ColumnConstraints columnConstraints = new ColumnConstraints();
        root.getColumnConstraints().addAll(columnConstraints, columnConstraints);

        RowConstraints rowConstraints = new RowConstraints();
        root.getRowConstraints().addAll(rowConstraints, rowConstraints, rowConstraints,
                rowConstraints, rowConstraints, rowConstraints, rowConstraints,
                rowConstraints, rowConstraints);
        // bpane.setCenter(root);
VBox vBox=new VBox();
vBox.getChildren().add(root);
        return vBox;
    }
    public void addStaff(){
       
        String firstName = firstNameField.getText();
        
            int employeeId = Integer.parseInt(employeeIdField.getText());


            String lastName = lastNameField.getText();
            String position = positionField.getText();
            String department = departmentField.getText();
            String phoneNumber = phoneNumberField.getText();
            String email = emailField.getText();
            String gender = genderToggleGroup.getSelectedToggle() == maleRadioButton ? "Male" : "Female";
            double salary = Double.parseDouble(salaryField.getText());
            DataBaseManegement dataBaseManegement=new DataBaseManegement();
            dataBaseManegement.insertEmployee(employeeId, firstName, lastName, position, department, phoneNumber, email, gender, salary);
            
        


    }

    public boolean fieldsAreValid() {
        // You can implement validation rules here for each field

        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String position = positionField.getText();
        String phoneNumber = phoneNumberField.getText();
        String email = emailField.getText();
        String salary = salaryField.getText();

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
        if (position.isEmpty()||!position.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(null, "Please enter a valid role.", "Error", JOptionPane.ERROR_MESSAGE);
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
            int id = Integer.parseInt(salary);
            if (id <= 0) {
                JOptionPane.showMessageDialog(null, "Please enter salary (positive double).", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid ID (numeric value).", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }




    public void clearFields() {
        // Clear input fields here
        employeeIdField.clear();
        firstNameField.clear();
        lastNameField.clear();
        positionField.clear();
        departmentField.clear();
        
        phoneNumberField.clear();
        emailField.clear();
        genderToggleGroup.selectToggle(null);
        salaryField.clear();
    }


}
