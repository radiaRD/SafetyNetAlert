package com.safetyNet.safetyNetAlert;

import com.jsoniter.JsonIterator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.FileReader;
import java.io.IOException;

public class readDataFromJsonFile {
    private String path = "D:\\projetoc4\\safetyNetAlert\\src\\main\\resources\\data.json";

    // read the json file
//    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public String readJsonFileData() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(path));
        JSONObject jsonObject = (JSONObject) obj;
        String data =  JsonIterator.deserialize(jsonObject.toString()).get('*', '*').toString();
        return data;

    }
}
