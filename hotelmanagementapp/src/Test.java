import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Test extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Employee Work Schedule");

        // Create the root BorderPane
        BorderPane root = new BorderPane();
        root.setStyle("-fx-border-color: blue; -fx-background-color: lightblue;");

        // Create the left GridPane
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setStyle("-fx-background-color: pink;");
        gridPane.setPadding(new Insets(10));

        // Add column constraints
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col1.setMaxWidth(120);
        col1.setMinWidth(10);
        col1.setPrefWidth(54);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col2.setMaxWidth(166);
        col2.setMinWidth(10);
        col2.setPrefWidth(166);

        gridPane.getColumnConstraints().addAll(col1, col2);

        // Add row constraints
        RowConstraints row1 = new RowConstraints();
        row1.setMaxHeight(235);
        row1.setMinHeight(10);
        row1.setPrefHeight(126);

        RowConstraints row2 = new RowConstraints();
        row2.setMaxHeight(302);
        row2.setMinHeight(10);
        row2.setPrefHeight(104);

        RowConstraints row3 = new RowConstraints();
        row3.setMaxHeight(375);
        row3.setMinHeight(10);
        row3.setPrefHeight(312);

        gridPane.getRowConstraints().addAll(row1, row2, row3);

        // Create labels, text fields, and button
        Label enterIdLabel = new Label("Enter ID:");
        enterIdLabel.setFont(new Font(24));
        enterIdLabel.setTextFill(javafx.scene.paint.Color.web("#26b558"));

        TextField employeeIdTextField = new TextField();
        employeeIdTextField.setPrefHeight(39);
        employeeIdTextField.setPrefWidth(148);
        employeeIdTextField.setStyle("-fx-border-color: blue;");

        Label workScheduleLabel = new Label("Work Schedule");
        workScheduleLabel.setFont(new Font(24));
        workScheduleLabel.setTextFill(javafx.scene.paint.Color.web("#08d026"));
        GridPane.setColumnIndex(workScheduleLabel, 1);

        Label seeYourLabel = new Label("See your");
        seeYourLabel.setFont(new Font(24));
        seeYourLabel.setTextFill(javafx.scene.paint.Color.web("#16d766"));

        Button submitButton = new Button("Submit");
        submitButton.setFont(new Font("System Bold", 24));
        submitButton.setStyle("-fx-background-color: green;");
        submitButton.setTextFill(javafx.scene.paint.Color.web("#2126b5"));
        GridPane.setColumnIndex(submitButton, 1);
        GridPane.setRowIndex(submitButton, 2);
        GridPane.setValignment(submitButton, javafx.geometry.VPos.TOP);
        submitButton.setOnAction(event -> submitButtonHandler());

        // Add nodes to the grid pane
        gridPane.add(enterIdLabel, 0, 1);
        gridPane.add(employeeIdTextField, 1, 1);
        gridPane.add(workScheduleLabel, 1, 0);
        gridPane.add(seeYourLabel, 0, 0);
        gridPane.add(submitButton, 1, 2);

        // Set the left grid pane as the root's left component
        root.setLeft(gridPane);

        // Create the top HBox
        HBox topHBox = new HBox();
        topHBox.setStyle("-fx-background-color: brown;");
        topHBox.setPadding(new Insets(10));

        // Create the label for the top HBox
        Label titleLabel = new Label("Employee page to see their Work Schedule");
        titleLabel.setFont(new Font("System Bold", 36));
        titleLabel.setTextFill(javafx.scene.paint.Color.web("#214dd1"));
        topHBox.getChildren().add(titleLabel);

        // Set the top HBox as the root's top component
        root.setTop(topHBox);

        // Create the scene
        Scene scene = new Scene(root, 1000, 800);

        // Set the scene on the primary stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void submitButtonHandler() {
        // Handle submit button click event
        System.out.println("Submit button clicked");
    }
}