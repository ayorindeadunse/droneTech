package com.example.droneTech.services;

import com.example.droneTech.RequestsAndResponses.DroneRegistrationRequest;
import com.example.droneTech.models.Drone;
import com.example.droneTech.models.LoadDrone;
import com.example.droneTech.models.Medication;

import java.util.List;

public interface IDroneService {

    Drone registerDrone(DroneRegistrationRequest drone); //get drone serialNumber
    String getDroneState(String serialNumber);//get the drone state. Use a switchcase method to return the droneState
    int getBatteryLevel(String serialNumber); //get the battery level for a particular drone from event log.
    List<String> getAvailableDrones(); //get the list of available drones from the event log. The serial numbers will be added to the ArrayList object.
    String LoadDrone(String serialNumber,List<LoadDrone> loadDrone); //return the drone state after loading the drone with medication.
    List<String> getLoadedMedication(String serialNumber); //get a list of loaded medication for a given drone
    List<Medication> registerMedication(Medication medication); //register a medication item in inventory and return the details; /** extra method **/
    List<Drone> getDroneDetails(String serialNumber); //Get spec details of a drone; /** extra method */
    int recalculateBatteryLevel(int currentBatteryLevel, int medicineCount); /*assumed logic for drone battery level calculation */

}
