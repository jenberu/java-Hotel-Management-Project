package hotelmanagementapp;

import javafx.application.Application;

import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Hotelmanagementapp extends Application {

    private Map<String, String> userCredentials = new HashMap<>();

    public static void main(String[] args) {

        launch(args);
    }

    static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {

        LoginPage loginPage = new LoginPage();
        loginPage.getLoginpage(primaryStage);

    }
}
