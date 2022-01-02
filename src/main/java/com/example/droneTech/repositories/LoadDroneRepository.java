package com.example.droneTech.repositories;

import com.example.droneTech.models.LoadDrone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoadDroneRepository extends JpaRepository<LoadDrone,Long> {
    @Query(value = "SELECT MEDICINE_CODE FROM LOADEDDRONES WHERE SERIAL_NUMBER = ?1 AND DRONE_STATE = 2", nativeQuery=true)
    List<String> getDroneAvailableMedication(String serialNumber);
}
