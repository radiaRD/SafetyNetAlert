package com.safetyNet.safetyNetAlert;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetyNet.safetyNetAlert.model.FireStation;
import com.safetyNet.safetyNetAlert.model.MedicalRecords;
import com.safetyNet.safetyNetAlert.model.Persons;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class SafetyNetAlertApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    SafetyNetData safetyNetData;

    @AfterEach
    public void clearData() throws IOException, ParseException {
        safetyNetData.dataEmpty();
    }

    @Test
    void getPersonsTest() throws Exception {

        this.mockMvc.perform(get("/persons")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(23)));


    }

    @Test
    void getFireStationTest() throws Exception {

        this.mockMvc.perform(get("/fireStation")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(13)));


    }

    @Test
    void getMedicalRecordsTest() throws Exception {
        this.mockMvc.perform(get("/medicalRecords")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(23)));

    }

    @Test
    void getEmailByCityTest() throws Exception {
        this.mockMvc.perform(get("/communityEmail/Culver")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(15)));
    }

    @Test
    void getPhoneNumberByStationTest() throws Exception {
        this.mockMvc.perform(get("/phoneAlert/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)));
        this.mockMvc.perform(get("/phoneAlert/3")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(7)));
    }

    @Test
    void getpersonInfoByNameTest() throws Exception {
        this.mockMvc.perform(get("/personInfo/John/Boyd")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].lastName", is("Boyd")))
                .andExpect(jsonPath("$[0].firstName", is("John")))
                .andExpect(jsonPath("$", hasSize(6)));
    }

    @Test
    void getPersonsByAddressTest() throws Exception {
        this.mockMvc.perform(get("/fire/1509 Culver St")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].station", is(3)))
                .andExpect(jsonPath("$[4].station", is(3)))
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    void getChildrenByAddressTest() throws Exception {
        this.mockMvc.perform(get("/childAlert/489 Manchester St")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
        this.mockMvc.perform(get("/childAlert/1509 Culver St")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(6)));
    }

    @Test
    void getUserByStationNumberTest() throws Exception {
        this.mockMvc.perform(get("/fireStation/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.userList", hasSize(6)))
                .andExpect(jsonPath("$.countChildren", is(1)))
                .andExpect(jsonPath("$.countAdult", is(5)));
    }

    @Test
    void floodByStationTest() throws Exception {
        this.mockMvc.perform(get("/flood/stations?stations=1,4")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].address", is("908 73rd St")))
                .andExpect(jsonPath("$[0].floodList", hasSize(2)))
                .andExpect(jsonPath("$[1].address", is("947 E. Rose Dr")))
                .andExpect(jsonPath("$[1].floodList", hasSize(3)))
                .andExpect(jsonPath("$[2].address", is("644 Gershwin Cir")))
                .andExpect(jsonPath("$[2].floodList", hasSize(1)))
                .andExpect(jsonPath("$[3].address", is("489 Manchester St")))
                .andExpect(jsonPath("$[3].floodList", hasSize(1)))
                .andExpect(jsonPath("$[4].address", is("112 Steppes Pl")))
                .andExpect(jsonPath("$[4].floodList", hasSize(3)));
    }

    @Test
    public void postPersonsTest() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders
                .post("/persons")
                .content(asJsonString(new Persons("Boyd", "Lily", "1509 Culver St", "Culver", 97451, "841-874-6512", "jaboyd@email.com", new FireStation("", 3), new MedicalRecords())))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        this.mockMvc.perform(get("/persons")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(24)));
    }


    public static String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void updatePersonsTest() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders
                .put("/persons/Boyd/John")
                .content(asJsonString(new Persons("John", "Boyd", "489 Manchester St", "Culver", 97451, "841-874-6512", "jaboyd@email.com", new FireStation(), new MedicalRecords())))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        this.mockMvc.perform(get("/persons")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].address",  is("489 Manchester St")));
    }

    @Test
    public void deletePersonsTest() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.delete("/persons/Boyd/John"));
        this.mockMvc.perform(get("/persons")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(22)));
    }

    @Test
    public void postFireStationTest() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders
                .post("/fireStation")
                .content(asJsonString( new FireStation("400 Manchester St", 3) ))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        this.mockMvc.perform(get("/fireStation")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(14)));
    }

    @Test
    public void updateFireStationTest() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders
                .put("/fireStation/489 Manchester St")
                .content(asJsonString( new FireStation("400 Manchester St", 3)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        this.mockMvc.perform(get("/fireStation")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[6].address",  is("400 Manchester St")))
                .andExpect(jsonPath("$[6].station",  is(3)));
        this.mockMvc.perform(get("/persons")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[10].address",  is("400 Manchester St")))
                .andExpect(jsonPath("$[10].station.address",  is("400 Manchester St")));
    }

    @Test
    public void deleteFireStationTest() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.delete("/fireStation/4"));
        this.mockMvc.perform(get("/fireStation")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(11)));
        this.mockMvc.perform(get("/persons")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[10].address",  is(nullValue())))
               .andExpect(jsonPath("$[10].station",  is(nullValue())));

    }

    @Test
    public void deleteAddressFirestationTest() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.delete("/fireStation/489 Manchester St/4"));
        this.mockMvc.perform(get("/fireStation")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(12)));
        this.mockMvc.perform(get("/persons")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[10].address",  is(nullValue())))
                .andExpect(jsonPath("$[10].station",  is(nullValue())));
    }

    @Test
    public void postMedicalRecordsTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/medicalRecords")
                .content(asJsonString( new MedicalRecords()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        this.mockMvc.perform(get("/medicalRecords")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(24)));
    }

    @Test
    public void updateMedicalRecordsTest() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders
                .put("/medicalRecords/John/Boyd")
                .content(asJsonString( new MedicalRecords("Boyd","John","02/18/2012", Arrays.asList("",""),Arrays.asList("",""),8)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        this.mockMvc.perform(get("/medicalRecords")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].birthdate",  is("02/18/2012")))
                .andExpect(jsonPath("$[0].age",  is(8)));
        this.mockMvc.perform(get("/persons")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].medicalRecords.birthdate",  is("02/18/2012")));

    }

    @Test
    public void deleteMedicalRecordsTest() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.delete("/medicalRecords/John/Boyd"));
        this.mockMvc.perform(get("/medicalRecords")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(22)));
        this.mockMvc.perform(get("/persons")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].medicalRecords",  is(nullValue())));
    }

}
