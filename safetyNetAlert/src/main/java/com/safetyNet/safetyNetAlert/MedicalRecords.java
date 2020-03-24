package com.safetyNet.safetyNetAlert;

import com.fasterxml.jackson.annotation.JsonFilter;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class MedicalRecords {

    private String lastName;
    private String firstName;
    private String birthdate;
    private List allergies;
    private List medications;



    public MedicalRecords() {
    }

    public MedicalRecords(String lastName, String firstName, String birthdate,List allergies, List medications )  {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthdate = birthdate;
        this.allergies = allergies;
        this.medications = medications;
    }

    public String getLastName(){
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public List getAllergies() {
        return allergies;
    }

    public List getMedications() {
        return medications;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setBirthdate(String  birthdate) {
        this.birthdate = birthdate;
    }

    public void setAllergies(List allergies) {
        this.allergies = allergies;
    }

    public void setMedications(List medications) {
        this.medications =  medications;
    }



    @Override
    public String toString() {
        return "SafetyNetMedicalRecordsModel{" +
                "lastName='" + lastName + '\'' +
            ", firstName='" + firstName + '\'' +
            ", birthdate=" + birthdate +
            ", allergies='" + allergies + '\'' +
            ", medications='" + medications + '\'' +
            '}';
}

}
