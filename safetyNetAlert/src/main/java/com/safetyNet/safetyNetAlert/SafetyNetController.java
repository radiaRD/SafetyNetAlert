package com.safetyNet.safetyNetAlert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.jsoniter.JsonIterator;
import org.apache.catalina.connector.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static org.apache.el.util.MessageFactory.get;


@RestController
public class SafetyNetController {

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public ListIterator readJsonFilePersons() throws IOException, ParseException {
        readDataFromJsonFile data = new  readDataFromJsonFile();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse( data.readJsonFileData());
        JSONObject jsonObject = (JSONObject) obj;
        ObjectMapper mapper = new ObjectMapper();
        JSONArray Listpersons = (JSONArray) jsonObject.get("persons");
        ListIterator iterator = Listpersons.listIterator();
        while (iterator.hasNext()) {
           iterator.set(mapper.readValue(iterator.next().toString(), SafetyNetPersonsModel.class));
        }
        return Listpersons.listIterator();
    }

    @RequestMapping(value = "/fireStations", method = RequestMethod.GET)
    public ListIterator readJsonFileStation() throws IOException, ParseException {
        readDataFromJsonFile data = new  readDataFromJsonFile();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse( data.readJsonFileData());
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray ListStations = (JSONArray) jsonObject.get("firestations");
        ObjectMapper mapper = new ObjectMapper();
        ListIterator iterator = ListStations.listIterator();
        while (iterator.hasNext()) {
            iterator.set(mapper.readValue(iterator.next().toString(), SafetyNetFireStationModel.class));

        }
        return ListStations.listIterator();
    }


    @RequestMapping(value = "/medicalRecords", method = RequestMethod.GET)
    public ListIterator readJsonFileMedicalrecords() throws IOException, ParseException {
        readDataFromJsonFile data = new  readDataFromJsonFile();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(data.readJsonFileData());
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray ListMedical = (JSONArray) jsonObject.get("medicalrecords");
        ObjectMapper mapper = new ObjectMapper();
        ListIterator iterator = ListMedical.listIterator();
        while (iterator.hasNext()) {
           iterator.set(mapper.readValue(iterator.next().toString(), SafetyNetMedicalRecordsModel.class));

        }
        return ListMedical.listIterator();
    }

//filtrer les données pour obtenir les email de tous les habitants de la ville
@RequestMapping(value = "/email", method = RequestMethod.GET)
public MappingJacksonValue communityEmail() throws IOException, ParseException {
    readDataFromJsonFile data = new  readDataFromJsonFile();
    JSONParser parser = new JSONParser();
    Object obj = parser.parse(data.readJsonFileData());
    JSONObject jsonObject = (JSONObject) obj;
    JSONArray Listpersons = (JSONArray) jsonObject.get("persons");
    ObjectMapper mapper = new ObjectMapper();
    ListIterator iterator = Listpersons.listIterator();
    while (iterator.hasNext()) {
        iterator.set(mapper.readValue(iterator.next().toString(), SafetyNetPersonsModel.class));
    }

    SimpleBeanPropertyFilter monFiltre = SimpleBeanPropertyFilter.filterOutAllExcept("email");

    FilterProvider listDeNosFiltres = new SimpleFilterProvider().addFilter("monFiltreDynamique", monFiltre);

    MappingJacksonValue personFiltres = new MappingJacksonValue(Listpersons.listIterator());

    personFiltres.setFilters(listDeNosFiltres);

    return personFiltres;
}

}


