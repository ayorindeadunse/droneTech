package com.example.droneTech.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class MedicationRegister {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id; // primary key
    private Long MedicationId; //make this a unique field
    private Date createdDate;
    private Date modifiedDate;

    public MedicationRegister() {
    }

    public MedicationRegister(Long medicationId, Date createdDate, Date modifiedDate) {
        MedicationId = medicationId;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public Long getMedicationId() {
        return MedicationId;
    }

    public void setMedicationId(Long medicationId) {
        MedicationId = medicationId;
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
