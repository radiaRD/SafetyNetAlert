package com.safetyNet.safetyNetAlert;

import org.json.simple.parser.ParseException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
public class SafetyNetController {

    private List<Persons> persons;
    private List<FireStation> firestations;
    private List<MedicalRecords> medicalRecords;

    public SafetyNetController() {
    }

    @Autowired
    public SafetyNetController(List<Persons> persons) {
        this.persons = persons;
    }


    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    @ResponseBody
    public List<Persons> getPersons() throws IOException, ParseException {
        SafetyNetData persons = new SafetyNetData();
        return persons.readJsonFilePersons();
    }

    @RequestMapping(value = "/fireStation", method = GET)
    @ResponseBody
    public List<FireStation> getFirestations() throws IOException, ParseException {
        SafetyNetData firestations = new SafetyNetData();
        return firestations.readJsonFileStation();
    }

    @RequestMapping(value = "/medicalRecords", method = GET)
    @ResponseBody
    public List<MedicalRecords> getMedicalRecords() throws IOException, ParseException {
        SafetyNetData medicalRecords = new SafetyNetData();
        return medicalRecords.readJsonFileMedicalrecords();
    }


    @RequestMapping(value = "/fireStation/{station}", method = GET)
    @ResponseBody
    public List<UserDTO> getUserByStationNumber(@PathVariable int station) throws IOException, ParseException {
        // on recupere les firestations
        SafetyNetData firestations = new SafetyNetData();
        List<FireStation> fireStations = firestations.readJsonFileStation();

        List<FireStation> fireStation2 = fireStations.stream()    // converting the list to stream
                .filter(f -> f.getStation() == station)   // filter the stream to create a new stream
                .collect(Collectors.toList());  // collect the final stream and convert it to a List}
        Set<String> fireStation3 =
                fireStation2.stream()
                        .map(FireStation::getAddress)
                        .collect(Collectors.toSet());
        List<Persons> persons = getPersons();
        List<Persons> resultOut = persons.stream()
                .filter(e -> fireStation3.contains(e.getAddress()))
                .collect(Collectors.toList());


        ModelMapper modelMapper = new ModelMapper();
        List<UserDTO> user = Arrays.asList(modelMapper.map(resultOut, UserDTO[].class));

        return user;

    }

    @RequestMapping(value = "/communityEmail/{city}", method = GET)
    @ResponseBody
    public Set<String> getEmailByCity(@PathVariable("city") String city) throws IOException, ParseException {
        List<Persons> persons = getPersons();
        List<Persons> persons1 = persons.stream()
                .filter(f -> f.getCity().equals(city))   // filter the stream to create a new stream
                .collect(Collectors.toList());
        Set<String> emailPerson =
                persons1.stream()
                        .map(Persons::getEmail)
                        .collect(Collectors.toSet());
        return emailPerson;
    }


    @RequestMapping(value = "/phoneAlert/{station}", method = GET)
    @ResponseBody
    public Set<String> getPhoneNumberByStation(@PathVariable int station) throws IOException, ParseException {
        // on recupere les firestations
        SafetyNetData firestations = new SafetyNetData();
        List<FireStation> fireStations = firestations.readJsonFileStation();

        List<FireStation> fireStation2 = fireStations.stream()    // converting the list to stream
                .filter(f -> f.getStation() == station)   // filter the stream to create a new stream
                .collect(Collectors.toList());  // collect the final stream and convert it to a List}
        Set<String> fireStation3 =
                fireStation2.stream()
                        .map(FireStation::getAddress)
                        .collect(Collectors.toSet());
        List<Persons> persons = getPersons();
        List<Persons> resultOut = persons.stream()
                .filter(e -> fireStation3.contains(e.getAddress()))
                .collect(Collectors.toList());
        Set<String> phoneNumber =
                resultOut.stream()
                        .map(Persons::getPhone)
                        .collect(Collectors.toSet());

        return phoneNumber;
    }


}

