package models;

public class UserData {
    private int age;
    private String gender;
    private double bmi;
    private double glucose;
    private String smokingStatus;
    private boolean hasHypertension;
    private boolean hasHeartDisease;
    private String maritalStatus;
    private String workType;
    private String residenceType;

    public UserData(int age, String gender, double bmi, double glucose, String smokingStatus,
            boolean hasHypertension, boolean hasHeartDisease, String maritalStatus,
            String workType, String residenceType) {
        this.age = age;
        this.gender = gender;
        this.bmi = bmi;
        this.glucose = glucose;
        this.smokingStatus = smokingStatus;
        this.hasHypertension = hasHypertension;
        this.hasHeartDisease = hasHeartDisease;
        this.maritalStatus = maritalStatus;
        this.workType = workType;
        this.residenceType = residenceType;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public double getGlucose() {
        return glucose;
    }

    public void setGlucose(double glucose) {
        this.glucose = glucose;
    }

    public String getSmokingStatus() {
        return smokingStatus;
    }

    public void setSmokingStatus(String smokingStatus) {
        this.smokingStatus = smokingStatus;
    }

    public boolean hasHypertension() {
        return hasHypertension;
    }

    public void setHasHypertension(boolean hasHypertension) {
        this.hasHypertension = hasHypertension;
    }

    public boolean hasHeartDisease() {
        return hasHeartDisease;
    }

    public void setHasHeartDisease(boolean hasHeartDisease) {
        this.hasHeartDisease = hasHeartDisease;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getResidenceType() {
        return residenceType;
    }

    public void setResidenceType(String residenceType) {
        this.residenceType = residenceType;
    }

}
