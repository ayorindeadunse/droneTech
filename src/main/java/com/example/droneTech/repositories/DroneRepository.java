package com.example.droneTech.repositories;

import com.example.droneTech.models.Drone;
import com.example.droneTech.models.DroneState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone,String> {

    @Query(value = "SELECT DISTINCT SERIAL_NUMBER FROM EVENTLOG WHERE DRONE_STATE = 0", nativeQuery=true)
    List<String> getAllAvailableDrones();

    @Query(value = "SELECT MEDICINE_CODE FROM LOADEDDRONES WHERE SERIAL_NUMBER = ?1", nativeQuery=true)
    List<String> getDroneAvailableMedication(String serialNumber);

    @Query(value ="SELECT DRONE_WEIGHT from DRONES WHERE SERIAL_NUMBER = ?1",nativeQuery = true)
    int getDroneWeight(String serialNumber);

    @Query(value = "SELECT BATTERY_LEVEL from EVENTLOG WHERE SERIAL_NUMBER = ?1 ORDER BY DATE_CREATED DESC LIMIT 1",nativeQuery = true)
    int getDroneBatteryLevel(String serialNumber);

    @Query(value = "SELECT * FROM DRONES WHERE SERIAL_NUMBER = ?1",nativeQuery = true)
    Drone checkIfDroneExists(String serialNumber);

    @Query(value = "SELECT DRONE_STATE FROM EVENTLOG WHERE SERIAL_NUMBER = ?1 ORDER BY DATE_CREATED DESC LIMIT 1", nativeQuery=true)
    DroneState getCurrentDroneState(String serialNumber);
}
