package com.safetyNet.safetyNetAlert.model;


import com.safetyNet.safetyNetAlert.SafetyNetData;
import org.springframework.beans.factory.annotation.Autowired;

public class FireStation {
    private String address;
    private int station;
    @Autowired
    private SafetyNetData data;

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
