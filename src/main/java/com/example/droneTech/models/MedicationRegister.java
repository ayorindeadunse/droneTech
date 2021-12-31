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
    @Column(name = "medicationId")
    private Long MedicationId; //make this a unique field
    @Column(name = "createdDate")
    private Date createdDate;
    @Column(name = "modifiedDate")
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
