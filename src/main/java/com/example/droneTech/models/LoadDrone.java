package com.example.droneTech.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "loadeddrones")
public class LoadDrone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "serialNumber")
    private String serialNumber;
    @Transient
    @Column(name = "medicationId")
    private List<Long> medicationId;
    @Column(name = "droneState")
    private DroneState droneState;
    @Column(name = "dateCreated")
    private Date dateCreated;
    @Column(name = "dateModified")
    private Date dateModified;

    public LoadDrone() {
    }

    public LoadDrone(String serialNumber,List<Long> medicationId, DroneState droneState, Date dateCreated, Date dateModified) {
        this.serialNumber = serialNumber;
        this.droneState = droneState;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.medicationId = medicationId;
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

    public List<Long> getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(List<Long> medicationId) {
        this.medicationId = medicationId;
    }

    //MediationId should be a string
}
