package com.example.droneTech.services;

import com.example.droneTech.models.Drone;
import com.example.droneTech.models.LoadDrone;

import java.util.List;

public interface IDroneServive {

    String registerDrone(Drone drone); //get drone serialNumber
    String getDroneState(String serialNumber);//get the drone state
    int getBatteryLevel(String serialNumber); //get the battery level for a particular drone
    List<String> getAvailableDrones(); //get the list of available drones from the event log. The serial numbers will be added to the ArrayList object.
    String LoadDrone(LoadDrone loadDrone); //return the drone state after loading the drone with medication.



}
