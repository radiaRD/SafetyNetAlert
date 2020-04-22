package com.safetyNet.safetyNetAlert;

import com.safetyNet.safetyNetAlert.model.FireStation;
import com.safetyNet.safetyNetAlert.model.MedicalRecords;
import com.safetyNet.safetyNetAlert.model.Persons;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
class SafetyNetDataTest {
    @MockBean
    SafetyNetData safetyNetData;


    @Test
    void getPersons() throws IOException, ParseException {

        assert(safetyNetData.getPersons().equals(safetyNetData.readJsonFilePersons()) );

    }
    @Test
    void getFireStations() throws IOException, ParseException {

        assert(safetyNetData.getFireStations().equals(safetyNetData.readJsonFileStation()) );}

        @Test
        void getMedicalRecords() throws IOException, ParseException {

            assert(safetyNetData.getMedicalRecords().equals(safetyNetData.readJsonFileMedicalrecords()) );
    }


    @Test
    void getLinkList() throws IOException, ParseException {

        for (int i = 0; i< safetyNetData.getPersons().size();i++){
        assert(safetyNetData.getPersons().get(i).getMedicalRecords().getLastName().equals(safetyNetData.getPersons().get(i).getLastName()));
            assert(safetyNetData.getPersons().get(i).getStation().getAddress().equals(safetyNetData.getPersons().get(i).getAddress()));}
    }
}