package com.example.droneTech.repositories;

import com.example.droneTech.models.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends JpaRepository<Medication,Long> {

    @Query(value = "SELECT MEDICINE_WEIGHT from MEDICATIONS where code = ?1",nativeQuery = true)
    int medicationWeight(String medicationCode);

    @Query(value = "SELECT * FROM MEDICATIONS WHERE CODE = ?1",nativeQuery = true)
    Medication checkIfMedicationExists(String code);
}
