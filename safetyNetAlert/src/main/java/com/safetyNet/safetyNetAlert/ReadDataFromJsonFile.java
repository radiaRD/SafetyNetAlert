package com.safetyNet.safetyNetAlert;

import com.jsoniter.JsonIterator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.FileReader;
import java.io.IOException;

class ReadDataFromJsonFile {
    private String path = "D:\\projetoc4\\safetyNetAlert\\src\\main\\resources\\data.json";


    @Autowired
    public String readJsonFileData() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(path));
        JSONObject jsonObject = (JSONObject) obj;
        String data = JsonIterator.deserialize(jsonObject.toString()).toString();
        return data;

    }
}
