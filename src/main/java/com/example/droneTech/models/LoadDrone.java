package com.example.droneTech.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "loadeddrones")
public class LoadDrone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "serialNumber",unique = true)
    private String serialNumber;
    @Column(name = "medicinecode",unique = true)
    private String medicinecode;
    @Column(name = "droneState")
    private DroneState droneState;
    @Column(name = "dateCreated")
    private Date dateCreated;
    @Column(name = "dateModified")
    private Date dateModified;

    public LoadDrone() {
    }

    public LoadDrone(String serialNumber,String medicinecode, DroneState droneState, Date dateCreated, Date dateModified) {
        this.serialNumber = serialNumber;
        this.droneState = droneState;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.medicinecode = medicinecode;
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

    public String getMedicationCode() {
        return medicinecode;
    }

    public void setMedicationCode(String medicinecode) {
        this.medicinecode = medicinecode;
    }

}
