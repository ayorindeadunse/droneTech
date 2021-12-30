package com.example.droneTech.repositories;

import com.example.droneTech.models.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone,String> {

    @Query(value = "SELECT serialNumber FROM eventlog WHERE droneState = 'IDLE'", nativeQuery=true)
    List<String> getAllAvailableDrones();

    @Query(value = "SELECT medicationId FROM loadeddrones WHERE serialNumber = ?1", nativeQuery=true)
    List<String> getDroneAvailableMedication(String serialNumber);

   /* @Query(value ="SELECT droneState from eventlog WHERE serialNumber = ?1 ORDER BY dateCreated desc LIMIT 1",nativeQuery = true)
    String getCurrentDroneState(String serialNumber);*/

    @Query(value = "SELECT batteryLevel from eventlog WHERE serialNumber = ?1 ORDER BY dateCreated desc LIMIT 1",nativeQuery = true)
    int getDroneBatteryLevel(String serialNumber);

    @Query(value = "SELECT * FROM drones WHERE serialNumber = ?!",nativeQuery = true)
    Drone checkIfDroneExists(String serialNumber);
}
