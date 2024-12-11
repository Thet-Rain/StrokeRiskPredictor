
package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Dataset {
    private List<Map<String, Object>> data;

    public Dataset(String filePath) {
        this.data = loadData(filePath);
    }

    public void preprocess() {
        for (Map<String, Object> row : data) {
            // Fill missing BMI with mean
            if (row.get("bmi") == null || row.get("bmi") == "N/A" ) {
                row.put("bmi", calculateMean("bmi"));
            }
            

            // Encode categorical variables (e.g., smoking_status)
            row.put("smoking_status", encodeSmokingStatus((String) row.get("smoking_status")));
        }
       
    }

    private double calculateMean(String key) {
        double sum = 0;
        int count = 0;
        for (Map<String, Object> row : data) {
            if (row.get(key) != null) {
                sum += (double) row.get(key);
                count++;
            }
        }
        return count > 0 ? sum / count : 0;
    }

    private double encodeSmokingStatus(String status) {
        switch (status) {
            case "formerly smoked": return 1.0;
            case "never smoked": return 0.0;
            case "smokes": return 2.0;
            default: return 0.0;
        }
    }

    private List<Map<String, Object>> loadData(String filePath) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String[] headers = br.readLine().split(",");

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Map<String, Object> row = new HashMap<>();

                for (int i = 0; i < headers.length; i++) {
                    String key = headers[i];
                    String value = values[i];
//                    System.out.println(key + " " + value); //DEBUGGING LINE
                  
                    
                    if (key.equals("id")){
                    	//row.put(key, value);
                    } else if (key.equals("bmi") || key.equals("avg_glucose_level")) {
                        row.put(key, value.equals("N/A")  ? null : Double.parseDouble(value));
                    } else if ( key.equals("hypertension") || key.equals("heart_disease") ) {
                        row.put(key, Double.parseDouble(value)); //!!
                    }
                      else if ( key.equals("age") ) {
                    	  row.put(key, isInt(value)  ? Double.parseDouble(value) : (int) Math.ceil(Double.parseDouble(value))); //!!!!!
                    } else if (key.equals("smoking_status")){
                        row.put(key, value);
                    } else if (key.equals("stroke")){
                    	row.put(key, Integer.parseInt(value));
                    }else {
                    	
                    }
                }
                dataList.add(row);
                //System.out.println(row); //DEBUGING LINE
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public List<Map<String, Object>> getData() {
        return data;
    }
    
    public boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
