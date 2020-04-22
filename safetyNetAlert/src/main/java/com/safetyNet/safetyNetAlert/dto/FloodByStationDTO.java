package com.safetyNet.safetyNetAlert.dto;

import com.safetyNet.safetyNetAlert.model.MedicalRecords;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FloodByStationDTO {
    private static final Logger logger = LogManager.getLogger(FloodByStationDTO.class);

    private String lastName;
    private String phone;
    private int age;
    private MedicalRecords medicalRecords;
    private List<String> allergies;
    private List<String> medications;

    public FloodByStationDTO() {

    }

    public FloodByStationDTO(String lastName, String phone, int age, MedicalRecords medicalRecords, List<String> allergies, List<String> medications) {
        this.lastName = lastName;
        this.phone = phone;
        this.age = age;
        this.medicalRecords = medicalRecords;
        this.allergies = allergies;
        this.medications = medications;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return medicalRecords.getAge();
    }

    public void setAge(int age) {
        this.age = age;
    }

//    public MedicalRecords getMedicalRecords() {
//        return medicalRecords;
//    }

    public void setMedicalRecords(MedicalRecords medicalRecords) {
        this.medicalRecords = medicalRecords;
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
}
