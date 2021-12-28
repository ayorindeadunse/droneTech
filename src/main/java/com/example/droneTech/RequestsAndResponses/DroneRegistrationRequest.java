package com.example.droneTech.RequestsAndResponses;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DroneRegistrationRequest {
    @NotBlank
    @Size(max = 100)
    private String serialNumber;
    @NotBlank
    @Size(max = 500)
    private int droneWeight;

    private static AtomicLong idCounter = new AtomicLong();

    @NotBlank
    private int batteryCapacity;

    @NotBlank
    private String state;



    public DroneRegistrationRequest() {
    }

    // Create unique serial number
    public static String createSerialNumber()
    {
        return String.valueOf(idCounter.getAndIncrement());
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }



}
