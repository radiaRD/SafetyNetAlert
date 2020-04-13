package com.safetyNet.safetyNetAlert.model;

import com.safetyNet.safetyNetAlert.AgeCalculator;
import com.safetyNet.safetyNetAlert.SafetyNetData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MedicalRecords {

    private String lastName;
    private String firstName;
    private String birthdate;
    private List<String> allergies;
    private List<String> medications;
    private int age;

    public MedicalRecords() {
    }

    public MedicalRecords(String lastName, String firstName, String birthdate, List<String> allergies, List<String> medications, int age) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthdate = birthdate;
        this.allergies = allergies;
        this.medications = medications;
        this.age = age;

    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public List<String> getMedications() {
        return medications;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void updatemedicalexceptFirstNameLastName(String birthdate, List<String> allergies, List<String> medications,int age){
        this.birthdate = birthdate;
        this.allergies = allergies;
        this.medications = medications;
        this.age= age;
    }

    @Override
    public String toString() {
        return "{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthdate=" + birthdate +
                ", allergies='" + allergies + '\'' +
                ", medications='" + medications + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

}
