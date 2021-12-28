package com.example.droneTech.RequestsAndResponses;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DroneRegistrationRequest {
    @NotBlank
    @Size(max = 100)
    private String serialNumber;
    @Size(max = 500)
    private int droneWeight;
    private int batteryCapacity;
    private String state;
}
