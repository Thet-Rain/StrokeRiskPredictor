
package models;

import java.io.*;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.*;


class StrokePrediction {

    public static void main(String[] args) {
        try {
        	File currentDir = new File(""); //PlaceHolder
        	System.out.println(currentDir.getAbsolutePath());
            // Step 1: Load and preprocess the dataset
        	String filePath = currentDir.getAbsolutePath() + "\\src\\models\\models\\stroke-data2.csv";
        	System.out.println(filePath);
        		
            Dataset dataset = new Dataset(filePath);
            dataset.preprocess();

            // Step 2: Bootstrap the dataset for Random Forest
            int numTrees = 100;
            RandomForest randomForest = new RandomForest(numTrees);

            randomForest.train(dataset);

            // Step 3: Save the Random Forest
            String RFmodelPath = currentDir.getAbsolutePath() + "\\src\\models\\models\\random_forest_model.dat";
            randomForest.saveForest(RFmodelPath);
            System.out.println("Random Forest model saved to: " + RFmodelPath);

            // Step 4: Load the Random Forest
            RandomForest loadedForest = RandomForest.loadForest(RFmodelPath);
            System.out.println("Random Forest model loaded successfully.");

            // Step 5: Predict stroke risk for a test data point
            Map<String, Double> testData = new HashMap<>();
            testData.put("age", 33.0);
            testData.put("hypertension", 1.0);
            testData.put("heart_disease", 1.0);
            testData.put("avg_glucose_level", 200.5);
            testData.put("bmi", 25.5);
            testData.put("smoking_status", 3.0);

            int prediction = loadedForest.predict(testData);
            System.out.println("Predicted Stroke Risk: " + prediction);
            switch(prediction) {
            case 1 :  System.out.println("You have a very low risk. you ok!");
            			break;
            case 2 :  System.out.println("You have a very moderate risk. Please start exercising!");
						break;
            case 3 :  System.out.println("You are at highrisk group of getting a stroke! GO SEE A DOCTOR!");
						break;
            }
           

            // Step 6: Visualize the first decision tree
//            System.out.println("Visualization of the first tree:");
//            loadedForest.printTree(0);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}