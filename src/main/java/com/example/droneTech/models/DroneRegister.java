package com.example.droneTech.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "droneregister")
public class DroneRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id; // primary key
    @Column(name = "serialNumber",unique = true)
    private String serialNumber; //make this a unique field
    @Column(name = "createdDate")
    private Date createdDate;
    @Column(name = "serialNumber")
    private Date modifiedDate;

    public DroneRegister() {
    }

    public DroneRegister(String serialNumber, Date createdDate, Date modifiedDate) {
        this.serialNumber = serialNumber;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getId() {
        return Id;
    }

}
