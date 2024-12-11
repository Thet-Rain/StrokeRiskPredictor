package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {

        Parent root;
        try {
            //resizable, but forced to be 1024x768 min
            primaryStage.setResizable(true);
            primaryStage.setMinWidth(1024);
            primaryStage.setMinHeight(768);
            primaryStage.setTitle("Stroke Risk Predictor");
            root = FXMLLoader.load(getClass().getResource("/views/LandingPage.fxml"));
            Scene scene = new Scene(root,1024,768);
            String landingPageCSS = this.getClass().getResource("/views/landingPage.css").toExternalForm();
            scene.getStylesheets().add(landingPageCSS);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
