package com.example.droneTech.models;

public class Drone {
    // Declare drone properties
    private Long id; //auto increment. Identity column.
    private String serialNumber;
    private DroneModel droneModel;
    private int droneWeight;
    private Double batteryCapacity;

    // add constructor

    public Drone() {
    }

    public Drone(String serialNumber, DroneModel droneModel, int droneWeight, Double batteryCapacity) {
        this.serialNumber = serialNumber;
        this.droneModel = droneModel;
        this.droneWeight = droneWeight;
        this.batteryCapacity = batteryCapacity;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public DroneModel getDroneModel() {
        return droneModel;
    }

    public void setDroneModel(DroneModel droneModel) {
        this.droneModel = droneModel;
    }

    public int getDroneWeight() {
        return droneWeight;
    }

    public void setDroneWeight(int droneWeight) {
        this.droneWeight = droneWeight;
    }

    public Double getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(Double batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }
}
