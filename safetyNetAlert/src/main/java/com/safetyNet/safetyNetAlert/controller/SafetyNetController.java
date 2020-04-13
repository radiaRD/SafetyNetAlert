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

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
public class SafetyNetController {

    @Autowired
    private SafetyNetData data;


    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    @ResponseBody
    public List<Persons> getPersons() throws IOException, ParseException {

        return data.getPersons();
    }


    @RequestMapping(value = "/fireStation", method = GET)
    @ResponseBody
    public List<FireStation> getFirestations() throws IOException, ParseException {

        return data.getFireStations();
    }

    @RequestMapping(value = "/medicalRecords", method = GET)
    @ResponseBody
    public List<MedicalRecords> getMedicalRecords() throws IOException, ParseException {

        return data.getMedicalRecords();
    }


    @RequestMapping(value = "/fireStation/{station}", method = GET)
    @ResponseBody
    public UsersCountDTO getUserByStationNumber(@PathVariable int station) throws IOException, ParseException {
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

        List<Persons> person = data.getPersons();
        List<Persons> resultOut = person.stream()
                .filter(f -> f.getAddress().equals(address) && 18 > f.getMedicalRecords().getAge())
                .collect(Collectors.toList());
        Set<String> lastN =
                resultOut.stream()
                        .map(Persons::getLastName)
                        .collect(Collectors.toSet());
        List<Persons> resultOut1 = getPersons().stream()
                .filter(e -> lastN.contains(e.getLastName()))
                .collect(Collectors.toList());

        ModelMapper modelMapper = new ModelMapper();
        List<ChildrenByAddressDTO> user = Arrays.asList(modelMapper.map(resultOut1, ChildrenByAddressDTO[].class));
        return user;
    }

    @GetMapping("/flood/stations")
    @ResponseBody
    public List<FloodDTO> floodByStation(@RequestParam(value = "stations") List<Integer> stations) throws IOException, ParseException {
        List<FloodDTO> flood =new ArrayList<>();
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