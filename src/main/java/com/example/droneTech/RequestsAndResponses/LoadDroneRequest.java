package com.example.droneTech.RequestsAndResponses;

import java.util.List;

public class LoadDroneRequest {

    private String serialNumber;
    private List<String> medicationId;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public List<String> getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(List<String> medicationId) {
        this.medicationId = medicationId;
    }
}
