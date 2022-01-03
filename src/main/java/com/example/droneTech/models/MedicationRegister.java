package com.example.droneTech.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "medicationregister")
public class MedicationRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long Id; // primary key
    @Column(name = "medicationCode")
    private String medicationCode; //make this a unique field
    @Column(name = "createdDate")
    private Date createdDate;
    @Column(name = "modifiedDate")
    private Date modifiedDate;

    public MedicationRegister() {
    }

    public MedicationRegister(String medicationCode, Date createdDate, Date modifiedDate) {
        this.medicationCode = medicationCode;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public String getMedicationCode() {
        return medicationCode;
    }

    public void setMedicationCode(String medicationCode) {
        medicationCode = medicationCode;
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
}
