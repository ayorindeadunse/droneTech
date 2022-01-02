package com.example.droneTech.repositories;

import com.example.droneTech.models.Drone;
import com.example.droneTech.models.DroneState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone,String> {

    @Query(value ="SELECT DRONE_WEIGHT from DRONES WHERE SERIAL_NUMBER = ?1",nativeQuery = true)
    int getDroneWeight(String serialNumber);

    @Query(value = "SELECT * FROM DRONES WHERE SERIAL_NUMBER = ?1",nativeQuery = true)
    Drone checkIfDroneExists(String serialNumber);

}
