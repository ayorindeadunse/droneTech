package com.example.droneTech.repositories;

import com.example.droneTech.models.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneRepository extends JpaRepository<Drone,String> {
}
