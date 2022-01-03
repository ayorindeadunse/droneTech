package com.example.droneTech.repositories;

import com.example.droneTech.models.MedicationRegister;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRegisterRepository extends JpaRepository<MedicationRegister,Long> {
}
