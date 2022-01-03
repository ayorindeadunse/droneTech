package com.example.droneTech.RequestsAndResponses;

import com.example.droneTech.models.DroneModel;
import com.example.droneTech.models.DroneState;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


//import static com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat.UUID;
import java.util.UUID;

public class DroneRegistrationRequest {
    @NotBlank
  //  @Size(max = 100)
   // private String serialNumber;
    @NotBlank
    @Size(max = 500)
    private int droneWeight;
    @NotBlank
    private DroneModel droneModel;
    // Create unique serial number
    public String createSerialNumber()
    {
        String uniqueID = UUID.randomUUID().toString();
        return uniqueID;
    }

    public String setSerialNumber() {

      String serialNumber = createSerialNumber();
      return serialNumber;
    }

    public int getDroneWeight() {
        return droneWeight;
    }
    public void setDroneWeight(int droneWeight) {
        this.droneWeight = droneWeight;
    }
    public DroneModel getDroneModel() {
        return droneModel;
    }

    public void setDroneModel(DroneModel droneModel) {
        this.droneModel = droneModel;
    }
}
