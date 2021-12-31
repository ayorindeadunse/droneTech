package com.example.droneTech.controllers;

import com.example.droneTech.RequestsAndResponses.DroneRegistrationRequest;
import com.example.droneTech.models.Drone;
import com.example.droneTech.services.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DroneController {

    @Autowired
    private DroneService droneService;

    @PostMapping("/registerdrone")
public ResponseEntity Register(@RequestBody DroneRegistrationRequest drone)
{
    Drone d = droneService.registerDrone(drone);
    return ResponseEntity.ok(d);
}
}
