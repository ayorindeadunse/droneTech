package com.example.droneTech.controllers;

import com.example.droneTech.RequestsAndResponses.ApiResponse;
import com.example.droneTech.RequestsAndResponses.DroneRegistrationRequest;
import com.example.droneTech.RequestsAndResponses.LoadDroneRequest;
import com.example.droneTech.RequestsAndResponses.MedicationRegistrationRequest;
import com.example.droneTech.models.Drone;
import com.example.droneTech.models.LoadDrone;
import com.example.droneTech.models.Medication;
import com.example.droneTech.services.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class DroneController {

    @Autowired
    private DroneService droneService;

    @PostMapping("/registerdrone")
public ResponseEntity Register(@RequestBody DroneRegistrationRequest drone)
{
    Drone d = droneService.registerDrone(drone);
    return ResponseEntity.ok(d);
   //return ResponseEntity.ok(new ApiResponse(true, "Drone registered successfully!" + d));
}

@PostMapping("/loaddrone")
    public ResponseEntity LoadDrone(@RequestBody LoadDroneRequest loadDrone)
{
    List<LoadDrone> l = droneService.loadDrone(loadDrone);
    return ResponseEntity.ok(l);
}

@PostMapping("/registermedication")
    public ResponseEntity registerMedication(@RequestBody MedicationRegistrationRequest medication)
{
    Medication m = droneService.registerMedication(medication);
    return ResponseEntity.ok(m);
}


}
