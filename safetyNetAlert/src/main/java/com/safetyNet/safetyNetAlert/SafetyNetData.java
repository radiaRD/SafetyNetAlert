package com.safetyNet.safetyNetAlert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetyNet.safetyNetAlert.controller.SafetyNetController;
import com.safetyNet.safetyNetAlert.model.FireStation;
import com.safetyNet.safetyNetAlert.model.MedicalRecords;
import com.safetyNet.safetyNetAlert.model.Persons;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
public class SafetyNetData {

    private static final Logger logger = LogManager.getLogger(SafetyNetData.class);

    private List<Persons> persons = new ArrayList<>();
    private List<MedicalRecords> medicalRecords = new ArrayList<>();
    private List<FireStation> fireStations = new ArrayList<>();
    private int age;

    public List<Persons> readJsonFilePersons() throws IOException, ParseException {
        ReadDataFromJsonFile data = new ReadDataFromJsonFile();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(data.readJsonFileData());
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray listpersons = (JSONArray) jsonObject.get("persons");
        ObjectMapper mapper = new ObjectMapper();
        ListIterator iterator = listpersons.listIterator();
        while (iterator.hasNext()) {
            persons.add(mapper.readValue(iterator.next().toString(), Persons.class));
        }
        List<Persons> listePerson = new ArrayList<>(persons);
        return listePerson;
    }

    public List<FireStation> readJsonFileStation() throws IOException, ParseException {
        ReadDataFromJsonFile data = new ReadDataFromJsonFile();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(data.readJsonFileData());
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray listStations = (JSONArray) jsonObject.get("firestations");
        ObjectMapper mapper = new ObjectMapper();
        ListIterator iterator = listStations.listIterator();
        while (iterator.hasNext()) {
            fireStations.add(mapper.readValue(iterator.next().toString(), FireStation.class));

        }
        List<FireStation> fireStation = new ArrayList<>(fireStations);
        return fireStation;
    }


    public List<MedicalRecords> readJsonFileMedicalrecords() throws IOException, ParseException {
        ReadDataFromJsonFile data = new ReadDataFromJsonFile();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(data.readJsonFileData());
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray listMedical = (JSONArray) jsonObject.get("medicalrecords");
        ObjectMapper mapper = new ObjectMapper();
        ListIterator iterator = listMedical.listIterator();
        while (iterator.hasNext()) {
            medicalRecords.add(mapper.readValue(iterator.next().toString(), MedicalRecords.class));

        }
        List<MedicalRecords> listMedical1 = new ArrayList<MedicalRecords>(medicalRecords);
        return listMedical1;
    }

    public void initData() {
        try {
            this.readJsonFileStation();
            this.readJsonFilePersons();
            this.readJsonFileMedicalrecords();
        } catch (IOException e) {
            logger.error("Error initializing lists persons, firestation and medicalrecords");
            e.printStackTrace();
        } catch (ParseException e) {
            logger.error("Error initializing lists persons, firestation and medicalrecords");
            e.printStackTrace();
        }
    }

    public void linkList() {
        for (Persons person : persons) {

            for (FireStation station : fireStations) {
                if (person.getAddress().equals(station.getAddress())) {
                    person.setStation(station);
                    break;
                }

            }

            for (MedicalRecords medicalRecord : medicalRecords) {
                if (medicalRecord.getFirstName().equals(person.getFirstName()) && medicalRecord.getLastName().equals(person.getLastName())) {
                    LocalDate currentDate = LocalDate.now();
                    String birthDate = medicalRecord.getBirthdate();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");
                    LocalDate localDate = LocalDate.parse(birthDate, formatter);
                    medicalRecord.setAge(AgeCalculator.calculateAge(localDate, currentDate));
                    person.setMedicalRecords(medicalRecord);

                    break;
                }
            }
        }
    }

    public void dataEmpty() throws IOException, ParseException {
        persons.clear();
        fireStations.clear();
        medicalRecords.clear();
        this.readJsonFilePersons();
        this.readJsonFileStation();
        this.readJsonFileMedicalrecords();
        this.linkList();
    }

    public List<Persons> getPersons() {
        return persons;
    }

    public void setPersons(List<Persons> persons) {
        this.persons = persons;
    }

    public List<MedicalRecords> getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(List<MedicalRecords> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    public List<FireStation> getFireStations() {
        return fireStations;
    }

    public void setFireStations(List<FireStation> fireStations) {
        this.fireStations = fireStations;
    }


}