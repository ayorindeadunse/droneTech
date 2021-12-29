package com.example.droneTech.repositories;

import com.example.droneTech.models.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone,String> {
    //get available drones from the db
    //@Query("FROM EventLog WHERE serialNumber = ?1 and droneState = 'LOADING'")

    //List<String> getTheAvailableDrones(String serialNumber);
}
