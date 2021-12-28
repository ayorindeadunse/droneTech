package com.example.droneTech.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "droneRegister")
public class DroneRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
