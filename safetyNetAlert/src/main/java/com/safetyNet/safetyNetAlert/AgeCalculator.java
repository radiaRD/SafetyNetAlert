package com.safetyNet.safetyNetAlert;

import com.safetyNet.safetyNetAlert.model.MedicalRecords;


import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AgeCalculator {

    private static final Logger logger = LogManager.getLogger(AgeCalculator.class);

    private LocalDate currentDate = LocalDate.now();
    private MedicalRecords medicalRecords = new MedicalRecords();
    private String birthDate = medicalRecords.getBirthdate();
    private int age;


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



