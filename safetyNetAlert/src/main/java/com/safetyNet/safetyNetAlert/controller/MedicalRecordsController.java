package com.safetyNet.safetyNetAlert.controller;

import com.safetyNet.safetyNetAlert.SafetyNetData;
import com.safetyNet.safetyNetAlert.model.FireStation;
import com.safetyNet.safetyNetAlert.model.MedicalRecords;
import com.safetyNet.safetyNetAlert.model.Persons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
public class MedicalRecordsController {
    private static final Logger logger = LogManager.getLogger(MedicalRecordsController.class);
    @Autowired
    private SafetyNetData data;

    private void addMedicalRecord(MedicalRecords medicalRecord) {
        this.data.getMedicalRecords().add(medicalRecord);
    }

    private void deleteperson(Persons persons) {
        persons.setMedicalRecords(null);
    }

    private void deleteMedicalRecordByName(String firstName, String lastName, Persons person) {
        data.getMedicalRecords().removeIf(u -> u.getFirstName().equals(firstName) && u.getLastName().equals(lastName));
        if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
            this.deleteperson(person);
        }

    }

    private void updateMedicalrecord(String firstName, String lastName, MedicalRecords medicalRecords) {

        for (int i = 0; i < data.getMedicalRecords().size(); i++) {
            MedicalRecords f = data.getMedicalRecords().get(i);
            if (f.getFirstName().equals(firstName) && f.getLastName().equals(lastName)) {
                data.getMedicalRecords().get(i).updatemedicalexceptFirstNameLastName(medicalRecords.getBirthdate(), medicalRecords.getAllergies(), medicalRecords.getMedications(), medicalRecords.getAge());
                return;
            }
        }
    }

    private void update(String firstName, String lastName, MedicalRecords medicalRecords) {
        for (int j = 0; j < data.getPersons().size(); j++) {
            Persons f = data.getPersons().get(j);
            if (f.getFirstName().equals(firstName) && f.getLastName().equals(lastName)) {
                f.getMedicalRecords().updatemedicalexceptFirstNameLastName(medicalRecords.getBirthdate(), medicalRecords.getAllergies(), medicalRecords.getMedications(), medicalRecords.getAge());
                return;
            }
        }
    }

    @RequestMapping(value = "/medicalRecords", method = RequestMethod.POST)
    void addMedicalRecords(@RequestBody MedicalRecords medicalRecord) {
        logger.info("add a medical record");
        this.addMedicalRecord(medicalRecord);
    }

    @RequestMapping(value = "/medicalRecords/{firstName}/{lastName}", method = RequestMethod.DELETE)
    public void deleteMedicalRecordsByName(@PathVariable String firstName, @PathVariable String lastName) {
        logger.info("delete a medical record : "+ firstName + "\t" + lastName);
        for (Persons person : data.getPersons()) {
            this.deleteMedicalRecordByName(firstName, lastName, person);
        }
    }

    @RequestMapping(value = "/medicalRecords/{firstName}/{lastName}", method = RequestMethod.PUT)
    public void updateMedicalrecords(@PathVariable String firstName, @PathVariable String lastName, @RequestBody MedicalRecords medicalRecords) {
        logger.info("update a medical record : " + lastName + "\t" + firstName);
        for (Persons person : data.getPersons()) {
            this.updateMedicalrecord(firstName, lastName, medicalRecords);
            this.update(firstName, lastName, medicalRecords);
        }
    }
}
