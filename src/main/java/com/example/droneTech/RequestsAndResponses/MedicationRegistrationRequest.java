package com.example.droneTech.RequestsAndResponses;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class MedicationRegistrationRequest {

    @NotBlank
    @Pattern(regexp = "[A-Za-z0-9_.-]*")
    private String name;

    @NotBlank
    private int medicineWeight;

    @NotBlank
    @Pattern(regexp = "[A-Z0-9_]")
    private String code;

    private byte[] medicationImage;

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
}
