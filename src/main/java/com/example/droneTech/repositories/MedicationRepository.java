package com.example.droneTech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends JpaRepository<MedicationRepository,Long> {

    @Query(value = "SELECT medicineWeight from medications where code = ?1",nativeQuery = true)
    int medicationWeight(String medicationCode);
}
