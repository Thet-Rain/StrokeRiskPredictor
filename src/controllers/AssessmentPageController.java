package controllers;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.UserData;

public class AssessmentPageController {

    @FXML
    private MenuItem FAQ;

    @FXML
    private MenuItem aboutStrokeAware;

    @FXML
    private TextField ageTextField;

    @FXML
    private GridPane assessmentPagePane;

    @FXML
    private TextField bmiTextField;

    @FXML
    private Label createdByLabel;

    @FXML
    private HBox footerHBox;

    @FXML
    private ComboBox<String> genderComboBox;

    @FXML
    private Label githubLabel;

    @FXML
    private TextField glucoseTextField;

    @FXML
    private CheckBox heartDiseaseCheckBox;

    @FXML
    private MenuButton helpButton;

    @FXML
    private Button homeButton;

    @FXML
    private MenuItem howToUse;

    @FXML
    private CheckBox hypertensionCheckBox;

    @FXML
    private ComboBox<String> maritalStatusComboBox;

    @FXML
    private HBox menuBar;

    @FXML
    private Button resetButton;

    @FXML
    private ComboBox<String> residenceTypeComboBox;

    @FXML
    private ComboBox<String> smokingStatusComboBox;

    @FXML
    private Button submitButton;

    @FXML
    private Label versionLabel;

    @FXML
    private ComboBox<String> workTypeComboBox;

    @FXML
    void initialize() {

        // allows for age to be 0-120
        ageTextField.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.isEmpty()) {
                return change;
            }
            if (newText.matches("\\d{0,3}")) {
                try {
                    int ageValue = Integer.parseInt(newText);
                    if (ageValue <= 120) {
                        return change;
                    }
                } catch (NumberFormatException e) {
                    return null;
                }
            }
            return null;
        }));

        // allows for bmi to be 0.0-60.0
        bmiTextField.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.isEmpty()) {
                return change;
            }
            if (newText.matches("\\d{0,2}(\\.\\d?)?")) {
                try {
                    double bmiValue = Double.parseDouble(newText);
                    if (bmiValue <= 60) {
                        return change;
                    }
                } catch (NumberFormatException e) {
                    return null;
                }
            }
            return null;
        }));

        // allows for glucose to be 0.00-300.00
        glucoseTextField.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.isEmpty()) {
                return change;
            }
            if (newText.matches("\\d{0,3}(\\.\\d{0,2})?")) {
                try {
                    double averageGlucoseValue = Double.parseDouble(newText);
                    if (averageGlucoseValue <= 300) {
                        return change;
                    }
                } catch (NumberFormatException e) {
                    return null;
                }
            }
            return null;
        }));

        // combobox initialization
        genderComboBox.getItems().removeAll(genderComboBox.getItems());
        genderComboBox.getItems().addAll("Male", "Female", "Other");

        smokingStatusComboBox.getItems().removeAll(smokingStatusComboBox.getItems());
        smokingStatusComboBox.getItems().addAll("Formerly Smoked", "Never Smoked", "Smokes", "Unknown");

        maritalStatusComboBox.getItems().removeAll(maritalStatusComboBox.getItems());
        maritalStatusComboBox.getItems().addAll("Yes", "No");

        workTypeComboBox.getItems().removeAll(workTypeComboBox.getItems());
        workTypeComboBox.getItems().addAll("Private Company", "Government Job", "Self-Employed", "Never Worked",
                "Children");

        residenceTypeComboBox.getItems().removeAll(residenceTypeComboBox.getItems());
        residenceTypeComboBox.getItems().addAll("Rural", "Urban");
    }

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
    void onResetButtonClick(ActionEvent event) {
        // textfields
        ageTextField.clear();
        bmiTextField.clear();
        glucoseTextField.clear();

        // comboboxes
        genderComboBox.setValue(null);
        smokingStatusComboBox.setValue(null);
        maritalStatusComboBox.setValue(null);
        workTypeComboBox.setValue(null);
        residenceTypeComboBox.setValue(null);

        // checkboxes
        hypertensionCheckBox.setSelected(false);
        heartDiseaseCheckBox.setSelected(false);
    }

    @FXML
    void onSubmitButtonClick(ActionEvent event) {
        try {
            String age = ageTextField.getText();
            String gender = genderComboBox.getValue();
            String bmi = bmiTextField.getText();
            String glucose = glucoseTextField.getText();
            String smokingStatus = smokingStatusComboBox.getValue();
            boolean hasHypertension = hypertensionCheckBox.isSelected();
            boolean hasHeartDisease = heartDiseaseCheckBox.isSelected();
            String maritalStatus = maritalStatusComboBox.getValue();
            String workType = workTypeComboBox.getValue();
            String residenceType = residenceTypeComboBox.getValue();

            // error if any field is empty
            if (age.isEmpty() || gender == null || bmi.isEmpty() || glucose.isEmpty() || smokingStatus == null ||
                    maritalStatus == null || workType == null || residenceType == null) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Input Validation Error");
                alert.setHeaderText("Incomplete Fields");
                alert.setContentText("Please fill out all fields before submitting.");
                alert.showAndWait();
                return;

            }

            int ageValue = Integer.parseInt(age);
            double bmiValue = Double.parseDouble(bmi);
            double glucoseValue = Double.parseDouble(glucose);

            // BEGIN DATA PROCESSING

            UserData userData = new UserData(ageValue, gender, bmiValue, glucoseValue, smokingStatus, hasHypertension,
                    hasHeartDisease,
                    maritalStatus, workType, residenceType);

            // PROCESS DATA HERE
            // *******************************************************************************************************
            boolean isAtElevatedRisk = false; // PUT METHOD TO ASSESS ELEVATED RISK HERE

            if (isAtElevatedRisk) {
                swapScene((Stage) submitButton.getScene().getWindow(), "/views/elevatedRisk.css",
                        "/views/ElevatedRisk.fxml", userData);
            } else {
                swapScene((Stage) submitButton.getScene().getWindow(), "/views/normalRisk.css",
                        "/views/NormalRisk.fxml");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid numeric input. Please check your entries.");
        }
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

    void swapScene(Stage currentStage, String cssPage, String fxmlPage, UserData userData) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPage));
            Scene newScene = new Scene(loader.load());

            // pass user data
            Object controller = loader.getController();
            if (controller instanceof ElevatedRiskController) {
                ((ElevatedRiskController) controller).setUserData(userData);
            }

            double currentX = currentStage.getX();
            double currentY = currentStage.getY();
            double currentWidth = currentStage.getWidth();
            double currentHeight = currentStage.getHeight();
            currentStage.setScene(newScene);
            currentStage.setX(currentX);
            currentStage.setY(currentY);
            currentStage.setWidth(currentWidth);
            currentStage.setHeight(currentHeight);
            String css = getClass().getResource(cssPage).toExternalForm();
            newScene.getStylesheets().add(css);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
