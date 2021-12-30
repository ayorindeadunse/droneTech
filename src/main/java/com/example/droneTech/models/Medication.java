package com.example.droneTech.models;

import javax.persistence.*;

@Entity
@Table(name = "medications")
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id; // identity
    @Column(name = "name")
    private String name;
    @Column(name = "medicineWeight")
    private int medicineWeight;
    @Column(name = "code",unique = true)
    private String code;
    @Column(name = "medicationImage")
    private byte[] medicationImage;

    public Medication() {
    }

    public Medication(String name, int medicineWeight, String code, byte[] medicationImage) {
        this.name = name;
        this.medicineWeight = medicineWeight;
        this.code = code;
        this.medicationImage = medicationImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMedicineWeight() {
        return medicineWeight;
    }

    public void setMedicineWeight(int medicineWeight) {
        this.medicineWeight = medicineWeight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public byte[] getMedicationImage() {
        return medicationImage;
    }

    public void setMedicationImage(byte[] medicationImage) {
        this.medicationImage = medicationImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
