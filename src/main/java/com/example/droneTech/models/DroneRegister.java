package com.example.droneTech.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class DroneRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id; // primary key
    private String serialNumber; //make this a unique field
    private Date createdDate;
    private Date modifiedDate;

    public DroneRegister() {
    }

    public DroneRegister(String serialNumber, Date createdDate, Date modifiedDate) {
        serialNumber = serialNumber;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
