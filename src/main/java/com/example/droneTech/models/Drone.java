package com.example.droneTech.models;

import javax.persistence.*;

@Entity
@Table(name = "drones")
public class Drone {
    // Declare drone properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //auto increment. Identity column.
    private String serialNumber;
    private DroneModel droneModel;
    private int droneWeight;
    private Double batteryCapacity;
    private DroneState droneState;

    // add constructor

    public Drone() {
    }

    public Drone(String serialNumber, DroneModel droneModel, int droneWeight, Double batteryCapacity, DroneState droneState) {
        this.serialNumber = serialNumber;
        this.droneModel = droneModel;
        this.droneWeight = droneWeight;
        this.batteryCapacity = batteryCapacity;
        this.droneState = droneState;
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

    public DroneState getDroneState() {
        return droneState;
    }

    public void setDroneState(DroneState droneState) {
        this.droneState = droneState;
    }
}
