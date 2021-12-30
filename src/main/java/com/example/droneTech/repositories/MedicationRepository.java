package com.example.droneTech.repositories;

import com.example.droneTech.models.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends JpaRepository<Medication,Long> {

    @Query(value = "SELECT medicineWeight from medications where code = ?1",nativeQuery = true)
    int medicationWeight(String medicationCode);

    @Query(value = "SELECT * FROM medications WHERE code = ?!",nativeQuery = true)
    Medication checkIfMedicationExists(String code);
}
