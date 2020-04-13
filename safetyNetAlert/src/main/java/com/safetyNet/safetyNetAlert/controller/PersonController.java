package com.safetyNet.safetyNetAlert.controller;

import com.safetyNet.safetyNetAlert.SafetyNetData;
import com.safetyNet.safetyNetAlert.model.Persons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private SafetyNetData data;

    private Persons getPersonByLastName(String lastName, String firstName) {
        for (Persons person : data.getPersons()) {
            if (person.getLastName() == lastName && person.getFirstName() == firstName) return person;
        }
        return null;
    }

    private void addPerson(Persons person) {
        this.data.getPersons().add(person);
    }

    private List<Persons> getAllPersons() {
        return this.data.getPersons();
    }

    private void deletePerson(String lastName, String firstName) {
        data.getPersons().removeIf(u -> u.getLastName().equals(lastName) && u.getFirstName().equals(firstName));
    }

    private void updatePerson(String lastName, String firstName, Persons person) {

        for (int i = 0; i < data.getPersons().size(); i++) {

            Persons p = data.getPersons().get(i);

            if (p.getLastName().equals(lastName) && p.getFirstName().equals(firstName)) {

                p.updatepersonsexceptFirstNameLastName(person.getAddress(),person.getCity(),person.getZip(),person.getPhone(),person.getEmail(),person.getStation(),person.getMedicalRecords());

                return;
            }
        }
    }

    @RequestMapping(value = "/persons", method = RequestMethod.POST)
    void addPersons(@RequestBody Persons person) {
        this.addPerson(person);
    }

    @RequestMapping(value = "/persons/{lastName}/{firstName}", method = RequestMethod.DELETE)
    public void deletepersons(@PathVariable String lastName, @PathVariable String firstName) {
        this.deletePerson(lastName, firstName);
    }

    @RequestMapping(value = "/persons/{lastName}/{firstName}", method = RequestMethod.PUT)
    public void updatePersons(@PathVariable String lastName, @PathVariable String firstName, @RequestBody Persons person) {

        this.updatePerson(lastName, firstName, person);

    }


}
