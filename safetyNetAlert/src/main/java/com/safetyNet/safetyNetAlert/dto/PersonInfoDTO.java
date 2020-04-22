package com.safetyNet.safetyNetAlert.dto;

import com.safetyNet.safetyNetAlert.model.MedicalRecords;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class PersonInfoDTO {

    private static final Logger logger = LogManager.getLogger(PersonInfoDTO.class);

    private String lastName;
    private String firstName;
    private String address;
    private String email;
    private MedicalRecords medicalRecords;
    private String birthdate;
    private List<String> allergies;
    private List<String> medications;
    private int age;

    public PersonInfoDTO() {

    }

    public PersonInfoDTO(String firstName, String lastName, String address, String email, String birthdate, List<String> allergies, List<String> medications, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.birthdate = birthdate;
        this.allergies = allergies;
        this.medications = medications;
        this.age = age;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


//    public MedicalRecords getMedicalRecords() {
//        return medicalRecords;
//    }

    public void setMedicalRecords(MedicalRecords medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    public String getBirthdate() {
        return medicalRecords.getBirthdate();
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public List<String> getAllergies() {
        return medicalRecords.getAllergies();
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public List<String> getMedications() {
        return medicalRecords.getMedications();
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public int getAge() {
        return medicalRecords.getAge();
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "{" +
                " lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", medicalRecords='" + medicalRecords + '\'' +

                '}';
    }


}