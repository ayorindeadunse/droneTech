package com.example.droneTech.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "medications")
public class MedicationRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
