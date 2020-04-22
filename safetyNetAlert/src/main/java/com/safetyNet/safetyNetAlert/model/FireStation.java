package com.safetyNet.safetyNetAlert.model;


import com.safetyNet.safetyNetAlert.SafetyNetData;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FireStation {

    private static final Logger logger = LogManager.getLogger(FireStation.class);

    @Autowired
    private SafetyNetData data;

    private String address;
    private int station;


    public FireStation() {
    }

    public FireStation(String address, int station) {
        this.address = address;
        this.station = station;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStation() {
        return station;
    }

    public void setStation(int station) {
        this.station = station;
    }

    @Override
    public String toString() {
        return "Station { " + " address='" + address + '\'' +
                ", station='" + station + '\'' +
                '}';
    }

}
