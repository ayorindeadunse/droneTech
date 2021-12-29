package com.example.droneTech.repositories;

import com.example.droneTech.models.DroneRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneRegisterRepository extends JpaRepository<DroneRegister,String> {
}
