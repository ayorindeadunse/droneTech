package com.example.droneTech.controllers;

import com.example.droneTech.RequestsAndResponses.*;
import com.example.droneTech.exceptions.DroneException;
import com.example.droneTech.models.Drone;
import com.example.droneTech.models.DroneState;
import com.example.droneTech.models.LoadDrone;
import com.example.droneTech.models.Medication;
import com.example.droneTech.services.DroneService;
import com.example.droneTech.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class DroneController {

    @Autowired
    private DroneService droneService;

    @PostMapping("/api/droneTech/registerdrone")
public ResponseEntity Register(@RequestBody DroneRegistrationRequest drone)
{
    //Check drone weight
    if(drone.getDroneWeight() > Constants.DRONE_WEIGHT)
    {
        return ResponseEntity.badRequest().body(new ApiResponse(false,"Maximum Drone Weight is " + Constants.DRONE_WEIGHT,drone));
    }
    Drone d = droneService.registerDrone(drone);
    //return ResponseEntity.ok(d);
   return ResponseEntity.ok(new ApiResponse(true, "Drone registered successfully!",d));
}

@PostMapping("/api/droneTech/loaddrone")
    public ResponseEntity LoadDrone(@RequestBody LoadDroneRequest loadDrone) throws DroneException {
    List<LoadDrone> l = null;
    //try {
        l = droneService.loadDrone(loadDrone);
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getDroneState() == DroneState.IDLE) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Battery Level is too low for this operation", l));
            }
            return ResponseEntity.ok(new ApiResponse(true, "Drone Loaded Successfully!", l));
        }

       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "An Error occurred on the server", l));

}

@PostMapping("/api/droneTech/registermedication")
    public ResponseEntity registerMedication(@RequestBody MedicationRegistrationRequest medication)
{
    Medication m = droneService.registerMedication(medication);
    //return ResponseEntity.ok(m);
    return ResponseEntity.ok(new ApiResponse(true,"Medicine Successfully registered.",m));
}

@PostMapping("/api/droneTech/getloadedmedication")
    public ResponseEntity getLoadedMedication(@RequestBody GetMedicationRequest getMedicationRequest)
    {
        List<String> getMeds = droneService.getLoadedMedication(getMedicationRequest);
       // return ResponseEntity.ok(getMeds);
        return ResponseEntity.ok(new ApiResponse(true,"Medication Successfully  loaded",getMeds));
    }
    @GetMapping("/api/droneTech/getavailabledrones")
    public ResponseEntity getAvailableDrones()
    {
        List<String> getDrones = droneService.getAvailableDrones();
       // return ResponseEntity.ok(getDrones);
        return ResponseEntity.ok(new ApiResponse(true,"Available Drones Successfully retrieved",getDrones));
    }
    @PostMapping("/api/droneTech/getdronebatterylevel")
    public ResponseEntity getDroneBatteryLevel(@RequestBody GetDroneBatteryLevelRequest getDroneBatteryLevelRequest)
    {
        List<Integer> droneBatteryLevelResponse = droneService.getDroneBatteryLevel(getDroneBatteryLevelRequest);
      //  return ResponseEntity.ok(droneBatteryLevelResponse);
        return ResponseEntity.ok(new ApiResponse(true,"Current Drone Battery Level Returned",droneBatteryLevelResponse));
    }
}
