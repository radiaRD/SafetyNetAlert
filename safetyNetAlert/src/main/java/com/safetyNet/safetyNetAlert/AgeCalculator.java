package com.safetyNet.safetyNetAlert;

import com.safetyNet.safetyNetAlert.model.MedicalRecords;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;


public class AgeCalculator {

    LocalDate currentDate = LocalDate.now();
    MedicalRecords medicalRecords = new MedicalRecords();
    String birthDate = medicalRecords.getBirthdate();
    @Autowired
    int age;


    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
    String date = birthDate;

    //convert String to LocalDate
    LocalDate localDate = LocalDate.parse(date, formatter);

    public AgeCalculator(int localDate) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public static int calculateAge(LocalDate localDate, LocalDate currentDate) {
        if ((localDate != null) && (currentDate != null)) {
            return Period.between(localDate, currentDate).getYears();
        } else {
            return 0;
        }
    }
}



