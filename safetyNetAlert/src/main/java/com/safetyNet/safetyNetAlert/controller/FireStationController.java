package com.safetyNet.safetyNetAlert.controller;

import com.safetyNet.safetyNetAlert.SafetyNetData;
import com.safetyNet.safetyNetAlert.model.FireStation;
import com.safetyNet.safetyNetAlert.model.Persons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FireStationController {

    @Autowired
    private SafetyNetData data;

    private void addFireStation(FireStation fireStation) {
        this.data.getFireStations().add(fireStation);
    }
    private void deleteStation(Persons persons){
        persons.setStation(null);
        persons.setAddress(null);
    }

    private void deleteAddressFirestation(String address, int station, Persons person) {
        data.getFireStations().removeIf(u -> u.getAddress().equals(address) && u.getStation() == (station));
        if (person.getStation().getStation() == (station) && person.getAddress().equals(address)) {
          this.deleteStation(person);
        }

    }

    private void deleteFirestation(int station, Persons person) {
        data.getFireStations().removeIf(u -> u.getStation() == (station));
        if (person.getStation().getStation() == (station)) {
          this.deleteStation(person);
        }
        //       data.getPersons().removeIf(e ->e.getStation().getStation() == (station));
    }


    private void updateFirestation(String address, FireStation fireStation, Persons person) {

        for (int i = 0; i < data.getFireStations().size(); i++) {
            FireStation f = data.getFireStations().get(i);
            if (f.getAddress().equals(address)) {
                data.getFireStations().set(i, fireStation);
                return;
            }
        }
        for (int j = 0; j < data.getPersons().size(); j++) {
            Persons f = data.getPersons().get(j);
            if (f.getAddress().equals(address)) {
                f.setStation(fireStation);
                f.setAddress(fireStation.getAddress());
                return;
            }
        }
    }


    @RequestMapping(value = "/fireStation", method = RequestMethod.POST)
    void addfireStations(@RequestBody FireStation fireStation) {
        this.addFireStation(fireStation);
    }

    @RequestMapping(value = "/fireStation/{address}/{station}", method = RequestMethod.DELETE)
    public void deleteAddressFireStations(@PathVariable String address, @PathVariable int station) {
        for (Persons person : data.getPersons()) {
            this.deleteAddressFirestation(address, station, person);
        }
    }

    @RequestMapping(value = "/fireStation/{station}", method = RequestMethod.DELETE)
    public void deleteFireStations(@PathVariable int station) {
        for (Persons person : data.getPersons()) {
            this.deleteFirestation(station, person);
        }
    }

    @RequestMapping(value = "/fireStation/{address}", method = RequestMethod.PUT)
    public void updateFireStations(@PathVariable String address, @RequestBody FireStation fireStation) {
        for (Persons person : data.getPersons()) {
            this.updateFirestation(address, fireStation, person);
        }
    }


}
