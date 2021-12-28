package com.example.droneTech.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "eventlog")
public class EventLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String serialNumber;
    private String droneState;
    private int batteryLevel;
    private Date dateCreated;
    private Date dateModified;

    public EventLog() {
    }

    public EventLog(String serialNumber, String droneState, int batteryLevel, Date dateCreated,Date dateModified) {
        this.serialNumber = serialNumber;
        this.droneState = droneState;
        this.batteryLevel = batteryLevel;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDroneState() {
        return droneState;
    }

    public void setDroneState(String droneState) {
        this.droneState = droneState;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }
}
