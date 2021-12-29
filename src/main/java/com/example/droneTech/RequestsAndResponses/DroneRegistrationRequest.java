package com.example.droneTech.RequestsAndResponses;

import com.example.droneTech.models.DroneModel;
import com.example.droneTech.models.DroneState;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


//import static com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat.UUID;
import java.util.UUID;

public class DroneRegistrationRequest {
    @NotBlank
    @Size(max = 100)
    private String serialNumber;
    @NotBlank
    @Size(max = 500)
    private int droneWeight;

    @NotBlank
    private int batteryCapacity;

    @NotBlank
    private DroneState state;

    @NotBlank
    private DroneModel droneModel;


    // Create unique serial number
    public String createSerialNumber()
    {
        String uniqueID = UUID.randomUUID().toString();
        return uniqueID;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {

        this.serialNumber = serialNumber;
        this.serialNumber = createSerialNumber();
    }

    public int getDroneWeight() {
        return droneWeight;
    }

    public void setDroneWeight(int droneWeight) {
        this.droneWeight = droneWeight;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public DroneState getState() {
        return state;
    }

    public void setState(DroneState state) {
        this.state = state;
    }

    public DroneModel getDroneModel() {
        return droneModel;
    }

    public void setDroneModel(DroneModel droneModel) {
        this.droneModel = droneModel;
    }
}
