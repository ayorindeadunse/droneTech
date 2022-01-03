package com.example.droneTech.RequestsAndResponses;

import com.example.droneTech.models.DroneState;

public class DroneBatteryLevelResponse {
    private String serialNumber;
    private DroneState droneState;
    private int batteryLevel;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public DroneState getDroneState() {
        return droneState;
    }

    public void setDroneState(DroneState droneState) {
        this.droneState = droneState;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }
}
