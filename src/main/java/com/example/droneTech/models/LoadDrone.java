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
    private String medicineCode;
    @Column(name = "droneState")
    private DroneState droneState;
    @Column(name = "dateCreated")
    private Date dateCreated;
    @Column(name = "dateModified")
    private Date dateModified;

    public LoadDrone() {
    }

    public LoadDrone(String serialNumber,String medicineCode, DroneState droneState, Date dateCreated, Date dateModified) {
        this.serialNumber = serialNumber;
        this.droneState = droneState;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.medicineCode = medicineCode;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getMedicineCode() {
        return medicineCode;
    }

    public void setMedicineCode(String medicineCode) {
        this.medicineCode = medicineCode;
    }

    public DroneState getDroneState() {
        return droneState;
    }

    public void setDroneState(DroneState droneState) {
        this.droneState = DroneState.LOADED;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
