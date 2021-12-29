package com.example.droneTech.repositories;

import com.example.droneTech.models.EventLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventLogRepository extends JpaRepository<EventLog,Long> {
}
