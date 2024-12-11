package controllers;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NormalRiskController {

    @FXML
    private MenuItem FAQ;

    @FXML
    private MenuItem aboutStrokeAware;

    @FXML
    private Label createdByLabel;

    @FXML
    private HBox footerHBox;

    @FXML
    private Label githubLabel;

    @FXML
    private MenuButton helpButton;

    @FXML
    private Button homeButton;

    @FXML
    private MenuItem howToUse;

    @FXML
    private HBox menuBar;

    @FXML
    private GridPane normalRiskPagePane;

    @FXML
    private Label textLabel;

    @FXML
    private VBox tipBodyText;

    @FXML
    private Label tipHeaderLabel;

    @FXML
    private Label titleLabel;

    @FXML
    private Label versionLabel;

    @FXML
    void onAboutStrokeAwareClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About StrokeAware");
        alert.setHeaderText("About StrokeAware");
        alert.setContentText(
                "StrokeAware helps assess your stroke risk using a machine learning model based on your input data.");
        alert.showAndWait();
    }

    @FXML
    void onFaqClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("FAQs");
        alert.setHeaderText("Frequently Asked Questions");
        alert.setContentText(
                "Q: What is BMI?\n" +
                        "A: BMI stands for Body Mass Index. It is used to calculate obesity by comparing weight to height.\n\n"
                        +
                        "Q: What does Average Glucose Level mean?\n" +
                        "A: It refers to your average blood sugar level, typically measured in mg/dL.\n\n" +
                        "Q: Who should use StrokeAware?\n" +
                        "A: StrokeAware is designed for anyone who wants to assess their risk of stroke based on personal health and lifestyle data.\n\n"
                        +
                        "Q: How accurate is StrokeAware's prediction?\n" +
                        "A: StrokeAware provides an estimated risk using a machine learning model, but should not replace professional medical advice or diagnosis.\n\n"
                        +
                        "Q: Can StrokeAware diagnose a stroke?\n" +
                        "A: No, the app cannot diagnose a stroke. It only offers an assessment based on your provided data.\n\n"
                        +
                        "Q: Why is smoking status important?\n" +
                        "A: Smoking significantly increases the risk of stroke. Including this information improves the accuracy of the risk assessment.\n\n"
                        +
                        "Q: What does Residence Type mean?\n" +
                        "A: Residence Type refers to whether you live in an urban or rural area, as location and lifestyle can influence stroke risks.\n\n"
                        +
                        "Q: What happens to my data after I submit it?\n" +
                        "A: Your data is processed locally and is not stored or shared with any external service.\n\n" +
                        "Q: How do I reset the input fields?\n" +
                        "A: Use the Reset button at the bottom of the form to clear all fields and start over.\n\n" +
                        "Q: Why can't I submit the form?\n" +
                        "A: Ensure all required fields are filled correctly. Double-check for valid values in fields like Age, BMI, and Average Glucose Level.\n\n"
                        +
                        "Q: Where can I learn more about stroke prevention?\n" +
                        "A: Visit trusted sources like the American Stroke Association (stroke.org) or consult with your healthcare provider.");
        alert.showAndWait();
    }

    @FXML
    void onHomeButtonClick(ActionEvent event) {
        swapScene((Stage) homeButton.getScene().getWindow(), "/views/landingPage.css", "/views/landingPage.fxml");
    }

    @FXML
    void onHowToUseClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("How to Use StrokeAware");
        alert.setHeaderText("How to Use StrokeAware");
        alert.setContentText(
                "1. Fill out the required fields.\n2. Click Submit to calculate your risk.\n3. Review the results.\n");
        alert.showAndWait();
    }

    @FXML
    void openGitHub(MouseEvent event) {
        try {
            String url = "https://github.com/mcbaer23812/StrokePredictor";
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void swapScene(Stage currentStage, String cssPage, String fxmlPage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPage));
            Scene newScene = new Scene(loader.load());
            double currentX = currentStage.getX();
            double currentY = currentStage.getY();
            double currentWidth = currentStage.getWidth();
            double currentHeight = currentStage.getHeight();
            currentStage.setScene(newScene);
            currentStage.setX(currentX);
            currentStage.setY(currentY);
            currentStage.setWidth(currentWidth);
            currentStage.setHeight(currentHeight);
            currentStage.setScene(newScene);
            String css = getClass().getResource(cssPage).toExternalForm();
            newScene.getStylesheets().add((css));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
