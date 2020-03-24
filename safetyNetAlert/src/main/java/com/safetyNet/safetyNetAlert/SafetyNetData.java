package com.safetyNet.safetyNetAlert;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class SafetyNetData {



    public List readJsonFilePersons() throws IOException, ParseException {
        ReadDataFromJsonFile data = new ReadDataFromJsonFile();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse( data.readJsonFileData());
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray listpersons = (JSONArray) jsonObject.get("persons");
        ObjectMapper mapper = new ObjectMapper();
        ListIterator iterator = listpersons.listIterator();
        while (iterator.hasNext()) {
            iterator.set(mapper.readValue(iterator.next().toString(), Persons.class));
        }
        List<Object> listePerson = new ArrayList<>(listpersons);
        return listpersons;
    }


    public List readJsonFileStation() throws IOException, ParseException {
        ReadDataFromJsonFile data = new ReadDataFromJsonFile();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse( data.readJsonFileData());
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray listStations = (JSONArray) jsonObject.get("firestations");
        ObjectMapper mapper = new ObjectMapper();
        ListIterator iterator = listStations.listIterator();
        while (iterator.hasNext()) {
            iterator.set(mapper.readValue(iterator.next().toString(), FireStation.class));

        }
        List<Object> fireStation = new ArrayList<>(listStations);
        return fireStation;
    }


    public List readJsonFileMedicalrecords() throws IOException, ParseException {
        ReadDataFromJsonFile data = new ReadDataFromJsonFile();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(data.readJsonFileData());
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray listMedical = (JSONArray) jsonObject.get("medicalrecords");
        ObjectMapper mapper = new ObjectMapper();
        ListIterator iterator = listMedical.listIterator();
        while (iterator.hasNext()) {
            iterator.set(mapper.readValue(iterator.next().toString(), MedicalRecords.class));

        }
        return listMedical;
    }


}
