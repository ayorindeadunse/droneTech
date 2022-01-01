package com.example.droneTech.RequestsAndResponses;

import com.example.droneTech.models.LoadDrone;

import java.util.List;

public class LoadDroneRequest {

    private String serialNumber;
    private List<String> medicineCode;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

   /* public List<LoadDrone> getLoadDrone() {
        return loadDrone;
    }

    public void setLoadDrone(List<LoadDrone> loadDrone) {
        this.loadDrone = loadDrone;
    }*/

    public List<String> getMedicineCode() {
        return medicineCode;
    }

    public void setMedicineCode(List<String> medicineCode) {
        this.medicineCode = medicineCode;
    }
}
