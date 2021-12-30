package com.example.droneTech.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "eventlog")
public class EventLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "serialNumber",unique = true)
    private String serialNumber;
    @Column(name = "droneState")
    private DroneState droneState;
    @Column(name = "batteryLevel")
    private int batteryLevel;
    @Column(name = "dateCreated")
    private Date dateCreated;
    @Column(name = "dateModified")
    private Date dateModified;

    public EventLog() {
    }

    public EventLog(String serialNumber, DroneState droneState, int batteryLevel, Date dateCreated,Date dateModified) {
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }
}
