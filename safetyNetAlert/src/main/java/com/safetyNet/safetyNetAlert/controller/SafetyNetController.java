package com.safetyNet.safetyNetAlert.controller;

import com.safetyNet.safetyNetAlert.SafetyNetData;
import com.safetyNet.safetyNetAlert.dto.*;
import com.safetyNet.safetyNetAlert.model.FireStation;
import com.safetyNet.safetyNetAlert.model.MedicalRecords;
import com.safetyNet.safetyNetAlert.model.Persons;
import org.json.simple.parser.ParseException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
public class SafetyNetController {

    private static final Logger logger = LogManager.getLogger(SafetyNetController.class);

    @Autowired
    private SafetyNetData data;


    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    @ResponseBody
    public List<Persons> getPersons() throws IOException, ParseException {
        logger.info("get list of persons");
        return data.getPersons();
    }


    @RequestMapping(value = "/fireStation", method = GET)
    @ResponseBody
    public List<FireStation> getFirestations() throws IOException, ParseException {
        logger.info("get list of fire stations");
        return data.getFireStations();
    }

    @RequestMapping(value = "/medicalRecords", method = GET)
    @ResponseBody
    public List<MedicalRecords> getMedicalRecords() throws IOException, ParseException {
        logger.info("get list of medical records");
        return data.getMedicalRecords();
    }


    @RequestMapping(value = "/fireStation/{station}", method = GET)
    @ResponseBody
    public UsersCountDTO getPersonsByStationNumber(@PathVariable int station) throws IOException, ParseException {
        logger.info("get list of persons covered by fire station : " + station);
        List<FireStation> fireStations = getFirestations();

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

        List<Persons> result = resultOut.stream()
                .filter(f -> 18 >= f.getMedicalRecords().getAge())
                .collect(Collectors.toList());

        List<Persons> result1 = resultOut.stream()
                .filter(f -> 18 < f.getMedicalRecords().getAge())
                .collect(Collectors.toList());

        ModelMapper modelMapper = new ModelMapper();
        UsersCountDTO users = new UsersCountDTO(Arrays.asList(modelMapper.map(resultOut, UserDTO[].class)), result.size(), result1.size());
        return users;

    }

    @RequestMapping(value = "/communityEmail/{city}", method = GET)
    @ResponseBody
    public Set<String> getEmailByCity(@PathVariable("city") String city) throws IOException, ParseException {
        logger.info("get list of emails persons by city : " +city);
        List<Persons> persons = getPersons();
        List<Persons> persons1 = persons.stream()
                .filter(f -> f.getCity().equals(city))
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
        logger.info("get list of phone numbers by station number : "+ station);
        List<FireStation> fireStations = data.getFireStations();

        List<FireStation> fireStation2 = fireStations.stream()
                .filter(f -> f.getStation() == station)
                .collect(Collectors.toList());
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

    @RequestMapping(value = "/personInfo/{firstName}/{lastName}", method = GET)
    @ResponseBody
    public List<PersonInfoDTO> getpersonInfoByName(@PathVariable String lastName) throws IOException, ParseException {
        logger.info("get information by last name : "+ lastName);
        List<Persons> resultOut = getPersons().stream()
                .filter(f -> f.getLastName().equals(lastName))
                .collect(Collectors.toList());
        ModelMapper modelMapper = new ModelMapper();
        List<PersonInfoDTO> user = Arrays.asList(modelMapper.map(resultOut, PersonInfoDTO[].class));
        return user;

    }


    @RequestMapping(value = "/fire/{address}", method = GET)
    @ResponseBody
    public List<PersonsByAddressDTO> getPersonsByAddress(@PathVariable String address) throws IOException, ParseException {
        logger.info("get list persons and fire station number by address : " + address);
        List<FireStation> fireStations = data.getFireStations();
        List<FireStation> fireStation2 = fireStations.stream()
                .filter(f -> f.getAddress().equals(address))
                .collect(Collectors.toList());
        Set<String> fireStation3 =
                fireStation2.stream()
                        .map(FireStation::getAddress)
                        .collect(Collectors.toSet());
        List<Persons> persons = getPersons();
        List<Persons> resultOut = persons.stream()
                .filter(e -> fireStation3.contains(e.getAddress()))
                .collect(Collectors.toList());

        ModelMapper modelMapper = new ModelMapper();
        List<PersonsByAddressDTO> user = Arrays.asList(modelMapper.map(resultOut, PersonsByAddressDTO[].class));
        return user;
    }

    @RequestMapping(value = "/childAlert/{address}", method = GET)
    @ResponseBody
    public List<ChildrenByAddressDTO> getChildrenByAddress(@PathVariable String address) throws IOException, ParseException {
        logger.info("get list of children by address : " + address);
        List<Persons> person = data.getPersons();
        List<Persons> resultOut = person.stream()
                .filter(f -> f.getAddress().equals(address) && 18 > f.getMedicalRecords().getAge())
                .collect(Collectors.toList());
        Set<String> lastName =
                resultOut.stream()
                        .map(Persons::getLastName)
                        .collect(Collectors.toSet());
        List<Persons> resultOut1 = getPersons().stream()
                .filter(e -> lastName.contains(e.getLastName()))
                .collect(Collectors.toList());

        ModelMapper modelMapper = new ModelMapper();
        List<ChildrenByAddressDTO> user = Arrays.asList(modelMapper.map(resultOut1, ChildrenByAddressDTO[].class));
        return user;
    }

    @GetMapping("/flood/stations")
    @ResponseBody
    public List<FloodDTO> floodByStation(@RequestParam(value = "stations") List<Integer> stations) throws IOException, ParseException {
        logger.info("get list of residents by stations numbers : "+ stations);
        List<FloodDTO> flood = new ArrayList<>();
        for (int station : stations) {
            List<FireStation> fireStations = data.getFireStations();
            List<FireStation> fireStation2 = fireStations.stream()
                    .filter(f -> f.getStation() == station)
                    .collect(Collectors.toList());
            Set<String> fireStation3 =
                    fireStation2.stream()
                            .map(FireStation::getAddress)
                            .collect(Collectors.toSet());

            for (String fireStation : fireStation3) {
                List<Persons> persons = getPersons();
                List<Persons> resultOut = persons.stream()
                        .filter(e -> fireStation.contains(e.getAddress()))
                        .collect(Collectors.toList());
                ModelMapper modelMapper = new ModelMapper();
                flood.add(new FloodDTO(fireStation, Arrays.asList(modelMapper.map(resultOut, FloodByStationDTO[].class))));
            }
        }
        return flood;

    }


}