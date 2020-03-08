package com.safetyNet.safetyNetAlert;

import com.fasterxml.jackson.annotation.JsonFilter;

//@JsonFilter("monFiltreDynamique")
public class SafetyNetFireStationModel {
    private String address;
    private int station;

    public SafetyNetFireStationModel() {

    }
    public String getAddress() {
      return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public int getStation (){
        return station;
    }

    public void setStation(int station) {
        this.station = station;
    }
    @Override
    public String toString(){
        return"Station { "+ " address='" + address + '\'' +
                    ", station='" + station + '\'' +
                    '}';
        }


}
