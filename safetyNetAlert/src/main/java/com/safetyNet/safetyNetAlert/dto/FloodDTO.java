package com.safetyNet.safetyNetAlert.dto;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FloodDTO {

    private static final Logger logger = LogManager.getLogger(FloodDTO.class);

    private String address;
    private List<FloodByStationDTO> floodList;

    public FloodDTO(String address, List<FloodByStationDTO> floodList) {
        this.address = address;
        this.floodList = floodList;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<FloodByStationDTO> getFloodList() {
        return floodList;
    }

    public void setFloodList(List<FloodByStationDTO> floodList) {
        this.floodList = floodList;
    }
}
