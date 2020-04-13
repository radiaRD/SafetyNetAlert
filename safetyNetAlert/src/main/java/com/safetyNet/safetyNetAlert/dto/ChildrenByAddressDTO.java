package com.safetyNet.safetyNetAlert.dto;

import com.safetyNet.safetyNetAlert.model.MedicalRecords;

public class ChildrenByAddressDTO {
    private String lastName;
    private String firstName;
    private int age;
    private MedicalRecords medicalRecords;

    public ChildrenByAddressDTO(){}

    public ChildrenByAddressDTO(String lastName, String firstName, int age, MedicalRecords medicalRecords) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.medicalRecords = medicalRecords;
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
}


